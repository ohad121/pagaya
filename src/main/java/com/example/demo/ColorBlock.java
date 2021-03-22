package com.example.demo;

public enum ColorBlock implements Block {
    RED("\u001B[31m", "r"),
    GREEN("\u001B[32m", "g"),
    BLUE("\u001B[34m", "b"),
    YELLOW("\u001B[33m", "y");

    private String color;
    private String colorCode;

    ColorBlock(String colorCode, String color) {
        this.color = color;
        this.colorCode = colorCode;
    }

    public static ColorBlock fromString(String userColorInput) {
        ColorBlock colorBlockToReturn = null;
        for (ColorBlock colorBlock : ColorBlock.values()) {
            if (colorBlock.color.equalsIgnoreCase(userColorInput)) {
                colorBlockToReturn = colorBlock;
            }
        }

        return colorBlockToReturn;
    }

    public static ColorBlock random() {
        int randomColorBlockIndex = (int) (Math.random() * (ColorBlock.values().length));
        return ColorBlock.values()[randomColorBlockIndex];
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", colorCode, color, colorCode);
    }
}
