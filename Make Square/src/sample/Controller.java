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

    public static ArrayList<boolean[][]> shapes;

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

    public void submit(ActionEvent event) throws IOException {
        this.assignShapes();
        this.solve();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("solve.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void initializeShapes() {
        shapes = new ArrayList<>();

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
                {true, true, true, true}
        };

        shapeH = new boolean[][]{
                {false, true},
                {false, true},
                {true, true},
                {false, true}
        };
    }

    private void assignShapes() {
        shapes = new ArrayList<>();

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

    private void solve() {
        
    }
}
