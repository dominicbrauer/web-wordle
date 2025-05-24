import type { Game, GameSession } from "../lib/models";

/**
 * Requests a GameSession from the server. If there is no
 * valid session yet, a new one gets created.
 * @returns a GameSession object
 */
export async function requestGameSession(): Promise<GameSession> {
  const response = await fetch('http://localhost:8080/game/start', {
    method: 'GET',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
  return await response.json() as GameSession;
}

/**
 * 
 */
export async function submitGuess(gameSession: GameSession): Promise<GameSession> {
  const response = await fetch('http://localhost:8080/game/guess', {
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
}

/**
 * 
 */
export async function requestNextGame(gameSession: GameSession): Promise<GameSession> {
  const response = await fetch('http://localhost:8080/game/next-game', {
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
}