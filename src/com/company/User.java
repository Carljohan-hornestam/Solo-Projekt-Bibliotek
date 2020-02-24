package com.company;

import java.util.List;

public class User {

    private String name;
    private String mail;
    private String tel;
    private String activeLoans = "";
    private String uniqueId;


    public User(List<String> userInfoFromDisk) {
        for (String s : userInfoFromDisk) {
            String trim = s.substring(s.indexOf(":") + 1).trim();
            if (s.contains("name:")) {
                this.name = trim;
            } else if (s.contains("mail:")) {
                this.mail = trim;
            } else if (s.contains("tel:")) {
                this.tel = trim;
            } else if (s.contains("activeLoans:")) {
                this.activeLoans = trim;
            } else if (s.contains("uniqueId:")) {
                this.uniqueId = trim;
            }
        }
    }
    public String getName(){
        return name;
    }

    public String getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(String activeLoans) {
        this.activeLoans = activeLoans;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                ", activeLoans='" + activeLoans + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }
    public void printActiveLoans(){
        if (!activeLoans.isBlank()) {
            System.out.println("Your borrowed books are: " + activeLoans);
        }
        else {
            System.out.println("You haven't borrowed any books!");
        }
    }
}

