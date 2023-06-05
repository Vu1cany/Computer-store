package ru.computerstore.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.computerstore.dto.LaptopDTO;
import ru.computerstore.models.Laptop;
import ru.computerstore.services.LaptopService;
import ru.computerstore.util.ItemErrorResponse;
import ru.computerstore.util.ItemNotSavedException;

import java.util.List;


@RestController
@RequestMapping("items/laptops")
public class LaptopController {
    private final LaptopService laptopService;
    private final ModelMapper modelMapper;

    @Autowired
    public LaptopController(LaptopService laptopService, ModelMapper modelMapper) {
        this.laptopService = laptopService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<LaptopDTO> index(){
        return laptopService.findAll().stream().map(this::convertToLaptopDTO).toList();
    }

    @GetMapping("/{id}")
    public LaptopDTO show(@PathVariable("id")int id){
        return convertToLaptopDTO(laptopService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid LaptopDTO laptopDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        laptopService.save(converToLaptop(laptopDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id")int id,
                                             @RequestBody @Valid LaptopDTO laptopDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        laptopService.update(id, converToLaptop(laptopDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private static ResponseEntity<ItemErrorResponse> handlerException(RuntimeException e){
        ItemErrorResponse response = new ItemErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private void findValidErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder();

            for (FieldError fieldError : fieldErrors) {
                errorMessage.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }

            throw new ItemNotSavedException(errorMessage.toString());
        }
    }

    private Laptop converToLaptop(LaptopDTO laptopDTO){
        return modelMapper.map(laptopDTO, Laptop.class);
    }

    private LaptopDTO convertToLaptopDTO(Laptop laptop){
        return modelMapper.map(laptop, LaptopDTO.class);
    }
}
