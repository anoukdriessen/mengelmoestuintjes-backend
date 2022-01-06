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

INSERT INTO users(id, password, enabled)
VALUES  ('guus', '$2a$10$uKGUS7jKVu5AFIeb9OgdKu8KFt.qwqhlKXimA27W0.pcjqMrnrVki', true),
        ('anouk', '$2a$10$P1ssYzPmSevyoeZ7nngWlOboBJvwv/uW3IT3c1aKIy77ueOZz/IeS', true);

INSERT INTO authorities(username, authority)
VALUES ('guus', 'ROLE_USER'),
       ('anouk', 'ROLE_ADMIN');