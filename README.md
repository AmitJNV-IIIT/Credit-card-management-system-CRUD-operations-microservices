1. Service Registry Docker: 

docker build . -t service-registry:latest
docker run -it -p 8761:8761 service-registry:latest

2. DB Docker for Gateway Service and User Service (users, user_info and roles tables)

docker pull mysql:5.7
docker network create springboot-mysql-net
docker run --name mysqldb --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=cpsdbms -e MYSQL_USER=sa -e MYSQL_PASSWORD=1234 -d mysql:5.7
docker exec -it <container> bash

mysql -usa -p1234
show databases;
use cpsdbms;

CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    added_by VARCHAR(255),
    resources VARCHAR(255),
    role VARCHAR(255)
);

INSERT INTO roles (added_by, resources, role) VALUES ('GC', 'users, documents, admins', 'SuperAdmin');
INSERT INTO roles (added_by, resources, role) VALUES ('GC', 'users, documents', 'Admin');
INSERT INTO roles (added_by, resources, role) VALUES ('Vivek', 'documents', 'User');

select * from roles;

3. User Service Docker:

docker run --name user-service --network springboot-mysql-net -it -p 8081:8081 user-service:latest

4. Gateway Service Docker: 

docker run --name gateway-service --network springboot-mysql-net -it -p 8084:8084 gateway-service:latest

5. DB Docker for Card Service:

docker pull mysql:5.7
docker network create card-mysql-net
docker run --name mysqldb-card --network card-mysql-net -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=cpsdbms -e MYSQL_USER=sa -e MYSQL_PASSWORD=1234 -d mysql:5.7
docker exec -it <container> bash

mysql -usa -p1234
show databases;
use cpsdbms;

6. Card Service Docker:

docker run --name card-service --network card-mysql-net -it -p 8082:8082 card-service:latest

7. DB Docker for Transaction Service: 

docker pull mysql:5.7
docker network create transaction-mysql-net
docker run --name mysqldb-transaction --network transaction-mysql-net -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=cpsdbms -e MYSQL_USER=sa -e MYSQL_PASSWORD=1234 -d mysql:5.7
docker exec -it <container> bash

mysql -usa -p1234
show databases;
use cpsdbms;

8. Transaction Service Docker

docker run --name transaction-service --network transaction-mysql-net -it -p 8083:8083 transaction-service:latest
