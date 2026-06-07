CREATE TABLE categories (
    id       UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    name     VARCHAR(100) NOT NULL,
    type     VARCHAR(20)  NOT NULL,
    user_id  UUID         NOT NULL,

    CONSTRAINT fk_categories_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_categories_user_id ON categories(user_id);