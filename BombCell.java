package org.cis1200.minesweeper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BombCell extends Cell {
    private static final String IMG_FILE = "files/bomb.png"; // Path to bomb image
    private static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File(IMG_FILE));
        } catch (IOException e) {
            System.out.println("Internal Error: " + e.getMessage());
        }
    }

    public BombCell(int x, int y, int size) {
        super(x, y, size);
        setBomb(true); // Mark cell as a bomb
    }

    @Override
    public void draw(Graphics g) {
        // Use getter methods to access x, y, and size
        if (isRevealed()) {
            if (img != null) {
                g.drawImage(img, getX() * getSize(),
                        getY() * getSize(), getSize(), getSize(), null);
            } else {
                g.setColor(Color.RED);
                g.fillOval(getX() * getSize() + 5,
                        getY() * getSize() + 5, getSize() - 10, getSize() - 10);
            }
        } else {
            super.draw(g); // Use the default drawing for unrevealed cells
        }
    }
}