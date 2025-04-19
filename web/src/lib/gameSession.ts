export interface Scoring {
  in_word: boolean;
  correct_idx: boolean;
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

export interface GameSession {
  status: string;
  guesses_used: number;
  guesses: Array<Guess>;
}