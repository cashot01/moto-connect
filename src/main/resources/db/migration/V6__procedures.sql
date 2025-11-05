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

CREATE OR REPLACE FUNCTION fn_somatorio_manual_json()
    RETURNS TEXT
    LANGUAGE plpgsql
AS $$
DECLARE
    v_usuario_atual TEXT := NULL;
    v_total_usuario INT := 0;
    v_total_geral INT := 0;
    v_json_result JSONB := '[]'::JSONB;
    v_current_array JSONB := '[]'::JSONB;
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
            -- Adiciona o usuário anterior ao resultado
            IF v_usuario_atual IS NOT NULL THEN
                v_json_result = v_json_result || jsonb_build_object(
                    'usuario', v_usuario_atual,
                    'subtotal', v_total_usuario,
                    'detalhes', v_current_array
                );
                v_total_geral := v_total_geral + v_total_usuario;
                v_total_usuario := 0;
                v_current_array := '[]'::JSONB;
            END IF;
            v_usuario_atual := reg.usuario;
        END IF;

        -- Adiciona os detalhes do modelo atual
        v_current_array = v_current_array || jsonb_build_object(
            'modelo', reg.modelo,
            'total', reg.total
        );
        v_total_usuario := v_total_usuario + reg.total;
    END LOOP;

    -- Adiciona o último usuário
    IF v_usuario_atual IS NOT NULL THEN
        v_json_result = v_json_result || jsonb_build_object(
            'usuario', v_usuario_atual,
            'subtotal', v_total_usuario,
            'detalhes', v_current_array
        );
        v_total_geral := v_total_geral + v_total_usuario;
    END IF;

    -- Retorna o resultado completo como JSON formatado
    RETURN jsonb_pretty(
        jsonb_build_object(
            'relatorio_motos', v_json_result,
            'total_geral', v_total_geral
        )
    );
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN '{"erro": "Nenhum dado encontrado"}';
    WHEN OTHERS THEN
        RETURN '{"erro": "Erro desconhecido: ' || SQLERRM || '"}';
END;
$$;

-- Testar a função
SELECT fn_somatorio_manual_json();
