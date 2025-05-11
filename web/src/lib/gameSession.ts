export interface Char {
  character: string;
  scoring: string;
}

export interface Guess {
  word: string;  
  was_correct: boolean;
  character_info: Array<Char>;
}

export interface GameSession {
  status: string;
  guesses_used: number;
  current_guess: string;
  current_guess_valid: boolean;
  guesses: Array<Guess>;
  scores: Array<number>;
}