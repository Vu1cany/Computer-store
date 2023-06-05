package ru.computerstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Entity
public class Laptop extends Item{
    @Enumerated(EnumType.STRING)
    private LaptopSize size;

    public Laptop(int serialNumber, String manufacturer, double price, LaptopSize size) {
        super(serialNumber, manufacturer, price);
        this.size = size;
    }

    public Laptop() {}

    public LaptopSize getSize() {
        return size;
    }

    public void setSize(LaptopSize size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop laptop)) return false;
        if (!super.equals(o)) return false;
        return size == laptop.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size);
    }
}
