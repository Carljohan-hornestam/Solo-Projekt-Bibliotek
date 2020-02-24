package com.company.Bookhandler;

import com.company.Book;
import com.company.Database.FileManager;

import java.util.List;
import java.util.Scanner;

public class BookEditor {
    String newTitle = null;
    Scanner scan = new Scanner(System.in);
    boolean isOk = false;

public void editChoice(List<Book> foundBooks){
    int placeInList = 1;
    for (Book book : foundBooks){
        System.out.println(placeInList + " " + book);
        placeInList++;
    }
    System.out.println("What book's title would you like to edit? Enter its number: ");
    int bookChoice = scan.nextInt();
    System.out.println("You chose " + foundBooks.get(bookChoice - 1));
    Book selectedBook = foundBooks.get(bookChoice - 1);
    editBook(selectedBook);
}
    public void editBook(Book selectedBook) {
        String fileName = "Database/books/" + selectedBook.getTitle() + ".txt";
        do {
            System.out.println("Enter new title: ");
            newTitle = scan.nextLine();
            if (newTitle.matches(".*[A-Za-z].*")) {
                selectedBook.setTitle(newTitle);
                System.out.println(newTitle + " is now the new title of " + selectedBook);
                isOk = true;
            } else {
                // TODO fixa bug med att detta skrivs ut direkt
                System.out.println("Input can not contain only digits!");
            }
        } while (!isOk);

        FileManager.getInstance().editFile(fileName, "Title", "Title: " + newTitle);
        FileManager.getInstance().renameFile(fileName, "Database/books/" + newTitle + ".txt");
    }
}
