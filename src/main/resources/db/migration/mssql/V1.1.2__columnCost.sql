ALTER TABLE dbo.projects
	ADD COLUMN cost integer;

UPDATE
	dbo.projects
SET cost = random() * 1000000
WHERE cost IS NULL;

ALTER TABLE dbo.projects
	ALTER COLUMN cost SET NOT NULL;