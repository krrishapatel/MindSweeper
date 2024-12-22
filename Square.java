package org.cis1200.minesweeper;

import java.awt.*;

/**
 * Represents a Minesweeper grid cell.
 * It can be revealed, flagged, or contain a bomb.
 */
public class Square extends GameObj {
    public static final int CELL_SIZE = 30;

    private boolean isBomb;         // True if this cell contains a bomb
    private boolean isRevealed;     // True if this cell has been revealed
    private boolean isFlagged;      // True if this cell is flagged
    private int adjacentBombs;      // Number of bombs in adjacent cells

    /**
     * Constructs a Minesweeper cell.
     *
     * @param gridX    The cell's column index in the grid.
     * @param gridY    The cell's row index in the grid.
     * @param courtWidth The width of the game court in pixels.
     * @param courtHeight The height of the game court in pixels.
     */
    public Square(int gridX, int gridY, int courtWidth, int courtHeight) {
        super(
                gridX * CELL_SIZE, // Pixel X position (column * cell size)
                gridY * CELL_SIZE, // Pixel Y position (row * cell size)
                CELL_SIZE,         // Cell width
                CELL_SIZE,         // Cell height
                courtWidth,        // Game court width
                courtHeight        // Game court height
        );

        this.isBomb = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.adjacentBombs = 0;
    }

    // Getters and Setters
    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        this.isBomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        this.isRevealed = true;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void toggleFlag() {
        this.isFlagged = !this.isFlagged;
    }

    public int getAdjacentBombs() {
        return adjacentBombs;
    }

    public void setAdjacentBombs(int adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }

    /**
     * Draws the Minesweeper cell based on its current state.
     *
     * @param g The graphics context used for rendering.
     */
    @Override
    public void draw(Graphics g) {
        // Draw the cell background
        if (isRevealed) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(getPx(), getPy(), getWidth(), getHeight());

            if (isBomb) {
                // Draw bomb representation
                g.setColor(Color.RED);
                g.fillOval(getPx() + 5, getPy() + 5, getWidth() - 10, getHeight() - 10);
            } else if (adjacentBombs > 0) {
                // Draw adjacent bomb count
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(adjacentBombs),
                        getPx() + getWidth() / 2 - 5, getPy() + getHeight() / 2 + 5);
            }
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(getPx(), getPy(), getWidth(), getHeight());

            if (isFlagged) {
                // Draw flag marker
                g.setColor(Color.YELLOW);
                g.drawString("F", getPx() + getWidth() / 2 - 5, getPy() + getHeight() / 2 + 5);
            }
        }

        // Draw the cell border
        g.setColor(Color.BLACK);
        g.drawRect(getPx(), getPy(), getWidth(), getHeight());
    }
}