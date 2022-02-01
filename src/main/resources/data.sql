INSERT INTO users (username, password, enabled, email, level, xp, level_up_limit, name, birthday, province, member_since)
VALUES ('gebruiker', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'user@mail.nl', '1', '1000', '2000', 'gebruiker', '12-01-2022', 'UTRECHT', '2022-01-01'),
       ('itiskevin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'info@mengelmoestuintjes.nl', '1', '1000', '2000', 'Kevin', '12-01-1991', 'OVERIJSSEL', '2022-01-01'),
       ('mengelmoestuintje', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'moderator@mengelmoestuintjes.nl', '1', '1000', '2000', 'Mengelmoestuintje', '22-02-2022', 'HIDDEN', '2022-01-01'),
       ('vivalanouk', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE,
        'info@anoukdriessen.nl', '1', '1000', '2000', 'Anouk', '22-04-1995', 'OVERIJSSEL', '2022-01-01');

INSERT INTO authorities (username, authority)
VALUES ('gebruiker', 'ROLE_USER'),
       ('itiskevin', 'ROLE_USER'),
       ('mengelmoestuintje', 'ROLE_USER'),
       ('mengelmoestuintje', 'ROLE_MODERATOR'),
       ('vivalanouk', 'ROLE_USER'),
       ('vivalanouk', 'ROLE_MODERATOR'),
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

INSERT INTO posts (category, user_id, published, title, description)
VALUES ('NOTE', 'vivalanouk', FALSE,
        'Mijn eerste tomaat geoogst', 'vandaag mijn eerste tomaat geoogst'),
       ('NOTE', 'vivalanouk', FALSE,
        'Kijk mijn tomaten oogst!', 'dit is mijn oogst van de 5 planten op mijn volkstuintje'),
       ('NOTE', 'vivalanouk', TRUE,
        'Kijk mijn tomaten oogst!', 'dit is mijn oogst van de 5 planten op mijn volkstuintje'),
       ('POST', 'vivalanouk', FALSE,
        'Ik moet ECHT vaker mijn planten water geven', 'De planten zijn overleden, ik heb ze te weinig en op de verkeerde tijd water gegeven :('),
       ('POST', 'vivalanouk', FALSE,
        'Ik moet ECHT vaker mijn planten water geven', 'De planten zijn overleden, ik heb ze te weinig en op de verkeerde tijd water gegeven :('),
       ('POST', 'vivalanouk', TRUE,
        'Ik moet ECHT vaker mijn planten water geven', 'De planten zijn overleden, ik heb ze te weinig en op de verkeerde tijd water gegeven :('),
       ('POST', 'vivalanouk', TRUE,
        'Dit is mijn plan voor het nieuwe seizoen', 'Ik heb eindelijk mijn plan klaar voor het nieuwe seizoen!');

INSERT INTO posts (user_id, category, published, title, summary, description, created)
VALUES ('mengelmoestuintje','BLOG', TRUE, 'Laat ik mij even voorstellen','Ik ben Anouk Driessen, 26 lentes jong en erg enthousiast over (bijna) alles wat met Techniek en Natuur te maken heeft!',
        'In mijn vrije tijd ben ik graag in de natuur te vinden, of dat nu op mijn sup- of kiteboard op het water is of met mijn verrekijker in het bos om vogels te spotten die ik (nog niet) kan herkennen ' ||
        'Een kwaliteit waar ik zelf erg trots op ben is dat als ik ergens voor ga ik ALL-IN ga, alles over dat onderwerp of ding wil weten en mezelf altijd enorm weet uit te dagen ' ||
         'Dit was het recept voor wat ik nu noem: Mengelmoestuintjes.',
         '2022-01-16T14:12:29.5216685'),
       ('mengelmoestuintje','BLOG', TRUE, 'Hello World!',
        'We zijn LIVE! Het heeft heel wat uren achter de computer en in de boeken gekost maar Mengelmoestuintjes is een feit',
        'Welkom bij het begin van Mengelmoestuintjes, dit is een project ontstaan vanuit de opleiding Full Stack van NOVI. ' ||
        'De afgelopen 6 maanden ben ik (Anouk Driessen) bezig geweest met de development van zowel de Front End als de Back End van de applicatie.',
        '2022-01-17T14:12:29.5216685'),
       ('mengelmoestuintje','BLOG', TRUE, 'Start HIER!','Dit is een rondleiding door de applicatie! Er zijn een aantal dingen die je kunt doen zonder dat je een gebruiker bent echter begint het echte feest pas als je een profiel aanmaakt',
        'Het aanmaken van een tuintje in 4 stappen: STAP 1. Kies een gebruikersnaam en een veilig en uniek wachtwoord. Vul daarnaast je email adres in. ' ||
        'STAP 2. Maak je profiel compleet door een (display) naam, je geboortedatum, de provincie waar jij en je tuintje gevestigd zijn in te vullen en niet te vergeten: kies of upload een profielafbeelding' ||
        'STAP 3. Tijd voor je eerste tuintje, kies de lengte en de breedte van je tuintje en geef je tuintje een passende naam' ||
        'STAP 4. Het einde is in zicht, alles staat klaar voor jou mengelmoestuintje. Dus... Wat ga jij als eerste doen?',
        '2022-01-18T14:12:29.5216685'),
       ('mengelmoestuintje','BLOG', TRUE, 'Mengelmoestuintjes de Academy','Met trots presenteren we de Academy!',
        'Ons team heeft hard gewerkt aan de realisatie voor een Academy en eindelijk kunnen we deze presenteren!',
        '2022-01-21T14:12:29.5216685');

INSERT INTO posts (user_id, category, published, title, summary, description, created)
VALUES ('mengelmoestuintje','LEARNING', FALSE, 'Les 1','Leer je grond kennen',
        'blablabla', '2022-01-18T14:12:29.5216685'),
       ('mengelmoestuintje','LEARNING', FALSE, 'Les 2','De basisbehoefte van de plant',
        'blablabla', '2022-01-18T14:12:29.5216685'),
       ('mengelmoestuintje','LEARNING', FALSE, 'Les 3','Al het basis en niet zo basis gereedschap',
        'blablabla', '2022-01-18T14:12:29.5216685');

INSERT INTO tasks(user_id, type, done, title, created, deadline)
VALUES ('vivalanouk', 'TODO', FALSE, 'to do expired', '10-01-2022', '22-01-2022'),
       ('vivalanouk', 'TODO', FALSE, 'to do today', '10-01-2022', '27-01-2022'),
       ('vivalanouk', 'TODO', FALSE, 'to do tomorrow', '10-01-2022', '28-01-2022'),
       ('vivalanouk', 'TODO', FALSE, 'to do soon', '10-01-2022', '30-01-2022'),
       ('vivalanouk', 'TODO', FALSE, 'to do soon', '10-01-2022', '29-01-2022'),
       ('itiskevin', 'GARDENING', FALSE, 'water geven', '10-01-2022', '10-01-2022');

INSERT INTO gardens(name, x, y, size )
VALUES ('volkstuintje', 3, 3, '9'),
       ('achtertuin', 2, 3, '6');

-- INSERT INTO fields(name, status, garden_id)
-- VALUES ('A1', 'EMPTY', 1),
--        ('A2', 'EMPTY', 1),
--        ('A3', 'EMPTY', 1),
--        ('B1', 'EMPTY', 1),
--        ('B2', 'EMPTY', 1),
--        ('B3', 'EMPTY', 1),
--        ('C1', 'EMPTY', 1),
--        ('C2', 'EMPTY', 1),
--        ('C3', 'EMPTY', 1),
--        ('A1', 'EMPTY', 2),
--        ('A2', 'PATH', 2),
--        ('A3', 'GRASS', 2),
--        ('B1', 'PLANTABLE', 2),
--        ('B2', 'PLANTED', 2),
--        ('B3', 'PLANTED_AND_WATERED', 2);

INSERT INTO gardens_users(garden_id, user_id)
VALUES (1, 'itiskevin'),
       (1, 'vivalanouk'),
       (2, 'vivalanouk');

-- INSERT INTO gardens_fields(garden_id, field_id)
-- VALUES (1, 1),
--        (1, 2),
--        (1, 3),
--        (1, 4),
--        (1, 5),
--        (1, 6),
--        (1, 7),
--        (1, 8),
--        (1, 9),
--        (2, 1),
--        (2, 2),
--        (2, 3),
--        (2, 4),
--        (2, 5),
--        (2, 6);