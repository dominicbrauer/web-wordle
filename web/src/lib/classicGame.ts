import type { GameSession } from "./gameSession";

export interface ClassicGame {
  status: string;
  game_index: number;
  games: Array<GameSession>;
  scores: Array<Array<number>>;
}