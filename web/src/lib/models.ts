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
  was_correct: boolean;
  character_info: Array<Char>;
}

export interface Game {
  guesses_used: number;
  current_guess: string;
  current_guess_valid: boolean;
  guesses: Array<Guess>;
  final_score: number;
}

export interface GameSession {
  status: string;
  game_index: number;
  games: Array<Game>;
}