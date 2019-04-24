package com.hse.java.xo;

import org.jetbrains.annotations.NotNull;

public class Logic {

    @NotNull private TicTacToe.Viewer viewer;
    private XO[][] board = {{XO.EMPTY, XO.EMPTY, XO.EMPTY},
                            {XO.EMPTY, XO.EMPTY, XO.EMPTY},
                            {XO.EMPTY, XO.EMPTY, XO.EMPTY}};
    private XO turn = XO.X;

    public Logic(@NotNull TicTacToe.Viewer viewer) {
        this.viewer = viewer;
    }

    public XO pushedButton(int x, int y) throws  IllegalArgumentException {
        if (x > 2 || x < 0 || y > 2 || y < 0) {
            throw new IllegalArgumentException("Position is out of board!!!");
        }


    }

    private void changeTurn() {
        if (turn == XO.X) {
            turn = XO.O;
        } else {
            turn = XO.X;
        }
    }
}
