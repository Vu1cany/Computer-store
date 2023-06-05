package ru.computerstore.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.computerstore.dto.ComputerDTO;
import ru.computerstore.models.Computer;
import ru.computerstore.services.ComputerService;
import ru.computerstore.util.ItemErrorResponse;
import ru.computerstore.util.ItemNotSavedException;

import java.util.List;

@RestController
@RequestMapping("/items/computers")
public class ComputerController{
    private final ComputerService computerService;
    private final ModelMapper modelMapper;

    @Autowired
    public ComputerController(ComputerService computerService, ModelMapper modelMapper) {
        this.computerService = computerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<ComputerDTO> index(){
        return computerService.findAll().stream().map(this::convertToComputerDTO).toList();
    }

    @GetMapping("/{id}")
    public ComputerDTO show(@PathVariable("id")int id){
        return convertToComputerDTO(computerService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ComputerDTO computerDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        computerService.save(convertToComputer(computerDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id")int id,
                                             @RequestBody @Valid ComputerDTO computerDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        computerService.update(id, convertToComputer(computerDTO));
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

    private Computer convertToComputer(ComputerDTO computerDTO){
        return modelMapper.map(computerDTO, Computer.class);
    }

    private ComputerDTO convertToComputerDTO(Computer computer){
        return modelMapper.map(computer, ComputerDTO.class);
    }
}
