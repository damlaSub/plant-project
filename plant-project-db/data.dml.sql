DELETE FROM plants;
DELETE FROM hydration_levels;
DELETE FROM sunlight_levels;
DELETE FROM accounts;
INSERT INTO accounts
	(first_name, last_name, email, password, role)
	VALUES
	('Damla', 'Plant', 'damla@plantme.com', '$2a$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC', 'ADMIN'),
	('Lindsay', 'Test', 'test@test.com', '$2a$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC', 'USER'),
	('Damla', 'Test', 'damla@test.com', '$2a$11$Qs5QuF6Y5ihMU758PPZMleWxuYvFrLkyE2i0Gl00pqYbMfFt6voYC', 'USER');
INSERT INTO hydration_levels
	(hydration_code, hydration_name, logical_order)
	VALUES
	('HY01', 'Low', 1), ('HY02', 'Medium', 2), ('HY03','A lot', 3);
	
INSERT INTO sunlight_levels
	(sunlight_code, sunlight_name, logical_order)
	VALUES
	('SN01', 'Part shade', 1), ('SN02', 'Part sun', 2), ('SN03', 'Full sun', 3);
	
INSERT INTO plants
	(code, common_name, latin_name, description, image, hydration_id, sunlight_id, added_at)
	VALUES
	('PL01', 'Calathea', 'Calatheia pinstripe', '<p>Calathea Pinstripe prefers bright, indirect light. It can tolerate low light conditions but avoid placing it in direct sunlight as this can cause its leaves to fade or scorch. A north or east-facing window is ideal. If placed near a south or west-facing window, ensure it is shielded by a sheer curtain to filter the intense sunlight. </p><p>Keep the soil consistently moist but not waterlogged. Overwatering can lead to root rot, while underwatering can cause the leaves to curl and brown. Water when the top inch of the soil feels dry to the touch. Use room temperature water to avoid shocking the plant. Ensure that the pot has drainage holes to allow excess water to escape.</p><p>Repot the Calathea Pinstripe every 1-2 years using a well-draining potting mix. Prune any yellow or brown leaves to maintain the plant''s appearance and health. Calathea Pinstripe is sensitive to salts and minerals in tap water. Use filtered or distilled water to avoid mineral buildup in the soil.</p>',	'calathea.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY01'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN01'), now()),
	('PL02', 'ZZ Plant', 'Zamioculcas zamiifolia', '<p>ZZ Plants can live in low to bright, indirect sunlight. They are the perfect plant to bring life to a dark room or corner.
		</p><p>Your ZZ Plant only needs to be watered fortnightly, allowing its soil to completely dry out between waterings to prevent overwatering and root rot. During the winter months feel free to only water him once a month if his soil is still moist after 2 weeks.</p><p>ZZ Plants can easily be propagated in water from a stem and leaf cutting.</p>', 'zplant.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY02'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('PL03', 'Cactus', 'Hatiora gaertneri', '<p>Cacti will thrive in bright indirect light or direct full sun. They are perfect for South and South-West facing window sills that receive sunlight too strong for most houseplants.</p><p> Water once every 2 - 3 weeks, allowing its soil to completely dry out between waterings to prevent overwatering and root rot. During winter water once a month.</p><p> Cacti do not have any particular humidity requirements - but are not suited to very humid environments like bathrooms or terrariums.</p><p>Cacti do not have any particular humidity requirements - but are not suited to very humid environments like bathrooms or terrariums.</p>', 'cactus.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY01'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN03'), now()),
	('PL04', 'Monstera', 'Monstera deliciosa', '<p>Monstera Deliciosa can live in low to bright, dappled indirect light. Prolonged exposure to bright direct sunlight may burn and scorch their leaves. Growth will be slower in low light environments.</p><p> Monstera Deliciosa enjoys weekly waterings. Allow the soil to dry out between waterings, especially during the winter - when you might only need to water your plant fortnightly.</p><p>Monstera Deliciosa enjoys a humid environment, which is why we recommend frequent misting of its leaves. Alternatively, you can place your plant close to other plants, which increases the humidity of the air around them.</p>', 'monstera.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY02'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('PL05', 'Pilea', 'Pilea abbreviata', '<p>Pilea prefers bright, indirect light. Prolonged exposure to bright direct sunlight can discolour and damage their leaves.</p><p> Weekly, allowing the soil to completely dry out between waterings. In winter, we recommend watering less frequently to prevent overwatering and root rot.</p><p> No particular humidity requirements - but can suffer in very dry environments. Misting daily can help remedy this, as well as clustering your plants.</p>
		', 'pilea.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY03'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('PL06', 'Bird Of Paradise', 'Strelitzia alba', '<p>The leaves on the Bird of Paradise naturally split as it grows.</p><p> Weekly, allowing the soil to completely dry out between waterings. In winter, we recommend watering less frequently to prevent overwatering and root rot.</p><p> Your Bird of Paradise will love being misted regularly and it will help it avoid crisping tips on its leaves.</p>', 'strelitzia.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY02'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN02'), now()),
	('PL07', 'Rubber Tree', 'Ficus abbreviata', '<p>Prolonged exposure to bright direct sunlight may burn and scorch their leaves.</p><p> Allow the soil to completely dry out between waterings, then saturate. During winter you may only need to water your plant fortnightly.</p><p> Enjoys high humidity, which you can create by misting it frequently, placing it close to other plants or on a pebble tray partly filled with water. Avoid drafts.</p>', 'ficus.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY03'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN01'), now()),
	('PL08', 'Fan Palm', 'Livistona affredi', '<p>Your Fan Palm will do best with bright indirect light. Getting too much direct sunlight can burn the leaves. Too little light will stunt its growth.</p><p> Water weekly in summer, less in winter. Keep the soil moist but not wet, so ensure excess water drains fully.</p><p>Can tolerate average household environments, but make sure the air is not too dry by misting a few times a week.</p>', 'livistona.jpg',
		(SELECT h.id FROM hydration_levels h WHERE h.hydration_code = 'HY01'),
		(SELECT s.id FROM sunlight_levels s WHERE s.sunlight_code = 'SN03'), now());	

INSERT INTO account_plants
	(account_id, plant_id)
	VALUES
	((SELECT a.id FROM accounts a WHERE a.email = 'damla@test.com'),
	(SELECT p.id FROM plants p WHERE p.code = 'PL01')),
	((SELECT a.id FROM accounts a WHERE a.email = 'damla@test.com'),
	(SELECT p.id FROM plants p WHERE p.code = 'PL03')),
	((SELECT a.id FROM accounts a WHERE a.email = 'damla@test.com'),
	(SELECT p.id FROM plants p WHERE p.code = 'PL05')),
	((SELECT a.id FROM accounts a WHERE a.email = 'test@test.com'),
	(SELECT p.id FROM plants p WHERE p.code = 'PL01'));