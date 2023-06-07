DROP TABLE IF EXISTS tbl_user;
DROP TABLE IF EXISTS tbl_post;

-- 1
CREATE TABLE tbl_user
(
    id          INT AUTO_INCREMENT,
    name        VARCHAR(50)  NOT NULL UNIQUE ,
    password    VARCHAR(255) NOT NULL,
    email       VARCHAR(50)  NOT NULL UNIQUE ,
    phone       VARCHAR(11)  NOT NULL,
    birthdate   DATE         NOT NULL,
    profile_url TEXT         NULL,
    role_id     INT          NOT NULL,
    grade_id    INT          NOT NULL,
    created_at  DATE     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATE     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    primary key (id)
);

-- 2
CREATE TABLE tbl_post
(
    id         INT AUTO_INCREMENT,
    user_id    INT         NOT NULL,
    title      VARCHAR(50) NOT NULL,
    content    TEXT        NOT NULL,
    attachment TEXT        NULL,
    is_visible TINYINT     NOT NULL default 0,
    ctg_id     INT         not null,
    created_at DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    constraint posts_ibfk_1
        foreign key (user_id) references tbl_user (id)
            on delete cascade
);
