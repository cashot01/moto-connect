package br.com.fiap.motoconnect.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "modelo da moto obrigatório")
    private TipoModelo modelo;

    @NotBlank(message = "placa obrigatória")
    @Pattern(regexp = "^([A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4})$")
    private String placa;

    @NotNull(message = "Data obrigatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "A data de cadastro não pode ser futura")
    private LocalDate dataCadastro;

    @NotNull(message = "status obrigatorio")
    private TipoStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_rfid", referencedColumnName = "id")
    private Rfid rfid;


}
