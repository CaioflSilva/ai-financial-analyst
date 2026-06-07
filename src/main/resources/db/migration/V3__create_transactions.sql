CREATE TABLE transactions (
    id           UUID           PRIMARY KEY DEFAULT gen_random_uuid(),
    description  VARCHAR(255)   NOT NULL,
    amount       NUMERIC(15, 2) NOT NULL,
    type         VARCHAR(20)    NOT NULL,
    date         DATE           NOT NULL,
    category_id  UUID           NOT NULL,
    user_id      UUID           NOT NULL,

    CONSTRAINT fk_transactions_category
        FOREIGN KEY (category_id)
        REFERENCES categories(id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_transactions_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_category_id ON transactions(category_id);
CREATE INDEX idx_transactions_date ON transactions(date);