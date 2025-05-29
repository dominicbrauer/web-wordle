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
 * Posts the current gameSession to the server to get feedback
 * for the current guess.
 * @param gameSession the current gameSession
 * @returns updated gameSession with updated game
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
 * Posts the current gameSession to the server and requests
 * to continue (get a new game).
 * @param gameSession the current gameSession
 * @returns updated gameSession with a new game
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

/**
 * Posts gameSession data to the server and saves it in the
 * users statistics table if possible.
 * @param gameSession the current gameSession.
 */
export async function saveGameData(gameSession: GameSession) {
	const response = await fetch('http://localhost:8080/stats/save', {
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
}