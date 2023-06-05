package ru.computerstore.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.computerstore.dto.HddDTO;
import ru.computerstore.models.Hdd;
import ru.computerstore.services.HddService;
import ru.computerstore.util.ItemErrorResponse;
import ru.computerstore.util.ItemNotSavedException;

import java.util.List;

@RestController
@RequestMapping("items/hdds")
public class HddController {
    private final HddService hddService;
    private final ModelMapper modelMapper;

    @Autowired
    public HddController(HddService hddService, ModelMapper modelMapper) {
        this.hddService = hddService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<HddDTO> index(){
        return hddService.findAll().stream().map(this::convertToHddDTO).toList();
    }

    @GetMapping("/{id}")
    public HddDTO show(@PathVariable("id")int id){
        return convertToHddDTO(hddService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid HddDTO hddDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        hddService.save(convertToHdd(hddDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id")int id,
                                             @RequestBody @Valid HddDTO hddDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        hddService.update(id, convertToHdd(hddDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void findValidErrors(BindingResult bindingResult){
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

    private Hdd convertToHdd(HddDTO hddDTO){
        return modelMapper.map(hddDTO, Hdd.class);
    }

    private HddDTO convertToHddDTO(Hdd hdd){
        return modelMapper.map(hdd, HddDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ItemErrorResponse> handlerException(RuntimeException e){
        ItemErrorResponse response = new ItemErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
