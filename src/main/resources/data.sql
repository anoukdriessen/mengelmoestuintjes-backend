INSERT INTO users (username, password, enabled, email, level, xp, level_up_limit, name, birthday, province, member_since)
VALUES ('gebruiker', '$2a$12$NqhSMXWP/LqMLaOwNw1naevyjgEUeJHiaeGiMgOsrYvRab4CpXMD6', TRUE, 'info@mengelmoestuintjes.nl',  '1',  '1000', '2000', 'Gustavo', '01-04-2000', 'DRENTHE',    '2022-01-01'),
       ('itiskevin', '$2a$12$YpxMaVfaXlIh11w2WJ3fyeWZ2GTUcR4sHzxDKC0XoqGlNZhQUsklu', TRUE, 'kevin@mengelmoestuintjes.nl', '99', 'MAX',  'MAX',  'Kevin',   '12-01-1991', 'OVERIJSSEL', '2022-01-01'),
       ('lmnadora',  '$2a$12$4SA1oYMfKWq3cRGj8GQhA.kK5Cojnhg5Ru47iTi31vmO1X2zh9Roy', TRUE, 'iris@mengelmoestuintjes.nl',  '1',  '1000', '2000', '',        '12-01-1991', 'LIMBURG',    '2022-01-01');

INSERT INTO users (username, password, enabled, email, level, xp, level_up_limit, name, birthday, province, member_since, profile_img)
VALUES ('mengelmoestuintje', '$2a$12$DOhxC8HDGcaQOtSfUQLAI.JyCp4dvawek/i/CNzXHIcKtPJzaBWAS', TRUE, 'moderator@mengelmoestuintjes.nl', '99', 'MAX', 'MAX', 'Mengelmoestuintje', '22-02-2022', 'HIDDEN',     '2022-01-01', bytea('dmengelmoestuintje.png')),
       ('vivalanouk',        '$2a$12$B3RmAPRfiGfTSSXPkioJau17rUV2Ao6txtFEWiRTN9FPyyBLmr6Mm', TRUE, 'info@anoukdriessen.nl',           '1',  '1000','2000','Anouk',             '22-04-1995', 'OVERIJSSEL', '2022-01-01', bytea('vivalanouk.JPG'));

INSERT INTO authorities (authority, username)
VALUES ('ROLE_USER',      'gebruiker'),
       ('ROLE_USER',      'itiskevin'),
       ('ROLE_USER',      'lmnadora'),
       ('ROLE_USER',      'mengelmoestuintje'),
       ('ROLE_USER',      'vivalanouk'),
       ('ROLE_MODERATOR', 'mengelmoestuintje'),
       ('ROLE_MODERATOR', 'vivalanouk'),
       ('ROLE_ADMIN',     'vivalanouk');

INSERT INTO quotes ( author, text )
VALUES ('mengelmoestuintjes',   'Je hebt iedere dag twee keuzes: Groeien of Herhalen'),
       ('gertraude beese',      'Een leven zonder dromen is als een tuin zonder bloemen'),
       ('phil bosman',          'De wonderen van heel de wereld vind je terug in je eigen tuin'),
       ('ireen_boerderijgeluk', 'Wat met liefde is geplant raakt nooit uitgebloeid'),
       ('claudia.mytowergarden','Als jij een bloem was, zou ik jou plukken'),
       ('anouk driessen',       'Vergeet niet eerst je eigen bloemen water te geven'),
       ('percy b. green',       'Een man van woorden en niet van daden is als een tuin vol onkruid'),
       ('paul bourget',         'Verzorg uw tuin, en pluk er de vruchten voor anderen'),
       ('william shakespeare',  'Ons lichaam is onze tuin, waarvan onze wil de tuinman is'),
       ('alexander smith',      'Diepgeworteld in het menselijk hart is er de voorliefde voor tuinen en tuinieren.'),
       ('anastasius grün',      'Bloemen zijn langs elk pad te vinden, doch niet ieder weet er een krans van te vlechten.'),
       ('frida schanz',         'Zonder moeite geen vreugde, wie bloemen plant, moet veel water dragen.'),
       ('robert ulrich',        'Uitzicht op een groene omgeving werkt heilzaam'),
       ('the independent',      'Tuinieren is goed voor uw gezondheid. Het vermindert de stress, het verlaagt de bloeddruk en u leeft langer.'),
       ('dr. brigid boardman',  'De Tuin levert tegengif voor de pijn en frustratie van de oude dag.'),
       ('chinees gezegde',      'Hij die een tuin plant plant geluk'),
       ('b. nichols',           'Overweldigd worden door de geur van bloemen is een heerlijk soort nederlaag');

INSERT INTO posts (category, published, user_id, title, description)
VALUES ('NOTE', FALSE, 'gebruiker', 'Tomatenplant in de gaten houden!', 'Ik denk dat mijn tomatenplant een ziekte heeft opgelopen, ik moet deze de komende dagen extra in de gaten houden!'),
       ('NOTE', FALSE, 'gebruiker', 'Wishlist voor bloemen', 'Ik wil graag nog 3 verschillende soorten dahlias, korenbloemen en wat opvulling voor in de border'),
       ('NOTE', FALSE, 'gebruiker', 'Mijn tuindagen:', 'MA / WO / VR, Kevin zal DI en DO gaan, in het weekend gaan we samen'),
       ('NOTE', FALSE, 'itiskevin', 'Tomatenplant in de gaten houden!', 'Gustavo wees mij erop dat de tomatenplant wellicht een ziekte heeft, extra aandacht vereist!'),
       ('NOTE', FALSE, 'itiskevin', 'Sparen voor een nieuwe kas', 'Nodig: 1500 euro, momenteel: 750 euro'),
       ('NOTE', FALSE, 'itiskevin', 'Mijn tuindagen:', 'DI / DO, Gustavo zal MA, WO, VR gaan, in het weekend gaan we gezellig samen'),
       ('NOTE', FALSE, 'lmnadora',  'NOTE TO SELF!', 'Ik haat slakken!'),
       ('NOTE', FALSE, 'lmnadora',  'In maart krijg ik mijn nieuwe volkstuintje!', 'Planning maken!!!!'),
       ('NOTE', FALSE, 'lmnadora',  'Zaden die ik nog zoek:', 'Pastinaak, verschillende soorten bieten, winter en zomer wortels');

INSERT INTO posts (category, published, user_id, title, description)
VALUES ('NOTE', FALSE, 'vivalanouk',  'Deadline V1 22-02-2022', 'SPANNEND! link naar trello: Front-End: trello.com/b/gvU03u16/front-end / Back-End: trello.com/b/97sjzqUL/back-end'),
       ('NOTE', FALSE, 'vivalanouk',  'Marketing meeting verplaatst!', 'NIET maandag 7/2 10.00 --> vrijdag 11/2 13.30'),
       ('NOTE', FALSE, 'vivalanouk',  'Developers meeting inplannen', 'maandag 7/2 10.00-12.30 OF donderdag 10/2 15.00-17.30'),
       ('NOTE', FALSE, 'vivalanouk',  'Mijn weekly GOALS', '5 nieuwe volgers, 5% meer bereik'),
       ('NOTE', FALSE, 'vivalanouk',  'Instagram posts', 'MA: foto, DI: story, WO: reel, DO: story, VR: reel'),
       ('NOTE', FALSE, 'mengelmoestuintje',  'Deadline V1 22-02-2022', 'SPANNEND! link naar trello: Front-End: trello.com/b/gvU03u16/front-end / Back-End: trello.com/b/97sjzqUL/back-end'),
       ('NOTE', FALSE, 'mengelmoestuintje',  'Marketing meeting verplaatst!', 'NIET maandag 7/2 10.00 --> vrijdag 11/2 13.30'),
       ('NOTE', FALSE, 'mengelmoestuintje',  'Blogberichten deze week', 'Ma 8.00: zaai je mee, Wo 10.00: lekkere plekjes, Zo: de tuintaken van deze maand'),
       ('NOTE', FALSE, 'mengelmoestuintje',  'Academy deadline 01-03-2022', '2 meetings inplannen voor die tijd!'),
       ('NOTE', FALSE, 'mengelmoestuintje',  'Mijn weekly GOALS', '3 blogberichten met 1% meer bereik, 1 GIVE-AWAY van heerlijke huisjes');

INSERT INTO posts (category, published, user_id, created, title, summary, description)
VALUES ('POST', TRUE,  'gebruiker', '2022-01-01T14:12:29.5216685',
            '#MeetTheGardener','Hoi! Laat ik mij even voorstellen! Ik ben Gustavo samen met Kevin (@itiskevin) deel ik een volkstuintje in Steenwijk',
            'Tuinieren is een Corona hobby van mij geworden, door het vele thuiszitten miste ik de buitenlucht en besloot ik samen een volkstuintje te nemen, we waren net op tijd, na ons vulde de wachtrij zich enorm. Toch niet de enige met deze Corona hobby' ),
       ('POST', TRUE,  'gebruiker', '2022-01-31T14:12:29.5216685',
            '#DeOogstVan JANUARI','In het teken van 12 maanden tuinieren neem ik je mee in mijn oogst van Januari',
            'Deze maand ongeacht de kou toch nog redelijk wat winterse groenten kunnen oogsten van de tuin' ),
       ('POST', TRUE,  'gebruiker', '2022-01-05T14:12:29.5216685',
            'Oesterzammen uit eigen ZAK', 'Op instagram kwam ik @rotterzwam tegen, hier bestelde ik een cursus pakket, het resultaat verwacht je nooit!',
            'Oogstronde 1 bracht mij wel lieft 750 gram oesterzwammen uit 1 grote en 1 kleine zak, Oogstronde 2 ging x2 tegen alle verbazing in haalde we maar lieft 2 kilo oesterzwammen uit de zak. Het hele proces zie je op mijn instagram @ikbengebruiker'),
       ('POST', FALSE, 'gebruiker', '2022-01-07T14:12:29.5216685',
            'Wie oh wie kan mij helpen?','Helaas voor het derde jaar op rij zit ik met de gevreesde coloradokever',
            'Iedere dag controleer ik braaf de blaadjes van mijn aardappelplanten maar ze blijven maar komen, wat kan ik hiertegen doen?'),
       ('POST', FALSE, 'gebruiker', '2022-01-09T14:12:29.5216685',
            'Samen een rondje wandelen?','Wandelen is voor mij een soort van therapie, ik kom er helemaal tot rust, zoek jij nog een wandelmaatje?',
            'Ik ben namelijk opzoek naar een wandel maatje, alleen is namelijk ook maar zo alleen. Hoewel ik het soms heerlijk vind lijkt het mij leuk te kunnen brainstormen over ons dagelijkse bezigheden, wellicht heb jij wel de gouden tip voor me. Dus lijkt het je leuk neem dan contact met mij op via 0612345678. Whatsapp of bellen mag allebij!'),
       ('POST', TRUE,  'itiskevin', '2022-01-01T14:12:29.5216685',
            '#MeetTheGardener','Hoi! Laat ik mij even voorstellen! Ik ben Kevin samen met Gustavo (@gebruiker) deel ik een volkstuintje in Steenwijk',
            'Tuinieren is een Corona hobby van mij geworden, door het vele thuiszitten miste ik de buitenlucht en besloot ik samen een volkstuintje te nemen, we waren net op tijd, na ons vulde de wachtrij zich enorm. Toch niet de enige met deze Corona hobby' ),
       ('POST', TRUE,  'itiskevin', '2022-01-31T14:12:29.5216685',
            '#DeOogstVan JANUARI','In het teken van 12 maanden tuinieren neem ik je mee in mijn oogst van Januari',
            'Deze maand ongeacht de kou toch nog redelijk wat winterse groenten kunnen oogsten van de tuin' ),
       ('POST', TRUE,  'itiskevin', '2022-01-16T14:12:29.5216685',
            'Zag ken jij de oesterzwammenshoarma?','Gustavo (@gebruiker) kwam mij wel bijna een kilo oesterzwammen brengen en daar wist ik wel raad mee!',
            'Samen genoten we van een shoarma die je laat herinneren aan het moment dat de kroegen sluiten en je met allemaal dronken mensen de shoarma tent overhoop loopt'),
       ('POST', FALSE, 'gebruiker', '2022-01-16T14:12:29.5216685',
            'Ons moestuin winkeltje is geopend!','Ik heb besloten dat ik nog meer met jullie wil delen',
            'Iedere vrijdag kun je bij ons op de moestuin een versbox ophalen, de rest van de week kun je een bestelling plaatsen via ons instagram account @demoestuinwinkelvankevin'),
       ('POST', FALSE, 'gebruiker', '2022-01-16T14:12:29.5216685',
            'Aanrader! GEOCACHEN op de Engelsmanplaat','info engelsman plaat + geocache',
            'TODO mijn beleving beschrijven'),
       ('POST', TRUE,  'lmnadora', '2022-01-01T14:12:29.5216685',
            '#MeetTheGardener','Hoi! Laat ik mij even voorstellen! Samen met mijn ouders tuinieren we erop los in onze eigen tuin',
            'Dit jaar met de uitbreiding van een mooie kas, ik kan niet wachten wat dit oogst seizoen ons gaat brengen!' ),
       ('POST', TRUE,  'lmnadora', '2022-01-31T14:12:29.5216685',
            '#DeOogstVan JANUARI','In het teken van 12 maanden tuinieren neem ik je mee in mijn oogst van Januari',
            'Deze maand ongeacht de kou toch nog redelijk wat winterse groenten kunnen oogsten van de tuin' ),
       ('POST', TRUE,  'lmnadora', '2022-01-16T14:12:29.5216685',
            'Ben jij al begonnen met voorzaaien?','Ik wel! De pepers en paprikas beginnen hun kopje op te steken',
            'hoewel wij geen groeilicht hebben zijn ze prima opgekomen, bijna tijd voor ze in de kas te laten aansterken dan krijgen we richting de zomer weer heerlijk huisgemaakte sambal'),
       ('POST', FALSE, 'lmnadora', '2022-01-16T14:12:29.5216685',
            'Open tuindag!','In ons dorp organiseren we een opentuindag, ook ons tuintje wordt opengesteld voor publiek, kom je langs?',
            'Je bent welkom op zondag 20 februari aan de langebaanweg in meers limburg, van 10.00 tot 16.00 staat er koffie, zelfgemaakt gebak en bakken gezeligheid voor je klaar'),
       ('POST', FALSE, 'lmnadora', '2022-01-16T14:12:29.5216685',
            'Help mij!','Ik kom maar niet van de slakken af',
            'Tips voor aaltjes ?');

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
VALUES ('vivalanouk', 'TODO', FALSE, 'Nieuwe zaden kopen', '01-01-2022', '10-02-2022'),
       ('vivalanouk', 'TODO', FALSE, 'Gieter kopen', '01-01-2022', '12-02-2022'),
       ('vivalanouk', 'TODO', FALSE, 'Tweedehands tuinmaterialen scoren bij de kringloop', '13-02-2022', '28-01-2022'),
       ('vivalanouk', 'TODO', FALSE, 'compostbak omscheppen', '01-01-2022', '14-02-2022'),
       ('vivalanouk', 'TODO', FALSE, 'Datum plannen voor oogstdiner', '01-01-2022', '14-02-2022'),
       ('itiskevin', 'TODO', FALSE, 'Nieuwe zaden kopen', '01-01-2022', '10-02-2022'),
       ('itiskevin', 'TODO', FALSE, 'Gieter kopen', '01-01-2022', '12-02-2022'),
       ('itiskevin', 'TODO', FALSE, 'Tweedehands tuinmaterialen scoren bij de kringloop', '13-02-2022', '28-01-2022'),
       ('itiskevin', 'TODO', FALSE, 'compostbak omscheppen', '01-01-2022', '14-02-2022'),
       ('itiskevin', 'TODO', FALSE, 'Datum plannen voor oogstdiner', '01-01-2022', '14-02-2022');

INSERT INTO gardens(name, x, y, size )
VALUES ('volkstuintje', 3, 3, '9'),
       ('achtertuin', 2, 3, '6');

INSERT INTO gardens_users(garden_id, user_id)
VALUES (1, 'itiskevin'),
       (1, 'vivalanouk'),
       (2, 'vivalanouk');
