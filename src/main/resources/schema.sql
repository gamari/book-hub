CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY NOT NULL,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS books (
    id UUID PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    isbn10 TEXT UNIQUE,
    isbn13 TEXT UNIQUE,
    author TEXT,
    published_date DATE,
    genre TEXT,
    thumbnail TEXT,
    summary TEXT
);
CREATE TABLE IF NOT EXISTS reading_books (
    id UUID PRIMARY KEY NOT NULL,
    user_id UUID NOT NULL,
    book_id UUID,
    title TEXT,
    is_reading BOOLEAN NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
CREATE TABLE IF NOT EXISTS reading_histories (
    id UUID PRIMARY KEY NOT NULL,
    book_id UUID NOT NULL,
    note TEXT,
    commit_date TIMESTAMP NOT NULL,
    FOREIGN KEY (book_id) REFERENCES reading_books(id)
);
CREATE TABLE IF NOT EXISTS memos (
    id UUID PRIMARY KEY NOT NULL,
    reading_book_id UUID NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reading_book_id) REFERENCES reading_books(id)
);