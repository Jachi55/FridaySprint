package com.sprint.game_text;

public class Grid {
	private int rows;
	private int columns;
	private char[][] grid;
	
	public Grid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.grid = new char[this.rows][this.columns];
		
		// Fill grid
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                this.grid[row][col] = 'x';
            }
        }
		
	}
	
	public void updateTile(int[] tile_position, char tile_sprite) {
        this.grid[tile_position[0]][tile_position[1]] = tile_sprite;
    }

    public char getTile(int[] tilePosition) {
        // Check the position is valid
        char out = ' ';
        if ( (tilePosition[0] > this.rows || tilePosition[1] > this.columns) || (tilePosition[0] < 0 || tilePosition[1] < 0) ) {
        } else {
            out =  this.grid[tilePosition[0]][tilePosition[1]];
        }

        return out;
    }
	
    public void show() {
        // Clear screen
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        // Assemble single output string
        String out = "\r";

        // Loop through and add each tile to the string
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                out += this.grid[row][col];
            }
            out += "\n";
        }

        // Update screen
        System.out.print(out);
        
    }
}