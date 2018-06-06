CREATE TABLE contact(
    contact_id      BIGINT not null PRIMARY KEY,
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
CREATE INDEX idx_contact_organization
    ON contact(organization);
CREATE INDEX idx_contact_person
    ON contact(firstname, middlenames, lastname);

CREATE TABLE contact_relation (
    contact_relation_id BIGINT not null PRIMARY KEY,
    origin_contact_id   BIGINT not null,
    relation_name       TEXT CHARACTER SET utf8,
    related_contact_id  BIGINT not null
);

CREATE TABLE email_address (
    email_address TEXT CHARACTER SET utf8 PRIMARY KEY,
    category      TEXT CHARACTER SET utf8,
    contact_id    BIGINT not null
);

CREATE TABLE phone_number (
    phone_number    TEXT CHARACTER SET utf8 PRIMARY KEY,
    contact_id      BIGINT not null,
    category        TEXT CHARACTER SET utf8
);

