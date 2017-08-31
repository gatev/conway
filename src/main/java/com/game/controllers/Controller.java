package com.game.controllers;

import com.game.model.CellCoordinates;
import com.game.model.InitialConfig;
import com.game.services.GameManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class Controller {

    @Autowired
    GameManagerService gameManager;

    @PostMapping(path = "/play")
    public ResponseEntity<?> init(@RequestBody @Valid InitialConfig initialConfig, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String > errors = new TreeMap<>();
            bindingResult.getFieldErrors().stream().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        List<CellCoordinates> liveCells = gameManager.playGame(initialConfig);

        return new ResponseEntity<>(liveCells, HttpStatus.OK);
    }
}
