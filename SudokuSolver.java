import java.io.*;
import java.util.*;

public class SudokuSolver {

	public static void printSudoku(int[][] sudoku, int n){
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				System.out.print(sudoku[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean isSafe(int[][] sudoku, int num, int i, int j, int n){

		for(int k = 0;k < n;k++){
			if(sudoku[i][k] == num || sudoku[k][j] == num)return false;
		}

		int sx = (i/3)*3;
		int sy = (j/3)*3;

		for(int k = sx;k < sx + 3;k++){
			for(int l = sy;l < sy + 3;l++){
				if(sudoku[k][l] == num)return false;
			}
		}

		return true;
	}

	public static boolean solve(int[][] sudoku, int i, int j, int n){

		if(i == n){
			printSudoku(sudoku, n);
			return true;
		}

		if(j == n)return solve(sudoku, i + 1, 0, n);

		if(sudoku[i][j] != 0)return solve(sudoku, i, j + 1, n);

		for(int num = 1;num <= 9;num++){
			if(isSafe(sudoku, num, i, j, n)){
				sudoku[i][j] = num;
				boolean isSolveable = solve(sudoku, i, j + 1, n);
				if(isSolveable == true)return true;
			}
		}

		sudoku[i][j] = 0;
		return false;
	}

	public static void main(String[] args) {

		try {
			System.setIn(new FileInputStream("input.txt"));
			System.setOut(new PrintStream(new FileOutputStream("output.txt")));
		} 

		catch (Exception e) {
			System.err.println("Error");
		}

		Scanner sc = new Scanner(System.in);

		int n = 9;
		int[][] sudoku = new int[n][n];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < n;j++){
				sudoku[i][j] = sc.nextInt();
			}
		}

		solve(sudoku, 0, 0, n);



	}
}