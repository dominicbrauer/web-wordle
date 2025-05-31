# web-wordle
A Wordle web application. In this version of Wordle, you can earn points and set highscores depending on how well you guess.

## Getting Started
The frontend is built on [Astro](https://astro.build/). You will need the following to run the application:
- **Node.js** - `v18.20.8` or `v20.3.0`, `v22.0.0` or higher. (`v19` and `v21` are not supported.)
- **Text Editor** - VS Code with the Official Astro extension is recommended.
- **Terminal** - Astro is accessed through its command-line interface (CLI).

Now navigate into the `/web` directory and run:
```sh
pnpm install	# installs dependencies
pnpm dev		# starts local dev server on 'localhost:4321'
```
For further information or help on Astro, you can visit the [Astro Documentation](https://docs.astro.build/en/getting-started/).

To run the SpringBoot server, navigate into `/server` and run:
```sh
gradlew build
gradlew bootRun
```

## Tech Stack
The following main technologies were used for this application:
- Frontend: [Astro v5.7.4](https://astro.build/)
- Backend: [Java SpringBoot v3.4.4](https://spring.io/projects/spring-boot)

The database is running on **H2**, which stores data inside a `.db` file in `server/data`. To communicate between SpringBoot and the DB, we use **Spring Data JPA**.

## API
- [Random Word API](https://random-word-api.vercel.app/) - Used to generate a random five-letter word.
- [Dictionary API](https://dictionaryapi.dev/) - Used to validate if a word is a valid English word.

## Features
The current version of **wordle CHAMPIONS** has the following functionalities:

### Classic Mode
- An infinite runner and the main game mode - if you guess correctly, a new game will start. Collect points for your guesses and for how fast you succeed. Once you cannot guess the solution in six tries, it's over.
- Word Grid: The word grid will show your guesses and their feedback. Unsupported actions will play a headshake animation to increase usability.
- Digital Keyboard: If you do not have an analog keyboard (e.g. on Mobile), you can use the digital keyboard beneath the word grid.
- Scoreboard: Shows all the information for your current game session.
- Assistant: Wolfle is just a happy and cute emotional helper.

### Pages
- Home page: The concept of this game can also be found on the Home page.
- How to Play page: If you're unfamiliar with the general concept of *Wordle*, read the rules and mechanics here.

### Account Management
- You can create an account to store your game data permanently. On your account page, you can find your personal statistics. If you already have an account, just sign in with your credentials.
- Session Management: Instead of signing in each time you visit the site, a session will be created after your signin and stored on your device as a cookie. A valid cookie will sign you in automatically.

---
A project by Dominic Brauer