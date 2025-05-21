import { SETTINGS } from "../lib/config";
import { wait } from "../lib/helpers";
import { Scoring } from "../lib/models";

export async function flipTile(tile: HTMLDivElement, color: Scoring | string, delay: number) {
  const duration: number = SETTINGS.charTileFlipAnimationDuration / 2;
  let animationAngle: string;
  if (color == "RESET") {
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

  firstHalf.onfinish = async () => {
    if (color == "RESET") {
      tile.classList.remove('char-feedback-gray', 'char-feedback-yellow', 'char-feedback-green');
      tile.firstElementChild!.textContent = "";
    } else {
      switch(color) {
        case Scoring.GREEN: {
          tile.classList.add('char-feedback-green');
          break;
        }
        case Scoring.YELLOW: {
          tile.classList.add('char-feedback-yellow');
          break;
        }
        case Scoring.GRAY: {
          tile.classList.add('char-feedback-gray');
          break;
        }
      }
    }

    tile.animate([
      { transform: `rotate${animationAngle}(270deg)` },
      { transform: `rotate${animationAngle}(360deg)` }
    ], {
      duration: duration,
      easing: 'linear',
      fill: 'forwards'
    });
  }

  await wait(delay);
}