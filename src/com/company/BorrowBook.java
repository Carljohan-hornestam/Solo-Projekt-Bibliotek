package com.company;

import com.company.Database.FileManager;

import java.util.List;
import java.util.Scanner;

public class BorrowBook {
    Scanner scan = new Scanner(System.in);
    Library library;
    public BorrowBook(Library library){
        this.library = library;
    }
 // TODO fixa så man inte kommer hit om foundBooks = 0
    public void borrowBook(List<Book> booksToBorrow){
        if (booksToBorrow.size() == 0){
            return;
        }
        String fileName = "Database/users/" + Library.users.get(0).getName() + ".txt";
        int placeInList = 1;
        for (Book book : booksToBorrow){
            System.out.println(placeInList + " " + book);
            placeInList++;
        }
        System.out.println("What book would you like? Enter its number: ");
        int bookChoice = scan.nextInt();
        Book selectedBook = booksToBorrow.get(bookChoice - 1);
        if (selectedBook.getQuantity() < 1){
            System.out.println("Sorry, that book is not available");
        }
        else {
            System.out.println("You chose " + booksToBorrow.get(bookChoice - 1));
            selectedBook.setQuantity(selectedBook.getQuantity() - 1);
            if (Library.users.get(0).getActiveLoans().isBlank()) {
                Library.users.get(0).setActiveLoans(selectedBook.getIsbn());
                FileManager.getInstance().editFile(fileName, "Activeloans", "Activeloans: " + selectedBook.getIsbn());
            }
            else {
                Library.users.get(0).setActiveLoans(Library.users.get(0).getActiveLoans() + ", " + selectedBook.getIsbn());
                FileManager.getInstance().editFile(fileName, "Activeloans", "Activeloans: " + Library.users.get(0).getActiveLoans());
            }
        }
    }
}
