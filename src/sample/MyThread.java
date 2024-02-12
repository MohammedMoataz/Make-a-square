package sample;

import java.util.ArrayList;

public class MyThread implements Runnable {
    private final boolean[][] board;
    private final ArrayList<boolean[][]> shapes;
    private final ArrayList<Shape> solutions;

    public MyThread(boolean[][] board, ArrayList<boolean[][]> shapes) {
        this.board = board.clone();
        this.shapes = shapes;
        this.solutions = new ArrayList<>();
    }

    @Override
    public void run() {
        this.solve(0);
    }

    private boolean solve(int pos) {
        boolean flag = true;
        if (pos < shapes.size()) {
            boolean[][] shape = this.shapes.get(pos);
            for (int row = 0; row <= (this.board.length - shape.length); row++) {
                for (int col = 0; col <= (this.board[0].length - shape[0].length); col++) {
                    Shape myShape = new Shape(shape, row, col);
                    flag = true;

                    for (int i = 0; i < myShape.getMatrix().length; i++) {
                        for (int j = 0; j < myShape.getMatrix()[0].length; j++) {
                            if (myShape.getMatrix()[i][j] && this.board[i + myShape.getRow()][j + myShape.getCol()]) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag)
                            break;
                    }

                    if (flag) {
                        for (int i = 0; i < myShape.getMatrix().length; i++)
                            for (int j = 0; j < myShape.getMatrix()[0].length; j++)
                                if (myShape.getMatrix()[i][j])
                                    this.board[i + myShape.getRow()][j + myShape.getCol()] = true;

                        flag = this.solve(pos + 1);
                        try {
                            shape = this.rotate90Deg(shape);
                            Thread t1 = new Thread(new MyThread(board, shapes));
                            shape = this.rotate90Deg(shape);
                            Thread t2 = new Thread(new MyThread(board, shapes));
                            t1.start();
                            t2.start();
                            t1.join();
                            t2.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (!flag) {
                            for (int i = 0; i < myShape.getMatrix().length; i++)
                                for (int j = 0; j < myShape.getMatrix()[0].length; j++)
                                    if (myShape.getMatrix()[i][j])
                                        this.board[i + myShape.getRow()][j + myShape.getCol()] = false;
                        }
                    }

                    if (flag) {
                        this.solutions.add(myShape);
                        System.out.println();
                        this.printSolution();
                        break;
                    }
                }
                if (flag)
                    break;
            }
        }
        return flag;
    }

    private void printSolution() {
        for (boolean[] shape : this.board) {
            for (boolean cell : shape) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Shape> getShapes() {
        return this.solutions;
    }

    private boolean[][] rotate90Deg(boolean[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] rotated = new boolean[col][row];

        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                rotated[col - j - 1][i] = matrix[i][j];
            }
        }
        return rotated;
    }
}
