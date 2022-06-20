package cell;

public class Cell {
    int row, column;
    CellState cellState;

    public enum CellState {
        ALIVE, DEAD
    }

    public Cell(int row, int column, CellState cellState) {
        this.row = row;
        this.cellState = cellState;
        this.column = column;
    }

    public CellState getCellState() {
        return this.cellState;
    }
}