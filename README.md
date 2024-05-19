This demo uses a Spring Boot backend to manage the game state and a frontend that displays the game board and handles user interactions. 

The communication between the frontend and backend is done via WebSockets.

Entities Used
- GameState: Represents the state of the game, including the board, status message, and whether the game is over
  - Even though the game is displayed as a 3x3 table, we can model it using a 9 element array, using the array index to correspond to the table position 
- PlayerMove: Represents a move made by a player (i.e., the index of the cell they clicked)
- GameService: Contains the core game logic, including processing moves, checking for win conditions, and resetting the board
- GameController: Handles WebSocket messages for moves and game resets
- WebSocketConfig: Configures WebSocket endpoints and message broker

Basic Flow
1. Initialization: The game board is initialized with empty cells. The currentPlayer is set to 'X'
2. Frontend Player Move: When a player clicks a cell, a PlayerMove message is sent to the backend via WebSocket
3. Backend Process Move: The GameService processes the move by updating the board and checking for a win or draw condition
4. Update State: The updated GameState is sent back to all connected clients via WebSocket
5. Frontend Display Updates: The frontend updates the game board (essentially rebuilding it) and displays the new state
6. Game Over: If a win or draw condition is met, the game is marked as over, and a "Play Again" button is displayed
7. Reset Game: Clicking the "Play Again" button sends a reset message to the backend, which resets the game state


**Checking for Win Condition:**

The win condition is checked after each move by comparing the current state of the board to predefined winning patterns. 
There are only 8 possible winning patterns, corresponding to horizontal, vertical, or diagonal sweeps.   


## How to run
Start Backend by building and running the Spring Boot application: Can do this via IDE or by running `mvn spring-boot:run`.

Open frontend by opening **'index.html'** (located under `src/main/resources/static`) in any web browser.

Click on any cell to begin playing. 


