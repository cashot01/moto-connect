-- Alterar para uma função que RETORNA o JSON
CREATE OR REPLACE FUNCTION fn_join_json()
    RETURNS TEXT
    LANGUAGE plpgsql
AS $$
DECLARE
    v_json TEXT;
BEGIN
    SELECT jsonb_pretty(
                   jsonb_build_object(
                           'motos',
                           jsonb_agg(
                                   jsonb_build_object(
                                           'placa', m.placa,
                                           'modelo', m.modelo,
                                           'usuario', u.nome
                                   )
                           )
                   )
           )
    INTO v_json
    FROM tb_moto m
             JOIN tb_usuario u ON m.usuario_id = u.id;

    RETURN v_json;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN '{"erro": "Nenhum dado encontrado"}';
    WHEN OTHERS THEN
        RETURN '{"erro": "Erro desconhecido: ' || SQLERRM || '"}';
END;
$$;

-- Testar a função
SELECT fn_join_json();

-- porcedimento 2
CREATE OR REPLACE PROCEDURE sp_somatorio_manual_adaptado()
LANGUAGE plpgsql
AS $$
DECLARE
    v_usuario_atual TEXT := NULL;
    v_total_usuario INT := 0;
    v_total_geral INT := 0;
    reg RECORD;
    c_motos CURSOR FOR
        SELECT u.nome AS usuario, m.modelo, COUNT(*) AS total
        FROM tb_moto m
        JOIN tb_usuario u ON m.usuario_id = u.id
        GROUP BY u.nome, m.modelo
        ORDER BY u.nome, m.modelo;
BEGIN
    FOR reg IN c_motos LOOP
        -- Verifica se mudou de usuário
        IF v_usuario_atual IS NULL OR v_usuario_atual != reg.usuario THEN
            IF v_usuario_atual IS NOT NULL THEN
                RAISE NOTICE 'Subtotal do usuário %: %', v_usuario_atual, v_total_usuario;
                v_total_geral := v_total_geral + v_total_usuario;
                v_total_usuario := 0;
            END IF;
            v_usuario_atual := reg.usuario;
        END IF;

        -- Exibe os detalhes
        RAISE NOTICE 'Usuário: %, Modelo: %, Total: %', reg.usuario, reg.modelo, reg.total;
        v_total_usuario := v_total_usuario + reg.total;
    END LOOP;

    -- Imprime o último subtotal e total geral
    IF v_usuario_atual IS NOT NULL THEN
        RAISE NOTICE 'Subtotal do usuário %: %', v_usuario_atual, v_total_usuario;
        v_total_geral := v_total_geral + v_total_usuario;
    END IF;

    RAISE NOTICE 'Total geral: %', v_total_geral;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE NOTICE 'Nenhum dado encontrado.';
    WHEN OTHERS THEN
        RAISE NOTICE 'Erro desconhecido: %', SQLERRM;
END;
$$;

-- execução procedimento 2
CALL sp_somatorio_manual_adaptado();
