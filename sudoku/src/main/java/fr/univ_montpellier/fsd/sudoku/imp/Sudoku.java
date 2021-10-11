package fr.univ_montpellier.fsd.sudoku.imp;

public class Sudoku {

	int n;
	int s;
	int[][] grid;

	/*
	 * Create an instance of the problem sudoku (nxn)
	 * 
	 */

	public Sudoku(int n) {
		this.n = n;
		this.s = (int) Math.sqrt(n);
		this.grid = new int[n][n];
	}

	/*
	 * check if this.grid is a correct sudoku solution.
	 * 
	 */
	private boolean solutionChecker() {

		boolean checkLines = checkLines();
		boolean checkColumns = checkColumns();
		boolean checkBlocs = checkBlocs();
		
		return checkBlocs && checkColumns && checkLines;
	}
	
	private boolean checkLines() {
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				int value = grid[i][j];
				
				for(int k = 0; k < n; k++) {
					
					if (j != k && value == grid[i][k]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkColumns() {
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				int value = grid[j][i];
				
				for(int k = 0; k < n; k++) {
					
					if (j != k && value == grid[k][i]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkBlocs() {
		for(int nbBlocI = 0; nbBlocI < n; nbBlocI += s) {
			for (int nbBlocJ = 0; nbBlocJ < n; nbBlocJ += s) {
				
				// Vérification d'un bloc
				for(int i = 0; i < s; i++) {
					for(int j = 0; j < s; j++) {
						int value = grid[i][j];
						
						for(int k = 0; k < s; k++) {
							for(int l = 0; l < s; l++) {
								
								if ((k != i || l != j) && value == grid[k][l]) {
									return false;
								}
							}
						}
					}	
				}
			}
		}
		
		return true;
	}

	/*
	 * Generate a random grid solution
	 * 
	 */

	private void generateSolution() {

		int value = 1, i = 0, j = 0, counter = 0;
		
		while(i < n) {
			
			while (counter < n) {
				
					grid[i][j] = value++;
					j++;
					
					if (j == n) {
						j = 0;
					}
				
				counter++;
			}
			
			counter = 0;
			j = ((j + s) % n);
			
			if ((i+1) % s == 0 && i != 0) {
				j++;
			}
			
			value = 1;
			i++;
		}
	}

	/*
	 * Find a solution to the sudoku problem
	 * 
	 */
	public void findSolution() {

		generateSolution();
		
		boolean result = solutionChecker();
		
		System.out.println("Résultat : " + result);
	}
	
	/**
	 * Affiche la grille de sudoku.
	 */
	private void printGrid() {
		
		for (int k = 0; k < n; k++) {
			for(int l = 0; l < n; l++) {
				System.out.print(grid[k][l] + " ");
			}
			System.out.println("");
		}
	}

	public static void main(String args[]) {
		
		Sudoku sudoku = new Sudoku(1024);
		sudoku.findSolution();
		
		sudoku.printGrid();
	}
}
