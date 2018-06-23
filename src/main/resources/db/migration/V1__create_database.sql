CREATE TABLE contact(
    contact_id      BIGINT NOT NULL,
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
-- CREATE INDEX idx_contact_organization
--     ON contact(organization);
-- CREATE INDEX idx_contact_person
--     ON contact(firstname, middlenames, lastname);

CREATE TABLE contact_relation (
    contact_relation_id BIGINT NOT NULL,
    origin_contact_id   BIGINT NOT NULL,
    relation_name       TEXT CHARACTER SET utf8,
    related_contact_id  BIGINT NOT NULL
);

CREATE TABLE email_address (
    email_address TEXT CHARACTER SET utf8,
    category      TEXT CHARACTER SET utf8,
    contact_id    BIGINT NOT NULL
);

CREATE TABLE phone_number (
    phone_number    TEXT CHARACTER SET utf8,
    contact_id      BIGINT NOT NULL,
    category        TEXT CHARACTER SET utf8
);

