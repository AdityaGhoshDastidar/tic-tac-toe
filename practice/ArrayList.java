public static ArrayList<int[]> getEmptyCells(char[][] board) {

    ArrayList<int[]> emptyCells = new ArrayList<>();

    for (int row = 0; row < board.length; row++) {
        for (int col = 0; col < board[row].length; col++) {

            if (board[row][col] == ' ') {
                emptyCells.add(new int[]{row, col});
            }
        }
    }

    return emptyCells;
}