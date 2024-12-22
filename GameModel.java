package org.cis1200.minesweeper;

public class GameModel {
    private Cell[][] grid;
    private final int rows = 10;
    private final int cols = 10;
    private final int numBombs = 15;
    private boolean playing = false;

    public GameModel() {
        grid = new Cell[rows][cols];
    }

    public void reset() {
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(j, i, GameCourt.CELL_SIZE); // Initialize cells normally
            }
        }
        placeBombs();
        calculateAdjacentBombs();
        playing = true; // Reset game state
    }

    private void placeBombs() {
        int placedBombs = 0;
        while (placedBombs < numBombs) {
            int x = (int) (Math.random() * cols);
            int y = (int) (Math.random() * rows);

            // Check if this cell already has a bomb; if not, make it a BombCell
            if (!(grid[y][x] instanceof BombCell)) {
                // Replace Cell with BombCell
                grid[y][x] = new BombCell(x, y, GameCourt.CELL_SIZE);
                placedBombs++;
            }
        }
    }

    private void calculateAdjacentBombs() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!(grid[i][j] instanceof BombCell)) {
                    int count = 0;
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = j + dx;
                            int ny = i + dy;
                            if (nx >= 0 && nx < cols
                                    && ny >= 0 && ny < rows && grid[ny][nx] instanceof BombCell) {
                                count++;
                            }
                        }
                    }
                    grid[i][j].setAdjacentBombs(count);
                }
            }
        }
    }

    public boolean revealCell(int x, int y) {
        if (!playing) {
            return false; // Stop revealing if game is over
        }
        Cell cell = grid[y][x];
        if (cell.isRevealed() || cell.isFlagged()) {
            return false;
        }

        cell.reveal();
        if (cell.isBomb()) {
            playing = false;
            return true; // Game over
        } else if (cell.getAdjacentBombs() == 0) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx;
                    int ny = y + dy;
                    if (nx >= 0 && nx < cols && ny >= 0 && ny < rows) {
                        revealCell(nx, ny);
                    }
                }
            }
        }

        // Check if the game is won after each reveal
        if (hasWon()) {
            playing = false;
        }

        return false; // Continue playing
    }


    public void toggleFlag(int x, int y) {
        Cell cell = grid[y][x];
        if (!cell.isRevealed()) {
            cell.toggleFlag();
        }
    }

    public boolean isGameOver() {
        return !playing;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int countRemainingTiles() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isRevealed()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countFlaggedMines() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isFlagged()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean hasWon() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = grid[i][j];
                if (!cell.isBomb() && !cell.isRevealed()) {
                    return false; // Safe tile not revealed yet
                }
            }
        }
        return true; // All safe tiles are revealed
    }

}