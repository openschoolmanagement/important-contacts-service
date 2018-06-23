--
--   Copyright 2018 Open School Management
--
--   Licensed under the Apache License, Version 2.0 (the "License");
--   you may not use this file except in compliance with the License.
--   You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--   Unless required by applicable law or agreed to in writing, software
--   distributed under the License is distributed on an "AS IS" BASIS,
--   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--   See the License for the specific language governing permissions and
--   limitations under the License.
--

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

