ALTER TABLE public.projects
	ADD COLUMN cost integer;

UPDATE
	public.projects
SET cost = random() * 1000000
WHERE cost IS NULL;

ALTER TABLE public.projects
	ALTER COLUMN cost SET NOT NULL;