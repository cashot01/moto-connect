package br.com.fiap.motoconnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_historico_moto")
public class HistoricoMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A parte não pode estar em branco.")
    @Size(max = 50, message = "A parte deve ter no máximo 50 caracteres.")
    @Column(name = "parte", nullable = false, length = 50)
    private String parte;

    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @NotNull(message = "A moto não pode ser nula.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_historico_moto"))
    private Moto moto;






}
