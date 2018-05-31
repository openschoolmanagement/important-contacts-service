CREATE TABLE contact(
    contact_id      BIGINT not null,
    organization    TEXT CHARACTER SET utf8,
    salutation      TEXT CHARACTER SET utf8,
    academic_title  TEXT CHARACTER SET utf8,
    firstname       TEXT CHARACTER SET utf8,
    middlenames     TEXT CHARACTER SET utf8,
    lastname        TEXT CHARACTER SET utf8,
    addressline1    TEXT CHARACTER SET utf8,
    addressline2    TEXT CHARACTER SET utf8,
    city            TEXT CHARACTER SET utf8,
    zip_code        TEXT CHARACTER SET utf8,
    country         TEXT CHARACTER SET utf8,
    notes           TEXT CHARACTER SET utf8
);

CREATE TABLE contact_relation (
    contact_relation_id BIGINT not null,
    origin_contact_id   BIGINT not null,
    category            TEXT CHARACTER SET utf8,
    related_contact_id  BIGINT not null
);

CREATE TABLE email_address (
    email_address TEXT CHARACTER SET utf8,
    category      TEXT CHARACTER SET utf8,
    contact_id    BIGINT not null
);

CREATE TABLE phone_number (
    contact_id      BIGINT not null,
    category        TEXT CHARACTER SET utf8,
    phone_number    TEXT CHARACTER SET utf8
);

