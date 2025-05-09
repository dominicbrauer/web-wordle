import type { GameSession } from "../lib/gameSession";


const scoreLabels = document.querySelectorAll<HTMLSpanElement>('.score-label');

const wait = (duration: number = 500) => new Promise((resolve) => setTimeout(resolve, duration));

export async function animateScoreAdding(gameSession: GameSession, tile: HTMLDivElement, scores: number[][], index: number): Promise<void> {
  const currentRow = gameSession.guesses_used - 1;

  const { x, y, width, height } = tile.getBoundingClientRect();
  const xPos = x + width / 2;
  const yPos = y + height / 2;

  // Add element
  const scoreElement = document.createElement('div');
  scoreElement.className = "score-element";
  
  scoreElement.textContent = scores[currentRow][index].toString();

  document.body.appendChild(scoreElement);

  scoreElement.style.position = "absolute";
  scoreElement.style.fontSize = "1.5em";
  const scoreElementWidth = scoreElement.getBoundingClientRect().width;
  const scoreElementHeight = scoreElement.getBoundingClientRect().height;
  scoreElement.style.left = `${xPos - scoreElementWidth / 2}px`;
  scoreElement.style.top = `${yPos - scoreElementHeight / 2}px`;

  const scoreLabelX = scoreLabels[currentRow].getBoundingClientRect().x;

  await new Promise(async (resolve) => {
    const anim = scoreElement.animate([
      { transform: `translateX(0)` },
      { left: `${scoreLabelX}px` }
    ], {
      duration: 600,
      easing: 'cubic-bezier(1, 0, 1, 1)',
      fill: 'forwards'
    });

    await wait(300);
    resolve('');
    anim.onfinish = () => {
      scoreElement.remove();
      scoreLabels[currentRow].textContent = (parseInt(scoreLabels[currentRow].textContent!) + scores[currentRow][index]).toString() + 'P';
    };
  });
}