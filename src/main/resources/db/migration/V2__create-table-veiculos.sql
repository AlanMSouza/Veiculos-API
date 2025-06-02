CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    placa VARCHAR(20) NOT NULL UNIQUE,
    ano INT NOT NULL,
    cor VARCHAR(50),
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
    ON DELETE CASCADE
);