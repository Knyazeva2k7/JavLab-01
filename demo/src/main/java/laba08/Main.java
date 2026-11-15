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
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String pass = "";
        String filePath = "book.json";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {

            
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE SCHEMA IF NOT EXISTS study");//создание схемы
                stmt.execute("SET SCHEMA study");//открытие(?) схемы

                String initSql = Files.readString(Paths.get("music-create.sql"), StandardCharsets.UTF_8);
                stmt.execute(initSql);
            }

            System.out.println(" 1. All music tracks-------------------");

            String selectAllMusic = "SELECT id, name FROM study.music";

            try (Statement stmt = conn.createStatement();//создание объекта-исполнителя (для статических запросов)
                 ResultSet rs = stmt.executeQuery(selectAllMusic)) {//чтение 
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                }
            }

            System.out.println("2. Tracks without 'm' and 't'----------------------");

            String filterMusicSql = "SELECT id, name FROM study.music " +
                                    "WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%'";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(filterMusicSql)) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                }
            }


            System.out.println("Task 3. Adding a new track----------------");
            
            String insertMusicSql = "INSERT INTO study.music (id, name) VALUES (?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(insertMusicSql)) {//позволяет втавлять в запрос переменные
                pstmt.setInt(1, 999);
                pstmt.setString(2, "Verity");

                int rows = pstmt.executeUpdate();//изменение 

                System.out.println("Inserted rows into music: " + rows);
            }

            System.out.println("4. Creating tables and loading data from book.json--------------");

            String createVisitorsSql = "CREATE TABLE IF NOT EXISTS study.visitors (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "surname VARCHAR(100), " +
                    "phone VARCHAR(50) UNIQUE, " + //ограничение уникальности,чтоб не повторялись
                    "subscribed BOOLEAN" +
                    ")";

            String createBooksSql = "CREATE TABLE IF NOT EXISTS study.books (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " + //первичный ключ который генерируется бд
                    "name VARCHAR(255), " +
                    "author VARCHAR(255), " +
                    "publishing_year INT, " +
                    "isbn VARCHAR(50) UNIQUE, " +
                    "publisher VARCHAR(255)" +
                    ")";

            String createLinksSql = "CREATE TABLE IF NOT EXISTS study.visitor_books (" +
                    "visitor_id INT, " +
                    "book_id INT, " +
                    "PRIMARY KEY (visitor_id, book_id), " + //первичный ключ для двух строк, запрещающий создать одинаковые пары
                    "FOREIGN KEY (visitor_id) REFERENCES study.visitors(id) ON DELETE CASCADE, " + //связывает айди с колонкой 
                    "FOREIGN KEY (book_id) REFERENCES study.books(id) ON DELETE CASCADE" + 
                    ")";

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createVisitorsSql);
                stmt.execute(createBooksSql);
                stmt.execute(createLinksSql);
            }

            Gson gson = new Gson();//создание парсера

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

                int visitorId = getIdByKey(conn, selectVisitorId, v.phone());//получение сгенеренного айди посетителя по телефону

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
            System.out.println("Unique visitors and books have been successfully loaded");


            System.out.println("5. Books sorted by publishing year----------------");
            
            String sortBooksSql = "SELECT name, author, publishing_year FROM study.books ORDER BY publishing_year ASC";//с сортировкой по возрастанию

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sortBooksSql)) {
                while (rs.next()) {
                    System.out.printf("%d - %s (%s)%n",
                            rs.getInt("publishing_year"),
                            rs.getString("name"),
                            rs.getString("author"));
                }
            }


            System.out.println("6. Books published before 2000-----------------------");
            
            String oldBooksSql = "SELECT name, author, publishing_year FROM study.books WHERE publishing_year < 2000";
            
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(oldBooksSql)) {
                while (rs.next()) {
                    System.out.printf("%d - %s (%s)%n",
                            rs.getInt("publishing_year"),
                            rs.getString("name"),
                            rs.getString("author"));
                }
            }


            System.out.println("7. Adding personal info and favorite books--------------");
            Book myBook1 = new Book(
                    "The Master and Margarita",
                    "Mikhail Bulgakov",
                    1967,
                    "978-5-17-118366-0",
                    "AST"
            );
            Book myBook2 = new Book(
                    "1984",
                    "George Orwell",
                    1949,
                    "978-5-17-080115-2",
                    "AST"
            );
            Visitor me = new Visitor(
                    "Diana",
                    "Knyazeva",
                    "+7-800-555-35-55",
                    List.of(myBook1, myBook2),
                    true
            );

            try (PreparedStatement psVis = conn.prepareStatement(mergeVisitor)) {
                psVis.setString(1, me.name());
                psVis.setString(2, me.surname());
                psVis.setString(3, me.phone());
                psVis.setBoolean(4, me.subscribed());
                psVis.executeUpdate();
            }

            int myVisId = getIdByKey(conn, selectVisitorId, me.phone());

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

            // Read and print added personal info and favorite books
            String selectMyInfo = "SELECT v.name AS v_name, v.surname, v.phone, b.name AS b_name, b.author, b.publishing_year " +
                    "FROM study.visitors v " +
                    "JOIN study.visitor_books vb ON v.id = vb.visitor_id " +
                    "JOIN study.books b ON vb.book_id = b.id " +
                    "WHERE v.phone = ?";

            try (PreparedStatement ps = conn.prepareStatement(selectMyInfo)) {
                ps.setString(1, me.phone());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.printf("Visitor: %s %s (%s) -> Favorite book: %s (%s, %d)%n",
                                rs.getString("v_name"),
                                rs.getString("surname"),
                                rs.getString("phone"),
                                rs.getString("b_name"),
                                rs.getString("author"),
                                rs.getInt("publishing_year"));
                    }
                }
            }


            System.out.println("8. Dropping visitors and books tables------------------");
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS study.visitor_books");
                stmt.execute("DROP TABLE IF EXISTS study.books");
                stmt.execute("DROP TABLE IF EXISTS study.visitors");
                System.out.println("Tables visitor_books, books, and visitors have been dropped.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to get record ID by unique key (phone or ISBN)
    private static int getIdByKey(Connection conn, String query, String key) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, key);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        throw new SQLException("ID not found for key: " + key);
    }
}
