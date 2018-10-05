package com.example.tamm_feelsbook;

public class CommentTooLongException extends Exception {
    CommentTooLongException(){
        super("Comment too long: 100 characters maximum!");
    }
}
