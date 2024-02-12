package sample;

public class Shape {
    private boolean[][] matrix;
    private int row,
            col;

    public Shape(boolean[][] matrix, int row, int col) {
        this.matrix = matrix;
        this.row = row;
        this.col = col;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
