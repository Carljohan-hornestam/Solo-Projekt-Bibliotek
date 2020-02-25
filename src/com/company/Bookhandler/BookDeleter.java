package com.company.Bookhandler;

import com.company.Book;
import com.company.Database.FileManager;
import com.company.Library;

import java.util.List;
import java.util.Scanner;

public class BookDeleter {
    Scanner scan = new Scanner(System.in);

    public void deleteChoice(List<Book> foundBooks){
        if (foundBooks.size() == 0){
            return;
        }
        int placeInList = 1;
        for (Book book : foundBooks){
            System.out.println(placeInList + " " + book);
            placeInList++;
        }
        System.out.println("What book would you like to delete? Enter its number: ");
        int bookChoice = scan.nextInt();
        System.out.println("You chose " + foundBooks.get(bookChoice - 1));
        Book selectedBook = foundBooks.get(bookChoice - 1);
        Library.books.remove(selectedBook);
        deleteBook(selectedBook);
    }
    public void deleteBook(Book selectedBook) {
        String fileName = "Database/books/" + selectedBook.getTitle() + ".txt";
        FileManager.getInstance().deleteFile(fileName);
    }
}
