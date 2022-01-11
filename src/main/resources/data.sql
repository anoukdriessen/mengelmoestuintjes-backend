INSERT INTO users (username, password, enabled, email, level, xp, level_up_limit, name, birthday, province)
VALUES ('gebruiker', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'user@mail.nl', '1', '1000', '2000', 'gebruiker', '10-01-2022', 'UTRECHT'),
       ('itiskevin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'info@mengelmoestuintjes.nl', '1', '1000', '2000', 'Kevin', '30-12-1991', 'OVERIJSSEL'),
       ('mengelmoestuintje', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'moderator@mengelmoestuintjes.nl', '1', '1000', '2000', 'Mengelmoestuintje', '22-02-2022', 'HIDDEN'),
       ('vivalanouk', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'info@anoukdriessen.nl', '99', 'MAX', 'MAX', 'Anouk', '22-04-1995', 'OVERIJSSEL');

INSERT INTO authorities (username, authority)
VALUES ('gebruiker', 'ROLE_USER'),
       ('itiskevin', 'ROLE_USER'),
       ('mengelmoestuintje', 'ROLE_USER'),
       ('mengelmoestuintje', 'ROLE_MODERATOR'),
       ('vivalanouk', 'ROLE_USER'),
       ('vivalanouk', 'ROLE_MODERATOR'),
       ('vivalanouk', 'ROLE_DEVELOPER'),
       ('vivalanouk', 'ROLE_ADMIN');

INSERT INTO quotes ( author, text )
VALUES ('mengelmoestuintjes', 'Je hebt iedere dag twee keuzes: Groeien of Herhalen'),
       ('gertraude beese', 'Een leven zonder dromen is als een tuin zonder bloemen'),
       ('phil bosman', 'De wonderen van heel de wereld vind je terug in je eigen tuin'),
       ('ireen_boerderijgeluk', 'Wat met liefde is geplant raakt nooit uitgebloeid'),
       ('claudia.mytowergarden', 'Als jij een bloem was, zou ik jou plukken'),
       ('anouk driessen', 'Vergeet niet eerst je eigen bloemen water te geven'),
       ('percy b. green', 'Een man van woorden en niet van daden is als een tuin vol onkruid'),
       ('paul bourget', 'Verzorg uw tuin, en pluk er de vruchten voor anderen'),
       ('william shakespeare', 'Ons lichaam is onze tuin, waarvan onze wil de tuinman is'),
       ('alexander smith', 'Diepgeworteld in het menselijk hart is er de voorliefde voor tuinen en tuinieren.'),
       ('anastasius gr&#252;n', 'Bloemen zijn langs elk pad te vinden, doch niet ieder weet er een krans van te vlechten.'),
       ('frida schanz', 'Zonder moeite geen vreugde, wie bloemen plant, moet veel water dragen.'),
       ('robert ulrich', 'Uitzicht op een groene omgeving werkt heilzaam'),
       ('dr. alfred vogel', 'Gezondheid is leven in harmonie met de natuur.'),
       ('dr. alfred vogel', 'De natuur is verstandiger dan de mens'),
       ('dr. alfred vogel', 'De natuur is de apotheek, de plant het recept.'),
       ('the independent', 'Tuinieren is goed voor uw gezondheid. Het vermindert de stress, het verlaagt de bloeddruk en u leeft langer.'),
       ('gay search', 'Spitten en harken zorgen voor een stevige lichaamsbeweging die meer calorieën verbrandt dan fietsen'),
       ('dr. alfred vogel', 'De mens is zo gezond als zijn grond'),
       ('dr. brigid boardman', 'De Tuin levert tegengif voor de pijn en frustratie van de oude dag.'),
       ('chinees gezegde', 'Hij die een tuin plant plant geluk'),
       ('b. nichols', 'Overweldigd worden door de geur van bloemen is een heerlijk soort nederlaag'),
       ('f. bacon', 'De adem van bloemen is veel zoeter in de lucht dan in de hand');

INSERT INTO posts (user_id, category, title, description, image_url, published)
VALUES ('gebruiker', 'NOTE','Mijn eerste tomaat geoogst',
        'vandaag mijn eerste tomaat geoogst','url-to-image', FALSE),
       ('gebruiker', 'POST', 'Kijk mijn tomaten oogst!',
        'dit is mijn oogst van de 5 planten op mijn volkstuintje','url-to-image', TRUE),
       ('itiskevin', 'NOTE','Ik moet ECHT vaker mijn planten water geven',
        'De planten zijn overleden, ik heb ze te weinig en op de verkeerde tijd water gegeven :(','url-to-image', FALSE),
       ('itiskevin', 'POST','Dit is mijn plan voor het nieuwe seizoen',
        'Ik heb eindelijk mijn plan klaar voor het nieuwe seizoen!','url-to-image', TRUE);

INSERT INTO posts (user_id, category, title, summary, description, image_url, published)
VALUES ('mengelmoestuintje','BLOG','Hello World','We zijn LIVE!',
        'Welkom bij het begin van Mengelmoestuintjes, dit is een project ontstaan vanuit de opleiding Full Stack Developer',
        'url-to-image', TRUE),
       ('mengelmoestuintje','BLOG','Rondleiding door de applicatie','Start hier!',
        'Dit is een A tot Z handleiding voor het gebruik van Mengelmoestuintjes',
        'url-to-image', TRUE),
       ('mengelmoestuintje','BLOG','Mengelmoestuintjes de Academy','Met trots presenteren we de Academy!',
        'Ons team heeft hard gewerkt aan de realisatie voor een Academy en eindelijk kunnen we deze presenteren!',
        'url-to-image', FALSE),
       ('mengelmoestuintje','LEARNING','Les 1','Leer je grond kennen',
        'blablabla','url-to-image', FALSE),
       ('mengelmoestuintje','LEARNING','Les 2','De basisbehoefte van de plant',
        'blablabla','url-to-image', FALSE),
       ('mengelmoestuintje','LEARNING','Les 3','Al het basis en niet zo basis gereedschap',
        'blablabla','url-to-image', FALSE);

INSERT INTO tasks(user_id, type, done, title, description, created, deadline)
VALUES ('gebruiker', 'TODO', FALSE, 'verjaardagsboeket plukken', 'er is er een jarig en dat ben ik!', '10-01-2022', '10-01-2022'),
       ('itiskevin', 'GARDENING', FALSE, 'water geven', 'veld A1, B3 en C5', '10-01-2022', '10-01-2022');

INSERT INTO gardens(name, x, y, size)
VALUES ('volkstuintje', 10, 10, '100'),
       ('achtertuin', 5, 5, '25');

INSERT INTO gardens_users(garden_id, user_id)
VALUES (1, 'itiskevin'),
       (1, 'gebruiker'),
       (2, 'itiskevin');