package ru.computerstore.dto;

import ru.computerstore.models.LaptopSize;

public class LaptopDTO extends ItemDTO{
    private LaptopSize size;

    public LaptopSize getSize() {
        return size;
    }

    public void setSize(LaptopSize size) {
        this.size = size;
    }
}
