import board.Board;
import cell.Cell;
import cell.Cell.CellState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    @Test
    void shouldCreateABoardInitiallyWhenConfigurationIsGiven() {
        Cell cell = new Cell(0, 0, CellState.DEAD);
        Cell[][] boardConfiguration = {{cell}};
        CellState expectedCellState = CellState.DEAD;

        Board board = new Board(boardConfiguration);
        CellState actualCellState = board.cellPresentState(0, 0);

        assertEquals(expectedCellState, actualCellState);
    }

    @Test
    void liveCellShouldBeDeadWhenThereAreLessThanTwoLiveNeighbours() {
        Cell cell = new Cell(0, 0, CellState.ALIVE);
        Cell[][] boardConfiguration = {{cell}};
        CellState expectedCellState = CellState.DEAD;
        Board board = new Board(boardConfiguration);

        board.nextGeneration();
        CellState actualCellState = board.cellFutureState(0, 0);

        assertEquals(expectedCellState, actualCellState);
    }

    @Test
    void liveCellShouldDieWhenThereAreMoreThanThreeLiveNeighbourCells() {
        Cell cell00 = new Cell(0, 0, CellState.ALIVE);
        Cell cell01 = new Cell(0, 1, CellState.ALIVE);
        Cell cell02 = new Cell(0, 2, CellState.ALIVE);
        Cell cell10 = new Cell(1, 0, CellState.ALIVE);
        Cell cell11 = new Cell(1, 1, CellState.ALIVE);
        Cell cell12 = new Cell(1, 2, CellState.ALIVE);
        Cell[][] boardConfiguration = {{cell00, cell01, cell02}, {cell10, cell11, cell12}};
        Board board = new Board(boardConfiguration);
        CellState expectedCellState = CellState.DEAD;

        board.nextGeneration();
        CellState actualCellState = board.cellFutureState(1, 1);

        assertEquals(expectedCellState, actualCellState);
    }

    @Test
    void liveCellShouldLiveWhenThereAreExactlyTwoLiveNeighbourCells() {
        Cell cell00 = new Cell(0, 0, CellState.ALIVE);
        Cell cell01 = new Cell(0, 1, CellState.ALIVE);
        Cell cell10 = new Cell(1, 0, CellState.ALIVE);
        Cell cell11 = new Cell(1, 1, CellState.DEAD);
        Cell[][] boardConfiguration = {{cell00, cell01}, {cell10, cell11}};
        Board board = new Board(boardConfiguration);
        CellState expectedCellState = CellState.ALIVE;

        board.nextGeneration();
        CellState actualCellState = board.cellFutureState(0, 0);

        assertEquals(actualCellState, expectedCellState);
    }

    @Test
    void liveCellShouldLiveWhenThereAreExactlyThreeLiveNeighbourCells() {
        Cell cell00 = new Cell(0, 0, CellState.ALIVE);
        Cell cell01 = new Cell(0, 1, CellState.ALIVE);
        Cell cell10 = new Cell(1, 0, CellState.ALIVE);
        Cell cell11 = new Cell(1, 1, CellState.ALIVE);
        Cell[][] boardConfiguration = {{cell00, cell01}, {cell10, cell11}};
        Board board = new Board(boardConfiguration);
        CellState expectedCellState = CellState.ALIVE;

        board.nextGeneration();
        CellState actualCellState = board.cellFutureState(0, 0);

        assertEquals(actualCellState, expectedCellState);
    }

    @Test
    void deadCellShouldBecomeAliveWhenThereAreExactlyThreeLiveNeighbourCells() {
        Cell cell00 = new Cell(0, 0, CellState.ALIVE);
        Cell cell01 = new Cell(0, 1, CellState.ALIVE);
        Cell cell10 = new Cell(1, 0, CellState.ALIVE);
        Cell cell11 = new Cell(1, 1, CellState.DEAD);
        Cell[][] boardConfiguration = {{cell00, cell01}, {cell10, cell11}};
        Board board = new Board(boardConfiguration);
        CellState expectedCellState = CellState.ALIVE;

        board.nextGeneration();
        CellState actualCellState = board.cellFutureState(1, 1);

        assertEquals(actualCellState, expectedCellState);
    }
}
