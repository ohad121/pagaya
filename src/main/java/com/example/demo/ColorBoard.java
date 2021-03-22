package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ColorBoard implements Board<ColorBlock> {
    private static final int BOARD_SIZE = 18;
    private List<List<ColorBlock>> board;

    public ColorBoard() {
        board = new ArrayList<>();
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<ColorBlock> newRow = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                newRow.add(ColorBlock.random());
            }

            board.add(newRow);
        }
    }

    @Override
    public void display() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.printf("%s", board.get(i).get(j));
            }
            System.out.println("");
            System.out.print("\u001B[0m");

        }
    }

    @Override
    public boolean isFullOfTheSameColor() {
        ColorBlock firstColorBlock = board.get(0).get(0);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!firstColorBlock.equals(board.get(i).get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void colorInsert(ColorBlock newColorBlock) {
        ColorBlock firstColorBlock = board.get(0).get(0);
        if (firstColorBlock.equals(newColorBlock)) { // If the new color is the same as the first color, nothing to do.
            return;
        }

        changeColor(firstColorBlock, newColorBlock, 0, 0);
    }

    private void changeColor(ColorBlock firstColorBlock, ColorBlock newColorBlock, int row, int col) {
        if (row >= BOARD_SIZE || col >= BOARD_SIZE || row < 0 || col < 0) {
            return;
        }

        ColorBlock currentColorBlock = board.get(row).get(col);
        if (currentColorBlock.equals(firstColorBlock)) {
            board.get(row).set(col, newColorBlock);
            changeColor(firstColorBlock, newColorBlock, row - 1, col);
            changeColor(firstColorBlock, newColorBlock, row + 1, col);
            changeColor(firstColorBlock, newColorBlock, row, col + 1);
            changeColor(firstColorBlock, newColorBlock, row, col - 1);
        }
    }
}
