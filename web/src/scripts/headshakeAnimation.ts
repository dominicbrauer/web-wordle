export function headShake(row: HTMLDivElement): void {
	const animation = row.animate([
		{ transform: 'translate(0)', percentage: 0 },
		{ transform: 'translate(-6px) rotateY(-9deg)', percentage: 6.5 },
		{ transform: 'translate(5px) rotateY(7deg)', percentage: 18.5 },
		{ transform: 'translate(-3px) rotateY(-5deg)', percentage: 31.5 },
		{ transform: 'translate(2px) rotateY(3deg)', percentage: 43.5 },
		{ transform: 'translate(0)', percentage: 50 }
	], {
		duration: 500,
		easing: 'ease-in-out'
	});
	animation.play();
}