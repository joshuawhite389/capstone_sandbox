BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE family (
	family_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	family_name varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,

	CONSTRAINT pk_family PRIMARY KEY (family_id)
	
);

INSERT INTO family (family_name, password_hash) VALUES ('family1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');
INSERT INTO family (family_name, password_hash) VALUES ('family2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');

CREATE TABLE family_members (
	member_id serial NOT NULL,
	member varchar(50) NOT NULL,
	parent boolean NOT NULL,
	time_reading numeric DEFAULT 0,
	family_id int NOT NULL,

	CONSTRAINT pk_family_members PRIMARY KEY (member_id),
	CONSTRAINT fk_family_members FOREIGN KEY (family_id) REFERENCES family (family_id)
);

INSERT INTO family_members (member, parent, family_id) VALUES ('Tim', true, 1);
INSERT INTO family_members (member, parent, family_id) VALUES ('Sarah', true, 2);
INSERT INTO family_members (member, parent, family_id) VALUES ('Cam', false, 1);
INSERT INTO family_members (member, parent, family_id) VALUES ('Josh', false, 2);

COMMIT TRANSACTION;