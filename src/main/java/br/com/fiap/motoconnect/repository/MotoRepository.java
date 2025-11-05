package br.com.fiap.motoconnect.repository;

import br.com.fiap.motoconnect.model.Moto;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {

    @EntityGraph(attributePaths = {"usuario", "rfid"})
    Optional<Moto> findById(Long id);

    // Usando consulta nativa para chamar a função
    @Query(value = "SELECT fn_join_json()", nativeQuery = true)
    String executarProcedimentoJson();

    @Query(value = "SELECT fn_somatorio_manual_json()", nativeQuery = true)
    String executarFuncaoSomatorioJson();
}