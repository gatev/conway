package com.game.services;

import com.game.model.InitialConfig;
import com.game.model.Universe;
import org.springframework.stereotype.Service;

@Service
public interface GameManagerService {
    void initGame(InitialConfig initialConfig);
    void startMutations (int numberOfGenerations, Universe universe);
}
