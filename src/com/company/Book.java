package com.company;

import com.company.Database.LengthCheck;

import java.util.List;
import java.util.UUID;

public class Book {
    @LengthCheck(13)
    private String isbn;
    private String title;
    @LengthCheck(4)
    private String year;
    private String author;
    private String genre;
    private int quantity;
    private int totalQuantity;
    private String randomFileName;

    public Book (String isbn, String title, String year, String author, String genre, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.randomFileName = generateRandomFileName();
    }

    public Book(List<String> readFromFile, String fileName) {
        int i = 0;
        String[] stringsInfo = new String[7];
        String idFromFile = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
        for (String content : readFromFile) {
            String trim = content.substring(content.indexOf(":") + 1).trim();
            stringsInfo[i] = trim;
            i++;
        }
        this.isbn = stringsInfo[0];
        this.title = stringsInfo[1];
        this.year = stringsInfo[2];
        this.author = stringsInfo[3];
        this.genre = stringsInfo[4];
        this.quantity = 1;
        this.totalQuantity = 1;
        this.randomFileName = fileName;
    }
    public String generateRandomFileName(){
        return UUID.randomUUID() + ".txt";
    }

    public String getRandomFileName() {
        return randomFileName;
    }

    public void setRandomFileName(String randomFileName) {
        this.randomFileName = randomFileName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return title + " by " + author + "," + " Quantity: " + quantity;
    }
}
