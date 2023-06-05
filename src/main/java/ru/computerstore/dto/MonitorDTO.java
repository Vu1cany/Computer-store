package ru.computerstore.dto;

import jakarta.validation.constraints.Min;

public class MonitorDTO extends ItemDTO{
    @Min(value = 13, message = "Диагональ должна быть >= 13")
    private double diagonal;

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }
}
