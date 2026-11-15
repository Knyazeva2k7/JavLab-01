package laba08;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    private static final String DB_URL = "jdbc:h2:mem:study_db;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        String filePath = "book.json";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // ПОДГОТОВКА: Создание схемы study и выполнение скрипта music-create.sql
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE SCHEMA IF NOT EXISTS study");
                stmt.execute("SET SCHEMA study");

                String sqlScript = Files.readString(Paths.get("music-create.sql"), StandardCharsets.UTF_8);
                stmt.execute(sqlScript);
            }

            // ЗАДАНИЕ № 1. Список музыкальных композиций
            System.out.println("1. Список музыкальных композиций--------------");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, name FROM study.music")) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                }
            }

            // ЗАДАНИЕ № 2. Фильтрация композиций (отсутствуют обе буквы m и t без учёта регистра)
            System.out.println("2. Композиции без букв 'm' и 't'------------------");
            String filterSql = "SELECT id, name FROM study.music " +
                               "WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%'";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(filterSql)) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                }
            }

            // ЗАДАНИЕ № 3. Добавление композиции
            System.out.println("\n=== № 3. Добавление любимой композиции ===");
            String insertMusicSql = "INSERT INTO study.music (id, name) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertMusicSql)) {
                pstmt.setInt(1, 999);
                pstmt.setString(2, "Bohemian Rhapsody");
                int rows = pstmt.executeUpdate();
                System.out.println("Добавлено записей в music: " + rows);
            }

            // ЗАДАНИЕ № 4. Загрузка данных из JSON
            System.out.println("\n=== № 4. Создание таблиц и загрузка уникальных данных из books.json ===");
            try (Statement stmt = conn.createStatement()) {
                String createVisitorsSql = "CREATE TABLE IF NOT EXISTS study.visitors (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(100), " +
                        "surname VARCHAR(100), " +
                        "phone VARCHAR(50) UNIQUE, " +
                        "subscribed BOOLEAN)";
                stmt.execute(createVisitorsSql);

                String createBooksSql = "CREATE TABLE IF NOT EXISTS study.books (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "author VARCHAR(255), " +
                        "publishing_year INT, " +
                        "isbn VARCHAR(50) UNIQUE, " +
                        "publisher VARCHAR(255))";
                stmt.execute(createBooksSql);

                String createLinksSql = "CREATE TABLE IF NOT EXISTS study.visitor_books (" +
                        "visitor_id INT, " +
                        "book_id INT, " +
                        "PRIMARY KEY (visitor_id, book_id), " +
                        "FOREIGN KEY (visitor_id) REFERENCES study.visitors(id) ON DELETE CASCADE, " +
                        "FOREIGN KEY (book_id) REFERENCES study.books(id) ON DELETE CASCADE)";
                stmt.execute(createLinksSql);
            }

            Gson gson = new Gson();
            Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
            List<Visitor> visitors;
            try (Reader reader = new FileReader(filePath, StandardCharsets.UTF_8)) {
                visitors = gson.fromJson(reader, visitorListType);
            }

            String mergeVisitor = "MERGE INTO study.visitors (name, surname, phone, subscribed) KEY(phone) VALUES (?, ?, ?, ?)";
            String mergeBook = "MERGE INTO study.books (name, author, publishing_year, isbn, publisher) KEY(isbn) VALUES (?, ?, ?, ?, ?)";
            String selectVisitorId = "SELECT id FROM study.visitors WHERE phone = ?";
            String selectBookId = "SELECT id FROM study.books WHERE isbn = ?";
            String mergeLink = "MERGE INTO study.visitor_books (visitor_id, book_id) KEY(visitor_id, book_id) VALUES (?, ?)";

            for (Visitor v : visitors) {
                try (PreparedStatement psVis = conn.prepareStatement(mergeVisitor)) {
                    psVis.setString(1, v.name());
                    psVis.setString(2, v.surname());
                    psVis.setString(3, v.phone());
                    psVis.setBoolean(4, v.subscribed());
                    psVis.executeUpdate();
                }

                int visitorId = getIdByKey(conn, selectVisitorId, v.phone());

                if (v.favoriteBooks() != null) {
                    for (Book b : v.favoriteBooks()) {
                        try (PreparedStatement psBook = conn.prepareStatement(mergeBook)) {
                            psBook.setString(1, b.name());
                            psBook.setString(2, b.author());
                            psBook.setInt(3, b.publishingYear());
                            psBook.setString(4, b.isbn());
                            psBook.setString(5, b.publisher());
                            psBook.executeUpdate();
                        }

                        int bookId = getIdByKey(conn, selectBookId, b.isbn());

                        try (PreparedStatement psLink = conn.prepareStatement(mergeLink)) {
                            psLink.setInt(1, visitorId);
                            psLink.setInt(2, bookId);
                            psLink.executeUpdate();
                        }
                    }
                }
            }
            System.out.println("Уникальные посетители и книги успешно загружены.");

            // ЗАДАНИЕ № 5. Сортировка книг по году издания
            System.out.println("\n=== № 5. Книги, отсортированные по году издания ===");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT name, author, publishing_year FROM study.books ORDER BY publishing_year ASC")) {
                while (rs.next()) {
                    System.out.printf("%d — %s (%s)%n",
                            rs.getInt("publishing_year"),
                            rs.getString("name"),
                            rs.getString("author"));
                }
            }

            // ЗАДАНИЕ № 6. Книги до 2000 года
            System.out.println("\n=== № 6. Книги, изданные до 2000 года ===");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT name, author, publishing_year FROM study.books WHERE publishing_year < 2000")) {
                while (rs.next()) {
                    System.out.printf("%d — %s (%s)%n",
                            rs.getInt("publishing_year"),
                            rs.getString("name"),
                            rs.getString("author"));
                }
            }

            // ЗАДАНИЕ № 7. Добавление информации о себе и своих любимых книг
            System.out.println("\n=== № 7. Добавление себя и своих любимых книг ===");

            Book myBook1 = new Book(
                "Мастер и Маргарита",
                "Михаил Булгаков",
                1967,
                "978-5-17-118366-0",
                "АСТ"
            );

            Book myBook2 = new Book(
                    "1984",
                    "Джордж Оруэлл",
                    1949,
                    "978-5-17-080115-2",
                    "АСТ"
            );

            Visitor me = new Visitor(
                    "Имя",
                    "Фамилия",
                    "+7-999-000-11-22",
                    List.of(myBook1, myBook2),
                    true
            );

            // 1. Добавляем себя в таблицу посетителей
            try (PreparedStatement psVis = conn.prepareStatement(mergeVisitor)) {
                psVis.setString(1, me.name());
                psVis.setString(2, me.surname());
                psVis.setString(3, me.phone());
                psVis.setBoolean(4, me.subscribed());
                psVis.executeUpdate();
            }

            int myVisId = getIdByKey(conn, selectVisitorId, me.phone());

            // 2. Добавляем свои любимые книги и связываем их со своим ID
            for (Book b : me.favoriteBooks()) {
                try (PreparedStatement psBook = conn.prepareStatement(mergeBook)) {
                    psBook.setString(1, b.name());
                    psBook.setString(2, b.author());
                    psBook.setInt(3, b.publishingYear());
                    psBook.setString(4, b.isbn());
                    psBook.setString(5, b.publisher());
                    psBook.executeUpdate();
                }

                int myBookId = getIdByKey(conn, selectBookId, b.isbn());

                try (PreparedStatement psLink = conn.prepareStatement(mergeLink)) {
                    psLink.setInt(1, myVisId);
                    psLink.setInt(2, myBookId);
                    psLink.executeUpdate();
                }
            }

            // 3. Читаем добавленные данные из базы и выводим на экран
            String selectMyInfo = "SELECT v.name AS v_name, v.surname, v.phone, b.name AS b_name, b.author, b.publishing_year " +
                    "FROM study.visitors v " +
                    "JOIN study.visitor_books vb ON v.id = vb.visitor_id " +
                    "JOIN study.books b ON vb.book_id = b.id " +
                    "WHERE v.phone = ?";

            try (PreparedStatement ps = conn.prepareStatement(selectMyInfo)) {
                ps.setString(1, me.phone());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.printf("Посетитель: %s %s (%s) -> Любимая книга: %s (%s, %d)%n",
                                rs.getString("v_name"),
                                rs.getString("surname"),
                                rs.getString("phone"),
                                rs.getString("b_name"),
                                rs.getString("author"),
                                rs.getInt("publishing_year"));
                    }
                }
            }
            
            // ЗАДАНИЕ № 8. Удаление таблиц посетителей и книг
                       // ЗАДАНИЕ № 8. Удаление таблиц
            System.out.println("\n=== № 8. Удаление таблиц посетителей и книг ===");
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS study.visitor_books");
                stmt.execute("DROP TABLE IF EXISTS study.books");
                stmt.execute("DROP TABLE IF EXISTS study.visitors");
                System.out.println("Таблицы visitor_books, books и visitors успешно удалены.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getIdByKey(Connection conn, String query, String key) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, key);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        throw new SQLException("Не удалось найти ID для ключа: " + key);
    }
}




