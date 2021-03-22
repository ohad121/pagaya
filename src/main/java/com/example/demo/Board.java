package com.example.demo;

public interface Board<Block> {
    void display();
    boolean isFullOfTheSameColor();
    void colorInsert(Block block);
}
