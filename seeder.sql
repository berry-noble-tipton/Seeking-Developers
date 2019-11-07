USE seeking_db;

insert into users (id, email, github, is_admin, linkedin, password, photo, username, dev_type_id) values (1, 'smumford0@clickbank.net', 'www.github.com/dummy1', 1, 'www.linkedin.com/dummy1', '8lWHK72', 'http://dummyimage.com/233x163.png/cc0000/ffffff', 'debbens0', 1);
insert into users (id, email, github, is_admin, linkedin, password, photo, username, dev_type_id) values (2, 'dpottle1@instagram.com', 'www.github.com/dummy2', 0, 'www.linkedin.com/dummy2', 'bA1DZKyRJuV', 'http://dummyimage.com/117x218.bmp/cc0000/ffffff', 'ledgeon1', 2);
insert into users (id, email, github, is_admin, linkedin, password, photo, username, dev_type_id) values (3, 'fcasbourne2@ning.com', 'www.github.com/dummy3', 1, 'www.linkedin.com/dummy3', 'gQA8VDbTu', 'http://dummyimage.com/129x112.png/5fa2dd/ffffff', 'emichelmore2', 3);

insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values ();
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (1, '11/27/2018', 'Thylogale stigmatica', 1, 'Dangerous Liaisons', 1, 1);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (2, '5/11/2019', 'Priodontes maximus', 0, 'Swept Away', 1, 2);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (3, '10/22/2018', 'Antidorcas marsupialis', 0, 'Ali G Indahouse', 1, 3);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (4, '1/28/2019', 'Cyrtodactylus louisiadensis', 0, 'In the Bedroom', 1, 1);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (5, '7/17/2018', 'Bison bison', 1, 'Delivery Man', 1, 2);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (6, '5/16/2019', 'Galictis vittata', 1, 'Killing Season', 1, 3);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (7, '6/9/2019', 'Ara ararauna', 0, 'Rat Race, The (Garson Kanin''s The Rat Race)', 1, 1);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (8, '5/10/2019', 'Certotrichas paena', 1, 'Come On, Rangers', 2, 2);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (9, '11/21/2018', 'Streptopelia decipiens', 0, 'Brazil', 2, 3);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (10, '11/17/2018', 'Ictonyx striatus', 0, 'Beyond Silence (Jenseits der Stille)', 2, 1);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (11, '4/27/2019', 'Catharacta skua', 1, 'Along Came Polly', 2, 2);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (12, '5/17/2019', 'Bassariscus astutus', 0, 'Wedding Planner, The', 2, 3);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (13, '11/10/2018', 'Potorous tridactylus', 0, 'Plastic Paradise: The Great Pacific Garbage Patch', 2, 1);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (14, '9/11/2018', 'Cordylus giganteus', 0, 'Ox-Bow Incident, The', 3, 2);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (15, '8/6/2018', 'Upupa epops', 0, 'Great Escape: The Untold Story', 3, 3);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (16, '4/14/2019', 'Ursus arctos', 1, 'Trapped Ashes', 3, 1);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (17, '5/17/2019', 'Pseudalopex gymnocercus', 0, '5x2', 3, 2);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (18, '12/27/2018', 'Tamandua tetradactyla', 0, 'Fandry', 3, 3);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (19, '4/29/2019', 'Ara chloroptera', 1, 'Play it to the Bone', 3, 1);
insert into projects (id, creatingDate, description, is_complete, title, creator_id, dev_type_id) values (20, '9/25/2018', 'Megaderma spasma', 0, 'Whole Wide World, The', 3, 2);

use seeking_db;

insert into dev_type (id, title) values (1, 'front-end');
insert into dev_type (id, title) values (2, 'back-end');
insert into dev_type (id, title) values (3, 'full-stack');

insert into languages (id, language) values (1, 'HTML');
insert into languages (id, language) values (2, 'jQuery');
insert into languages (id, language) values (3, 'CSS');
insert into languages (id, language) values (4, 'Javascript');
insert into languages (id, language) values (5, 'Java');
insert into languages (id, language) values (6, 'Spring');
insert into languages (id, language) values (7, 'Node JS');
insert into languages (id, language) values (8, 'Bootstrap Framework');



insert into user_languagess (languages_id, users_id) values (1, 1);
insert into user_languagess (languages_id, users_id) values (2, 2);
insert into user_languagess (languages_id, users_id) values (3, 3);

insert into favorite_users (picked_user_id, picking_user_id) values (2, 1);
insert into favorite_users (picked_user_id, picking_user_id) values (2, 3);
insert into favorite_users (picked_user_id, picking_user_id) values (1, 3);

insert into user_projects (user_id, project_id) values (1, 1);
insert into user_projects (user_id, project_id) values (1, 2);
insert into user_projects (user_id, project_id) values (1, 3);
insert into user_projects (user_id, project_id) values (1, 4);
insert into user_projects (user_id, project_id) values (1, 5);
insert into user_projects (user_id, project_id) values (1, 6);
insert into user_projects (user_id, project_id) values (1, 7);
insert into user_projects (user_id, project_id) values (2, 8);
insert into user_projects (user_id, project_id) values (2, 9);
insert into user_projects (user_id, project_id) values (2, 10);
insert into user_projects (user_id, project_id) values (2, 11);
insert into user_projects (user_id, project_id) values (2, 12);
insert into user_projects (user_id, project_id) values (3, 13);
insert into user_projects (user_id, project_id) values (3, 14);
insert into user_projects (user_id, project_id) values (3, 15);
insert into user_projects (user_id, project_id) values (3, 16);
insert into user_projects (user_id, project_id) values (3, 17);
insert into user_projects (user_id, project_id) values (3, 18);
insert into user_projects (user_id, project_id) values (3, 19);
insert into user_projects (user_id, project_id) values (3, 20);


use seeking_db;

insert into languages (id, language) values (1, 'HTML');
insert into languages (id, language) values (2, 'jQuery');
insert into languages (id, language) values (3, 'CSS');
insert into languages (id, language) values (4, 'Javascript');
insert into languages (id, language) values (5, 'Java');
insert into languages (id, language) values (6, 'Spring');
insert into languages (id, language) values (7, 'Node JS');
insert into languages (id, language) values (8, 'Bootstrap Framework');