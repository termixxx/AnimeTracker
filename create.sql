CREATE TABLE "user"
(
    id            SERIAL8 PRIMARY KEY,
    name          TEXT         NOT NULL,
    login         TEXT         NOT NULL,
    password_hash VARCHAR(128) NOT NULL,
    picture_url   TEXT
);

CREATE TABLE anime
(
    id              SERIAL8 PRIMARY KEY,
    name            TEXT    NOT NULL,
    count_of_series INTEGER NOT NULL,
    genres          TEXT    NOT NULL,
    description     TEXT,
    release_year    DATE    NOT NULL,
    picture_url     TEXT    NOT NULL
);


CREATE TABLE user_anime
(
    id                        SERIAL8 PRIMARY KEY,
    number_of_episodes_viewed INTEGER DEFAULT 0        NOT NULL,
    favorite                  BOOLEAN DEFAULT FALSE    NOT NULL,
    comment                   TEXT,
    date_added                DATE                     NOT NULL,
    condition                 TEXT    DEFAULT 'Смотрю' NOT NULL,
    rating                    NUMERIC(1, 2),
    anime_id                  BIGINT,
    user_id                   BIGINT
);
ALTER TABLE user_anime
    ADD CONSTRAINT fk_anime
        FOREIGN KEY (anime_id)
            REFERENCES anime (id)
            ON DELETE CASCADE;
ALTER TABLE user_anime
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES "user" (id)
            ON DELETE CASCADE;

-- дропы
drop table user_anime;
drop table "user";
drop table anime;
