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

public class StackExample {

    public static void main(String[] args) {

        Stack<Move> moveStack = new Stack<>();

        // PUSH - add moves to the stack
        moveStack.push(new Move(0, 0, 'X'));
        moveStack.push(new Move(1, 1, 'O'));
        moveStack.push(new Move(2, 2, 'X'));

        // Print all moves without removing them
        System.out.println("Move History:");

        int moveNumber = 1;

        for (Move move : moveStack) {
            System.out.println(
                moveNumber + ". " +
                move.player + " -> (" +
                move.row + ", " +
                move.col + ")"
            );

            moveNumber++;
        }

        // PEEK - see the most recent move
        if (!moveStack.isEmpty()) {
            Move lastMove = moveStack.peek();

            System.out.println("\nLast move:");
            System.out.println(
                lastMove.player + " -> (" +
                lastMove.row + ", " +
                lastMove.col + ")"
            );
        }

        // POP - remove the most recent move
        if (!moveStack.isEmpty()) {
            Move undoneMove = moveStack.pop();

            System.out.println("\nUndoing:");
            System.out.println(
                undoneMove.player + " -> (" +
                undoneMove.row + ", " +
                undoneMove.col + ")"
            );
        }

        // Check remaining moves
        System.out.println("\nMoves remaining: " + moveStack.size());
    }
}