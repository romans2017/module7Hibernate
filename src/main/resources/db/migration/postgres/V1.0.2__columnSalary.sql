ALTER TABLE public.developers
	ADD COLUMN salary integer;

UPDATE
	public.developers
SET salary = random() * 10000
WHERE salary IS NULL;

ALTER TABLE public.developers
	ALTER COLUMN salary SET NOT NULL;