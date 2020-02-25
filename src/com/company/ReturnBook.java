package com.company;

import com.company.Database.FileManager;

import java.util.Scanner;

public class ReturnBook {
    Library library;
    Scanner scan = new Scanner(System.in);

    public ReturnBook(Library library){
        this.library = library;
    }

    public void returnBook(){
        String fileName = "Database/users/" + Library.users.get(0).getName() + ".txt";
        String newActiveLoans = "";
        Library.users.get(0).printActiveLoans();
        if (Library.users.get(0).getActiveLoans().isBlank()){
            return;
        }
        System.out.println("Enter the book's ISBN you wish to return: ");
        String isbn = scan.nextLine();

        if (Library.users.get(0).getActiveLoans().contains(isbn)){
            for (Book bookToReturn : Library.books){
                if (bookToReturn.getIsbn().equals(isbn)){
                    bookToReturn.setQuantity(bookToReturn.getQuantity() + 1);
                    System.out.println("You have successfully returned " + bookToReturn.getTitle());
                    newActiveLoans = Library.users.get(0).getActiveLoans().replaceAll(isbn + ",", "");
                    Library.users.get(0).setActiveLoans(newActiveLoans);

                }
            }
            FileManager.getInstance().editFile(fileName, "Activeloans", "Activeloans: " + Library.users.get(0).getActiveLoans());
        }
        else{
            System.out.println("You haven't borrowed that book!");
        }
    }
}
