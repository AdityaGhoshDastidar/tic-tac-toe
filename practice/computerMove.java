import java.util.ArrayList;

public static void computerMove(char[][] board) {

    // Get all empty cells
    ArrayList<int[]> emptyCells = getEmptyCells(board);

    // 1. Can computer win right now?
    for (int[] cell : emptyCells) {
        int row = cell[0];
        int col = cell[1];

        board[row][col] = 'O';

        if (checkWinner(board, 'O')) {
            System.out.println("Computer plays: " + row + "," + col);
            return;
        }

        board[row][col] = ' ';
    }

    // 2. Can human win next turn? Block it.
    for (int[] cell : emptyCells) {
        int row = cell[0];
        int col = cell[1];

        board[row][col] = 'X';

        if (checkWinner(board, 'X')) {
            board[row][col] = 'O';
            System.out.println("Computer blocks at: " + row + "," + col);
            return;
        }

        board[row][col] = ' ';
    }

    // 3. Otherwise play first available cell
    if (!emptyCells.isEmpty()) {
        int[] move = emptyCells.get(0);
        board[move[0]][move[1]] = 'O';

        System.out.println("Computer plays: " +
                           move[0] + "," + move[1]);
    }
}