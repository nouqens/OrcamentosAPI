CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_obras (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(255),
    descricao TEXT,
    status VARCHAR(100),
    cliente_id UUID,
    usuario_id UUID NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES tb_clientes(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES tb_usuarios(id)
        ON DELETE CASCADE
);