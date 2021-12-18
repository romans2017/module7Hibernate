CREATE TABLE companies_projects
(	
	project_id integer NOT NULL,
    company_id integer NOT NULL,
    CONSTRAINT pk_companies_projects_id PRIMARY KEY (project_id),
    CONSTRAINT fk_companies_projects_company_id FOREIGN KEY (company_id) REFERENCES companies (id),
    CONSTRAINT fk_companies_projects_project_id FOREIGN KEY (project_id) REFERENCES projects (id)
);

INSERT INTO companies_projects (company_id, project_id) SELECT company_id, id FROM projects;

ALTER TABLE projects DROP CONSTRAINT IF EXISTS fk_company_id;
ALTER TABLE projects DROP COLUMN IF EXISTS company_id;