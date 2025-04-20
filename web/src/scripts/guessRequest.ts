import type { GameSession } from "../lib/gameSession";

export async function guessRequest(gameSession: GameSession): Promise<GameSession> {
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