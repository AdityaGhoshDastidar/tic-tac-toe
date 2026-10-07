A Java-based Tic-Tac-Toe game developed in stages, starting with a basic console version and progressing to data-structure-based undo, a simple computer opponent, and a Java Swing graphical user interface.

The project demonstrates Java fundamentals, input validation, game-state management, Stack, ArrayList, and GUI development using Swing.

Tic-Tac-Toe GUI Testing

Testing Approach

I tested the Tic-Tac-Toe Swing GUI manually from a user's perspective. The goal was to check normal gameplay, game-over behavior, undo behavior, and invalid or repeated interactions.

Step 1 — Clean Start

Test: Clean compilation and launch

What I did: Closed the previous development environment and opened a fresh terminal. I compiled and launched the GUI from the project folder.

What I expected: The project should compile and the GUI should launch successfully from a clean terminal.

What actually happened: The GUI compiled and launched successfully.

Severity (my judgment): Minor

Step 2 — Full Game Testing

Test: Row win

What I did: Played a game until Player X completed a row.

What I expected: The game should detect the win and clearly indicate that Player X won.

What actually happened: The message correctly showed Player X wins!. However, the turn label at the top still displayed Player X's turn.

Severity (my judgment): Minor

Test: Column win

What I did: Played a game where a player completed a column.

What I expected: The column win should be detected and the correct winner message should appear.

What actually happened: The column win was detected correctly.

Severity (my judgment): Minor

Test: Diagonal win

What I did: Played a game where a player completed a diagonal.

What I expected: The diagonal win should be detected and clearly displayed.

What actually happened: The diagonal win was detected correctly.

Severity (my judgment): Minor

Test: Draw

What I did: Played a complete game without either player completing a winning combination.

What I expected: The GUI should display a clear draw message and the game should stop accepting moves.

What actually happened: The draw message appeared correctly. However, the board remained interactive after the game ended.

Severity (my judgment): Annoying

Test: Multiple undos

What I did: Played several moves, used Undo multiple times, and then continued playing.

What I expected: The moves should be removed in reverse order and I should be able to continue the game normally afterward.

What actually happened: The moves were undone and the game could continue afterward.

Severity (my judgment): Minor

Step 3 — Deliberately Trying to Break the GUI

Issue: Turn label remains active after a win

What I did: Played a game until Player X completed the top row.

What I expected: The game should clearly indicate that the game has ended, without showing another player's turn.

What actually happened: The message correctly showed Player X wins!, but the turn label at the top still displayed Player X's turn.

Severity (my judgment): Minor

Issue: Board remains playable after a win

What I did: Completed a game with Player X winning a row, clicked OK on the win message, and then clicked an empty board cell.

What I expected: Once a player wins, the current game should be over and further board clicks should not make moves. I should need to click Play Again to start another game.

What actually happened: After dismissing the win message, I could click an empty cell. The board state changed, the previous X and O marks were cleared, another X/O appeared, and the turn label changed.

Severity (my judgment): Annoying

Issue: Clicking an occupied cell gives no feedback

What I did: Clicked a cell that was already occupied by an X or O.

What I expected: The existing X/O mark should remain unchanged, no new mark should appear, and the player's turn should remain the same.

What actually happened: The old X/O mark remained visible. No new X/O mark appeared. The turn label remained Player X.

Severity (my judgment): Minor — the behavior worked correctly, but there was no explicit feedback explaining that the click was ignored.

Things That Worked Well but Felt Rough

Occupied-cell feedback

The occupied-cell validation works correctly: the existing mark is not overwritten and the turn does not change. However, there is no visible feedback telling the player why nothing happened after the click.

Game-over feedback

The win message is displayed correctly, but the turn label still shows an active player's turn. This can make the game feel as though it is still in progress.

Issues Found

Board remains playable after a win — Annoying

Turn label remains active after a win — Minor

No feedback when an occupied cell is clicked — Minor

Priority Fixes

1. Prevent board interaction after a win or draw

This is the first priority because a completed game should not accept additional moves or change its state.

2. Update the turn label when the game ends

This should be fixed because the current turn label conflicts with the win message and makes the game state less clear.

3. Add feedback for occupied-cell clicks

This would improve usability by explaining why a player's click was ignored, although the underlying validation already works correctly.

Fix Completed

Game-over interaction

I fixed the game-over behavior so that the board does not accept additional moves after a win or draw. The player must use Play Again to start another game.

Reflection

The testing showed that the core game logic works in normal gameplay, but testing the GUI from a user's perspective revealed problems that were not obvious from the code alone. In particular, a game can correctly display a win message while still allowing the board to change afterward. This testing helped identify the difference between functionality working technically and the application behaving correctly from the user's perspective.