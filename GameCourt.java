package org.cis1200.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameCourt extends JPanel {
    private final GameModel gameModel;
    private final JLabel status;

    public static final int CELL_SIZE = 30;
    public static final int COURT_WIDTH = CELL_SIZE * 10;
    public static final int COURT_HEIGHT = CELL_SIZE * 10;

    public GameCourt(JLabel status) {
        this.status = status;
        this.gameModel = new GameModel();

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameModel.isGameOver()) {
                    return;
                }

                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;

                if (x >= 0 && x < 10 && y >= 0 && y < 10) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (gameModel.revealCell(x, y)) {
                            updateStatus(); // Game Over
                        }
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        gameModel.toggleFlag(x, y);
                    }
                }

                repaint();
                updateStatus();
            }
        });
    }

    public void reset() {
        gameModel.reset();
        updateStatus();
        repaint();
    }

    private void updateStatus() {
        if (gameModel.isGameOver()) {
            if (gameModel.hasWon()) {
                status.setText("You win!");
            } else {
                status.setText("You lose!");
            }
        } else {
            status.setText("Mines flagged: " + gameModel.countFlaggedMines() +
                    " | Remaining tiles: " + gameModel.countRemainingTiles() +
                    " | Game state: Playing");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] grid = gameModel.getGrid();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j].draw(g);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
