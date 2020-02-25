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
    private boolean isRunning = true;

    public AddBook(Library library) {
        this.library = library;
    }

    public void addBook() {
        bookAddingProcess();
    }

    private void bookAddingProcess() {
        boolean isDuplicate = false;

        String isbn;
        do {
            System.out.println("Enter ISBN: ");
            isbn = scan.nextLine();
            if (isbn.matches(".*[0-9].*")) {
                if (checkLengthOfString(isbn)) {
                    if (checkForDuplicates(isbn)) {
                        return;
                    } else {
                        bookDetails.add("ISBN: " + isbn);
                        isDuplicate = true;
                    }
                }
            } else {
                System.out.println("The ISBN-number can only contain digits!");
            }
        } while (!isDuplicate);

        String title;
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

        String year;
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

        String author;
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

        String genre;
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
        addNewBook(isbn, title, year, author, genre);
    }

    private boolean checkLengthOfString(String isbnToCheck) {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotationsByType(LengthCheck.class);

            for (Annotation annotation : annotations) {
                LengthCheck checker = (LengthCheck) annotation;
                if (isbnToCheck.length() > checker.minLength() || isbnToCheck.length() < checker.minLength()) {
                    System.out.println("The ISBN-number must be 13 digits!");
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

    private void addNewBook(String isbn, String title, String year, String author, String genre) {
        int quantity = 1;
        Library.books.add(new Book(isbn, title, year, author, genre, quantity));
        System.out.println(title + " by " + author + " created!");
        String newBookFile = "Database/books/" + title;
        FileManager.getInstance().writeToFile(newBookFile, bookDetails);
        bookDetails.clear();
    }

}
