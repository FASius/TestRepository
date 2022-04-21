PRAGMA foreign_keys = ON;

CREATE TABLE "accounts" (
	"id"		 	INTEGER PRIMARY KEY NOT NULL,
	"email" 		TEXT UNIQUE NOT NULL,
	"password" 		TEXT NOT NULL,
	"username" 		TEXT UNIQUE NOT NULL
);

CREATE TABLE "check_ins" (
	"id"		 	INTEGER PRIMARY KEY NOT NULL,
	"created_at" 	INTEGER NOT NULL,
	"description"   TEXT,
	"location" 		TEXT NOT NULL,
	"latitude" 	    REAL NOT NULL,
	"longitude" 	REAL NOT NULL
);

CREATE TABLE "accounts_check_ins" (
	"account_id"	INTEGER NOT NULL,
	"check_in_id" 	INTEGER NOT NULL,
	FOREIGN KEY("account_id") REFERENCES "accounts"("id")
		ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("check_in_id") REFERENCES "check_ins"("id")
		ON UPDATE CASCADE ON DELETE CASCADE,
	UNIQUE("account_id", "check_in_id")
);

INSERT INTO "accounts" ("email", "password", "username")
VALUES ("google@google.com", "123", "fas");

INSERT INTO "check_ins"
("created_at", "description", "location", "latitude", "longitude")
VALUES
(0, "Simple description", "Tokyo", 35.672464894649, 139.75788809439902);

INSERT INTO "accounts_check_ins" ("account_id", "check_in_id")
VALUES (1, 1)
