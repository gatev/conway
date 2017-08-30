package com.game.controllers;

import com.game.model.InitialConfig;
import com.game.services.GameManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class InitController {

    @Autowired
    GameManagerService gameManager;

    @PostMapping(path = "/init")
    public ResponseEntity<Map<String, String>> init(@RequestBody @Valid InitialConfig initialConfig, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String > errors = new TreeMap<>();
            bindingResult.getFieldErrors().stream().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
        }

        gameManager.initGame(initialConfig);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
