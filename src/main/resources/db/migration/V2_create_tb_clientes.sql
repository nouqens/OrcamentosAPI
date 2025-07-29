CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_clientes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255),
    telefone VARCHAR(255),
    endereco VARCHAR(255),
    observacao TEXT,
    usuario_id UUID NOT NULL,
    CONSTRAINT fk_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES tb_usuarios(id)
        ON DELETE CASCADE
);