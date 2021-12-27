package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private boolean[][] solution;

    private ArrayList<boolean[][]> shapes;

    private Tree<boolean[][]> tree;

    @FXML
    private void initialize() {
        this.initializeShapes();

        this.countA.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countB.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countC.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countD.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countE.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countF.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countG.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4));
        this.countH.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3));
    }

    private void initializeShapes() {
        shapes = new ArrayList<>();

        solution = new boolean[][]{
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
    }

    public void submit(ActionEvent event) throws IOException {
        this.assignShapes();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("solve.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        Solve solve = loader.getController();
        this.solve(solve);

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

    private void solve(Solve solve) {
        boolean flag = false;
        for (boolean[][] shape : this.shapes) {
            for (int row = 0; row <= (this.solution.length - shape.length); row++) {
                for (int col = 0; col <= (this.solution[0].length - shape[0].length); col++) {
                    flag = this.addShape(shape, row, col);

                    if (flag) {
                        solve.addShape(shape, row, col);
                        break;
                    }
                }
                if (flag)
                    break;
            }
        }

        this.printSolution();
        System.out.println();
    }

    public boolean addShape(boolean[][] shape, int row, int col) {
        boolean flag = true;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] && this.solution[i + row][j + col]) {
                    flag = false;
                    break;
                }
            }
        }

        if (flag)
            for (int i = 0; i < shape.length; i++)
                for (int j = 0; j < shape[0].length; j++)
                    if (shape[i][j])
                        this.solution[i + row][j + col] = true;

        return flag;
    }

    private void printSolution() {
        for (boolean[] shape : this.solution) {
            for (boolean cell : shape) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private void rotateShape() {
        ArrayList<boolean[][]> newShapes = new ArrayList<>();

        for (boolean[][] shape : this.shapes) {
            for (boolean[] booleans : shape) {
                for (boolean aBoolean : booleans) {
                    System.out.print(aBoolean + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println();
        for (boolean[][] shape : this.shapes) {
            int row = shape.length,
                    col = shape[0].length;
            boolean[][] newShape = new boolean[col][row];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    newShape[j][i] = shape[i][j];
                }
            }
            newShapes.add(newShape);
        }
        System.out.println();

        for (boolean[][] shape : newShapes) {
            for (boolean[] booleans : shape) {
                for (boolean aBoolean : booleans) {
                    System.out.print(aBoolean + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
