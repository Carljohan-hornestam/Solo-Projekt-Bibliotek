package com.company.Database;


import com.company.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {
    String search;
    String searchChoice;
    Scanner scan = new Scanner(System.in);

    public List <Book> search() {
        List<Book> foundBooks = new ArrayList<>();
        boolean isRunning = true;
        do {
            System.out.println("What do you want to search for?");
            System.out.println("1: Title");
            System.out.println("2: Author");
            System.out.println("3: Genre");
            System.out.println("4: Year");
            searchChoice = scan.nextLine();

            if (searchChoice.equals("1")) {
                System.out.println("Search for title: ");
                search = scan.nextLine();
                foundBooks = FileManager.getInstance().searchInList(search, "title");
                return foundBooks;
            }
            if (searchChoice.equals("2")) {
                System.out.println("Search for author: ");
                search = scan.nextLine();
                foundBooks = FileManager.getInstance().searchInList(search, "author");
                return foundBooks;
            }
            if (searchChoice.equals("3")) {
                System.out.println("Search for genre: ");
                search = scan.nextLine();
                foundBooks = FileManager.getInstance().searchInList(search, "genre");
                return foundBooks;
            }
            if (searchChoice.equals("4")) {
                System.out.println("Search for year: ");
                search = scan.nextLine();
                foundBooks = FileManager.getInstance().searchInList(search, "year");
                return foundBooks;
            }
        } while (isRunning);
        return foundBooks;
    }
}
