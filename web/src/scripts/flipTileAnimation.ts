import { SETTINGS } from "../lib/constants";

export async function flipTile(tile: HTMLDivElement, color: string): Promise<void> {
  const duration: number = SETTINGS.charTileFlipAnimationDuration / 2;
  const animationAngle: string = SETTINGS.charTileFlipAnimationDirection;

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
      tile.classList.add(`char-feedback-${color}`);
  
      const secondHalf = tile.animate([
        { transform: `rotate${animationAngle}(270deg)` },
        { transform: `rotate${animationAngle}(360deg)` }
      ], {
        duration: duration,
        easing: 'linear',
        fill: 'forwards'
      });

      // await secondHalf.finished;
      resolve('');
    };
  });  
}