package laba6;

import java.util.List;

public record Visitor(String name, String surname, String phone, List<Book> favoriteBooks, boolean subscribed) {}
