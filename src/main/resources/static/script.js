document.addEventListener('DOMContentLoaded', () => {
  const boardElement = document.getElementById('board');
  const statusElement = document.getElementById('status');
  const playAgainButton = document.getElementById('play-again');

  const socket = new SockJS('http://localhost:8080/game');
  const stompClient = Stomp.over(socket);

  let gameOver = false;
  let currentBoard = Array(9).fill(' ');

  stompClient.connect({}, (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/updates', (message) => {
      const gameState = JSON.parse(message.body);
      console.log('Received game state: ', gameState);
      gameOver = gameState.gameOver;
      currentBoard = gameState.board;
      statusElement.textContent = gameState.status;
      updateBoard(currentBoard, gameOver);
      if (gameOver) {
        playAgainButton.style.display = 'block';
      } else {
        playAgainButton.style.display = 'none';
      }
    });
  });

  function updateBoard(board, isGameOver) {
    boardElement.innerHTML = '';
    if (typeof board === 'string') {
      board = board.split('');
    }
    board.forEach((value, index) => {
      const cell = document.createElement('div');
      cell.classList.add('cell');
      cell.textContent = value;
      cell.dataset.index = index;
      if (!isGameOver) {
        cell.addEventListener('click', handleCellClick);
      }
      boardElement.appendChild(cell);
    });
  }

  function handleCellClick(event) {
    if (gameOver) {
      return;
    }
    console.log("Current state of board: ", currentBoard)
    const index = event.target.dataset.index;
    if (currentBoard[index] !== ' ') {
      alert('This cell has already been clicked on!');
      return;
    }
    console.log('Cell clicked: ', index);
    stompClient.send('/app/move', {}, JSON.stringify({ index }));
  }

  playAgainButton.addEventListener('click', () => {
    stompClient.send('/app/reset', {});
  });

  // Initialize empty board
  updateBoard(Array(9).fill(' '), false);
});
