package br.com.fiap.motoconnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rfid")
public class Rfid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da área não pode estar em branco.")
    @Size(max = 100, message = "O nome da área deve ter no máximo 100 caracteres.")
    @Column(name = "nome_area", nullable = false, length = 100)
    private String nomeArea;

    @NotNull(message = "A latitude não pode ser nula.")
    @Column(name = "latitude", nullable = false, precision = 9, scale = 6)
    private Double latitude;

    @NotNull(message = "A longitude não pode ser nula.")
    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    private Double longitude;
}
