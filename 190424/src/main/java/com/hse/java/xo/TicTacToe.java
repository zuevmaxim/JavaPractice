package com.hse.java.xo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;



public class TicTacToe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final int N = 3;
    private Button[][] buttons = new Button[N][N];
    private Viewer viewer = new Viewer();
    private Logic logic = new Logic(viewer);

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int ci = i;
                int cj = j;
                buttons[i][j] = new Button(".");
                buttons[i][j].setOnAction(result -> {
                    logic.pushedButton(ci, cj);
                });
                root.add(buttons[i][j], i, j);
            }
        }

        var column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        var column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
        var column3 = new ColumnConstraints();
        column3.setPercentWidth(30);
        var row1 = new RowConstraints();
        row1.setPercentHeight(30);
        var row2 = new RowConstraints();
        row2.setPercentHeight(30);
        var row3 = new RowConstraints();
        row3.setPercentHeight(30);
        //row1.setFillHeight(true);
        root.getColumnConstraints().addAll(column1, column2);
        root.getRowConstraints().addAll(row1, row2, row3);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public class Viewer {

        public void showResult(XO result) {

        }

        public void set(int x, int y, XO symbol) {
            String text = ".";
            switch (symbol) {
                case X : text = "X" ;
                break;
                case O : text = "O";
                break;
            }
            buttons[x][y].setText(text);
        }
    }
}