package TIC_TAC_TOE;

import java.util.*;

public class TicTacToeGame {

	private Deque<Player> players;
	private Board gameBoard;

	public TicTacToeGame() {

	}

	public void initializeGame() {
		// Creating two players
		players = new LinkedList<>();
		PlayingPiece crossPiece = new PieceTypeX(PieceType.X);
		Player player1 = new Player("Player1", crossPiece);

		PlayingPiece noughtPiece = new PieceTypeO(PieceType.O);
		Player player2 = new Player("Player2", noughtPiece);

		players.add(player1);

		players.add(player2);

		// initializing board
		gameBoard = new Board(3);

	}

	public String startGame() {
		boolean noWinner = true;

		while (noWinner) {

			// get the players whose turn is first
			Player playerTurn = players.removeFirst();

			// print board
			gameBoard.printBoard();

			// check if there is free cells
			List<Pair> freeCells = gameBoard.getFreeCells();

			if (freeCells.isEmpty()) {
				noWinner = false;
				continue;

			}

			// enter row and columns (read the user input)
			System.out.println("Player: " + playerTurn.playerName + "  enter row and column");
			Scanner scannner = new Scanner(System.in);
			String s = scannner.nextLine(); // 1,0

			String str[] = s.split(",");

			int rowInput = Integer.valueOf(str[0]);
			int colInput = Integer.valueOf(str[1]);

			// adding piece and checking valid place
			boolean pieceAddedSuccessfully = gameBoard.addPiece(rowInput, colInput, playerTurn.playingPiece);

			if (!pieceAddedSuccessfully) {
				// player can't into cell need to select other row and col
				System.out.println("Enter correct row and column");
				players.addFirst(playerTurn);
				continue;

			}

			players.addLast(playerTurn);
			// check is there any winner
			boolean winner = isThereAnyWinner(rowInput, colInput, playerTurn.playingPiece.pieceType);

			if (winner) {
				return "Player: " + playerTurn.playerName + " is the Winner!!!";
			}

		}

		return "Tie";
	}

	private boolean isThereAnyWinner(int row, int col, PieceType pieceType) {

		boolean rowMatch = true;
		boolean columnMatch = true;
		boolean diagonalMatch = true;
		boolean antiDiagonalMatch = true;

		// row check

		for (int i = 0; i < gameBoard.size; i++) {
			if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
				rowMatch = false;
			}
		}

		// column check

		for (int i = 0; i < gameBoard.size; i++) {
			if (gameBoard.board[i][col] == null || gameBoard.board[i][col].pieceType != pieceType) {
				columnMatch = false;
			}
		}

		// diagonal check
		for (int i = 0, j = 0; i < gameBoard.size; i++, j++) {
			if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
				diagonalMatch = false;
			}
		}

		// anti diagonal check
		for (int i = 0, j = gameBoard.size - 1; i < gameBoard.size; i++, j--) {
			if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
				antiDiagonalMatch = false;
			}
		}

		return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;

	}

}
