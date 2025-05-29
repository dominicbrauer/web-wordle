import { WORD_LENGTH } from "./config";

/**
 * Represents the word grid cursor where letters are inserted.
 */
export class Cursor {
	public x: number;
	public y: number;
	public selfSet: boolean;
	public oob: boolean; // oob: out of bounds

	constructor(x: number, y: number, selfSet: boolean, oob: boolean) {
		this.x = x;
		this.y = y;
		this.selfSet = selfSet;
		this.oob = oob;
	}

	public coordToIdx(x: number = this.x, y: number = this.y): number {
		return y * WORD_LENGTH + x;
	}

	public static coordToIdx(x: number, y: number) {
		return y * WORD_LENGTH + x;
	}

	public idxToCoord(idx: number): number[] {
		return [idx % WORD_LENGTH, Math.floor(idx / WORD_LENGTH)];
	}

	public getPos(): number {
		return this.coordToIdx();
	}

	public addSelection(tiles: NodeListOf<HTMLDivElement>) {
		tiles[this.getPos()]?.classList.add('char-tile-selected');
	}

	public removeSelection(tiles: NodeListOf<HTMLDivElement>) {
		tiles[this.getPos()]?.classList.remove('char-tile-selected');
	}
}