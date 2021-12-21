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

INSERT INTO gebruikers( id, name, password, enabled, role )
VALUES ('vivalanouk', 'anouk', 'geheim123', true, 4),
       ('luminousNodes', 'kevin', 'qwerty999', true , 1),
       ('zonnestraal80', 'guus', 'wachtwoord', true, 0);

INSERT INTO posts ( title, category, description, image_url, author, visible )
VALUES ('Mijn eerste POST','0','blablablablablablablablablablablabla','url_naar_afbeelding','zonnestraal80','true'),
       ('Mijn tweede POST','0','blablablablablablablablablablablabla','url_naar_afbeelding','zonnestraal80','false'),
       ('Mijn eerste NOTE','1','blablablablablablablablablablablabla','url_naar_afbeelding','zonnestraal80','true'),
       ('Mijn tweede NOTE','1','blablablablablablablablablablablabla','url_naar_afbeelding','zonnestraal80','false'),
       ('MIJN EERSTE MILESTONE','2','blablablablablablablablablablablabla','url_naar_afbeelding','luminousNodes','true'),
       ('MIJN TWEEDE MILESTONE','2','blablablablablablablablablablablabla','url_naar_afbeelding','zonnestraal80','true'),
       ('Project','3','blablablablablablablablablablablabla','url_naar_afbeelding','vivalanouk','false'),
       ('Project','3','blablablablablablablablablablablabla','url_naar_afbeelding','vivalanouk','false'),
       ('BLOG','4','blablablablablablablablablablablabla','url_naar_afbeelding','luminousNodes','true'),
       ('BLOG','4','blablablablablablablablablablablabla','url_naar_afbeelding','luminousNodes','true'),
       ('Learning','5','blablablablablablablablablablablabla','url_naar_afbeelding','vivalanouk','false'),
       ('Learning','5','blablablablablablablablablablablabla','url_naar_afbeelding','vivalanouk','false');

INSERT INTO tasks( title, type, description, done, starting, due_date, points )
VALUES ('hello', 0, 'lala', false, null, null, 100),
       ('byebye', 0, 'lala', true, null, null, 100),
       ('hello', 1, 'lala', false, null, null, 100),
       ('byebye', 1, 'lala', true, null, null, 100),
       ('hello', 2, 'lala', false, null, null, 100),
       ('byebye', 2, 'lala', true, null, null, 100);

INSERT INTO plants( category, name, description )
VALUES (0, 'bloem', 'ik ben een bloem'),
       (3, 'fruit', 'ik ben fruit'),
       (2, 'kruid', 'ik ben een kruid'),
       (1, 'groenten', 'ik ben groeten');

INSERT INTO milestones( title, points, url_to_badge )
VALUES ('eerste login', 1000, '/afbeelding'),
       ('eerste seizoen', 1000, '/afbeelding'),
       ('eerste vriend', 1000, '/afbeelding');

INSERT INTO tuintjes( name, x, y )
VALUES ('voortuin', 5, 5),
       ('achtertuin', 10, 5),
       ('volkstuin', 5, 15);

INSERT INTO topics( title, category, description)
VALUES ('plant een pit', 0, 'lalala'),
       ('permaculture 101', 1, 'lalala'),
       ('creatief met...', 2, 'lalala');