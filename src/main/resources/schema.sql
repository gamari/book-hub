CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS books (
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    isbn10 TEXT,
    isbn13 TEXT,
    author TEXT,
    published_date DATE,
    genre TEXT,
    summary TEXT
);
CREATE TABLE IF NOT EXISTS reading_books (
    id UUID PRIMARY KEY,
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
    id UUID PRIMARY KEY,
    book_id UUID NOT NULL,
    note TEXT,
    commit_date TIMESTAMP NOT NULL,
    FOREIGN KEY (book_id) REFERENCES reading_books(id)
);