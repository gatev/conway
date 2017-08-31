package com.game;

import static org.junit.Assert.assertEquals;

import com.game.model.CellCoordinates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.game.model.Cell;
import com.game.model.CellState;
import com.game.model.Universe;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUniverse {
	Cell[][] cells = { { new Cell(CellState.D), new Cell(CellState.D), new Cell(CellState.D) },
			{ new Cell(CellState.A), new Cell(CellState.A), new Cell(CellState.A) },
			{ new Cell(CellState.D), new Cell(CellState.D), new Cell(CellState.D) } };

	@Test
	public void testFindNeighbours() {

		Universe universe = new Universe(cells);

		assertEquals(2, universe.getAliveNeighbours(0, 0, cells));
		assertEquals(3, universe.getAliveNeighbours(0, 1, cells));
		assertEquals(2, universe.getAliveNeighbours(0, 2, cells));
		assertEquals(1, universe.getAliveNeighbours(1, 0, cells));
		assertEquals(2, universe.getAliveNeighbours(1, 1, cells));
	}

	@Test
	public void testCellDie() {
		Universe universe = new Universe(cells);
		List<CellCoordinates> listToChangeState = new ArrayList<>();

		assertEquals(true, universe.isAlive(1,0, cells));
		assertEquals(true, universe.isAlive(1,2, cells));

		listToChangeState.add(new CellCoordinates(1, 0));
		listToChangeState.add(new CellCoordinates(1, 2));

		universe.changeState(listToChangeState, cells);

		assertEquals(false, universe.isAlive(1,0, cells));
		assertEquals(false, universe.isAlive(1,2, cells));
	}

	@Test
	public void testCellAlive() {
		Universe universe = new Universe(cells);
		List<CellCoordinates> listToChangeState = new ArrayList<>();

		assertEquals(false, universe.isAlive(0,1, cells));
		assertEquals(false, universe.isAlive(2,1, cells));

		listToChangeState.add(new CellCoordinates(0, 1));
		listToChangeState.add(new CellCoordinates(2, 1));

		universe.changeState(listToChangeState, cells);

		assertEquals(true, universe.isAlive(0,1, cells));
		assertEquals(true, universe.isAlive(2,1, cells));
	}

//	@Test
//	public void testUniverseStayTheSame() {
//		Cell[][] cells = { { new Cell(CellState.D), new Cell(CellState.D), new Cell(CellState.D) },
//				{ new Cell(CellState.A), new Cell(CellState.A), new Cell(CellState.D) },
//				{ new Cell(CellState.A), new Cell(CellState.D), new Cell(CellState.D) } };
//
//		Universe universe = new Universe(cells);
//
//		List<CellCoordinates> listToChangeState = new ArrayList<>();
//
//		universe.changeState(listToChangeState, cells);
//
//		assertEquals(cells, universe.getGrid());
//	}


}
