package ru.computerstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Entity
public class Computer extends Item{
    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor")
    private FormFactor formFactor;

    public Computer(int serialNumber, String manufacturer, double price, FormFactor formFactor) {
        super(serialNumber, manufacturer, price);
        this.formFactor = formFactor;
    }

    public Computer() {}

    public FormFactor getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer computer)) return false;
        if (!super.equals(o)) return false;
        return formFactor == computer.formFactor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formFactor);
    }
}
