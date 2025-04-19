import type { GameSession } from "../lib/gameSession";

async function guessRequest(gameSession: GameSession): Promise<GameSession> {
  const response = await fetch('http://localhost:8080/api/start-game', {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json'
    },
    body: ""
  });

  if (!response.ok) {
    throw new Error(`Error: ${response.statusText}`);
  }
  return await response.json() as GameSession;
};