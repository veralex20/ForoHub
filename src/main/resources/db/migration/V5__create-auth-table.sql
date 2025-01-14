CREATE TABLE auth_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID único para la tabla
    username VARCHAR(255) NOT NULL UNIQUE, -- Nombre de usuario único
    password VARCHAR(255) NOT NULL, -- Contraseña cifrada
    user_id BIGINT NOT NULL, -- Relación con la tabla 'users'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de creación
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Fecha de última actualización
    FOREIGN KEY (user_id) REFERENCES users(id) -- Llave foránea hacia la tabla 'users'
);
