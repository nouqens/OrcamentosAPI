CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_recursos (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao VARCHAR(255),
    unidade VARCHAR(100),
    preco_unitario DOUBLE PRECISION,
    tipo VARCHAR(100),
    usuario_id UUID NOT NULL,
    CONSTRAINT fk_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES tb_usuarios(id)
        ON DELETE CASCADE
);