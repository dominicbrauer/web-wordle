import type { GameSession } from "../lib/gameSession";

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