CREATE DATABASE grabber;

CREATE TABLE post (
	id SERIAL PRIMARY KEY,
	name TEXT,
	link TEXT UNIQUE,
	text TEXT,
	created TIMESTAMP
);

