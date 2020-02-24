package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
