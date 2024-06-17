CREATE DATABASE IF NOT EXISTS almfit;

USE almfit;

-- Insert roles
INSERT INTO role (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- Insert users with BCrypt encoded passwords
INSERT INTO usuario (username, email, first_name, last_name, password, birth_date, calorias_diarias, created_at)
VALUES
    ('user', 'user@example.com', 'Usuario', 'Regular', '$2a$10$w3hQ1TcE.DxM7G.zB7tT7OeBdx96n.gK4PEmg2OqL7/jw5Y9jGzS2', '1990-01-01', 2000, CURDATE()),
    ('admin', 'admin@example.com', 'Administrador', 'Admin', '$2a$10$gwmrQq7gTb87GpAswiefxeDSs2Su4v0qAccIPzT9yDzgB6vUTOETC', '2001-02-08', 2000, CURDATE()),
    ('user2', 'user2@gmail.com', 'Usuario2', 'Regular2', '$2a$10$FptVW7Dww0H0ioOrOlVa9OXx5JeZZcFPmTuO37ddVeHVDjrVQzHAy', '1995-05-05', 2000, CURDATE()),
    ('admin2', 'admin2@gmail.com', 'Administrador2', 'Admin2', '$2a$10$67eZodFwRJ.kD/ad3bDVlOVX88fJDipbW6sZlqtiMZ3TKf0o3B/Y2', '2000-10-10', 2000, CURDATE());

-- Assign roles to users
INSERT INTO usuario_role (usuario_id, role_id)
VALUES
    ((SELECT id FROM usuario WHERE username = 'user'), (SELECT id FROM role WHERE name = 'ROLE_USER')),
    ((SELECT id FROM usuario WHERE username = 'admin'), (SELECT id FROM role WHERE name = 'ROLE_ADMIN')),
    ((SELECT id FROM usuario WHERE username = 'admin'), (SELECT id FROM role WHERE name = 'ROLE_USER')),
    ((SELECT id FROM usuario WHERE username = 'user2'), (SELECT id FROM role WHERE name = 'ROLE_USER')),
    ((SELECT id FROM usuario WHERE username = 'admin2'), (SELECT id FROM role WHERE name = 'ROLE_ADMIN'));

-- Insert alimento types
INSERT INTO alimento_type (nombre) VALUES
                                       ('proteinas'), ('carbohidratos'), ('grasas'), ('carnes'), ('vegetales'),
                                       ('frutas'), ('lacteos'), ('cereales'), ('pescados'), ('frutos secos');

-- Insert alimentos
INSERT INTO alimento (nombre, proteinas, carbohidratos, grasas, calorias100)
VALUES
    ('Arroz', 2.5, 25.0, 0.5, 100.0),
    ('Pollo', 20.0, 0.0, 5.0, 100.0),
    ('Leche', 3.0, 5.0, 3.0, 100.0),
    ('Manzana', 0.5, 15.0, 0.5, 100.0),
    ('Pescado', 20.0, 0.0, 5.0, 100.0),
    ('Nuez', 5.0, 10.0, 20.0, 100.0),
    ('Zanahoria', 1.0, 10.0, 0.5, 100.0),
    ('Queso', 10.0, 5.0, 10.0, 100.0);

-- Link alimentos to types
INSERT INTO alimento_categorias (alimento_id, alimento_type_id)
VALUES
    ((SELECT id FROM alimento WHERE nombre = 'Arroz'), (SELECT id FROM alimento_type WHERE nombre = 'cereales')),
    ((SELECT id FROM alimento WHERE nombre = 'Arroz'), (SELECT id FROM alimento_type WHERE nombre = 'carbohidratos')),
    ((SELECT id FROM alimento WHERE nombre = 'Pollo'), (SELECT id FROM alimento_type WHERE nombre = 'carnes')),
    ((SELECT id FROM alimento WHERE nombre = 'Pollo'), (SELECT id FROM alimento_type WHERE nombre = 'proteinas')),
    ((SELECT id FROM alimento WHERE nombre = 'Leche'), (SELECT id FROM alimento_type WHERE nombre = 'lacteos')),
    ((SELECT id FROM alimento WHERE nombre = 'Manzana'), (SELECT id FROM alimento_type WHERE nombre = 'frutas')),
    ((SELECT id FROM alimento WHERE nombre = 'Pescado'), (SELECT id FROM alimento_type WHERE nombre = 'pescados')),
    ((SELECT id FROM alimento WHERE nombre = 'Pescado'), (SELECT id FROM alimento_type WHERE nombre = 'proteinas')),
    ((SELECT id FROM alimento WHERE nombre = 'Nuez'), (SELECT id FROM alimento_type WHERE nombre = 'frutos secos')),
    ((SELECT id FROM alimento WHERE nombre = 'Zanahoria'), (SELECT id FROM alimento_type WHERE nombre = 'vegetales')),
    ((SELECT id FROM alimento WHERE nombre = 'Queso'), (SELECT id FROM alimento_type WHERE nombre = 'lacteos'));