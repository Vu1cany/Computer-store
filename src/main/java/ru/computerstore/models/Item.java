package ru.computerstore.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "serial_number")
    protected int serialNumber;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Pattern(regexp = "([А-Я]|[A-Z]).+", message = "Пример корректного ввода: Производитель")
    @Column(name = "manufacturer")
    protected String manufacturer;

    @Min(value = 1, message = "Цена должна быть > 0")
    @Max(value = 9999999, message = "Цена должна быть < 9999999")
    @Column(name = "price")
    protected double price;

    @Transient
    protected long stockUnits;

    public Item(int serialNumber, String manufacturer, double price) {
        this.serialNumber = serialNumber;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Item() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return id == item.id && serialNumber == item.serialNumber && Double.compare(item.price, price) == 0 && stockUnits == item.stockUnits && Objects.equals(manufacturer, item.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, manufacturer, price, stockUnits);
    }
}
