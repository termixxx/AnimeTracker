CREATE TABLE user_account
(
    id          SERIAL8 PRIMARY KEY,
    name        TEXT NOT NULL,
    login       TEXT NOT NULL UNIQUE,
    password    TEXT NOT NULL,
    picture_url TEXT NULL
);

CREATE TABLE anime
(
    id              SERIAL8 PRIMARY KEY,
    name            TEXT    NOT NULL UNIQUE,
    count_of_series INTEGER NOT NULL,
    genres          TEXT    NOT NULL,
    description     TEXT    NUll,
    release_year    DATE    NOT NULL,
    picture_url     TEXT    NOT NULL
);


CREATE TABLE user_account_anime
(
    number_of_episodes_viewed INTEGER DEFAULT 0        NOT NULL,
    favorite                  BOOLEAN DEFAULT FALSE    NOT NULL,
    comment                   TEXT                     NULL,
    date_added                DATE                     NOT NULL,
    condition                 TEXT    DEFAULT 'Смотрю' NOT NULL,
    rating                    INTEGER                  NULL,
    anime_id                  BIGINT REFERENCES anime (id),
    user_account_id           BIGINT REFERENCES user_account (id)
);

ALTER TABLE user_account_anime
    ADD CONSTRAINT constraint_name UNIQUE (anime_id, user_account_id);