package ru.computerstore.dto;

import jakarta.validation.constraints.Min;

public class HddDTO extends ItemDTO{
    @Min(value = 1, message = "Объем должен быть > 0")
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
