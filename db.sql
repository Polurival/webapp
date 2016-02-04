CREATE TABLE resume (
  uuid      CHAR(36) PRIMARY KEY,
  full_name TEXT NOT NULL
);

CREATE TABLE contact (
  id          SERIAL PRIMARY KEY,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT     NOT NULL
  --   CONSTRAINT contact_fk FOREIGN KEY (resume_uuid)
  --   REFERENCES resume (uuid) ON DELETE CASCADE
);
CREATE UNIQUE INDEX contact_idx ON contact(resume_uuid, type);

CREATE TABLE text_section (
  id          SERIAL PRIMARY KEY,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value        TEXT     NOT NULL
);
CREATE UNIQUE INDEX text_section_idx ON text_section(resume_uuid, type);
