package com.game.services;

import com.game.model.CellCoordinates;
import com.game.model.InitialConfig;
import com.game.model.Universe;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface GameManagerService {
	List<CellCoordinates> playGame(InitialConfig initialConfig);
	List<CellCoordinates> startMutations (int numberOfGenerations, Universe universe);
}
