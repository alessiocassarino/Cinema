package com.example.demo.utility;

public class AdminUtility {

    public static String getStatus(Boolean isActive) {
        return isActive ? "attivo" : "inattivo";
    }

}
