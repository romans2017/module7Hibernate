ALTER TABLE projects ADD IF NOT EXISTS cost integer;

UPDATE 
	projects 
SET cost = random() * 1000000 
WHERE cost IS NULL;

ALTER TABLE public.projects
	ALTER COLUMN cost SET NOT NULL;