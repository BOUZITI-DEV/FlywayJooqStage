CREATE TABLE ticket(
    id SERIAL PRIMARY KEY,
    dev BIGINT,
    FOREIGN KEY(dev) REFERENCES dev(id)
);