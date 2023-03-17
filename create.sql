CREATE TABLE user_account
(
    id          SERIAL8 PRIMARY KEY,
    name        TEXT NOT NULL,
    login       TEXT NOT NULL,
    password    TEXT NOT NULL,
    picture_url TEXT NULL
);

CREATE TABLE anime
(
    id              SERIAL8 PRIMARY KEY,
    name            TEXT    NOT NULL,
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
    rating                    NUMERIC(1, 2)            NULL,
    anime_id                  BIGINT REFERENCES anime (id),
    user_account_id           BIGINT REFERENCES user_account (id) -- поменять в коде юзераккаунт
);
