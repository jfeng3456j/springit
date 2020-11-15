INSERT INTO link (id, creation_date,last_modified_date,
created_by,last_modified_by,title,url)
VALUES(1,NOW(),NOW(),NULL,NULL, 'Getting started with Spring Boot 2', 'https://therealdanvega.com/spring-boot-2');

INSERT INTO comment(id,created_by, creation_date, last_modified_by, last_modified_date, body, link_id)
VALUES(1, null , Now(), null, Now(), 'Awesome idea for a course',1);