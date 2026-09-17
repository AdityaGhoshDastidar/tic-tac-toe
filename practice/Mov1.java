import java.util.Scanner;
import java.util.Stack;

class Move {
    int row;
    int col;
    char player;

    Move(int row, int col, char player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }
}

public class TicTacToe {

    static char[][] board = new char[3][3];
    static Stack<Move> moveStack = new Stack<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        initializeBoard();

        char currentPlayer = 'X';

        System.out.println("=== TIC-TAC-TOE ===");
        System.out.println("Player X vs Player O");
        System.out.println("Enter 'u' instead of a row number to undo.");
        System.out.println();

        while (true) {

            printBoard();

            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.println("Enter row (1-3) or 'u' to undo:");

            String input = scanner.nextLine();

            // UNDO OPERATION
            if (input.equalsIgnoreCase("u")) {

                if (moveStack.isEmpty()) {
                    System.out.println("Nothing to undo!");
                } else {

                    // Print move history before undoing
                    printMoveHistory();

                    Move lastMove = moveStack.pop();

                    board[lastMove.row][lastMove.col] = ' ';

                    // Restore the player who made the undone move
                    currentPlayer = lastMove.player;

                    System.out.println(
                        "Undid " + lastMove.player +
                        "'s move at (" +
                        (lastMove.row + 1) + ", " +
                        (lastMove.col + 1) + ")."
                    );
                }

                continue;
            }

            int row;

            try {
                row = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 3 or 'u'.");
                continue;
            }

            System.out.println("Enter column (1-3):");

            int col;

            try {
                col = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 3.");
                continue;
            }

            // Check whether row and column are valid
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position. Use numbers from 1 to 3.");
                continue;
            }

            // Check whether the cell is already occupied
            if (board[row][col] != ' ') {
                System.out.println("That cell is already occupied.");
                continue;
            }

            // Place the player's move
            board[row][col] = currentPlayer;

            // Store the move in the Stack
            moveStack.push(new Move(row, col, currentPlayer));

            // Check for winner
            if (checkWinner(currentPlayer)) {

                printBoard();

                System.out.println(
                    "Player " + currentPlayer + " wins!"
                );

                break;
            }

            // Check for draw
            if (boardFull()) {

                printBoard();

                System.out.println("It's a draw!");

                break;
            }

            // Change player
            if (currentPlayer == 'X') {
                currentPlayer = 'O';
            } else {
                currentPlayer = 'X';
            }
        }

        scanner.close();
    }

    // Initialize every cell with a blank space
    static void initializeBoard() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    // Display the board
    static void printBoard() {

        System.out.println();

        System.out.println(
            " " + board[0][0] + " | " +
            board[0][1] + " | " +
            board[0][2]
        );

        System.out.println("---+---+---");

        System.out.println(
            " " + board[1][0] + " | " +
            board[1][1] + " | " +
            board[1][2]
        );

        System.out.println("---+---+---");

        System.out.println(
            " " + board[2][0] + " | " +
            board[2][1] + " | " +
            board[2][2]
        );

        System.out.println();
    }

    // Check rows, columns, and diagonals
    static boolean checkWinner(char player) {

        // Check rows
        for (int row = 0; row < 3; row++) {

            if (board[row][0] == player &&
                board[row][1] == player &&
                board[row][2] == player) {

                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {

            if (board[0][col] == player &&
                board[1][col] == player &&
                board[2][col] == player) {

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

    // Check whether all cells are occupied
    static boolean boardFull() {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    // Print all moves without removing them from the Stack
    static void printMoveHistory() {

        System.out.println();
        System.out.println("=== MOVE HISTORY ===");

        if (moveStack.isEmpty()) {
            System.out.println("No moves have been made.");
            return;
        }

        int moveNumber = 1;

        for (Move move : moveStack) {

            System.out.println(
                moveNumber + ". Player " +
                move.player +
                " -> (" +
                (move.row + 1) + ", " +
                (move.col + 1) + ")"
            );

            moveNumber++;
        }

        System.out.println("====================");
    }
}