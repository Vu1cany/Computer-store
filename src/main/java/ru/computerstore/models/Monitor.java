package ru.computerstore.models;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
public class Monitor extends Item{

    @Min(value = 13, message = "Диагональ должна быть >= 13")
    private double diagonal;

    public Monitor(int serialNumber, String manufacturer, double price, double diagonal) {
        super(serialNumber, manufacturer, price);
        this.diagonal = diagonal;
    }

    public Monitor() {}

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monitor monitor)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(monitor.diagonal, diagonal) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), diagonal);
    }
}
