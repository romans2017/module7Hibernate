ALTER TABLE dbo.developers
	ADD COLUMN salary integer;

UPDATE
	dbo.developers
SET salary = random() * 10000
WHERE salary IS NULL;

ALTER TABLE dbo.developers
	ALTER COLUMN salary SET NOT NULL;
