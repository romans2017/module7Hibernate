ALTER TABLE companies
    ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 100 );

ALTER TABLE customers
    ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 100 );
	
ALTER TABLE developers
    ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 100 );
	
ALTER TABLE projects
    ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 100 );
	
ALTER TABLE skills
    ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 100 );