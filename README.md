# web-wordle

A Wordle web application. In this version of Wordle, you can earn points and set highscores depending on how well you guess.

## Getting Started
The frontend is built on Astro, which needs Node.js to operate.
The backend uses Java SpringBoot (JDK 21).

All Astro-related commands are run from the `/web` directory, from a terminal:

| Command                | Action                                           |
| :----------------------| :----------------------------------------------- |
| `pnpm install`         | Installs dependencies                            |
| `pnpm dev`             | Starts local dev server at `localhost:4321`      |
| `pnpm build`           | Build your production site to `./dist/`          |
| `pnpm preview`         | Preview your build locally, before deploying     |
| `pnpm astro ...`       | Run CLI commands like `astro add`, `astro check` |
| `pnpm astro -- --help` | Get help using the Astro CLI                     |

To run the SpringBoot server, navigate into `/server` and run:
```sh
gradle build
gradle bootRun
```

## Tech Stack
The following main technologies were used for this application:
- Frontend: [Astro v5.7.4](https://astro.build/)
- Backend: [Java SpringBoot v3.4.4](https://spring.io/projects/spring-boot)

The database is running on **H2**, which stores data inside a `.db` file in `server/data`. To communicate between SpringBoot and the DB, we use **Spring Data JPA**.

## TO-DO List
- add name/email/password change options
- add session expiration check with deletion
- add game assistant feedback messages
### Other
- beautify
- comment code
- remove unnecessary code
- plan presentation
- responsive design
- penetration testing