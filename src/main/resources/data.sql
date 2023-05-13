CREATE TABLE USER (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      user VARCHAR(255),
                      email VARCHAR(255) UNIQUE,
                      password VARCHAR(255)
);
INSERT INTO USER (user, email, password) VALUES ('test','test@gmail.com',  '$2a$10$Wwsw6PhdpK1M.sftpI/D1.Eo9u1k04oLM.v79El.k3MxTvFNQizwi');