ALTER TABLE developers_projects DROP COLUMN IF EXISTS id;
ALTER TABLE developers_skills DROP COLUMN IF EXISTS id;

ALTER TABLE developers_projects
    ADD CONSTRAINT pk_dp_id PRIMARY KEY (developer_id, project_id);
ALTER TABLE developers_skills
    ADD CONSTRAINT pk_ds_id PRIMARY KEY (developer_id, skill_id);