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
	
	/**
	 * Vérifie que chaque ligne n'ait que des valeurs différentes.
	 */
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

	/**
	 * Vérifie que chaque colonne n'ait que des valeurs différentes.
	 */
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
	
	/**
	 * Vérifie que chaque bloc n'ait que des valeurs différentes.
	 */
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

		int i = 0, j = 0, value = 1;
		
		while(i < n)
		{
			while(j < n)
			{
				while(grid[i][j] == 0 && value <= n)
				{
					boolean isValidValue = isValidValueOnColumn(j, value) && isValidValueOnLine(i, value) && isValidOnBloc(i, j, value);
		
					if (isValidValue)
					{
						grid[i][j] = value;
						value = 1;
						
						printGrid();
						System.out.println("");
					}
					else 
					{
						value++;
					}
				}
				
				if (grid[i][j] == 0)
				{
					if (j > 0)
					{
						j--;
					}
					else if (i > 0)
					{
						i--;
						j = n-1;
					}
					
					value = grid[i][j] + 1;
					grid[i][j] = 0;
				}
				else if (j < n)
				{
					j++;
				}
			}
			
			i++;
			j = 0;
		}
	}
	
	/**
	 * Génère une solution en suivant un patèrne.
	 */
	private void generateSolutionWithPattern()
	{		
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

	/**
	 * Vérifie qu'une ligne n'ait que des valeurs différentes.
	 */
	private boolean isValidValueOnLine(int lineNumber, int value)
	{
		for(int i = 0; i < n; i++)
		{
			if (grid[lineNumber][i] == value)
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Vérifie qu'une colonne n'ait que des valeurs différentes.
	 */
	private boolean isValidValueOnColumn(int columnNumber, int value)
	{
		for(int i = 0; i < n; i++)
		{
			if (grid[i][columnNumber] == value)
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Vérifie qu'un bloc n'ait que des valeurs différentes.
	 */
	private boolean isValidOnBloc(int lineNumber, int columnNumber, int value)
	{
		while (lineNumber % s != 0)
		{
			lineNumber--;
		}
		
		while (columnNumber % s != 0)
		{
			columnNumber--;
		}
		
		for(int i = lineNumber; i < lineNumber + s; i++)
		{
			for(int j = columnNumber; j < columnNumber + s; j++)
			{
				if (grid[i][j] == value)
				{
					return false;
				}
			}
		}
		
		return true;
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
		
		Sudoku sudoku = new Sudoku(16);
		sudoku.findSolution();
		
		sudoku.printGrid();
	}
}
