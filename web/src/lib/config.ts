export const KEYBOARD_KEYS: Array<Array<string>> = [
  ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'],
  ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'],
  ['Z', 'X', 'C', 'V', 'B', 'N', 'M']
]; // default: QWERTY-layout

export const SETTINGS = {
  charTileFlipAnimationDuration: 500, // time milliseconds. default: 500
  charTileFlipAnimationDirection: 'Y', // 'X' or 'Y' based on your preference. default: 'Y'
};

export const WORD_LENGTH: number = 5; // default: 5
export const GUESSES: number = 6; // default: 6
export const ROW_MULTIPLIERS: number[] = [20, 7, 4, 2.5, 1.5, 1]; // default: [20, 7, 4, 2.5, 1.5, 1]

export const GRAY_TILE_VALUE = 1; // default: 1
export const YELLOW_TILE_VALUE = 2; // default: 2
export const GREEN_TILE_VALUE = 3; // default: 3