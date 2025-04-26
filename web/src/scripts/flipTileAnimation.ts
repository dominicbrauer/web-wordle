export async function flipTile(tile: HTMLDivElement, color: string): Promise<void> {
  const duration: number = 500;

  const firstHalf = tile.animate([
    { transform: 'rotateY(0deg)' },
    { transform: 'rotateY(90deg)' }
  ], {
    duration: duration / 2,
    easing: 'linear',
    fill: 'forwards'
  });

  firstHalf.onfinish = () => {
    tile.classList.add(`char-feedback-${color}`);

    tile.animate([
      { transform: 'rotateY(270deg)' },
      { transform: 'rotateY(360deg)' }
    ], {
      duration: duration / 2,
      easing: 'linear',
      fill: 'forwards'
    });
  }
}