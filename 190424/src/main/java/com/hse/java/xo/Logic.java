package com.hse.java.xo;

public class Logic {

    private XO[][] board = {{XO.EMPTY, XO.EMPTY, XO.EMPTY},
                            {XO.EMPTY, XO.EMPTY, XO.EMPTY},
                            {XO.EMPTY, XO.EMPTY, XO.EMPTY}};
    private XO turn = XO.X;
    private int step = 0;

    public XO getSymb(int x, int y) {
        return board[x][y];
    }

    public XO pushedButton(int x, int y) {
        if (x > 2 || x < 0 || y > 2 || y < 0) {
            return XO.GAME;
        } else if (board[x][y] != XO.EMPTY) {
            return XO.GAME;
        }

        board[x][y] = turn;
        step++;
        if (isVictory(x, y)) {
            return turn;
        } else if (step == 9) {
            return XO.DRAW;
        }
        changeTurn();

        return XO.GAME;
    }

    private void changeTurn() {
        if (turn == XO.X) {
            turn = XO.O;
        } else {
            turn = XO.X;
        }
    }

    private boolean isVictory(int x, int y) {
        if (board[x][0] == turn && board[x][1] == turn && board[x][2] == turn) {
            return true;
        }

        if (board[0][y] == turn && board[1][y] == turn && board[2][y] == turn) {
            return true;
        }

        if (x == y) {
            if (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn) {
                return true;
            }
        }

        if (x + y == 2) {
            if (board[2][0] == turn && board[1][1] == turn && board[0][2] == turn) {
                return true;
            }
        }

        return false;
    }
}
