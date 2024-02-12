package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Solve {
    @FXML
    private GridPane gridPane;

    private ArrayList<Color> colors;

    private int counter = 0;

    @FXML
    private void initialize() {
        colors = new ArrayList<>();
        colors.add(Color.rgb(0, 150, 250));
        colors.add(Color.rgb(150, 0, 250));
        colors.add(Color.rgb(250, 150, 0));
        colors.add(Color.rgb(0, 250, 150));
        colors.add(Color.rgb(250, 0, 150));
    }

    public void addShapes(ArrayList<Shape> shapes) {
        for (Shape shape : shapes) {
            for (int i = 0; i < shape.getMatrix().length; i++) {
                for (int j = 0; j < shape.getMatrix()[0].length; j++) {
                    if (shape.getMatrix()[i][j]) {
                        Rectangle rectangle = new Rectangle();
                        rectangle.setHeight(50);
                        rectangle.setWidth(50);
                        rectangle.setFill(this.colors.get(counter));
                        this.gridPane.add(rectangle, j + shape.getCol(), i + shape.getRow());
                    }
                }
            }
            counter++;
        }
    }

//    public void change(ActionEvent event) {
//    }
}
