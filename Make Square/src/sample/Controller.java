package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Spinner<Integer> countA,
            countB,
            countC,
            countD,
            countE,
            countF,
            countG,
            countH;

    private boolean[][] shapeA,
            shapeB,
            shapeC,
            shapeD,
            shapeE,
            shapeF,
            shapeG,
            shapeH;

    private boolean[][] board;

    private ArrayList<boolean[][]> shapes;

    @FXML
    private void initialize() {
        shapes = new ArrayList<>();

        board = new boolean[][]{
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };

        shapeA = new boolean[][]{
                {true, false, false},
                {true, true, true}
        };

        shapeB = new boolean[][]{
                {false, false, true},
                {true, true, true}
        };

        shapeC = new boolean[][]{
                {false, true, true},
                {true, true, false}
        };

        shapeD = new boolean[][]{
                {true, true, false},
                {false, true, true}
        };

        shapeE = new boolean[][]{
                {false, true, false},
                {true, true, true}
        };

        shapeF = new boolean[][]{
                {true, true},
                {true, true}
        };

        shapeG = new boolean[][]{
                {true},
                {true},
                {true},
                {true}
        };

        shapeH = new boolean[][]{
                {false, true},
                {false, true},
                {true, true},
                {false, true}
        };

        this.countA.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countB.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countC.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countD.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countE.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countF.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countG.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countH.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2));
    }

    public void submit(ActionEvent event) throws IOException {
        this.assignShapes();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("solve.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        Solve solve = loader.getController();
        boolean sol = this.solve(solve, 0);

        if(!sol) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("No solution");
            alert.setHeaderText("No solution found");
            alert.setContentText("Try again!");
            alert.show();
        }

        stage.show();
    }

    private void assignShapes() {
        int count = countA.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeA);
        }

        count = countB.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeB);
        }

        count = countC.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeC);
        }

        count = countD.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeD);
        }

        count = countE.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeE);
        }

        count = countF.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeF);
        }

        count = countG.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeG);
        }

        count = countH.getValue();
        while (count-- > 0) {
            shapes.add(this.shapeH);
        }
    }

    private boolean solve(Solve solve, int pos) {
        boolean flag = true;
        if (pos < shapes.size()) {
            boolean[][] shape = this.shapes.get(pos);
            if(pos > 1)
                shape = this.rotate90Deg(shape);

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

                        flag = this.solve(solve, pos + 1);

                        if (!flag) {
                            for (int i = 0; i < myShape.getMatrix().length; i++)
                                for (int j = 0; j < myShape.getMatrix()[0].length; j++)
                                    if (myShape.getMatrix()[i][j])
                                        this.board[i + myShape.getRow()][j + myShape.getCol()] = false;
                        }
                    }

                    if (flag) {
                        solve.addShapes(myShape);
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
