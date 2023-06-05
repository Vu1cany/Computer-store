package ru.computerstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
public class Hdd extends Item{
    @Min(value = 1, message = "Объем должен быть > 0")
    @Column(name = "capacity")
    private int capacity;

    public Hdd(int serialNumber, String manufacturer, double price, int capacity) {
        super(serialNumber, manufacturer, price);
        this.capacity = capacity;
    }

    public Hdd() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hdd hdd)) return false;
        if (!super.equals(o)) return false;
        return capacity == hdd.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacity);
    }
}
