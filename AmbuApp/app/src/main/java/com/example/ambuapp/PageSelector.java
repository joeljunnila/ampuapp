package com.example.ambuapp;

import android.widget.Switch;

public class PageSelector {

    private String[] valmistatutuminen = {
            "valmistautuminen1.txt",
            "valmistautuminen2.txt",
            "valmistautuminen3.txt"
    };

    private String[] synnytyksenAikana = {
            "synnytyksenAikana1.txt",
            "synnytyksenAikana2.txt",
            "synnytyksenAikana3.txt",
            "synnytyksenAikana4.txt",
            "synnytyksenAikana5.txt",
            "synnytyksenAikana6.txt",
            "synnytyksenAikana7.txt"
    };

    private String[] synnytyksenJalkeen = {
            "synnytyksenJalkeen1.txt",
            "synnytyksenJalkeen2.txt",
            "synnytyksenJalkeen3.txt",
            "synnytyksenJalkeen4.txt",
            "synnytyksenJalkeen5.txt"
    };

    public String getPage(String file, int pageNumber) {
        String page;
        switch (file) {
            case "Valmistautuminen":
                page = valmistatutuminen[pageNumber];
                break;
            case "SynnytyksenAikana":
                page = synnytyksenAikana[pageNumber];
                break;
            case "SynnytyksenJalkeen":
                page = synnytyksenJalkeen[pageNumber];
                break;
            default:
                page = "Error";
                break;
        }
        return page;
    }

    public int getPages(String file) {
        int pages;
        switch (file) {
            case "Valmistautuminen":
                pages = valmistatutuminen.length;
                break;
            default:
                pages = 0;
                break;
        }

        return pages;
    }
}
