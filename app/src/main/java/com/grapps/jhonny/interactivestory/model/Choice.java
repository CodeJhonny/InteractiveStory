package com.grapps.jhonny.interactivestory.model;

/**
 * Created by Jhonny on 14/08/16.
 */
public class Choice {

    private String mText;
    private int mNextPage;


    // ===== constructors

    public Choice(String text, int nextPage) {
        mText = text;
        mNextPage = nextPage;

    }

    // ===== Getters and Setters

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getNextPage() {
        return mNextPage;
    }

    public void setNextPage(int nextPage) {
        mNextPage = nextPage;
    }
}
