package ru.computerstore.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.computerstore.dto.MonitorDTO;
import ru.computerstore.models.Hdd;
import ru.computerstore.models.Monitor;
import ru.computerstore.services.MonitorService;
import ru.computerstore.util.ItemErrorResponse;
import ru.computerstore.util.ItemNotSavedException;

import java.util.List;


@RestController
@RequestMapping("items/monitors")
public class MonitorController {
    private final MonitorService monitorService;
    private final ModelMapper modelMapper;

    @Autowired
    public MonitorController(MonitorService monitorService, ModelMapper modelMapper) {
        this.monitorService = monitorService;
        this.modelMapper = modelMapper;
    }

    @ExceptionHandler
    private static ResponseEntity<ItemErrorResponse> handlerException(RuntimeException e){
        ItemErrorResponse response = new ItemErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public List<MonitorDTO> index(){
        return monitorService.findAll().stream().map(this::convertToMonitorDTO).toList();
    }

    @GetMapping("/{id}")
    public MonitorDTO show(@PathVariable("id")int id){
        return convertToMonitorDTO(monitorService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MonitorDTO monitorDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        monitorService.save(convertToMonitor(monitorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id")int id,
                                             @RequestBody @Valid MonitorDTO monitorDTO,
                                             BindingResult bindingResult){
        findValidErrors(bindingResult);

        monitorService.update(id, convertToMonitor(monitorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
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

    private Monitor convertToMonitor (MonitorDTO monitorDTO){
        return modelMapper.map(monitorDTO, Monitor.class);
    }

    private MonitorDTO convertToMonitorDTO (Monitor monitor){
        return modelMapper.map(monitor, MonitorDTO.class);
    }
}
