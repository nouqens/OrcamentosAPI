CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_itens_orcamento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    obra_id UUID,
    recurso_id UUID,
    quantidade DOUBLE PRECISION,
    preco_unitario_momento DOUBLE PRECISION,
    preco_total_item DOUBLE PRECISION,
    CONSTRAINT fk_obra
        FOREIGN KEY (obra_id)
        REFERENCES tb_obras(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_recurso
        FOREIGN KEY (recurso_id)
        REFERENCES tb_recursos(id)
        ON DELETE SET NULL
);