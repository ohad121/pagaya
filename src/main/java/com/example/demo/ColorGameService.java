package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

// Dev.interviews@pagaya.com

@Service
public class ColorGameService implements GameService {
    private static final int TURNS = 21;
    @Autowired
    private Board<ColorBlock> board;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void runGame() {
        board.display();
        for (int i = 1; i <= TURNS; i++) {
            ColorBlock colorBlockMove = askMove(i);
            while (colorBlockMove == null) {
                colorBlockMove = askMove(i);
            }

            board.colorInsert(colorBlockMove);
            board.display();
        }

        if (board.isFullOfTheSameColor()) {
            System.out.println("You won!");
        } else {
            System.out.println("Game over! You lost");
        }
    }

    private ColorBlock askMove(int turn) {
        System.out.println(String.format("Turn %s, please choose a color (r, g, y, b):", turn));
        String userColorInput = scanner.nextLine();
        return ColorBlock.fromString(userColorInput);
    }
}
