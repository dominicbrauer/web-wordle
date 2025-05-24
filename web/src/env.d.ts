/// <reference types="astro/client" />

import type { User } from "./lib/models";

declare global {
	namespace App {
		interface Locals {
			user: User;
		}
	}
}
