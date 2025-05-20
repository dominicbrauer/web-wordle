import type { AstroCookies } from "astro";
import { defineMiddleware } from "astro:middleware";

export const onRequest = defineMiddleware(async (ctx, next) => {

  const cookie = ctx.cookies.get("session");

  if (!cookie) {
    return next();
  }

  // const session = await requestSession(cookie!.value);

  return next();
});

/**
 * 
 */
// async function requestSession(cookieValue: string) {
//   const response = await fetch('http://localhost:8080/api/guess', {
//     method: 'POST',
//     credentials: 'include',
//     headers: {
//       'Content-Type': 'application/json',
//     }
//   });

//   if (!response.ok) {
//     throw new Error(`Error: ${response.statusText}`);
//   }
//   return;
// }