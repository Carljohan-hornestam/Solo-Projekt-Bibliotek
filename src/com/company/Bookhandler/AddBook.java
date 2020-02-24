package com.company.Bookhandler;


import com.company.Book;
import com.company.Database.FileManager;
import com.company.Database.LengthCheck;
import com.company.Library;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddBook {
    Library library;
    Scanner scan = new Scanner(System.in);
    private List<String> bookDetails = new ArrayList<>();

    @LengthCheck(minLength = 13)
    private String isbn;
    private String title;
    private String year;
    private String author;
    private String genre;
    private int quantity = 1;
    private boolean isRunning = true;
    private boolean isDuplicate = true;

    public AddBook(Library library) {
        this.library = library;
    }

    public void addBook() {
        enterIsbn();
        enterTitle();
        enterYear();
        enterAuthor();
        enterGenre();
        addNewBook();
    }

    private void enterIsbn() {
        do {
            System.out.println("Enter ISBN: ");
            isbn = scan.nextLine();
            checkLengthOfString(isbn);
            if (isbn.matches(".*[0-9].*")) {
                checkForDuplicates(isbn);
                bookDetails.add("ISBN: " + isbn);
            } else {
                System.out.println("You may only enter digits!");
                isDuplicate = false;
            }
        }
        while (!isDuplicate);
    }

    private void enterTitle() {
        do {
            System.out.println("Enter title: ");
            title = scan.nextLine();

            if (title.matches(".*[A-Za-z].*")) {
                bookDetails.add("Title: " + title);
                isRunning = true;
            } else {
                System.out.println("You may only enter letters!");
                isRunning = false;
            }
        } while (!isRunning);
    }

    private void enterYear() {
        do {
            System.out.println("Enter the year of release: ");
            year = scan.nextLine();

            if (year.matches(".*[0-9].*")) {
                bookDetails.add("Year: " + year);
                isRunning = true;
            } else {
                System.out.println("You may only enter digits!");
                isRunning = false;
            }
        } while (!isRunning);
    }

    private void enterAuthor() {
        do {
            System.out.println("Enter the name of the author: ");
            author = scan.nextLine();

            if (author.matches(".*[A-Za-z].*")) {
                bookDetails.add("Author: " + author);
                isRunning = true;
            } else {
                System.out.println("You may only enter letters!");
                isRunning = false;
            }
        } while (!isRunning);
    }

    private void enterGenre() {
        do {
            System.out.println("Enter the genre of the book: ");
            genre = scan.nextLine();

            if (genre.matches(".*[A-Za-z]")) {
                bookDetails.add("Genre: " + genre);
                isRunning = true;
            } else {
                System.out.println("You may only enter letters!");
                isRunning = false;
            }
        } while (!isRunning);
    }

    // TODO fixa return false
    private boolean checkLengthOfString(String isbnToCheck) {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotationsByType(LengthCheck.class);

            for (Annotation annotation : annotations) {
                LengthCheck checker = (LengthCheck) annotation;
                if (isbnToCheck.length() > checker.minLength()) {
                    System.out.println("The ISBN-number can not be longer than 13 digits!");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkForDuplicates(String isbn) {
        for (Book book : Library.books) {
            if (isbn.equals(book.getIsbn())) {
                System.out.println("That book already exists! Another copy has been added");
                book.setQuantity(book.getQuantity() + 1);
                return true;
            }
        }
        return false;
    }

    private void addNewBook() {
        Library.books.add(new Book(isbn, title, year, author, genre, quantity));
        System.out.println(title + " by " + author + " created!");
        String newBookFile = "Database/books/" + title;
        FileManager.getInstance().writeToFile(newBookFile, bookDetails);
        bookDetails.clear();
    }

}
