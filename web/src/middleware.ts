import { defineMiddleware } from "astro:middleware";
import type { User } from "./lib/models";

export const onRequest = defineMiddleware(async (ctx, next) => {
	const sessionCookie = ctx.cookies.get("session");

	if (!sessionCookie) {
		return next();
	}

	try {
		const userData: User = await requestUserBySession(sessionCookie.value);
		ctx.locals.user = userData;
	} catch (e) {}

	return next();
});

/**
 * 
 */
async function requestUserBySession(cookieValue: string) {
	const response = await fetch('http://localhost:8080/session/get-user', {
		method: 'POST',
		body: cookieValue,
	});

	if (!response.ok) {
		throw new Error(`Error: ${response.statusText}`);
	}
	return await response.json() as User;
}