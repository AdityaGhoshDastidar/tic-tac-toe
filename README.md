# Tic-Tac-Toe

A Java console-based Tic-Tac-Toe game developed in stages, starting with a basic two-player game and progressing to data-structure-based undo and a simple computer opponent.

The project demonstrates Java fundamentals, input validation, game-state management, `Stack`, and `ArrayList`.

## Features

- Two-player Tic-Tac-Toe
- Single-player mode against the computer
- Undo functionality using `Stack`
- Computer prioritizes:
  1. Winning when possible
  2. Blocking the player's winning move
  3. Choosing an available position otherwise
- `ArrayList` used to track currently empty positions
- Winner and draw detection
- Input validation for invalid and occupied positions

## How to Run

Compile the program:

javac TicTacToe.java

Run the program:

java TicTacToe

## What I Learned

I learned that a Stack is a good fit for undo because it follows the Last-In, First-Out (LIFO) principle, so the most recent move can be removed first. A List is useful for tracking empty cells because it allows the program to store and manage all currently available positions for the computer to choose from.

## Possible Improvements

- Add a graphical user interface using Java Swing.
- Add difficulty levels for the computer opponent.
- Add a score system to track multiple games.
- Allow players to restart a game without closing the program.
