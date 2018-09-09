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

--
-- Domain Model
--
CREATE TABLE contact (
    id              VARCHAR(36) NOT NULL,
    organization    TEXT CHARACTER SET utf8 NULL,
    salutation      TEXT CHARACTER SET utf8 NULL,
    academicTitle   TEXT CHARACTER SET utf8 NULL,
    givenName       TEXT CHARACTER SET utf8 NULL,
    middleNames     TEXT CHARACTER SET utf8 NULL,
    lastName        TEXT CHARACTER SET utf8 NULL,
    notes           TEXT CHARACTER SET utf8 NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE emailAddress (
    id              VARCHAR(36) NOT NULL,
    label           TEXT CHARACTER SET utf8 NULL,
    email           TEXT CHARACTER SET utf8 NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE labeledDate (
    id              VARCHAR(36) NOT NULL,
    label           TEXT CHARACTER SET utf8 NULL,
    dateValue       DATE NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE phoneNumber (
    id              VARCHAR(36) NOT NULL,
    label           TEXT CHARACTER SET utf8 NULL,
    phoneNumber     TEXT CHARACTER SET utf8 NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE postalAddress (
    id              VARCHAR(36) NOT NULL,
    label           TEXT CHARACTER SET utf8 NULL,
    street          TEXT CHARACTER SET utf8 NULL,
    city            TEXT CHARACTER SET utf8 NULL,
    state           TEXT CHARACTER SET utf8 NULL,
    postalCode      TEXT CHARACTER SET utf8 NULL,
    country         TEXT CHARACTER SET utf8 NULL,
    isoCountryCode  TEXT CHARACTER SET utf8 NULL,
    PRIMARY KEY (`id`)
);
