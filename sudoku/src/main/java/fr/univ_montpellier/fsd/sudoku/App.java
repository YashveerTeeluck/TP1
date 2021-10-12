package fr.univ_montpellier.fsd.sudoku;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {

    	long start = System.currentTimeMillis();
    	new fr.univ_montpellier.fsd.sudoku.ppc.Sudoku().solve();
		long end = System.currentTimeMillis();

		System.out.println("Temps d'exécution du solver choco : " + (end - start) + "ms");
		
    	// Notre solution
    	start = System.currentTimeMillis();
		new fr.univ_montpellier.fsd.sudoku.imp.Sudoku(9).findSolution();
		end = System.currentTimeMillis();

		System.out.println("Temps d'exécution de l'algorithme naïf : " + (end - start) + "ms");
    }
}
