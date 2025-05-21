import type { Game } from "../lib/Game";


/**
 * Sends a word guess to the server
 * and waits for its response.
 * @param Game 
 * @returns Promise with Game
 */
export async function submitGuess(Game: Game): Promise<Game> {
  const response = await fetch('http://localhost:8080/api/guess', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(Game),
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
  return await response.json() as Game;
};


/**
 * Requests a Game from the server. If there is no
 * valid session yet, a new one is created.
 * @returns Promise with Game
 */
export async function requestGame(): Promise<Game> {
  const response = await fetch('http://localhost:8080/api/start-game', {
    method: 'GET',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
  return await response.json() as Game;
};


/**
 * Sends a request to the server to continue with the next game.
 * @param Game 
 * @returns Promise with Game
 */
export async function requestNextGame(Game: Game): Promise<Game> {
  const response = await fetch('http://localhost:8080/api/game-continue', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(Game),
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
  return await response.json() as Game;
};

/**
 * Submits all game data to the server. The Game
 * will be invalidated.
 */
export async function submitLostGame(Game: Game): Promise<void> {
  const response = await fetch('http://localhost:8080/api/game-over', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(Game),
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
}