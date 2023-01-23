
----------------------------------------Aanmaken Assignments--------------------------------------------
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Autowassen', 'Mijn auto is erg vies, wie wilt hem voor mij schoonmaken.', 'Ik heb alle benodigdheden in huis.', 'Alleen de buitenkant en de velgen hoeven schoon.', '10 euro', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Grasmaaien', 'Het grasmaaien rond mijn huis.', 'Ik heb alleen een ouderwetse rol maaier die je moet duwen.', 'De voor en achtertuin netjes gemaaid', 'in overleg', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Stofzuigen', 'De boven verdieping stofzuigen., Ik kan de trap niet meer op.', 'Ik heb zelf een stofzuiger op de beganegrond staan.', 'De vloer en op de kastjes zuigen.', 'Kop koffie met een taartje?', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Boodschappen doen', 'De week boodschappen doen', 'Zelf vooraf betalen.', 'Boodschappen in de aanbieding kopen.', 'Product naar keuze voor 15 euro.', 'AVAILABLE');
INSERT INTO assignments (title, description, essentials, demands, reward, assignment_status) VALUES ('Schilderen', 'De kozijnen aan de voorkant van het huis moeten geschilderd worden', 'Alle schilder spullen zelf meenemen, ik heb wel een lange ladder.', 'Kozijnen in groen geschilderd, zowel boven als beneden', '50 euro per uur.', 'AVAILABLE');

----------------------------------------Aanmaken Gebruiker Sebastiaan-------------------------------------
INSERT INTO clients (username, firstname, lastname, mobile, adres, place, zipcode, email, story) VALUES ('sebastiaan', 'Bas', 'van der Kamp', '0612345678', 'Thuis 1', 'Huizen', '1234AB', 'bas@novi.nl', 'Ik ben een mislukte elektrischen vandaar dat ik nu programmer probeer te worden');
INSERT INTO executor (name) VALUES ('sebastiaan');
INSERT INTO users (username, password, enabled, apikey, client_username) VALUES ('sebastiaan', '$2a$12$Q63ccJHHpacqykAdV1eJt.MBETxQszGC6czyMAICKgAXPcT9SAM6S', true, '7847493', 'sebastiaan');
INSERT INTO authorities (username, authority) VALUES ('sebastiaan', 'ROLE_USER');
UPDATE clients SET executor_name = 'sebastiaan' WHERE username = 'sebastiaan';
UPDATE Assignments SET client_username = 'sebastiaan' WHERE title = 'Autowassen';
UPDATE Assignments SET client_username = 'sebastiaan' WHERE title = 'Schilderen';
UPDATE Assignments SET client_username = 'sebastiaan' WHERE title = 'Grasmaaien';

----------------------------------------Aanmaken Gebruiker Jeremy------------------------------------------
INSERT INTO clients (username, firstname, lastname, mobile, adres, place, zipcode, email, story) VALUES ('jeremy', 'Jeremy', 'Koster', '0698765432', 'Zeelandweg 2', 'Goes', '9876ZY', 'jeremy@novi.nl', 'Ik ben een mislukte timmerman vandaar dat ik nu programmer probeer te worden');
INSERT INTO executor (name) VALUES ('jeremy');
INSERT INTO users (username, password, enabled, apikey, client_username) VALUES ('jeremy', '$2a$12$Q63ccJHHpacqykAdV1eJt.MBETxQszGC6czyMAICKgAXPcT9SAM6S', true, '7847493', 'jeremy');
INSERT INTO authorities (username, authority) VALUES ('jeremy', 'ROLE_USER');
UPDATE clients SET executor_name = 'jeremy' WHERE username = 'jeremy';
UPDATE Assignments SET client_username = 'jeremy' WHERE title = 'Boodschappen doen';
UPDATE Assignments SET client_username = 'jeremy' WHERE title = 'Stofzuigen';

----------------------------------------Aanmaken Admin Mark------------------------------------------------
INSERT INTO clients (username, firstname, lastname, mobile, adres, place, zipcode, email, story) VALUES ('admin', 'Mark', 'Rensen', '0611223344', '3e kantoor links', 'NOVI Hoofdkantoor', '1122MR', 'Mark@novi.nl', 'Ik ben de Admin voor dit mooie project, zullen we alles gaan testen?');
INSERT INTO executor (name) VALUES ('admin');
INSERT INTO users (username, password, enabled, apikey, client_username) VALUES ('admin', '$2a$12$Iszwf.MkqE87SR71Tvm6qONL4pgC/DC2QW58Irujhpy8KOIoA7PsC', true, '7847493', 'admin');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
UPDATE clients SET executor_name = 'admin' WHERE username = 'admin';