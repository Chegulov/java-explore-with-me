DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS compilations CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS events_compilations CASCADE;
DROP TABLE IF EXISTS requests CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

CREATE TABLE IF NOT EXISTS categories
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS compilations
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    pinned BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS locations
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    lat FLOAT NOT NULL,
    lon FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(250) NOT NULL,
    email VARCHAR(254) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS events
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    annotation VARCHAR(2000) NOT NULL,
    category_id BIGINT NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
    confirmed_requests BIGINT NOT NULL,
    event_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    published_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    initiator_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    location_id BIGINT NOT NULL REFERENCES locations(id) ON DELETE CASCADE,
    paid BOOLEAN NOT NULL,
    description VARCHAR(7000) NOT NULL,
    participant_limit BIGINT NOT NULL,
    request_moderation BOOLEAN NOT NULL,
    state VARCHAR(50) NOT NULL,
    title VARCHAR(120) NOT NULL,
    views BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS requests
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    requester_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS events_compilations
(
    event_id BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    compilation_id BIGINT NOT NULL REFERENCES compilations(id) ON DELETE CASCADE,
    CONSTRAINT pk_events_compilations
        PRIMARY KEY (event_id, compilation_id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    text VARCHAR(2000) NOT NULL,
    created_on TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_on TIMESTAMP WITHOUT TIME ZONE,
    published_on TIMESTAMP WITHOUT TIME ZONE,
    state VARCHAR(50) NOT NULL,
    author_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    event_id  BIGINT NOT NULL REFERENCES events(id) ON DELETE CASCADE
);