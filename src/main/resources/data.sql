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

INSERT INTO tasks_garden (title, description, done, points, month)
    VALUES ('De grote schoonmaak #1','Maak je tuin klaar voor het nieuwe seizoen.','false','50','1'),
           ('Appelboom gesnoeid','Attention, Interest, Desire, Action','false','500','1'),
           ('Klimop gesnoeid','Attention, Interest, Desire, Action','false','500','1'),
           ('Planten water geven','Attention, Interest, Desire, Action','false','100','0'),
           ('Vogels een handje geholpen','Bijgevoerd Attention, Interest, Desire, Action','false','100','2'),
           ('Eerste maand in seizoen #1','Attention, Interest, Desire, Action','false','1000','3'),
           ('Mijn eerste tomaat','Attention, Interest, Desire, Action','false','250','3'),
           ('Mijn eerste komkommer','Attention, Interest, Desire, Action','false','250','4'),
           ('Mijn eerste pompoen','Attention, Interest, Desire, Action','false','250','5'),
           ('Mijn eerste halloween','Attention, Interest, Desire, Action','false','250','10'),
           ('Mijn eerste kerst','Attention, Interest, Desire, Action','false','250','12'),
           ('Voor de eerste x deze Vogel of Nest gespot','In Mei leggen alle vogels een ei','false','250','5'),
           ('Vergeten Groenten Geplant','Attention, Interest, Desire, Action','false','250','5'),
           ('Vergeten Groenten Geoogst','Attention, Interest, Desire, Action','false','250','8'),
           ('Mijn vergeten groeten creatie','Attention, Interest, Desire, Action','false','150','8'),
           ('Vogels een handje geholpen','Attention, Interest, Desire, Action','false','100','12'),
           ('Deze hoor je niet te zien','error','false','0','13'),
           ('De grote schoonmaak #2','Maak je tuin klaar voor het nieuwe seizoen.','false','50','4'),
           ('De grote schoonmaak #3','Maak je tuin klaar voor het nieuwe seizoen.','false','50','7'),
           ('De grote schoonmaak #4','Maak je tuin klaar voor het nieuwe seizoen.','false','50','10'),
           ('Deze is maandofhankelijk','jaja','false','0','0');

INSERT INTO tasks_todo (title, description, done, due_date)
    VALUES ('Water geven','lala','false','16-12-2021'),
           ('Het zaaien van X','lala','false','17-12-2021'),
           ('Het voorzaaien van X','lala','false','18-12-2021'),
           ('Onkruid weghalen','lala','false','15-12-2021');


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