package com.company.Database;


import com.company.Book;
import com.company.Database.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {
    String search;
    Scanner scan = new Scanner(System.in);

    public List search() {
        List<Book> foundBooks = new ArrayList<>();
        System.out.println("Search for title: ");
        search = scan.nextLine();
        foundBooks = FileManager.getInstance().searchInList(search, foundBooks);
        // TODO fixa return
        if (foundBooks.size() == 0){
            System.out.println("No books found.");
        }
        else{
            return foundBooks;
        }
        return foundBooks;
    }

}
