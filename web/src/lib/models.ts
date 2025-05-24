export enum Scoring {
	GRAY = "GRAY",
	YELLOW = "YELLOW",
	GREEN = "GREEN"
}

export interface Char {
	character: string;
	scoring: Scoring;
}

export interface Guess {
	word: string;  
	wasCorrect: boolean;
	characterInfo: Array<Char>;
}

export interface Game {
	guessesUsed: number;
	currentGuess: string;
	currentGuess_valid: boolean;
	guesses: Array<Guess>;
	finalScore: number;
}

export interface GameSession {
	status: string;
	gameIndex: number;
	games: Array<Game>;
}

export interface Statistics {
	highestScoreTotal: number;
	highestGameReached: number;
	highestScoreGame: number;
	totalGamesPlayed: number;
}

export interface User {
	userName: string;
	email: string;
	stats: Statistics;
}