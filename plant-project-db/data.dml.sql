DELETE FROM plants;
DELETE FROM accounts;

INSERT INTO accounts
	(first_name, last_name, email, password)
	VALUES
	('Damla', 'Test', 'dont@testme', '12345678Test');

INSERT INTO plants
	(name, latin_name, description, image_url, water, sun, added_at)
	VALUES
	('Calethea', 'Caletheia monstera', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'calethea.jpg', 2, 2, now()
		),
	('Z Plant', 'Zamioculcas zamiifolia', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'zplant.jpg', 3, 2, now()
		),
	('Cactus', 'Hatiora gaertneri', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'cactus.jpg', 1, 3, now()
		),
	('Monstera', 'Monstera deliciosa', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'monstera.jpg', 2, 2, now()
		),
	('Pilea', 'Pilea abbreviata', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'pilea.jpg', 1, 2, now()
		),
	('Strelitzia', 'Strelitzia alba', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'strelitzia.jpg', 2, 1, now()
		),
	('Ficus', 'Ficus abbreviata', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'ficus.jpg', 2, 2, now()
		),
	('Livistona', 'Livistona affredi', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit.', 'livistona.jpg', 3, 1, now()
		);