--CREATES THE MEMBER TABLE AND POPULATES IT
CREATE TABLE member (
  uid INT UNSIGNED NOT NULL AUTO_INCREMENT,
  fName VARCHAR(25) NOT NULL,
  lName VARCHAR(25) NOT NULL,
  pNumber VARCHAR(10) NOT NULL,
  cCard VARCHAR(16) NOT NULL,
  uName VARCHAR(25) NOT NULL,
  admin TINYINT NOT NULL DEFAULT 0,
  password VARCHAR(256) NOT NULL,
  PRIMARY KEY (uid),
  UNIQUE INDEX uid_UNIQUE (uid ASC),
  UNIQUE INDEX uName_UNIQUE (uName ASC));
  
  INSERT INTO member (uid,fName,lName,pNumber,cCard,uName,admin,password) 
  VALUES (1,"John","Smith",9059059059,1111222233334444,"JohnnyBoy",0,"password123"),
  (2,"William","Doe",9059059055,4444333322221111,"admin",1,"admin"),
  (3,"Jane","Dough",9059059000,1234123412341234,"money",0,"cash");
  
  --CREATES THE CARS TABLE AND POPULATES IT
  CREATE TABLE cars (
  id int UNSIGNED NOT NULL AUTO_INCREMENT,
  model  varchar(45) NOT NULL,
  make varchar(20) NOT NULL,
  price float NOT NULL,
  availability varchar(11) NOT NULL,
  location varchar(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cars (id,model,make,price,availability,location) 
VALUES (1,"Honda","Civic",29.99,"4","Brampton"),
(2,"Dodge","Ram 1500",39.99,"2","Oakvile"),
(3,"BMW","i8",69.99,"1","Mississauga");  
  
  
  
--CREATES THE ORDERS TABLE AND POPULATES IT
CREATE TABLE orders (
  oid int(11) NOT NULL AUTO_INCREMENT,
  uid int(11) NOT NULL,
  id int(11) NOT NULL,
  dateRented date NOT NULL,
  dateReturned varchar(45) DEFAULT NULL,
  active tinyint(4) NOT NULL,
  PRIMARY KEY (oid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO orders (oid,uid,id,dateRented,dateReturned,active) 
VALUES (1,1,1,CURDATE() ,NULL,0),
(2,2,2,"2017-12-05" ,"2017-12-12",1),
(3,3,3,"2017-12-08" ,"2017-12-16",1);