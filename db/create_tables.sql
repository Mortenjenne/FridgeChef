-- db/create_tables.sql
-- Schema for FridgeChef application (PostgreSQL)
-- Creates empty tables used by the application.

BEGIN;

-- Accounts table
CREATE TABLE IF NOT EXISTS accounts (
  accountID SERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  accountName VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

-- Fridges: one fridge per account
CREATE TABLE IF NOT EXISTS fridges (
  fridgeID SERIAL PRIMARY KEY,
  accountID INTEGER NOT NULL UNIQUE REFERENCES accounts(accountID) ON DELETE CASCADE
);

-- Ingredients: IDs are expected to come from the Spoonacular API, so use integer as primary key
CREATE TABLE IF NOT EXISTS ingredients (
  ingredientID INTEGER PRIMARY KEY,
  name VARCHAR(255),
  image VARCHAR(255)
);

-- Account ingredients: which ingredients each account (fridge) contains
CREATE TABLE IF NOT EXISTS account_ingredients (
  id SERIAL PRIMARY KEY,
  accountID INTEGER NOT NULL REFERENCES accounts(accountID) ON DELETE CASCADE,
  ingredientID INTEGER NOT NULL REFERENCES ingredients(ingredientID) ON DELETE CASCADE,
  UNIQUE (accountID, ingredientID)
);

CREATE INDEX IF NOT EXISTS idx_account_ingredients_account ON account_ingredients(accountID);
CREATE INDEX IF NOT EXISTS idx_account_ingredients_ingredient ON account_ingredients(ingredientID);

-- Favorite dishes: store API dish IDs per account
CREATE TABLE IF NOT EXISTS favorite_dishes (
  id SERIAL PRIMARY KEY,
  accountID INTEGER NOT NULL REFERENCES accounts(accountID) ON DELETE CASCADE,
  dishID INTEGER NOT NULL,
  UNIQUE (accountID, dishID)
);

CREATE INDEX IF NOT EXISTS idx_favorite_dishes_account ON favorite_dishes(accountID);

COMMIT;

-- To apply this script locally:
-- psql -h <host> -U <user> -d <database> -f db/create_tables.sql
-- Example (local default):
-- psql -h localhost -U postgres -d fridgechef -f db/create_tables.sql

