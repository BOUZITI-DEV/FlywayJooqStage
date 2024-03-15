CREATE TABLE book(
    id SERIAL PRIMARY KEY,
    title VARCHAR(50)
);
CREATE TABLE author(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);
CREATE TABLE bookAuthor(
    book_id BIGINT,
    author_id BIGINT,
    FOREIGN KEY(book_id) REFERENCES book(id),
    FOREIGN KEY(author_id) REFERENCES author(id),
    PRIMARY KEY(book_id, author_id)
);