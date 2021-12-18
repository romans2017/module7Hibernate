CREATE TABLE customers_projects
(	
	project_id integer NOT NULL,
    customer_id integer NOT NULL,
    CONSTRAINT pk_customers_projects_id PRIMARY KEY (project_id),
    CONSTRAINT fk_customers_projects_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT fk_customers_projects_project_id FOREIGN KEY (project_id) REFERENCES projects (id)
);

INSERT INTO customers_projects (customer_id, project_id) SELECT customer_id, id FROM projects;

ALTER TABLE projects DROP CONSTRAINT IF EXISTS fk_customer_id;
ALTER TABLE projects DROP COLUMN IF EXISTS customer_id;