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
    return y * 5 + x;
  }

  public idxToCoord(idx: number): number[] {
    return [idx % 5, Math.floor(idx / 5)];
  }

  public getPos(): number {
    return this.coordToIdx();
  }

  public addSelection(tiles: NodeListOf<HTMLDivElement>): void {
    tiles[this.getPos()]?.classList.add('char-tile-selected');
  }

  public removeSelection(tiles: NodeListOf<HTMLDivElement>): void {
    tiles[this.getPos()]?.classList.remove('char-tile-selected');
  }
}