ALTER TABLE dbo.projects
	ADD COLUMN creation_date date;

UPDATE
	dbo.projects
SET creation_date = '2001-01-01'
WHERE creation_date IS NULL;

ALTER TABLE dbo.projects
	ALTER COLUMN creation_date SET NOT NULL;
