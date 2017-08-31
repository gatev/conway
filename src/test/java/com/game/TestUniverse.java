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

		assertEquals(2, universe.getAliveNeighbours(0, 0));
		assertEquals(3, universe.getAliveNeighbours(0, 1));
		assertEquals(2, universe.getAliveNeighbours(0, 2));
		assertEquals(1, universe.getAliveNeighbours(1, 0));
		assertEquals(2, universe.getAliveNeighbours(1, 1));
	}

	@Test
	public void testCellDie() {
		Universe universe = new Universe(cells);
		List<CellCoordinates> listToChangeState = new ArrayList<>();

		assertEquals(true, universe.isAlive(1,0));
		assertEquals(true, universe.isAlive(1,2));

		listToChangeState.add(new CellCoordinates(1, 0));
		listToChangeState.add(new CellCoordinates(1, 2));

		universe.changeState(listToChangeState);

		assertEquals(false, universe.isAlive(1,0));
		assertEquals(false, universe.isAlive(1,2));
	}

	@Test
	public void testCellAlive() {
		Universe universe = new Universe(cells);
		List<CellCoordinates> listToChangeState = new ArrayList<>();

		assertEquals(false, universe.isAlive(0,1));
		assertEquals(false, universe.isAlive(2,1));

		listToChangeState.add(new CellCoordinates(0, 1));
		listToChangeState.add(new CellCoordinates(2, 1));

		universe.changeState(listToChangeState);

		assertEquals(true, universe.isAlive(0,1));
		assertEquals(true, universe.isAlive(2,1));
	}

	@Test
	public void testUniverseStayTheSame() {
		Cell[][] cells = { { new Cell(CellState.D), new Cell(CellState.D), new Cell(CellState.D) },
				{ new Cell(CellState.A), new Cell(CellState.A), new Cell(CellState.D) },
				{ new Cell(CellState.A), new Cell(CellState.D), new Cell(CellState.D) } };

		Universe universe = new Universe(cells);

		List<CellCoordinates> listToChangeState = new ArrayList<>();

		universe.changeState(listToChangeState);

		assertEquals(cells, universe.getGrid());
	}


}
