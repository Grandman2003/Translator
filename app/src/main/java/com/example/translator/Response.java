package com.example.translator;

public class Response {
    int code;
    String lang;
    String[] text;

    @Override
    public String toString() {
        return text[0];
    }
}