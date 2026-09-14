import java.util.Scanner;
import java.util.Stack;

public class TicTacToe {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create and initialize the board
        char[][] board = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
        };

        char currentPlayer = 'X';
        boolean gameOver = false;

        System.out.println("Welcome to Tic-Tac-Toe!");

        // Main game loop
        while (!gameOver) {

            printBoard(board);

            System.out.println("Player " + currentPlayer + "'s turn.");

            int row;
            int column;

            // Keep asking until a valid move is entered
            while (true) {

                System.out.print("Enter row (1-3): ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    continue;
                }

                row = scanner.nextInt();

                System.out.print("Enter column (1-3): ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    continue;
                }

                column = scanner.nextInt();

                // Check if row and column are valid
                if (row < 1 || row > 3 || column < 1 || column > 3) {
                    System.out.println("Invalid position. Enter numbers between 1 and 3.");
                    continue;
                }

                // Convert 1-3 input into 0-2 array indexes
                row--;
                column--;

                // Check if the cell is already filled
                if (board[row][column] != '-') {
                    System.out.println("That cell is already filled. Try again.");
                    continue;
                }

                // Valid move
                break;
            }

            // Place player's symbol
            board[row][column] = currentPlayer;

            // Check if current player wins
            if (checkWinner(board, currentPlayer)) {

                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins!");

                gameOver = true;
            }

            // Check for draw
            else if (isDraw(board)) {

                printBoard(board);
                System.out.println("Draw!");

                gameOver = true;
            }

            // Switch player
            else {

                if (currentPlayer == 'X') {
                    currentPlayer = 'O';
                } else {
                    currentPlayer = 'X';
                }
            }
        }

        scanner.close();
    }

    // Method to print the board
    public static void printBoard(char[][] board) {

        System.out.println();

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                System.out.print(" " + board[i][j] + " ");

                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (i < board.length - 1) {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    // Method to check for a winner
    public static boolean checkWinner(char[][] board, char player) {

        // Check rows
        for (int i = 0; i < 3; i++) {

            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) {

                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {

            if (board[0][i] == player &&
                board[1][i] == player &&
                board[2][i] == player) {

                return true;
            }
        }

        // Check main diagonal
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {

            return true;
        }

        // Check other diagonal
        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {

            return true;
        }

        return false;
    }

    // Method to check for a draw
    public static boolean isDraw(char[][] board) {

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == '-') {
                    return false;
                }
            }
        }

        return true;
    }
}