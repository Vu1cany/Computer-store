package ru.computerstore.util;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException() {
        super("Item with this id wasn't found");
    }
}
