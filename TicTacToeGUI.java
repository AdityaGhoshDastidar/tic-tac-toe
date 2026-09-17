import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI {

    static JButton[] buttons = new JButton[9];
    static JLabel turnLabel;
    static char currentPlayer = 'X';
    static boolean gameOver = false;

    public static void main(String[] args) {

        TicTacToe.initializeBoard();

        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        turnLabel = new JLabel("Player X's turn", SwingConstants.CENTER);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));

        // Create the 9 game buttons
        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));

            final int position = i + 1;

            buttons[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (gameOver) {
                        return;
                    }

                    int row = (position - 1) / 3;
                    int col = (position - 1) % 3;

                    // Ignore occupied cells
                    if (TicTacToe.board[row][col] != ' ') {
                        return;
                    }

                    // Existing game logic
                    TicTacToe.makeMove(position, currentPlayer);

                    // Display X or O
                    buttons[position - 1].setText(
                            String.valueOf(currentPlayer)
                    );

                    // Check winner
                    if (TicTacToe.checkWinner(currentPlayer)) {

                        JOptionPane.showMessageDialog(
                                frame,
                                "Player " + currentPlayer + " wins!"
                        );

                        turnLabel.setText(
                                "Player " + currentPlayer + " wins!"
                        );

                        gameOver = true;
                        return;
                    }

                    // Check draw
                    if (TicTacToe.boardFull()) {

                        JOptionPane.showMessageDialog(
                                frame,
                                "It's a draw!"
                        );

                        turnLabel.setText("It's a draw!");

                        gameOver = true;
                        return;
                    }

                    // Switch player
                    currentPlayer =
                            currentPlayer == 'X' ? 'O' : 'X';

                    turnLabel.setText(
                            "Player " + currentPlayer + "'s turn"
                    );
                }
            });

            gridPanel.add(buttons[i]);
        }

        // Undo button
        JButton undoButton = new JButton("Undo");

        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (TicTacToe.moveStack.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            frame,
                            "There is no move to undo."
                    );

                    return;
                }

                // Existing undo logic
                TicTacToe.undoMove();

                refreshBoard();

                // Restore the correct player's turn
                if (TicTacToe.moveStack.isEmpty()) {
                    currentPlayer = 'X';
                } else {
                    currentPlayer =
                            TicTacToe.moveStack.peek().player == 'X'
                                    ? 'O'
                                    : 'X';
                }

                gameOver = false;

                turnLabel.setText(
                        "Player " + currentPlayer + "'s turn"
                );
            }
        });

        // Play Again button
        JButton playAgainButton = new JButton("Play Again");

        playAgainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Reuse existing initialization logic
                TicTacToe.initializeBoard();

                // Reset GUI buttons
                for (int i = 0; i < 9; i++) {
                    buttons[i].setText("");
                }

                // Reset game state
                currentPlayer = 'X';
                gameOver = false;

                turnLabel.setText("Player X's turn");
            }
        });

        // Bottom panel
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(undoButton);
        bottomPanel.add(playAgainButton);

        frame.setLayout(new BorderLayout());

        frame.add(turnLabel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Refresh the GUI from the existing board
    static void refreshBoard() {

        for (int i = 0; i < 9; i++) {

            int row = i / 3;
            int col = i % 3;

            if (TicTacToe.board[row][col] == ' ') {
                buttons[i].setText("");
            } else {
                buttons[i].setText(
                        String.valueOf(TicTacToe.board[row][col])
                );
            }
        }
    }
}