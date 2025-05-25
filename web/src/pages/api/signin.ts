import type { APIRoute } from "astro";

/**
 * Represents one week or 7 days in milliseconds.
 */
const WEEK: number = 1000 * 60 * 60 * 24 * 7;

export const GET: APIRoute = (ctx) => {
	const token = ctx.url.searchParams.get("token");
	const site = import.meta.env.PROD ? ctx.site : new URL("http://localhost:4321");

	if (!token) {
		return new Response("Token required!", { status: 400 });
	}

	ctx.cookies.set("session", token, {
		httpOnly: true,
		domain: site!.hostname,
		expires: new Date(Date.now() + WEEK),
		sameSite: "lax",
		secure: import.meta.env.PROD,
		path: "/"
	});

	return ctx.redirect("/");
};