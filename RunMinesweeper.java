package org.cis1200.minesweeper;

import javax.swing.*;
import java.awt.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunMinesweeper implements Runnable {
    public void run() {
        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(200, 200); // Adjusted location for centering

        // Set preferred size for the frame
        frame.setPreferredSize(new Dimension(420, 430)); // Expanded size

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Mines flagged: 0 | " +
                "Remaining tiles: 100 | Game state: Playing");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);

        // Center the game board
        JPanel centeredCourt = new JPanel();
        centeredCourt.setLayout(new GridBagLayout());
        centeredCourt.add(court);
        frame.add(centeredCourt, BorderLayout.CENTER);

        // Control panel
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> court.reset());
        control_panel.add(reset);

        // Instructions button
        final JButton instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(e -> {
            String instructions = "Instructions\n\n" +
                    "Objective:\n\n" +
                    "The goal of the game is to uncover all safe tiles without " +
                    "triggering any mines. Each tile has a number " +
                    "that indicates how many mines are in adjacent tiles.\n" +
                    "Use this information " +
                    "to avoid mines and reveal all the safe areas.\n\n" +
                    "Starting the Game:\n\n" +
                    "When you start the game, you'll see a grid of hidden tiles. The number " +
                    "of " +
                    "rows and columns varies depending on the game difficulty.\n" +
                    "The mines are placed randomly on the grid, and you don't know" +
                    " where they are.\n\n" +
                    "Revealing a Tile (Click):\n\n" +
                    "Click on any tile to reveal it. If you click on a safe tile, it will show " +
                    "a number indicating how many mines are adjacent to it.\n" +
                    "If you click on a tile that contains a mine, the game ends immediately " +
                    "with a loss.\n\n" +
                    "Flagging a Suspected Mine (Right-Click):\n\n" +
                    "Right-click on any tile to place a flag. This indicates you suspect that" +
                    " tile " +
                    "contains a mine.\n" +
                    "Right-click again to remove the flag if you change your mind.\n" +
                    "You can use flags to help track where you think mines are located, so you " +
                    "don’t " +
                    "accidentally reveal them later.\n\n" +
                    "Revealing Adjacent Tiles (Cascading Reveal):\n\n" +
                    "If you reveal an EmptyTile (a tile with no adjacent mines), the game will " +
                    "automatically reveal adjacent tiles, starting a cascade of reveals.\n" +
                    "This process continues until all adjacent non-empty tiles are revealed. " +
                    "This " +
                    "is a key mechanic of Minesweeper to help uncover large areas quickly.\n\n" +
                    "Winning the Game:\n\n" +
                    "To win, you must uncover all the safe tiles on the grid without revealing " +
                    "a mine.\n" +
                    "The game will notify you with a Win message once all non-mine tiles are " +
                    "revealed.\n\n" +
                    "Losing the Game:\n\n" +
                    "If you click on a tile containing a mine, the game will end with a Loss.\n" +
                    "The mine will be revealed, and the game will show a Game Over message.\n\n"
                    ;
            JOptionPane.showMessageDialog(
                    frame, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
        });
        control_panel.add(instructionsButton);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start a new game
        court.reset();
    }
}