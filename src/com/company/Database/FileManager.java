package com.company.Database;

import com.company.Book;
import com.company.Library;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static FileManager fileManager = null;

    private FileManager() {
    }

    public static FileManager getInstance() {
        if (fileManager == null) {
            fileManager = new FileManager();
        }
        return fileManager;
    }

    public File[] readFromFolder(File folderPath) {
        return folderPath.listFiles();
    }

    public List<String> readFromFile(Path path) {
        List<String> contents = null;
        try {
            contents = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    public void writeToFile(String fileName, List<String> details) {
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("Filename already exists!");
        } else {
            try (PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8)) {
                for (String detail : details) {
                    writer.println(detail);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<Book> searchInList(String search, String whatToSearchFor) {
        List<Book> foundBooks = new ArrayList<>();
        if (whatToSearchFor.equals("title")) {
            for (Book book : Library.books) {
                if (book.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    foundBooks.add(book);
                }
            }
        }
        if (whatToSearchFor.equals("author")) {
            for (Book book : Library.books) {
                if (book.getAuthor().toLowerCase().contains(search.toLowerCase())) {
                    foundBooks.add(book);
                }
            }
        }
        if (whatToSearchFor.equals("genre")) {
            for (Book book : Library.books) {
                if (book.getGenre().toLowerCase().contains(search.toLowerCase())) {
                    foundBooks.add(book);
                }
            }
        }
        if (whatToSearchFor.equals("year")) {
            for (Book book : Library.books) {
                if (book.getYear().toLowerCase().contains(search.toLowerCase())) {
                    foundBooks.add(book);
                }
            }
        }
        return foundBooks;
    }

    public void editFile(String fileName, String oldLine, String newLine) {
        List<String> lines = new ArrayList<>();
        String line;
        String str = "";
        try {
            File fileToEdit = new File(fileName);
            FileReader fileReader = new FileReader(fileToEdit);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (line.toLowerCase().contains(oldLine.toLowerCase()))
                    line = newLine;
                lines.add(line);
            }
            fileReader.close();
            bufferedReader.close();
            FileWriter fileWriter = new FileWriter(fileToEdit);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String string : lines) {
                str += string + "\n";
            }
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Deprecated
    // No longer in use
    public void renameFile(String fileName, String newFileName) {
        File oldFile = new File(fileName);
        File newFile = new File(newFileName);
        boolean rename = oldFile.renameTo(new File(String.valueOf(newFile)));
        if (rename) {
            System.out.println("File successfully renamed");
        } else {
            System.out.println("Failed to rename");
        }
    }

    public void deleteFile(String fileName) {
        File fileToDelete = new File(fileName);

        if (!fileToDelete.exists()) {
            System.out.println("That file doesn't exist!");
            return;
        }
        try {
            fileToDelete.delete();
            System.out.println("File deleted");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error trying to delete file!");
        }
    }
}