/**
 * 
 * @param n 
 * @returns 
 */
export function range(n: number): number[] {
  return Array.from({ length: n }, (_, i) => i);
}

/**
 * Adds up all numbers in a 2D array
 * @returns the sum of the numbers
 */
export function addScores(scores: number[][]): number {
  const totalScore: number = scores.reduce((acc, row) => {
        return acc + row.reduce((acc2, val) => {
          return acc2 + val;
        }, 0);
      }, 0);
  return totalScore;
}

/**
 * Starts a timeout.
 * @param duration the timeout duration in milliseconds
 */
export const wait = (duration: number) => new Promise((resolve) => setTimeout(resolve, duration));