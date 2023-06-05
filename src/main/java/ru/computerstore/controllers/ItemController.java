package ru.computerstore.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.computerstore.dto.ItemDTO;
import ru.computerstore.models.Item;
import ru.computerstore.services.ItemService;
import ru.computerstore.util.ItemErrorResponse;
import ru.computerstore.util.ItemNotSavedException;

import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public Item show(@PathVariable("id")int id) {
        return itemService.findById(id);
    }

    @ExceptionHandler
    private static ResponseEntity<ItemErrorResponse> handlerException(RuntimeException e) {
        ItemErrorResponse response = new ItemErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
