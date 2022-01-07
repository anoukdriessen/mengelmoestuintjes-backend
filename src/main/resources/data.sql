INSERT INTO users (username, password, enabled, email,
                   level, xp, level_up_limit, name, birthday,
                   province)
VALUES ('itiskevin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE, 'info@mengelmoestuintjes.nl',
        '1', '1000', '2000', 'Kevin', '30-12-1991',
        'OVERIJSSEL'),
       ('mengelmoestuintje', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE, 'moderator@mengelmoestuintjes.nl',
        '1', '1000', '2000', 'Mengelmoestuintje', '02-02-2022',
        'HIDDEN'),
       ('vivalanouk', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE, 'info@anoukdriessen.nl',
        '99', 'MAX', 'MAX', 'Anouk', '22-04-1995',
        'OVERIJSSEL');

INSERT INTO authorities (username, authority)
VALUES ('itiskevin', 'ROLE_USER'),
       ('mengelmoestuintje', 'ROLE_USER'),
       ('mengelmoestuintje', 'ROLE_MODERATOR'),
       ('vivalanouk', 'ROLE_USER'),
       ('vivalanouk', 'ROLE_MODERATOR'),
       ('vivalanouk', 'ROLE_DEVELOPER'),
       ('vivalanouk', 'ROLE_ADMIN');
