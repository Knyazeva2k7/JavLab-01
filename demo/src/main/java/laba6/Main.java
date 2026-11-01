package laba6;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String filePath = "book.json"; 

        try (FileReader reader = new FileReader(filePath)) {
            Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
            List<Visitor> visitors = gson.fromJson(reader, visitorListType);

            System.out.println("1. List of library visitors----------");
            visitors.stream()
                    .forEach(v -> System.out.println(v.surname() + " " + v.name() + " (Phone: " + v.phone() + ")"));
            
            long totalVisitors = visitors.stream().count();
            System.out.println("Total number of visitors: " + totalVisitors);

            System.out.println("2. Books without repetitions----------");
            List<Book> uniqueBooks = visitors.stream()
                    .flatMap(v -> v.favoriteBooks().stream())
                    .distinct() 
                    .collect(Collectors.toList());

            uniqueBooks.forEach(b -> System.out.println("- " + b.name() + " (" + b.author() + ")"));
            System.out.println("Number of unique books in favorites: " + uniqueBooks.size());

            System.out.println("3. Sorting books by year of publication----------");
            visitors.stream()
                    .flatMap(v -> v.favoriteBooks().stream())
                    .distinct()
                    .sorted((b1, b2) -> Integer.compare(b1.publishingYear(), b2.publishingYear()))
                    .forEach(b -> System.out.println(b.publishingYear() + " year " + b.name() + " [" + b.author() + "]"));


            System.out.println("4. Searching for author 'Jane Austen'-------------");
            boolean hasJaneAusten = visitors.stream()
                    .flatMap(v -> v.favoriteBooks().stream())
                    .anyMatch(b -> "Jane Austen".equalsIgnoreCase(b.author()));
            
            System.out.println("Are there any books by the author Jane Austen in the favorites? -> " + (hasJaneAusten ? "Yes" : "No"));

            System.out.println("5.Maximum number of books a single visitor can have in their favorites----------");
            int maxBooksCount = visitors.stream()
                    .mapToInt(v -> v.favoriteBooks().size())
                    .max()
                    .orElse(0);
            
            System.out.println("Maximum number of books per person: " + maxBooksCount);

            System.out.println("6.Generated SMS messages-----------------");
            
            double averageBooks = visitors.stream()
                    .mapToInt(v -> v.favoriteBooks().size())
                    .average()
                    .orElse(0.0);
            
            System.out.println("(For reference: average number of books per visitor = " + averageBooks + ")");

            List<SmsMessage> smsList = visitors.stream().filter(Visitor::subscribed).map(v -> { //subscribed = true
                        int count = v.favoriteBooks().size(); // используем метод из рекорда
                        String text;
                        if (count > averageBooks) {
                            text = "you are a bookworm";
                        } 
                        else if (count < averageBooks) {
                            text = "read more";
                        } 
                        else {
                            text = "fine";
                        }
                        return new SmsMessage(v.phone(), text);
                    })
                    .collect(Collectors.toList());

            if (smsList.isEmpty()) {
                System.out.println("No subscribers to send SMS to");
            } else {
                smsList.forEach(sms -> System.out.println("Кому: " + sms.phoneNumber() + " | Text: \"" + sms.message() + "\""));
            }

        } catch (IOException e) {
            System.err.println("Error while working with the file: " + e.getMessage());
        }
    }
}
