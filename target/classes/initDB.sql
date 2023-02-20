CREATE TABLE IF NOT EXISTS Sentence_Entity
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    input  VARCHAR(1000) NOT NULL,
    output VARCHAR(1000) NOT NULL,
    ip     VARCHAR(50)   NOT NULL
);

CREATE TABLE IF NOT EXISTS Word_Entity
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(30) NOT NULL,
    id_sentence BIGINT NOT NULL
);