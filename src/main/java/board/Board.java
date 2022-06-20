package board;

import cell.Cell;
import cell.Cell.CellState;

public class Board {
    private final Cell[][] board;
    private final Cell[][] nextGenerationBoard;

    public Board(Cell[][] board) {
        this.board = board;
        nextGenerationBoard = board;
    }

    public void nextGeneration() {
        for (int rowIterator = 0; rowIterator < board.length; rowIterator++) {
            for (int columnIterator = 0; columnIterator < board[rowIterator].length; columnIterator++) {
                int liveNeighboursCount = numberOfLiveNeighbours(rowIterator, columnIterator);
                CellState presentCellState = board[rowIterator][columnIterator].getCellState();
                CellState futureCellState;
                if (presentCellState == CellState.ALIVE) {
                    if (liveNeighboursCount < 2 || liveNeighboursCount > 3) {
                        futureCellState = CellState.DEAD;
                    } else {
                        futureCellState = CellState.ALIVE;
                    }
                } else if (presentCellState == CellState.DEAD && liveNeighboursCount == 3) {
                    futureCellState = CellState.ALIVE;
                } else {
                    futureCellState = presentCellState;
                }
                nextGenerationBoard[rowIterator][columnIterator] = new Cell(rowIterator, columnIterator, futureCellState);
            }
        }
    }

    private int numberOfLiveNeighbours(int cellRow, int cellColumn) {
        int aliveCells = 0;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (isNeighbour(row, column, cellRow, cellColumn) && (cellPresentState(row, column) == CellState.ALIVE)) {
                    aliveCells++;
                }
            }
        }
        return aliveCells;
    }

    public CellState cellPresentState(int row, int column) {
        return board[row][column].getCellState();
    }

    public CellState cellFutureState(int row, int column) {
        return nextGenerationBoard[row][column].getCellState();
    }

    private boolean isNeighbour(int row, int column, int cellRow, int cellColumn) {
        return isInNeighbourRow(row, cellRow) && isInNeighbourColumn(column, cellColumn) && !(row == cellRow && column == cellColumn);
    }

    private boolean isInNeighbourColumn(int column, int cellColumn) {
        return (column + 1 == cellColumn) || (column == cellColumn) || (column - 1 == cellColumn);
    }

    private boolean isInNeighbourRow(int row, int cellRow) {
        return (row + 1 == cellRow) || (row == cellRow) || (row - 1 == cellRow);
    }
}
