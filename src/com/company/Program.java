package com.company;


public class Program {
    Library library = new Library();
    Menu menu = new Menu(library);

    public Program(){
        run();
    }

    public void run(){
        library.load();
        menu.mainMenu();
    }
}
