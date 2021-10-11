package fr.univ_montpellier.fsd.sudoku;

import fr.univ_montpellier.fsd.sudoku.imp.Sudoku;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    	
    	// Notre solution
    	long start = System.currentTimeMillis();
		new fr.univ_montpellier.fsd.sudoku.imp.Sudoku(1024).findSolution();
		long end = System.currentTimeMillis();

		System.out.println("Temps d'exécution : " + (end - start) + "ms");
		

    	start = System.currentTimeMillis();
    	new fr.univ_montpellier.fsd.sudoku.ppc.Sudoku().solve();
		end = System.currentTimeMillis();

		System.out.println("Temps d'exécution : " + (end - start) + "ms");
    }
}
