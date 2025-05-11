import type { GameSession } from "../lib/gameSession";


/**
 * Sends a word guess to the server
 * and waits for its response.
 * @param gameSession 
 * @returns Promise with GameSession
 */
export async function submitGuess(gameSession: GameSession): Promise<GameSession> {
  const response = await fetch('http://localhost:8080/api/guess', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(gameSession),
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
  return await response.json() as GameSession;
};


/**
 * Requests a gameSession from the server. If there is no
 * valid session yet, a new one is created.
 * @returns Promise with GameSession
 */
export async function requestGameSession(): Promise<GameSession> {
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
  return await response.json() as GameSession;
};


/**
 * Sends a request to the server to continue with the next game.
 * @param gameSession 
 * @returns Promise with GameSession
 */
export async function requestNextGame(gameSession: GameSession): Promise<GameSession> {
  const response = await fetch('http://localhost:8080/api/game-continue', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(gameSession),
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
  return await response.json() as GameSession;
};