import { SETTINGS } from "../lib/config";

export async function flipTile(tile: HTMLDivElement, color: string): Promise<void> {
  const duration: number = SETTINGS.charTileFlipAnimationDuration / 2;
  let animationAngle: string;
  if (color == "reset") {
    animationAngle = 'X';
  } else {
    animationAngle = SETTINGS.charTileFlipAnimationDirection;
  }

  const firstHalf = tile.animate([
    { transform: `rotate${animationAngle}(0deg)` },
    { transform: `rotate${animationAngle}(90deg)` }
  ], {
    duration: duration,
    easing: 'linear',
    fill: 'forwards'
  });

  await new Promise((resolve) => {
    firstHalf.onfinish = async () => {
      if (color == "reset") {
        tile.classList.remove('char-feedback-gray', 'char-feedback-yellow', 'char-feedback-green');
        tile.firstElementChild!.textContent = "";
      } else {
        tile.classList.add(`char-feedback-${color}`);
      }

      tile.animate([
        { transform: `rotate${animationAngle}(270deg)` },
        { transform: `rotate${animationAngle}(360deg)` }
      ], {
        duration: duration,
        easing: 'linear',
        fill: 'forwards'
      });

      resolve('');
    };
  });
}