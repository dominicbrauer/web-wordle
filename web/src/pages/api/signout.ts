import type { APIRoute } from "astro";

export const GET: APIRoute = async (ctx) => {
	const cookieValue = ctx.cookies.get("session")?.value;

	const response = await fetch('http://localhost:8080/session/signout', {
		method: 'POST',
		body: cookieValue,
	});

	if (!response.ok) {
		throw new Error(`Error: ${response.statusText}`);
	}

	ctx.cookies.delete('session');

	return ctx.redirect("/");
};