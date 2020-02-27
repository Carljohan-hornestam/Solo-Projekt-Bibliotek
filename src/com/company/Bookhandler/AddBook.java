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
import java.util.UUID;

public class AddBook {
    Library library;
    Scanner scan = new Scanner(System.in);

    private boolean isRunning = true;

    public AddBook(Library library) {
        this.library = library;
    }

    public void addBook() {
        List<String> bookDetails = new ArrayList<>();
        String regexLetters = ".*[A-Za-z].*";
        String regexNumbers = ".*[0-9].*";
        String isbn;
        boolean isDuplicate = false;
        do {
            isbn = enterBookDetails("ISBN", regexNumbers);
            if (checkForDuplicates(isbn)){
                return;
            }
            else {
                bookDetails.add("ISBN: " + isbn);
                break;
            }
        }while (!isDuplicate);
        String title = enterBookDetails("Title", regexLetters);
        bookDetails.add("Title: " + title);
        String year = enterBookDetails("Year", regexNumbers);
        bookDetails.add("Year: " + year);
        String author = enterBookDetails("Author", regexLetters);
        bookDetails.add("Author: " + author);
        String genre = enterBookDetails("Genre", regexLetters);
        bookDetails.add("Genre: " + genre);
        addNewBook(isbn, title, year, author, genre, bookDetails);
    }
    private String enterBookDetails(String str, String regex) {
        String input;
        do {
            System.out.println("Enter " + str + ": ");
            input = scan.nextLine();

            if (str.equals("ISBN") || str.equals("Year")){
                if (checkLengthOfString(input, str.toLowerCase()) && input.matches(regex)){
                    System.out.println(str + " is valid!");
                    isRunning = true;
                    return input;
                }
                else {
                    System.out.println(str + " invalid");
                    isRunning = false;
                }
            }
            if (str.equals("Title") || str.equals("Author") || str.equals("Genre")){
                if (checkLengthOfString(input, str.toLowerCase()) && input.matches(regex)){
                    System.out.println(str + " is valid!");
                    isRunning = true;
                    return input;
                }
                else {
                    System.out.println(str + " invalid");
                    isRunning = false;
                }
            }
        } while (!isRunning);
        return input;
    }
    private void addNewBook(String isbn, String title, String year, String author, String genre, List<String> bookDetails) {
        Book newBook = new Book(isbn, title, year, author, genre, 1);
        Library.books.add(newBook);
        newBook.setRandomFileName("Database/books/" + newBook.generateRandomFileName());
        System.out.println(title + " by " + author + " created!");
        String newBookFile = newBook.getRandomFileName();
        FileManager.getInstance().writeToFile(newBookFile, bookDetails);
        bookDetails.clear(); }

    private boolean checkLengthOfString(String stringtoCheck, String fieldName) {
        Field field = null;
        try {
            field = Book.class.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Annotation[] annotations = field.getAnnotationsByType(LengthCheck.class);

        for (Annotation annotation : annotations) {
            LengthCheck checker = (LengthCheck) annotation;
            if (stringtoCheck.length() != checker.value()) {
                System.out.println("The " + fieldName + "-number must be " + checker.value() + " digits!");
                return false;
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

}
