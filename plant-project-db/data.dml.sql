DELETE FROM plants;
DELETE FROM hydration_levels;
DELETE FROM sunlight_levels;
DELETE FROM accounts;

INSERT INTO accounts
	(first_name, last_name, email, password)
	VALUES
	('Damla', 'Test', 'dont@testme', '12345678Test');

INSERT INTO hydration_levels
	(name, logical_order)
	VALUES 
	('Low', 1), ('Medium', 2), ('A lot', 3);
	
INSERT INTO sunlight_levels
	(name, logical_order)
	VALUES 
	('Part shade', 1), ('Part sun', 2), ('Full sun', 3);
	
INSERT INTO plants
	(common_name, latin_name, description, image, hydration_id, sunlight_id, added_at)
	VALUES
	('Calethea', 'Caletheia monstera', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.',	'calethea.jpg',
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'Low'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Part shade'), now()),
	('Z Plant', 'Zamioculcas zamiifolia', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'zplant.jpg', 
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'Medium'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Part sun'), now()),
	('Cactus', 'Hatiora gaertneri', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'cactus.jpg',
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'Low'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Full sun'), now()),
	('Monstera', 'Monstera deliciosa', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'monstera.jpg',
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'Medium'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Part sun'), now()),
	('Pilea', 'Pilea abbreviata', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'pilea.jpg',
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'Medium'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Part sun'), now()),
	('Strelitzia', 'Strelitzia alba', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'strelitzia.jpg',
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'Medium'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Part sun'), now()),
	('Ficus', 'Ficus abbreviata', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'ficus.jpg',
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'A lot'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Part shade'), now()),
	('Livistona', 'Livistona affredi', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'livistona.jpg',
		(SELECT w.id FROM hydration_levels w WHERE w.name = 'Low'),
		(SELECT s.id FROM sunlight_levels s WHERE s.name = 'Full sun'), now());	
	