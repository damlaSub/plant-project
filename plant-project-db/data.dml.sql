DELETE FROM plants;
DELETE FROM hydration_levels;
DELETE FROM sunlight_levels;
DELETE FROM users;
DELETE FROM roles;

INSERT INTO roles 
	(role_name)
	VALUES 
	('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users
	(first_name, last_name, email, password, role_id)
	VALUES
	('Damla', 'Test', 'damla@test.com', '12345678Test', (SELECT r.id FROM roles r WHERE r.role_name = 'ROLE_ADMIN'));

INSERT INTO hydration_levels
	(hydration_code, hydration_name, logical_order)
	VALUES 
	('HY01', 'Low', 1), ('HY02', 'Medium', 2), ('HY03','A lot', 3);
	
INSERT INTO sunlight_levels
	(sunlight_code, sunlight_name, logical_order)
	VALUES 
	('SN01', 'Part shade', 1), ('SN02', 'Part sun', 2), ('SN03', 'Full sun', 3);
	
INSERT INTO plants
	(common_name, latin_name, description, image, hydration_id, sunlight_id, added_at)
	VALUES
	('Calethea', 'Caletheia monstera', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.',	'calethea.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY01'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN01'), now()),
	('Z Plant', 'Zamioculcas zamiifolia', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'zplant.jpg', 
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY02'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('Cactus', 'Hatiora gaertneri', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'cactus.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY01'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN03'), now()),
	('Monstera', 'Monstera deliciosa', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'monstera.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY02'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('Pilea', 'Pilea abbreviata', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'pilea.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY03'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('Strelitzia', 'Strelitzia alba', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'strelitzia.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY02'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('Ficus', 'Ficus abbreviata', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'ficus.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY03'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN01'), now()),
	('Livistona', 'Livistona affredi', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'livistona.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY01'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN03'), now());	
	