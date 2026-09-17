import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TicTacToe {

    private static final char EMPTY = ' ';
    private static final char HUMAN = 'X';
    private static final char COMPUTER = 'O';

     static char[][] board = new char[3][3];
     static Stack<Move> moveStack = new Stack<>();
     static ArrayList<Integer> emptyCells = new ArrayList<>();

    static class Move {
        int position;
        char player;

        Move(int position, char player) {
            this.position = position;
            this.player = player;
        }
    }

    public static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = EMPTY;
            }
        }

        moveStack.clear();
        emptyCells.clear();

        for (int position = 1; position <= 9; position++) {
            emptyCells.add(position);
        }
    }

    public static void printBoard() {
        System.out.println();
        System.out.println(" " + cell(1) + " | " + cell(2) + " | " + cell(3));
        System.out.println("---+---+---");
        System.out.println(" " + cell(4) + " | " + cell(5) + " | " + cell(6));
        System.out.println("---+---+---");
        System.out.println(" " + cell(7) + " | " + cell(8) + " | " + cell(9));
        System.out.println();
    }

    private static String cell(int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        if (board[row][col] == EMPTY) {
            return String.valueOf(position);
        }

        return String.valueOf(board[row][col]);
    }

    private static boolean isValidPosition(int position) {
        return position >= 1 && position <= 9;
    }

    private static boolean isEmpty(int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        return board[row][col] == EMPTY;
    }

     static void makeMove(int position, char player) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        board[row][col] = player;
        moveStack.push(new Move(position, player));
        emptyCells.remove(Integer.valueOf(position));
    }

     static void undoMove() {
        if (moveStack.isEmpty()) {
            System.out.println("There is no move to undo.");
            return;
        }

        Move lastMove = moveStack.pop();

        int row = (lastMove.position - 1) / 3;
        int col = (lastMove.position - 1) % 3;

        board[row][col] = EMPTY;
        emptyCells.add(lastMove.position);

        System.out.println("Undid player " + lastMove.player
                + "'s move at position " + lastMove.position + ".");
    }

     static boolean checkWinner(char player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player &&
                board[row][1] == player &&
                board[row][2] == player) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player &&
                board[1][col] == player &&
                board[2][col] == player) {
                return true;
            }
        }

        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {
            return true;
        }

        return false;
    }

     static boolean boardFull() {
        return emptyCells.isEmpty();
    }

    private static boolean wouldWin(int position, char player) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;

        board[row][col] = player;
        boolean wins = checkWinner(player);
        board[row][col] = EMPTY;

        return wins;
    }

    private static void computerMove() {
        // First: try to win.
        for (int position : emptyCells) {
            if (wouldWin(position, COMPUTER)) {
                makeMove(position, COMPUTER);
                System.out.println("Computer plays position " + position + ".");
                return;
            }
        }

        // Second: block the human from winning.
        for (int position : emptyCells) {
            if (wouldWin(position, HUMAN)) {
                makeMove(position, COMPUTER);
                System.out.println("Computer plays position " + position + ".");
                return;
            }
        }

        // Otherwise: choose the first available position.
        int position = emptyCells.get(0);
        makeMove(position, COMPUTER);
        System.out.println("Computer plays position " + position + ".");
    }

    private static void twoPlayerGame(Scanner scanner) {
        initializeBoard();

        char currentPlayer = 'X';

        while (true) {
            printBoard();

            System.out.println("Player " + currentPlayer
                    + ", enter a position (1-9) or 'u' to undo:");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("u")) {
                if (moveStack.isEmpty()) {
                    System.out.println("There is no move to undo.");
                } else {
                    undoMove();

                    if (!moveStack.isEmpty()) {
                        currentPlayer = moveStack.peek().player == 'X' ? 'O' : 'X';
                    } else {
                        currentPlayer = 'X';
                    }
                }

                continue;
            }

            try {
                int position = Integer.parseInt(input);

                if (!isValidPosition(position)) {
                    System.out.println("Please enter a number from 1 to 9.");
                    continue;
                }

                if (!isEmpty(position)) {
                    System.out.println("That position is already occupied.");
                    continue;
                }

                makeMove(position, currentPlayer);

                if (checkWinner(currentPlayer)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (boardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = currentPlayer == 'X' ? 'O' : 'X';

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number from 1 to 9 or 'u'.");
            }
        }
    }

    private static void singlePlayerGame(Scanner scanner) {
        initializeBoard();

        while (true) {
            printBoard();

            System.out.println("Your turn (X). Enter a position (1-9) or 'u' to undo:");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("u")) {
                if (moveStack.isEmpty()) {
                    System.out.println("There is no move to undo.");
                } else {
                    // Undo the computer's move first, then the human's
                    // previous move so the player returns to the
                    // previous decision point.
                    if (!moveStack.isEmpty()
                            && moveStack.peek().player == COMPUTER) {
                        undoMove();
                    }

                    if (!moveStack.isEmpty()
                            && moveStack.peek().player == HUMAN) {
                        undoMove();
                    }
                }

                continue;
            }

            try {
                int position = Integer.parseInt(input);

                if (!isValidPosition(position)) {
                    System.out.println("Please enter a number from 1 to 9.");
                    continue;
                }

                if (!isEmpty(position)) {
                    System.out.println("That position is already occupied.");
                    continue;
                }

                makeMove(position, HUMAN);

                if (checkWinner(HUMAN)) {
                    printBoard();
                    System.out.println("You win!");
                    break;
                }

                if (boardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                computerMove();

                if (checkWinner(COMPUTER)) {
                    printBoard();
                    System.out.println("Computer wins!");
                    break;
                }

                if (boardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number from 1 to 9 or 'u'.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TIC-TAC-TOE ===");
        System.out.println("1. Two Player");
        System.out.println("2. Play Against Computer");
        System.out.print("Choose a mode: ");

        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            twoPlayerGame(scanner);
        } else if (choice.equals("2")) {
            singlePlayerGame(scanner);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}