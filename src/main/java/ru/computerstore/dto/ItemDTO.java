package ru.computerstore.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class ItemDTO {

    protected int id;

    protected int serialNumber;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "([А-Я]|[A-Z]).+", message = "Пирмер корректного ввода: Производитель")
    protected String manufacturer;

    @Min(value = 1, message = "Цена должна быть > 0")
    @Max(value = 9999999, message = "Цена должна быть < 9999999")
    protected double price;

    protected long stockUnits;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStockUnits() {
        return stockUnits;
    }

    public void setStockUnits(long stockUnits) {
        this.stockUnits = stockUnits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
