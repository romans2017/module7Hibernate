CREATE TABLE public.companies_developers
(	
	developer_id integer NOT NULL,
    company_id integer NOT NULL,
    CONSTRAINT pk_companies_developers_id PRIMARY KEY (developer_id),
    CONSTRAINT fk_companies_developers_company_id FOREIGN KEY (company_id) REFERENCES companies (id),
    CONSTRAINT fk_companies_developers_developer_id FOREIGN KEY (developer_id) REFERENCES developers (id)
);

INSERT INTO companies_developers (company_id, developer_id) SELECT company_id, id FROM developers;

ALTER TABLE developers DROP CONSTRAINT IF EXISTS fk_company_id;