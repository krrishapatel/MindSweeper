package org.cis1200.minesweeper;

import java.awt.*;

public class Cell {
    private final int x;
    private final int y;
    private final int size;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentBombs;

    public Cell(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.isBomb = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.adjacentBombs = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        isRevealed = true;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void toggleFlag() {
        isFlagged = !isFlagged;
    }

    public int getAdjacentBombs() {
        return adjacentBombs;
    }

    public void setAdjacentBombs(int adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }

    public void draw(Graphics g) {
        if (isRevealed) {
            g.setColor(isBomb ? Color.RED : Color.WHITE);
            g.fillRect(x * size, y * size, size, size);

            if (!isBomb && adjacentBombs > 0) {
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(adjacentBombs),
                        x * size + size / 2 - 5, y * size + size / 2 + 5);
            }
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x * size, y * size, size, size);

            if (isFlagged) {
                g.setColor(Color.YELLOW);
                g.drawString("F", x * size + size / 2 - 5, y * size + size / 2 + 5);
            }
        }

        g.setColor(Color.BLACK);
        g.drawRect(x * size, y * size, size, size);
    }
}