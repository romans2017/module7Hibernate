INSERT INTO companies (id, country, name) VALUES (1, 'Ukraine', 'Company 1');
INSERT INTO companies (id, country, name) VALUES (2, 'USA', 'Company 2');
INSERT INTO companies (id, country, name) VALUES (3, 'USA', 'Company 3');
INSERT INTO companies (id, country, name) VALUES (4, 'GERMANY', 'Company 4');
INSERT INTO companies (id, country, name) VALUES (5, 'GERMANY', 'Company 5');

INSERT INTO customers (id, country, name) VALUES (1, 'Ukraine', 'Customer 1');
INSERT INTO customers (id, country, name) VALUES (2, 'Ukraine', 'Customer 2');
INSERT INTO customers (id, country, name) VALUES (3, 'USA', 'Customer 3');
INSERT INTO customers (id, country, name) VALUES (4, 'France', 'Customer 4');
INSERT INTO customers (id, country, name) VALUES (5, 'France', 'Customer 5');

INSERT INTO skills (id, language, level) VALUES (1, 'Java', 'Junior');
INSERT INTO skills (id, language, level) VALUES (2, 'Java', 'Middle');
INSERT INTO skills (id, language, level) VALUES (3, 'Java', 'Senior');
INSERT INTO skills (id, language, level) VALUES (4, 'JavaScript', 'Junior');
INSERT INTO skills (id, language, level) VALUES (5, 'JavaScript', 'Middle');
INSERT INTO skills (id, language, level) VALUES (6, 'JavaScript', 'Senior');

INSERT INTO developers (id, company_id, age, name) VALUES (1, 1, 23, 'Developer 1');
INSERT INTO developers (id, company_id, age, name) VALUES (2, 2, 30, 'Developer 2');
INSERT INTO developers (id, company_id, age, name) VALUES (3, 3, 50, 'Developer 3');
INSERT INTO developers (id, company_id, age, name) VALUES (4, 4, 18, 'Developer 4');
INSERT INTO developers (id, company_id, age, name) VALUES (5, 5, 28, 'Developer 5');
INSERT INTO developers (id, company_id, age, name) VALUES (6, 5, 16, 'Developer 6');
INSERT INTO developers (id, company_id, age, name) VALUES (7, 1, 23, 'Developer 7');
INSERT INTO developers (id, company_id, age, name) VALUES (8, 2, 30, 'Developer 8');
INSERT INTO developers (id, company_id, age, name) VALUES (9, 3, 50, 'Developer 9');
INSERT INTO developers (id, company_id, age, name) VALUES (10, 4, 18, 'Developer 10');
INSERT INTO developers (id, company_id, age, name) VALUES (11, 5, 28, 'Developer 11');
INSERT INTO developers (id, company_id, age, name) VALUES (12, 5, 16, 'Developer 12');

INSERT INTO projects (id, company_id, customer_id, name, description) VALUES (1, 2, 1, 'Project 1', 'important, immediate');
INSERT INTO projects (id, company_id, customer_id, name, description) VALUES (2, 1, 2, 'Project 2', 'important');
INSERT INTO projects (id, company_id, customer_id, name, description) VALUES (3, 4, 2, 'Project 3', 'immediate');
INSERT INTO projects (id, company_id, customer_id, name, description) VALUES (4, 4, 3, 'Project 4', 'not important');
INSERT INTO projects (id, company_id, customer_id, name, description) VALUES (5, 4, 5, 'Project 5', 'pure');
INSERT INTO projects (id, company_id, customer_id, name, description) VALUES (6, 1, 5, 'Project 6', 'lazy');

INSERT INTO developers_projects (id, developer_id, project_id) VALUES (1, 2, 1);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (2, 1, 2);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (3, 4, 3);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (4, 4, 4);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (5, 4, 5);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (6, 1, 6);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (7, 8, 1);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (8, 7, 2);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (9, 10, 3);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (10, 10, 4);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (11, 10, 5);
INSERT INTO developers_projects (id, developer_id, project_id) VALUES (12, 7, 6);

INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (1, 1, 3);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (2, 2, 2);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (3, 3, 4);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (4, 4, 4);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (5, 5, 5);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (6, 6, 6);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (7, 7, 3);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (8, 8, 2);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (9, 9, 2);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (10, 10, 4);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (11, 11, 3);
INSERT INTO developers_skills (id, developer_id, skill_id) VALUES (12, 12, 1);
