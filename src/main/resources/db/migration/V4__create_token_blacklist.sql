CREATE TABLE token_blacklist (
    id          UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    token       TEXT        NOT NULL UNIQUE,
    blacklisted_at TIMESTAMP NOT NULL DEFAULT NOW(),
    expires_at  TIMESTAMP   NOT NULL
);

CREATE INDEX idx_token_blacklist_token ON token_blacklist(token);
CREATE INDEX idx_token_blacklist_expires_at ON token_blacklist(expires_at);