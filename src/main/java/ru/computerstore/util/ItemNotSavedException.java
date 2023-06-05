package ru.computerstore.util;

public class ItemNotSavedException extends RuntimeException{
    public ItemNotSavedException(String message) {
        super(message);
    }
}
