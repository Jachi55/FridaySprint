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
	
	public void updateTile(int[] tile_position, Entity e) {
		
		// Update previous tile back to 'x'
		
		int [] prev = e.getPrevPosition();
		this.grid[prev[0]][prev[1]] = 'x';
		

		
		// Update new entity tile
		
        this.grid[tile_position[0]][tile_position[1]] = e.getSprite();
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
        System.out.flush(); 

        // Assemble single output string
        String out = "\r";

        // Loop through and add each tile to the string
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                out += this.grid[row][col] + " ";
            }
            out += "\n";
        }

        // Update screen
        System.out.print(out);
        
    }
    
    
    public int getRows() {
    	return rows;
    }
    
    public int getColumns() {
    	return columns;
    }
}