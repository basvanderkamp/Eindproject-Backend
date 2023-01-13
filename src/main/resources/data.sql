INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Autowassen', 'Mijn auto is erg vies, wie wilt hem voor mij schoonmaken.', 'Ik heb alle benodigdheden in huis.', 'Alleen de buitenkant en de velgen hoeven schoon.', '10 euro', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Grasmaaien', 'Het grasmaaien rond mijn huis.', 'Ik heb alleen een ouderwetse rol maaier die je moet duwen.', 'De voor en achtertuin netjes gemaaid', 'in overleg', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Stofzuigen', 'De boven verdieping stofzuigen., Ik kan de trap niet meer op.', 'Ik heb zelf een stofzuiger op de beganegrond staan.', 'De vloer en op de kastjes zuigen.', 'Kop koffie met een taartje?', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Boodschappen doen', 'De week boodschappen doen', 'Zelf vooraf betalen.', 'Boodschappen in de aanbieding kopen.', 'Product naar keuze voor 15 euro.', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Schilderen', 'De kozijnen aan de voorkant van het huis moeten geschilderd worden', 'Alle schilder spullen zelf meenemen, ik heb wel een lange ladder.', 'Kozijnen in groen geschilderd, zowel boven als beneden', '50 euro per uur.', 'AVAILABLE');



-- INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold) VALUES (1001, 'NH3216SMART', 'Nikkei', 'HD smart TV', 159, 32, 100, 'LED', 'HD ready',  true, true, false, false, false, false, 235885, 45896);
-- INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold) VALUES (1002, '43PUS6504/12/L', 'Philips', '4K UHD LED Smart Tv', 379, 43, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 8569452, 5685489);
-- INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold) VALUES (1003, '43PUS6504/12/M', 'Philips', '4K UHD LED Smart Tv', 379, 50, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 345549, 244486);
-- INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold) VALUES (1004, '43PUS6504/12/S', 'Philips', '4K UHD LED Smart Tv', 379, 58, 60, 'LED', 'Ultra HD',  true, true, false, true, false, false, 6548945, 4485741);
-- INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold) VALUES (1005, 'OLED55C16LA', 'LG', 'LG OLED55C16LA', 989, 55, 100, 'OLED', 'ULTRA HD',  true, true, true, true, true, false, 50000, 20500);
--

-- INSERT INTO assignments_clients(assignments_id, clietns_id) values (1, Bas);
-- INSERT INTO television_wall_bracket(television_id, wall_bracket_id) values (1003, 1003);
-- INSERT INTO television_wall_bracket(television_id, wall_bracket_id) values (1004, 1003);
-- INSERT INTO television_wall_bracket(television_id, wall_bracket_id) values (1001, 1004);
-- INSERT INTO television_wall_bracket(television_id, wall_bracket_id) values (1001, 1005);

INSERT INTO clients (username, firstname, lastname, mobile, adres, place, zipcode, email, story) VALUES ('sebastiaan', 'Bas', 'van der Kamp', '0612345678', 'Thuis 1', 'Huizen', '1234AB', 'bas@novi.nl', 'Ik ben een mislukte elektrischen vandaar dat ik nu programmer probeer te worden');
INSERT INTO users (username, password, enabled, apikey, client_username) VALUES ('sebastiaan', '$2a$12$Q63ccJHHpacqykAdV1eJt.MBETxQszGC6czyMAICKgAXPcT9SAM6S', true, '7847493', 'sebastiaan');
INSERT INTO authorities (username, authority) VALUES ('sebastiaan', 'ROLE_USER');

INSERT INTO clients (username, firstname, lastname, mobile, adres, place, zipcode, email, story) VALUES ('jeremy', 'Jeremy', 'Koster', '0698765432', 'Zeelandweg 2', 'Goes', '9876ZY', 'jeremy@novi.nl', 'Ik ben een mislukte timmerman vandaar dat ik nu programmer probeer te worden');
INSERT INTO users (username, password, enabled, apikey, client_username) VALUES ('jeremy', '$2a$12$Q63ccJHHpacqykAdV1eJt.MBETxQszGC6czyMAICKgAXPcT9SAM6S', true, '7847493', 'jeremy');
INSERT INTO authorities (username, authority) VALUES ('jeremy', 'ROLE_USER');

INSERT INTO clients (username, firstname, lastname, mobile, adres, place, zipcode, email, story) VALUES ('admin', 'Mark', 'Rensen', '0611223344', '3e kantoor links', 'NOVI Hoofdkantoor', '1122MR', 'Mark@novi.nl', 'Ik ben de Admin voor dit mooie project, zullen we alles gaan testen?');
INSERT INTO users (username, password, enabled, apikey, client_username) VALUES ('admin', '$2a$12$Iszwf.MkqE87SR71Tvm6qONL4pgC/DC2QW58Irujhpy8KOIoA7PsC', true, '7847493', 'admin');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');

-- INSERT INTO client_assignment (client_id, assignment_id) values ('sebastiaan', 'Autowassen');
-- INSERT INTO client_assignment (client_id, assignment_id) values ('sebastiaan', 'Grasmaaien');
-- INSERT INTO client_assignment (client_id, assignment_id) values ('sebastiaan', 'Stofzuigen');
-- INSERT INTO client_assignment (client_id, assignment_id) values ('jeremy', 'Boodschappen doen');
-- INSERT INTO client_assignment (client_id, assignment_id) values ('jeremy', 'Schilderen');

