--Load Service Data
INSERT INTO mmsdb.Service(id,isDeleted,label,serviceCode,serviceDiscount) VALUES ('1', false, "Hospitalisation", "s1", 10);
INSERT INTO mmsdb.Service(id,isDeleted,label,serviceCode,serviceDiscount) VALUES ('2', false, "Hébèrgement", "s2", 10);
INSERT INTO mmsdb.Service(id,isDeleted,label,serviceCode,serviceDiscount) VALUES ('3', false, "Transport", "s3", 10);
INSERT INTO mmsdb.Service(id,isDeleted,label,serviceCode,serviceDiscount) VALUES ('4', false, "Consultation", "s4", 10);
INSERT INTO mmsdb.Service(id,isDeleted,label,serviceCode,serviceDiscount) VALUES ('5', false, "Billetterie", "s5", 10);
INSERT INTO mmsdb.Service(id,isDeleted,label,serviceCode,serviceDiscount) VALUES ('6', false, "Pharmacie", "s6", 10);

--Load Department Data
INSERT INTO mmsdb.Department(id,isDeleted,codeDepartment,label) VALUES ('1', false, "DG", "Direction Générale");
INSERT INTO mmsdb.Department(id,isDeleted,codeDepartment,label) VALUES ('2', false, "CGA", "Contrôle Gestion et Audit");
INSERT INTO mmsdb.Department(id,isDeleted,codeDepartment,label) VALUES ('3', false, "DC", "Direction Communication");
INSERT INTO mmsdb.Department(id,isDeleted,codeDepartment,label) VALUES ('4', false, "DAF", "Direction Audit et Finance");
INSERT INTO mmsdb.Department(id,isDeleted,codeDepartment,label) VALUES ('5', false, "DSH", "Direction Santé et Hospitalisation");
INSERT INTO mmsdb.Department(id,isDeleted,codeDepartment,label) VALUES ('6', false, "DHT", "Direction hôtellerie et Transport");
INSERT INTO mmsdb.Department(id,isDeleted,codeDepartment,label) VALUES ('7', false, "DRL", "Direction Réception et Logistique");

--Load Function Data
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('1',"Directeur Général",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('2',"Contrôle Gestion et Audit",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('3',"Responsable Communication et Logistique",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('4',"Directeur Audit et Finance",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('5',"Attaché de Santé",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('6',"Attaché Hôtelleries et transport",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('7',"Réception et Logistique",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('8',"Logistique",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('9',"Responsable Finance et Trésorerie",false);
INSERT INTO mmsdb.Function(id,title,isDeleted) VALUES ('10',"Responsable Facturation et Comptabilité",false);

--Load Countries Data
INSERT INTO mmsdb.Country(id,isDeleted,label,countryCode) VALUES ('1', false, "Tunisia", "TN");
INSERT INTO mmsdb.Country(id,isDeleted,label,countryCode) VALUES ('2', false, "Libya", "LY");
INSERT INTO mmsdb.Country(id,isDeleted,label,countryCode) VALUES ('3', false, "France", "FR");

--Load Regions Data
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('1', false, "Tunis","",1);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('2', false, "Sousse","",1);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('3', false, "Sfax","", 1);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('4', false, "Tripoli","",2);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('5', false, "Zentan","", 2);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('6', false, "Benghazi","",2);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('7', false, "Paris","",3);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('8', false, "Marseille","", 3);
INSERT INTO mmsdb.Region(id,isDeleted,label,regionCode,country_id) VALUES ('9', false, "Toulouse","",3);

--Load Cities Data
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('1', false, "El Menzah","1100",1);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('2', false, "Bab Souika","2050",1);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('3', false, "La Marsa","1780", 1);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('4', false, "Tripoli 1","500",4);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('5', false, "Tripoli 2","600", 4);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('6', false, "Tripoli 3","600",4);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('7', false, "Paris 1","10250",7);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('8', false, "Paris 2","11250", 7);
INSERT INTO mmsdb.City(id,isDeleted,label,cityCode,region_id) VALUES ('9', false, "Paris 3","12250",7);

--Load System Resource Data
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('1', false, "Patient","Res_Patient");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('2', false, "Employee","Res_Employee");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('3', false, "Doctor","Res_Doctor");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('4', false, "Agence d'Assurance","Res_Agence_Assurance");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('5', false, "Contrat","Res_Contrat");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('6', false, "Hôtel","Res_Hotel");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('7', false, "Clinique","Res_Clinique");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('8', false, "Agence de voyage","Res_Agence_Voyage");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('9', false, "Agence de location de voiture","Res_Agence_location_Voiture");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('10', false, "Pharmacie","Res_Pharmacie");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('11', false, "Séjour","Res_Sejour");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('23', false, "Réservation Hôtel","Res_reservation");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('12', false, "Hospitalisation","Res_Hospitalisation");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('13', false, "Transport","Res_Transport");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('14', false, "Groupe","Res_Groupe");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('15', false, "Permission","Res_Permission");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('16', false, "Ressource Système","Res_System_Resource");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('17', false, "Pays","Res_Pays");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('18', false, "Région","Res_Region");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('19', false, "Ville","Res_Ville");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('20', false, "Département","Res_Department");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('21', false, "Type d'Agence","Res_Type_Agence");
INSERT INTO mmsdb.SystemResource(id,isDeleted,label,systemResourceCode) VALUES ('22', false, "Service","Res_Service");

--Load Permission Data
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('1', false, "Ajouter Patient",1,"ADD");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('2', false, "Modifier Patient",1,"UPDATE");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('3', false, "Afficher Patient",1,"VIEW");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('4', false, "Supprimer Patient",1,"DELETE");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('5', false, "Ajouter Docteur",2,"ADD");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('6', false, "Modifier Docteur",2,"UPDATE");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('7', false, "Afficher Docteur",2,"VIEW");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('8', false, "Supprimer Docteur",2,"DELETE");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('9', false, "Ajouter Séjour",11,"ADD");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('10', false, "Modifier Séjour",11,"UPDATE");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('11', false, "Afficher Séjour",11,"VIEW");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('12', false, "Supprimer Séjour",11,"DELETE");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('13', false, "réserver hôtel",23,"ADD");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('14', false, "afficher réservation hôtel",23,"VIEW");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('15', false, "modifier réservation hôtel",23,"UPDATE");
INSERT INTO mmsdb.Permission(id,isDeleted,label,systemResource_id,operation) VALUES ('16', false, "Supprimer réservation hôtel",23,"DELETE");

--Load Groupe Data
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('1', false, "Administrateur");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('2', false, "Contrôle Gestion");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('3', false, "Audit");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('4', false, "Communication");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('5', false, "Santé");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('6', false, "Transport");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('7', false, "Hebergement");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('8', false, "Finance et Trésorerie");
INSERT INTO mmsdb.Groupe(id,isDeleted,label) VALUES ('9', false, "Facturation et Comptabilité");

--Set Groupe Permissions
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,1);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,2);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,3);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,4);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,5);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,6);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,7);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,8);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,9);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,10);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,11);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,12);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,13);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,14);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,15);
INSERT INTO mmsdb.Groupe_Permission (groupe_id,permission_id) VALUES (1,16);

--Load Person Data
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('1',"1987-12-06 01:12:00","Celibataire",NULL,NULL,"Ounissi","MALE","Mr",false,"Saber","ounissi.saber@gmail.com","06555575","P14f95",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('2',"1975-10-22 01:12:00","Married",NULL,NULL,"Nasri","MALE","Mr",false,"Mohamed","nasri.mohamed@gmail.com","05845962","P1258456",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('3',"1981-11-15 01:12:00","Celibataire",NULL,NULL,"Abid","MALE","Mr",false,"Haroun","abid.haroun@gmail.com","0725197","P45F753",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('4',"1960-04-10 01:12:00","Married",NULL,NULL,"Charni","FEMALE","Mr",false,"Fatma","charni.fatma@gmail.com","03482496","P748632",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('5',"1990-08-19 01:12:00","Celibataire",NULL,NULL,"Dhokkar","MALE","Mr",false,"Oussema","d.oussema@gmail.com","06124632","P15F633",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('6',"1970-05-01 01:12:00","Married",NULL,NULL,"Naily","MALE","Mr",false,"Besma","neily.besma@gmail.com","02478525","P1258456",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('7',"1958-02-18 01:12:00","Married",NULL,NULL,"Jber","MALE","Mr",false,"Fares","jaber.fares@gmail.com","0742369","P1258456",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('8',"1968-09-28 01:12:00","Married",NULL,NULL,"Helmi","MALE","Mr",false,"wassim","helmi.wassim@gmail.com","09412856","P1258456",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('9',"1986-03-12 01:12:00","Celibataire",NULL,NULL,"Sadki","MALE","Mr",false,"Amel","sadki.amel@gmail.com","02484365","P1258456",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('10',"1986-03-12 01:12:00","Celibataire",NULL,NULL,"Rabeh","MALE","Mr",false,"Khaled","rabeh.khaled@gmail.com","07854126","P521478",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('11',"1986-03-12 01:12:00","Celibataire",NULL,NULL,"Chawki","FEMALE","Miss",false,"Olfa","chawki.olfa@gmail.com","05478215","P481672",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('12',"1960-03-12 01:12:00","Married",NULL,NULL,"Ben Fadhel","MALE","Mr",false,"Slim","benfadhel.slim@gmail.com","05478215","P481672",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('13',"1965-03-12 01:12:00","Married",NULL,NULL,"Boujemla","MALE","Mr",false,"Ibrahim","chawki.olfa@gmail.com","05478215","P481672",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('14',"1970-03-12 01:12:00","Married",NULL,NULL,"LAABIDI","MALE","Mr",false,"Salah","chawki.olfa@gmail.com","05478215","P481672",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('15',"1975-03-12 01:12:00","Married",NULL,NULL,"Hentati","MALE","Mr",false,"Samir","chawki.olfa@gmail.com","05478215","P481672",NULL);
INSERT INTO  mmsdb.Person (id,birthday,civilState,createdAt,deletedAt,firstName,gender,honorificTitle,isDeleted,lastName,mail,matricule,passeport,updateAt) VALUES ('16',"1980-03-12 01:12:00","Married",NULL,NULL,"Raies","MALE","Mr",false,"Ahmed","chawki.olfa@gmail.com","05478215","P481672",NULL);
--Load Patient Data
INSERT INTO mmsdb.Patient (codePatient,id) VALUES ("P-0001",10);
INSERT INTO mmsdb.Patient (codePatient,id) VALUES ("P-0002",11);

--Load Employee Data
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0001",NULL,1,2500,"2010-01-01 00:01:00",'1',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0002",NULL,2,1000,"2010-01-01 00:01:00",'2',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0003",NULL,3,1800,"2010-01-01 00:01:00",'3',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0004",NULL,4,600,"2010-01-01 00:01:00",'4',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0005",NULL,5,800,"2010-01-01 00:01:00",'5',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0006",NULL,6,700,"2010-01-01 00:01:00",'6',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0007",NULL,7,1200,"2010-01-01 00:01:00",'7',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0008",NULL,8,1400,"2010-01-01 00:01:00",'8',NULL,NULL);
INSERT INTO mmsdb.Employee (codeEmployee,endDate,function_id,salary,startDate,id,department_id,manager_id) VALUES ("E-0009",NULL,9,900,"2010-01-01 00:01:00",'9',NULL,NULL);

--Load User Data
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('1',NULL,NULL,NULL,false,"admin","admin",NULL,1);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('2',NULL,NULL,NULL,false,"control","control",NULL,2);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('3',NULL,NULL,NULL,false,"audit","audit",NULL,3);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('4',NULL,NULL,NULL,false,"comm","comm",NULL,4);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('5',NULL,NULL,NULL,false,"sante","sante",NULL,5);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('6',NULL,NULL,NULL,false,"transport","transport",NULL,6);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('7',NULL,NULL,NULL,false,"hebergement","hebergement",NULL,7);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('8',NULL,NULL,NULL,false,"finance","finance",NULL,8);
INSERT INTO mmsdb.User (id,createdAt,deletedAt,encryptionType,isDeleted,login,password,updateAt,employee_id) VALUES ('9',NULL,NULL,NULL,false,"facturation","facturation",NULL,9);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,1);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,2);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,3);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,4);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,5);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,6);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,7);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,8);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (1,9);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (2,2);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (3,3);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (4,4);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (5,5);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (6,6);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (7,7);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (8,8);
INSERT INTO mmsdb.User_Groupe (user_id,groupe_id) VALUES (9,9);

--Load Provider Type Data
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('1', false, "Hôtel", "HOT");
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('2', false, "Agence Immobilière", "IMM");
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('3', false, "Agence voiture", "VOI");
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('4', false, "Clinique", "CLI");
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('5', false, "Agence d'Assurances", "ASS");
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('6', false, "pharmacie", "PH");
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('7', false, "Agence de Voyage", "AV");
INSERT INTO mmsdb.ProviderType(id,isDeleted,label,typeCode) VALUES ('8', false, "Aéroport", "AE");

--Load Service Provider Data
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('1', false, "Hotel Africa Tunis", "Yaakoub Maher","2015-05-07 00:00:00","hebergment",'71342343',"hotelafrica@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('2', false, "Hotel oasis Tunis", "Dhief Samir","2009-05-07 00:00:00","hebergment",'71342343',"oasisHotel@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('20', false, "Hotel Salambo Sfax", "Lamloum Brahim","2015-05-07 00:00:00","hebergment",'71342343',"salamboHotel@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('21', false, "Hotel elMedina", "Driss Louay","2010-06-06 00:00:00","hebergment",'71342343',"ElMedinaHotel@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('22', false, "Hotel Reyon", "Charfa Zakariya","2015-05-07 00:00:00","hebergment",'71342343',"rayonHotel@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('23', false, "Hotel SanGo", "Hamdi Noureddine","2015-05-07 00:00:00","hebergment",'71342343',"hotelsango@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('24', false, "Hotel oasis Sfax", "Chagra Saif","2009-05-07 00:00:00","hebergment",'71342343',"oasisHotel@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('25', false, "Hotel Salambo Tunis", "Ben Saiidane Nizar","2015-05-07 00:00:00","hebergment",'71342343',"salamboHotel@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('26', false, "Hotel elMedina Sousse", "Rezgui Hatem","2010-06-06 00:00:00","hebergment",'71342343',"ElMedinaHotel@gmail.com",'71345435','22123124','1');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('27', false, "Hotel Reyon Hamamet", "Kafi Yussef","2015-05-07 00:00:00","hebergment",'71342343',"rayonHotel@gmail.com",'71345435','22123124','1');

INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('30', false, "Agence Immobilière Saleh", "Saleh ben ahmed","2015-05-07 00:00:00","hebergment",'71342343',"amenim@gmail.com",'71345435','22123332','2');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('31', false, "Agence Immobilière Ali", "ali ben ahmed","2009-05-07 00:00:00","hebergment",'71342343',"agenceAli@gmail.com",'71345435','22123128','2');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('32', false, "Agence rentAcar", "hamza ben ahmed","2015-05-07 00:00:00","Agence transport",'71342343',"agenceamen@gmail.com",'71345435','22123128','3');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('33', false, "Agence Immobilière Amen", "sawsen mhamdi","2010-06-06 00:00:00","hebergment",'71342343',"agenceamen@gmail.com",'71345435','22123129','2');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('34', false, "Agence Immobiliere", "nansi ben ahmed","2015-05-07 00:00:00","Agence transport",'71342343',"immob23@gmail.com",'71345435','22123126','2');

INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('5', false, "Clinique Sokra", "Mohamed ben ahmed","2015-05-07 00:00:00","hebergment",'71342343',"amenim@gmail.com",'71345435','22123124','4');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('6', false, "Clinique Hannibaal", "Saleh ben ahmed","2015-10-09 00:00:00","Agence transport",'71342343',"amenim@gmail.com",'71345435','22123124','4');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('7', false, "Clinique Tawfik", "Iheb ben ahmed","2015-05-07 00:00:00","hebergment",'71342343',"amenim@gmail.com",'71345435','22123124','4');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('8', false, "Clinque Amen", "Saleh ben ahmed","2015-02-10 00:00:00","Agence transport",'71342343',"agtras@gmail.com",'71345435','22123124','4');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('9', false, "Clinique Oasis", "Saleh ben ahmed","2012-01-07 00:00:00","hebergment",'71342343',"hebergement@gmail.com",'71345435','22123124','4');

INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('60', false, "Pharmacie Sokra", "Mohamed ben ahmed","2015-05-07 00:00:00","hebergment",'71342343',"amenim@gmail.com",'71345435','22123124','6');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('61', false, "Pharmacie Hannibaal", "Saleh ben ahmed","2015-10-09 00:00:00","Agence transport",'71342343',"amenim@gmail.com",'71345435','22123124','6');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('62', false, "Pharmacie Tawfik", "Iheb ben ahmed","2015-05-07 00:00:00","hebergment",'71342343',"amenim@gmail.com",'71345435','22123124','6');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('63', false, "Pharmacie Amen", "Saleh ben ahmed","2015-02-10 00:00:00","Agence transport",'71342343',"agtras@gmail.com",'71345435','22123124','6');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('64', false, "Pharmacie Oasis", "Saleh ben ahmed","2012-01-07 00:00:00","hebergment",'71342343',"hebergement@gmail.com",'71345435','22123124','6');

INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('70', false, "Star Assurances", "Rjab Ahmed","2015-05-07 00:00:00","Assurances",'71342343',"rjab.ahmed@gmail.com",'71100200','94123456','5');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('71', false, "GAT Assurances", "Massoudi Ali","2015-10-09 00:00:00","Assurances",'71342343',"massoudi.ali@gmail.com",'71200300','31584210','5');
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('72', false, "Comar Assurances", "Dallali Fatma","2015-05-07 00:00:00","Assurances",'71342343',"dallali.fatma@gmail.com",'17300400','22485632','5');

--Load Agence Assurance Data
INSERT INTO mmsdb.ServiceProvider(id,isDeleted,companyName,responsibleName,creationDate,activity,mobile,mail,fax,tel,typeProvider_id) VALUES ('3', false, "Hotel Africa Tunis", "Saleh ben ahmed","2015-05-07 00:00:00","hebergment",'71342343',"hotelafrica@gmail.com",'71345435','22123124','1');

--Load Conventions Data
INSERT INTO mmsdb.Contract(id,isDeleted,codeContract,responsibleName,startDate,endDate,insuranceAgency_id,montant) VALUES ('1', false, "CVN-0001", "Malki Karim","2015-05-07 00:00:00","2015-12-07 00:00:00",'70','1500');
INSERT INTO mmsdb.Contract(id,isDeleted,codeContract,responsibleName,startDate,endDate,insuranceAgency_id,montant) VALUES ('2', false, "CVN-0002", "Mathlouthi Zied","2015-05-07 00:00:00","2015-12-07 00:00:00",'71','1500');
INSERT INTO mmsdb.Contract(id,isDeleted,codeContract,responsibleName,startDate,endDate,insuranceAgency_id,montant) VALUES ('3', false, "CVN-0003", "Mbarki Raouf","2015-05-07 00:00:00","2015-12-07 00:00:00",'72','1500');

--Load MedicalJourney Data
INSERT INTO mmsdb.MedicalJourney (amount,closeDate,createdAt,deletedAt,identifier,isDeleted,startDate,updateAt,contract_id,patient_id) VALUES (NULL,"2016-01-01 00:01:00",NULL,NULL,"M-CVN-0001-P-0001",false,"2015-01-01 00:01:00",NULL,1,10);
INSERT INTO mmsdb.MedicalJourney (amount,closeDate,createdAt,deletedAt,identifier,isDeleted,startDate,updateAt,contract_id,patient_id) VALUES (NULL,"2016-01-01 00:01:00",NULL,NULL,"M-CVN-0002-P-0002",false,"2015-01-01 00:01:00",NULL,2,11);

--Load Doctor Data
INSERT INTO mmsdb.Doctor (speciality,id,clinic_id) VALUES ("UROLOGIE",12,5);
INSERT INTO mmsdb.Doctor (speciality,id,clinic_id) VALUES ("STOMATOLOGIE",13,6);
INSERT INTO mmsdb.Doctor (speciality,id,clinic_id) VALUES ("CARDIOLOGIE",14,7);
INSERT INTO mmsdb.Doctor (speciality,id,clinic_id) VALUES ("PNEUMOLOGIE",15,8);
INSERT INTO mmsdb.Doctor (speciality,id,clinic_id) VALUES ("ANATOMIE ET CYTOLOGIE PATHOLOGIQUE",16,9);
