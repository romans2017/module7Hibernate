
CREATE TABLE skills
(
    id integer NOT NULL,
	language varchar(50) NOT NULL,
   	level varchar(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE companies
(
    id integer NOT NULL,
	name varchar(100) NOT NULL,
   	country varchar(100),
    PRIMARY KEY (id)
);

CREATE TABLE customers
(
    id integer NOT NULL,
	name varchar(100) NOT NULL,
   	country varchar(100),
    PRIMARY KEY (id)
);

CREATE TABLE developers
(
    id integer NOT NULL,
	company_id integer NOT NULL,
    name varchar(100) NOT NULL,
    age integer NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT FK_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE projects
(
    id integer NOT NULL,
	company_id integer NOT NULL,
	customer_id integer NOT NULL,
	name varchar(100) NOT NULL,
   	description text,
    PRIMARY KEY (id),
	CONSTRAINT FK_company_id FOREIGN KEY (company_id) REFERENCES companies (id),
	CONSTRAINT FK_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE developers_skills
(	
	id integer NOT NULL,
	developer_id integer NOT NULL,
	skill_id integer NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_developer_id FOREIGN KEY (developer_id) REFERENCES developers (id),
	CONSTRAINT FK_skill_id FOREIGN KEY (skill_id) REFERENCES skills (id)
);

CREATE TABLE developers_projects
(	
	id integer NOT NULL,
	developer_id integer NOT NULL,
	project_id integer NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_developer_id FOREIGN KEY (developer_id) REFERENCES developers (id),
	CONSTRAINT FK_project_id FOREIGN KEY (project_id) REFERENCES projects (id)
);

