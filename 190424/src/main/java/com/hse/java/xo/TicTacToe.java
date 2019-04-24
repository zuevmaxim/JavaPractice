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

    final int N = 3;
    Button[][] buttons = new Button[N][N];

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        Label labelTitle = new Label("Tic-Tac-Toe Game");

        // Put on cell (0,0), span 2 column, 1 row.
        root.add(labelTitle, 0, 0, 2, 1);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                buttons[i][j] = new Button(".");
                buttons[i][j].setOnAction(result -> {

                });
            }
        }


        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("GridPanel Layout Demo (o7planning.org)");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public class Viewer {

        public void showResult(Result result) {

        }
    }
}