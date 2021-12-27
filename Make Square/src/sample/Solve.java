package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Solve {
    @FXML
    private GridPane gridPane;

    private ArrayList<Color> colors;

    private static int counter = 0;

    @FXML
    private void initialize() {
        colors = new ArrayList<>();
        colors.add(Color.rgb(10, 150, 230));
        colors.add(Color.rgb(50, 230, 130));
        colors.add(Color.rgb(150, 100, 200));
        colors.add(Color.rgb(210, 200, 20));
    }

    public void addShape(boolean[][] shape, int row, int col) {
        for(int i = 0; i < shape.length; i++) {
            for(int j = 0; j < shape[0].length; j++) {
                if(shape[i][j]) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setHeight(50);
                    rectangle.setWidth(50);
                    rectangle.setFill(this.colors.get(counter));
                    this.gridPane.add(rectangle, j + col, i + row);
                }
            }
        }
        counter++;
    }

    public void change(ActionEvent event) {
    }
}
