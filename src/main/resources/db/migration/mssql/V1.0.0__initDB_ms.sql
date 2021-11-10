
CREATE TABLE dbo.skills
(
    id integer not null identity(1,1),
	language varchar(50) NOT NULL,
   	level varchar(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE dbo.companies
(
    id integer not null identity(1,1),
	name varchar(100) NOT NULL,
   	country varchar(100),
    PRIMARY KEY (id)
);

CREATE TABLE dbo.customers
(
    id integer not null identity(1,1),
	name varchar(100) NOT NULL,
   	country varchar(100),
    PRIMARY KEY (id)
);

CREATE TABLE dbo.developers
(
    id integer not null identity(1,1),
	company_id integer NOT NULL,
    name varchar(100) NOT NULL,
    age integer NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT FK_developers_company_id FOREIGN KEY (company_id) REFERENCES companies (id)
);

CREATE TABLE dbo.projects
(
    id integer not null identity(1,1),
	company_id integer NOT NULL,
	customer_id integer NOT NULL,
	name varchar(100) NOT NULL,
   	description text,
    PRIMARY KEY (id),
	CONSTRAINT FK_projects_company_id FOREIGN KEY (company_id) REFERENCES companies (id),
	CONSTRAINT FK_projects_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE dbo.developers_skills
(	
	id integer not null identity(1,1),
	developer_id integer NOT NULL,
	skill_id integer NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_developers_skills_developer_id FOREIGN KEY (developer_id) REFERENCES developers (id),
	CONSTRAINT FK_developers_skills_skill_id FOREIGN KEY (skill_id) REFERENCES skills (id)
);

CREATE TABLE dbo.developers_projects
(	
	id integer not null identity(1,1),
	developer_id integer NOT NULL,
	project_id integer NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT FK_developers_projects_developer_id FOREIGN KEY (developer_id) REFERENCES developers (id),
	CONSTRAINT FK_developers_projects_project_id FOREIGN KEY (project_id) REFERENCES projects (id)
);

