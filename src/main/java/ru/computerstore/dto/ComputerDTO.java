package ru.computerstore.dto;

import ru.computerstore.models.FormFactor;

public class ComputerDTO extends ItemDTO{
    private FormFactor formFactor;

    public FormFactor getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }
}
