package com.hse.java.xo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    private Logic logic;

    @BeforeEach
    void initialize() {
        logic = new Logic();
    }

    @Test
    void winTopHorizontalLine_CorrectState() {
        logic.pushedButton(0,0);
        logic.pushedButton(1,0);
        logic.pushedButton(0,1);
        logic.pushedButton(1, 1);

        assertEquals(XO.X, logic.pushedButton(0, 2));
    }

    @Test
    void winMiddleHorizontalLine_CorrectState() {
        logic.pushedButton(1,0);
        logic.pushedButton(0,0);
        logic.pushedButton(1,1);
        logic.pushedButton(0, 1);

        assertEquals(XO.X, logic.pushedButton(1, 2));
    }

    @Test
    void winBottomHorizontalLine_CorrectState() {
        logic.pushedButton(2,0);
        logic.pushedButton(1,0);
        logic.pushedButton(2,1);
        logic.pushedButton(1, 1);

        assertEquals(XO.X, logic.pushedButton(2, 2));
    }

    @Test
    void winTopVerticalLine_CorrectState() {
        logic.pushedButton(0,0);
        logic.pushedButton(0,1);
        logic.pushedButton(1,0);
        logic.pushedButton(1, 1);

        assertEquals(XO.X, logic.pushedButton(2, 0));
    }

    @Test
    void winMiddleVerticalLine_CorrectState() {
        logic.pushedButton(0,1);
        logic.pushedButton(0,0);
        logic.pushedButton(1,1);
        logic.pushedButton(1, 0);

        assertEquals(XO.X, logic.pushedButton(2, 1));
    }

    @Test
    void winBottomVerticalLine_CorrectState() {
        logic.pushedButton(0,2);
        logic.pushedButton(0,1);
        logic.pushedButton(1,2);
        logic.pushedButton(1, 1);

        assertEquals(XO.X, logic.pushedButton(2, 2));
    }

    @Test
    void winRightDiagonalLine_CorrectState() {
        logic.pushedButton(0,0);
        logic.pushedButton(1, 0);
        logic.pushedButton(1,1);
        logic.pushedButton(2,1);

        assertEquals(XO.X, logic.pushedButton(2,2));
    }

    @Test
    void winLeftDiagonalLine_CorrectState() {
        logic.pushedButton(0,2);
        logic.pushedButton(1, 0);
        logic.pushedButton(1,1);
        logic.pushedButton(2,1);

        assertEquals(XO.X, logic.pushedButton(2,0));
    }

    @Test
    void draw_CorrectState() {
        logic.pushedButton(0,0);
        logic.pushedButton(0,1);
        logic.pushedButton(0,2);
        logic.pushedButton(1,2);
        logic.pushedButton(1,1);
        logic.pushedButton(2,0);
        logic.pushedButton(1,0);
        logic.pushedButton(2,2);

        assertEquals(XO.DRAW, logic.pushedButton(2,1));
    }
}