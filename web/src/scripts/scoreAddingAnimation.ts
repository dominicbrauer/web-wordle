import { wait } from "../lib/helpers";

export async function animateScoreAdding(tile: HTMLDivElement, score: number, destinationLabel: HTMLSpanElement) {
	await wait(300);

	// Add element
	const scoreElement = document.createElement('span');
	document.body.appendChild(scoreElement);
	scoreElement.className = "score-element";
	scoreElement.textContent = score.toString();

	scoreElement.style.position = 'absolute';
	scoreElement.style.fontSize = '1.5em';

	const [tilePosX, tilePosY] = getPos(tile, scoreElement);
	const [labelPosX, labelPosY] = getPos(destinationLabel, scoreElement);

	scoreElement.style.left = `${tilePosX}px`;
	scoreElement.style.top = `${tilePosY}px`;

	await new Promise(async (resolve) => {
		const anim = scoreElement.animate([
			{ transform: 'translateX(0)', offset: 0 },
			{ left: `${labelPosX}px`, top: `${labelPosY}px`, offset: 1 }
		], {
			duration: 700,
			easing: 'cubic-bezier(1, 0, 1, 1)',
			fill: 'forwards'
		});

		resolve('');
		anim.onfinish = () => {
			scoreElement.remove();
			const newScore: number = Number(destinationLabel.textContent!.split("P")[0]) + score;
			destinationLabel.textContent = `${newScore}P`;
		};
	});
}

/**
 * Gets the center position of an element based on a reference element.
 * @param elem element of which we want the center position
 * @param referenceElem element which should be centered at that position
 * @returns the x and y position for 'elem'
 */
function getPos(elem: HTMLElement, referenceElem: HTMLElement): [number, number] {
	const refWidth = referenceElem.getBoundingClientRect().width;
	const refHeight = referenceElem.getBoundingClientRect().height;

	const {x, y, width, height} = elem.getBoundingClientRect();
	return [x + width / 2 - refWidth / 2, y + height / 2 - refHeight / 2];
}