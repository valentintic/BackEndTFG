CREATE DATABASE IF NOT EXISTS almfit;

USE almfit;

CREATE TABLE IF NOT EXISTS alimento (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        nombre VARCHAR(255) NOT NULL,
    proteinas DOUBLE NOT NULL,
    carbohidratos DOUBLE NOT NULL,
    grasas DOUBLE NOT NULL,
    calorias100 DOUBLE NOT NULL
    );

CREATE TABLE IF NOT EXISTS alimento_type (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             nombre VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS alimento_categorias (
                                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                   alimento_id BIGINT NOT NULL,
                                                   alimento_type_id BIGINT NOT NULL,
                                                   CONSTRAINT FK65g30n4jp9hmhf8gjp5qy8py4 FOREIGN KEY (alimento_id) REFERENCES alimento (id),
    CONSTRAINT FKfflyrjxgj5msx1vjfft01tmtm FOREIGN KEY (alimento_type_id) REFERENCES alimento_type (id)
    );

CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name ENUM ('ROLE_ADMIN', 'ROLE_USER') NOT NULL,
    CONSTRAINT UK8sewwnpamngi6b1dwaa88askk UNIQUE (name)
    );

CREATE TABLE IF NOT EXISTS usuario (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       birth_date DATE NULL,
                                       calorias_diarias INT NOT NULL,
                                       created_at DATE NULL,
                                       email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NULL,
    last_name VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    username VARCHAR(255) NOT NULL,
    CONSTRAINT UK5171l57faosmj8myawaucatdw UNIQUE (email),
    CONSTRAINT UK863n1y3x0jalatoir4325ehal UNIQUE (username)
    );

CREATE TABLE IF NOT EXISTS consumo_diario (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              cantidad DOUBLE NOT NULL,
                                              fecha DATE NULL,
                                              alimento_id BIGINT NOT NULL,
                                              usuario_id BIGINT NOT NULL,
                                              CONSTRAINT FKe43qqmb6qlnejt4kvbjjpe7i9 FOREIGN KEY (alimento_id) REFERENCES alimento (id),
    CONSTRAINT FKnqhwagemnhd81reaysasitlvc FOREIGN KEY (usuario_id) REFERENCES usuario (id)
    );

CREATE TABLE IF NOT EXISTS usuario_role (
                                            usuario_id BIGINT NOT NULL,
                                            role_id BIGINT NOT NULL,
                                            PRIMARY KEY (usuario_id, role_id),
    CONSTRAINT FKe7gfguqsiox6p89xggm8g2twf FOREIGN KEY (role_id) REFERENCES role (id),
    CONSTRAINT FKpc2qjts6sqq4hja9f6i3hf0ep FOREIGN KEY (usuario_id) REFERENCES usuario (id)
    );
