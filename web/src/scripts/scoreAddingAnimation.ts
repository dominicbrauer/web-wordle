import type { GameSession } from "../lib/gameSession";


const scoreLabels = document.querySelectorAll<HTMLSpanElement>('.score-label');


export function animateScoreAdding(gameSession: GameSession, tile: HTMLDivElement) {
  const currentRow = gameSession.guesses_used;

  const scoreElement = document.createElement('div');
  scoreElement.className = "score-element";
  // scoreElement.textContent = gameSession.
  document.body.appendChild(scoreElement);
}