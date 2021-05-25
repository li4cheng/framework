create table if not exists district
(
    id       bigint,
    pid      bigint,
    deep     bigint,
    name     varchar(255),
    prefix   varchar(255),
    pinyin   varchar(255),
    ext_id   bigint,
    ext_name varchar(255),
    primary key (id)
    );
