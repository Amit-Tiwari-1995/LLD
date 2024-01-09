package TIC_TAC_TOE;

import java.util.*;

public class Board {

	public int size;
	public PlayingPiece[][] board;

	public Board(int size) {
		this.size = size;
		board = new PlayingPiece[size][size];
	}

	public boolean addPiece(int row, int col, PlayingPiece playingPiece) {

		if (board[row][col] != null) {
			return false;
		}
		board[row][col] = playingPiece;
		return true;

	}

	public List<Pair> getFreeCells() {

		List<Pair> freeCells = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == null) {
					Pair pair = new Pair(i, j);
					freeCells.add(pair);
				}

			}

		}
		return freeCells;
	}

	public void printBoard() {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] != null) {
					System.out.print(board[i][j].pieceType.name() + "   ");
				} else {
					System.out.print("    ");

				}
				if (j < size - 1) {
					System.out.print("|");
				}
			}
			System.out.println();

			if (i < size - 1) {
				for (int k = 0; k < size; k++) {
					System.out.print("----");
					if (k < size - 1) {
						System.out.print("+");
					}
				}
				System.out.println();
			}

		}

	}

}
