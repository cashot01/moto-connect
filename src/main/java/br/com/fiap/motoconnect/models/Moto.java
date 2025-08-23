package br.com.fiap.motoconnect.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_moto")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull(message = "modelo da moto obrigatório")
    @NotNull(message = "{motorcycle.model.notnull}")
    private TipoModelo modelo;

    //@NotBlank(message = "placa obrigatória")
    @NotBlank(message = "{motorcycle.plate.notblank}")
    @Pattern(regexp = "^([A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4})$", message="{motorcycle.plate.regex}")
    private String placa;

    //@NotNull(message = "Data obrigatoria")
    @NotNull(message = "{motorcycle.date.notnull}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@PastOrPresent(message = "A data de cadastro não pode ser futura")
    @PastOrPresent(message = "{motorcycle.createdDate.pastOrPresent}")
    private LocalDate dataCadastro;

    //@NotNull(message = "status obrigatorio")
    @NotNull(message = "{motorcycle.status.notnull}")
    private TipoStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tb_rfid", referencedColumnName = "id")
    private Rfid rfid;

    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HistoricoMoto> historicos = new ArrayList<>();


}
