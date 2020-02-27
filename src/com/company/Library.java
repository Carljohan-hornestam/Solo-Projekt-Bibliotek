package com.company;

import com.company.Database.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

public class Library {

    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();

    public void load(){
        loadBooks();
        loadUsers();
    }

    public void loadBooks() {
        File folderPath = new File("Database/books/");
        String isbn = "";
        for (File file : FileManager.getInstance().readFromFolder(folderPath)) {
            try (BufferedReader brTest = new BufferedReader(new FileReader(file))) {
                String firstLine = brTest.readLine();
                final Path path = file.toPath();
                String fileName = String.valueOf(path);
                books.add(new Book(FileManager.getInstance().readFromFile(path), fileName));
                String isbnFromFile = firstLine.substring(firstLine.indexOf(":") + 1).trim();
                if (isbn.contains(isbnFromFile)) {
                    books.remove(books.size() - 1);
                    for (Book book : books) {
                        if (book.getIsbn().equals(isbnFromFile)) {
                            book.setQuantity(book.getQuantity() + 1);
                            book.setTotalQuantity(book.getTotalQuantity() + 1);
                        }
                    }
                }
                isbn = isbn.concat(" " + isbnFromFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void loadUsers() {
        File folderPath = new File("Database/users/");
        for (File file : FileManager.getInstance().readFromFolder(folderPath)) {
            final Path path = file.toPath();
            users.add(new User(FileManager.getInstance().readFromFile(path)));
        }
    }
    public void printBooks(){
        System.out.println("Available books, sorted by title");
        books.stream()
                .sorted(Comparator.comparing(s -> s.getTitle()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }
}
