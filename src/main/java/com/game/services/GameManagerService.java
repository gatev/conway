package com.game.services;

import com.game.model.CellCoordinates;
import com.game.model.InitialConfig;
import com.game.model.Universe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameManagerService {
    void initGame(InitialConfig initialConfig);
    void startMutations (int numberOfGenerations, Universe universe);
    List<CellCoordinates> getLiveCells();
}
