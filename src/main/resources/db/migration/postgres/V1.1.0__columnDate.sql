ALTER TABLE public.projects
	ADD COLUMN creation_date date;

UPDATE
	public.projects
SET creation_date = '2001-01-01'
WHERE creation_date IS NULL;

ALTER TABLE public.projects
	ALTER COLUMN creation_date SET NOT NULL;

