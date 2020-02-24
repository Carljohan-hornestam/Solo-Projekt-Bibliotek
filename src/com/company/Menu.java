package com.company;

import com.company.Bookhandler.AddBook;
import com.company.Bookhandler.BookDeleter;
import com.company.Bookhandler.BookEditor;
import com.company.Database.Search;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Library library;
    String menuChoice = "";
    Scanner scan = new Scanner(System.in);
    AddBook bookAdder = new AddBook(library);
    BookEditor editor = new BookEditor();
    BookDeleter deleter = new BookDeleter();
    BorrowBook borrow = new BorrowBook(library);
    ReturnBook bookReturner = new ReturnBook(library);
    Search s = new Search();
    private boolean isRunning = true;

    public Menu(Library library){
        this.library = library;
    }

    public void mainMenu(){
        do {
            System.out.println("Welcome to the library!");
            System.out.println("1: Admin");
            System.out.println("2: User");
            System.out.println("3: Quit");
            menuChoice = scan.nextLine();

            switch (menuChoice) {
                case "1":
                    adminMenu();
                    break;

                case "2":
                    userMenu();
                    break;
                case "3":
                    System.out.println("Bye bye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (isRunning);
    }
    private void adminMenu(){
        do {
            System.out.println("What do you want to do?");
            System.out.println("1: Add book to library");
            System.out.println("2: Edit book from library");
            System.out.println("3: Remove book from library");
            System.out.println("4: Back to main menu");
            // TODO fixa case 4
            menuChoice = scan.nextLine();

            switch (menuChoice) {
                case "1":
                    bookAdder.addBook();
                    break;
                case "2":
                    List<Book> booksToEdit = s.search();
                    editor.editChoice(booksToEdit);
                    break;
                case "3":
                    List<Book> booksToDelete = s.search();
                    deleter.deleteChoice(booksToDelete);
                    break;
                case "4":
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (isRunning);
    }
    private void userMenu(){
        do {
            System.out.println("What do you want to do?");
            System.out.println("1: View all books");
            System.out.println("2: Borrow book");
            System.out.println("3: Return book");
            System.out.println("4: View your borrowed books");
            System.out.println("5: Back to main menu");

            menuChoice = scan.nextLine();

            switch (menuChoice) {
                case "1":
                    library.printBooks();
                    break;
                case "2":
                    List<Book> booksToBorrow = s.search();
                    borrow.borrowBook(booksToBorrow);
                    break;
                case "3":
                    bookReturner.returnBook();
                    break;
                case "4":
                    Library.users.get(0).printActiveLoans();
                    break;
                case "5":
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (isRunning);
    }
}
