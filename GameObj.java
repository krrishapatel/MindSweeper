package org.cis1200.minesweeper;

import java.awt.*;

/**
 * An object in the Minesweeper game.
 *
 * Game objects exist in the game court and have a position, size,
 * and bounds. Their position should always be within their defined bounds.
 */
public abstract class GameObj {
    /*
     * Current position of the object (in terms of grid coordinates)
     * Coordinates are given by the top-left corner of the object.
     */
    private int px;
    private int py;

    /* Size of the object, in pixels. */
    private final int width;
    private final int height;

    /*
     * Upper bounds of the area in which the object can be positioned.
     * Maximum permissible x, y positions for the top-left corner of the object.
     */
    private final int maxX;
    private final int maxY;

    /**
     * Constructor
     */
    public GameObj(int px, int py, int width, int height, int courtWidth, int courtHeight) {
        this.px = px;
        this.py = py;
        this.width = width;
        this.height = height;

        // Define the maximum bounds for the object
        this.maxX = courtWidth - width;
        this.maxY = courtHeight - height;
    }

    // **********************************************************************************
    // * GETTERS
    // **********************************************************************************
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    // **************************************************************************
    // * SETTERS
    // **************************************************************************
    public void setPx(int px) {
        this.px = Math.min(Math.max(px, 0), this.maxX);
    }

    public void setPy(int py) {
        this.py = Math.min(Math.max(py, 0), this.maxY);
    }

    // **************************************************************************
    // * METHODS
    // **************************************************************************
    /**
     * Determines whether this game object overlaps another game object.
     *
     * @param that The other game object
     * @return True if the objects overlap, false otherwise.
     */
    public boolean intersects(GameObj that) {
        return (this.px < that.px + that.width
                && this.px + this.width > that.px
                && this.py < that.py + that.height
                && this.py + this.height > that.py);
    }

    /**
     * Default draw method that provides how the object should be drawn in the GUI.
     * Subclasses should override this method based on how their object should appear.
     *
     * @param g The <code>Graphics</code> context used for drawing the object.
     */
    public abstract void draw(Graphics g);
}