package br.com.fiap.motoconnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "moto", uniqueConstraints = {
    @UniqueConstraint(name = "uk_moto_placa", columnNames = {"placa"})
})
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O modelo não pode estar em branco.")
    @Size(max = 10, message = "O modelo deve ter no máximo 10 caracteres.")
    @Column(name = "modelo", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ModeloMoto modelo;

    @NotBlank(message = "A placa não pode estar em branco.")
    @Size(max = 7, message = "A placa deve ter 7 caracteres.")
    @Pattern(regexp = "^([A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4})$", message = "Formato de placa inválido.")
    @Column(name = "placa", nullable = false, length = 7)
    private String placa;

    @NotNull(message = "A data de cadastro não pode ser nula.")
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @NotBlank(message = "O status não pode estar em branco.")
    @Size(max = 15, message = "O status deve ter no máximo 15 caracteres.")
    @Column(name = "status", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private StatusMoto status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rfid_id", foreignKey = @ForeignKey(name = "fk_moto_rfid"))
    private Rfid rfid;

    @NotNull(message = "O usuário não pode ser nulo.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_moto_usuario"))
    private Usuario usuario;
}

