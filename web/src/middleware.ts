import { defineMiddleware } from "astro:middleware";

export const onRequest = defineMiddleware(async (ctx, next) => {
  const sessionCookie = ctx.cookies.get("session");

  if (!sessionCookie) {
    return next();
  }

  const userData = await requestUserBySession(sessionCookie.value);

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
  return;
}