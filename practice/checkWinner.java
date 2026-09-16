public static boolean checkWinner(char[][] board, char symbol) {

    // Rows
    for (int row = 0; row < 3; row++) {
        if (board[row][0] == symbol &&
            board[row][1] == symbol &&
            board[row][2] == symbol) {
            return true;
        }
    }

    // Columns
    for (int col = 0; col < 3; col++) {
        if (board[0][col] == symbol &&
            board[1][col] == symbol &&
            board[2][col] == symbol) {
            return true;
        }
    }

    // Diagonals
    if (board[0][0] == symbol &&
        board[1][1] == symbol &&
        board[2][2] == symbol) {
        return true;
    }

    if (board[0][2] == symbol &&
        board[1][1] == symbol &&
        board[2][0] == symbol) {
        return true;
    }

    return false;
}