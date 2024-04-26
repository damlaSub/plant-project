DELETE FROM plants;
DELETE FROM hydration_levels;
DELETE FROM sunlight_levels;
DELETE FROM accounts;
DELETE FROM roles;

INSERT INTO roles 
	(role_code)
	VALUES 
	('ADMIN'), ('USER');

INSERT INTO accounts
	(first_name, last_name, email, password, role_id)
	VALUES
	('Damla', 'Plant', 'damla@plantme.com', '$2a$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC', (SELECT r.id FROM roles r WHERE r.role_code = 'ADMIN')),
	('Lindsay', 'Test', 'test@test.com', '$2a$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC', (SELECT r.id FROM roles r WHERE r.role_code = 'USER')),
	('Damla', 'Test', 'damla@test.com', '$2a$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC', (SELECT r.id FROM roles r WHERE r.role_code = 'USER'));

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
	('Calathea', 'Calatheia monstera', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.',	'calathea.jpg',
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

INSERT INTO account_plants
	(account_id, plant_id)
	VALUES
	('2', '1'),
	('2', '2'),
	('2', '3'),
	('3', '6'),
	('3', '7'),
	('3', '8');
	