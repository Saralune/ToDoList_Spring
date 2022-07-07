-- ------------------------------------------------------------------------------
-- - Gestion droit d'accès                                   ---
-- ------------------------------------------------------------------------------

USE todolist;

-- -----------------------------------------------------------------------------
-- - Construction de la table des users                              ---
-- -----------------------------------------------------------------------------

INSERT INTO users(mail, PASSWORD, active) VALUES('saralune@mail.fr',	'$2a$12$PQBgT26y7hjZKdGChlkdPOaO16hNcwa3WFrEYFuuMUi2oyxqBs59K', 1);
INSERT INTO users(mail, PASSWORD, active) VALUES('test@test.fr',	'$2a$12$0suqSj/n2RsVKao..cGqLuO41/9gUXPEZKrq5sF3y45uadbxfSYsm', 1);
INSERT INTO users(mail, PASSWORD, active) VALUES('sara@test.fr',	'$2a$12$9uHBCsoPwLVgfKfLIkX2GO3B1O6PO6UiMFU4gSrL4eEKMulUA89TK', 1);

SELECT * FROM users;

-- -----------------------------------------------------------------------------
-- - Construction de la table avec 2 roles principaux                       ---
-- -----------------------------------------------------------------------------

INSERT INTO role ( role ) VALUES ( 'USER' );

SELECT * FROM role;

-- -----------------------------------------------------------------------------
-- - Construction de la table des roles par user         -- -
-- -----------------------------------------------------------------------------

INSERT INTO users_role(users_id, role_id) VALUES('1', '1');
INSERT INTO users_role(users_id, role_id) VALUES('2', '1');

SELECT * FROM users_role;