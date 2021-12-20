INSERT INTO quotes (author, text)
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

INSERT INTO posts (title, category, description, image_url, author, visible)
    VALUES ('Mijn eerste POST','0','blablablablablablablablablablablabla','url_naar_afbeelding','anouk','true'),
           ('Mijn tweede POST','0','blablablablablablablablablablablabla','url_naar_afbeelding','anouk','false'),
           ('Mijn eerste NOTE','1','blablablablablablablablablablablabla','url_naar_afbeelding','kevin','true'),
           ('Mijn tweede NOTE','1','blablablablablablablablablablablabla','url_naar_afbeelding','guus','false'),
           ('MIJN EERSTE MILESTONE','2','blablablablablablablablablablablabla','url_naar_afbeelding','guus','true'),
           ('MIJN TWEEDE MILESTONE','2','blablablablablablablablablablablabla','url_naar_afbeelding','guus','true'),
           ('Project','3','blablablablablablablablablablablabla','url_naar_afbeelding','kevin','false'),
           ('Project','3','blablablablablablablablablablablabla','url_naar_afbeelding','kevin','false'),
           ('BLOG','4','blablablablablablablablablablablabla','url_naar_afbeelding','anouk','true'),
           ('BLOG','4','blablablablablablablablablablablabla','url_naar_afbeelding','anouk','true'),
           ('Learning','5','blablablablablablablablablablablabla','url_naar_afbeelding','kevin','false'),
           ('Learning','5','blablablablablablablablablablablabla','url_naar_afbeelding','kevin','false');

INSERT INTO tasks(
    type, title, description, done )
VALUES ('TODO', 'hello', 'lala', false),
       ('TODO', 'bye', ''lala, true),
       ('GARDENING', 'hello', 'lala', false),
       ('GARDENING', 'bye', ''lala, true)

INSERT INTO plant_flowers (name, bloom)
    VALUES ('roos', ''),
           ('chrisant',''),
           ('zonnehoed',''),
           ('Dahlia', ''),
           ('Goudsbloem', '');

INSERT INTO plant_fruits (name, length)
VALUES ('manderijn', '0'),
       ('appels','0'),
       ('kersen','0');

INSERT INTO plant_herbs (name, good_for_tea)
VALUES ('rozemarijn','true'),
       ('dille', 'true'),
       ('kamille', 'true');

INSERT INTO PLANT_VEGETABLES (name)
VALUES ('tomaat'),
       ('wortel'),
       ('pompoen');

INSERT INTO authorities( role , username )
VALUES (0, 'vivalanouk'),
       (1, 'guus'),
       (2, 'zonnestraal80'),
       (3, 'luminousnodes'),
       (4, 'zorro');

