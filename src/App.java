
public class App {
	public static void main(String [] args) {
		int [][] board = {
				 {0, 0, 0, 7, 9, 0, 0, 5, 0},
	             {3, 5, 2, 0, 0, 8, 0, 4, 0},
	             {0, 0, 0, 0, 0, 0, 0, 8, 0},
	             {0, 1, 0, 0, 7, 0, 0, 0, 4},
	             {6, 0, 0, 3, 0, 1, 0, 0, 8},
	             {9, 0, 0, 0, 8, 0, 0, 1, 0},
	             {0, 2, 0, 0, 0, 0, 0, 0, 0},
	             {0, 4, 0, 5, 0, 0, 8, 9, 1},
	             {0, 8, 0, 0, 3, 7, 0, 0, 0}
		};
		
		printBoard(board);
		solveBoard(board);
		System.out.println("###################################");
		printBoard(board);
	}
	
	
// functions
	
	// print board
	public static void printBoard(int [][] bo) {
		for(int i = 0; i < bo.length; i++) {
			if(i % 3 == 0 && i != 0) {
				System.out.println("-----------");
			}
			for(int j = 0; j < bo[i].length; j++) {
				if(j % 3 == 0 && j != 0 && j != 8) {
					System.out.print("|" + bo[i][j]);
				} else if(j == 8) {
					System.out.print(bo[i][j] + "\n");
				} else {
					System.out.print(bo[i][j]);
				}
			}
		}
	}
	
	
	// find first empty slot in sudoku board
	public static int[] findEmpty(int [][] bo) {
		int [] pos = {-1, -1};
		for(int i = 0; i < bo.length; i++) {
			for(int j = 0; j < bo[i].length; j++) {
				if(bo[i][j] == 0) {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return null;
	}
	
	
	// check if you can insert number into position[row][col]
	public static boolean checkBoard(int [][] bo, int row, int col, int num) {
		// check row
		for(int i = 0; i < 9; i++) {
			if(bo[row][i] == num && col != i) {
				return false;
			}
		}
		// check column
		for(int i = 0; i < 9; i++) {
			if(bo[i][col] == num && row != i) {
				return false;
			}
		}
		// check box
		// find which part of box u check
		int boxX = Math.floorDiv(col, 3);
		int boxY = Math.floorDiv(row, 3);
		for (int i = boxY * 3; i < boxY * 3 + 3; i++) {
			for(int j = boxX * 3; j < boxX * 3 + 3; j++) {
				if(bo[i][j] == num && row != i && col != j) {
					return false;
				}
			}
		}
		return true;
	}
	
	// solve board
	public static boolean solveBoard(int [][] bo) {
		int [] find = findEmpty(bo);
		int row;
		int col;
		// if there is no empty slot in board return true
		if(find == null) {
			return true;
		} else {
			// find first empty position
			row = find[0];
			col = find[1];
		}
		for(int i = 1; i < 10; i++) {
			// find first number you can insert into position
			if(checkBoard(bo, row, col, i) == true) {
				bo[row][col] = i;
				// backtracking
				if(solveBoard(bo) == true) {
					return true;
				} else {
					bo[row][col] = 0;
				}
			}
		}
		return false;
	}

}

