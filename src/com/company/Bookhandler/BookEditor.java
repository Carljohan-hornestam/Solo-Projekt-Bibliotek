package com.company.Bookhandler;

import com.company.Book;
import com.company.Database.FileManager;

import java.util.List;
import java.util.Scanner;

public class BookEditor {
    Scanner scan = new Scanner(System.in);
    boolean isOk = false;

public void editChoice(List<Book> foundBooks){
    if (foundBooks.size() == 0){
        return;
    }
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
        String fileName = selectedBook.getRandomFileName();
        String newTitle;
        do {
            System.out.println("Enter new title: ");
            do {
                newTitle = scan.nextLine();
            } while (newTitle.length() == 0);

            if (newTitle.matches(".*[A-Za-z].*")) {
                selectedBook.setTitle(newTitle);
                System.out.println(newTitle + " is now the new title of " + selectedBook);
                isOk = true;
            } else {
                System.out.println("Input can not contain only digits!");
            }
        } while (!isOk);

        FileManager.getInstance().editFile(fileName, "Title", "Title: " + newTitle);
    }
}
