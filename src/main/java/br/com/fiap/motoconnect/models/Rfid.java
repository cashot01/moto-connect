package br.com.fiap.motoconnect.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_rfid")
public class Rfid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "nome da área requerida")
    @NotBlank(message = "{rfid.areaname.notblank}")
    private String nomeArea;

    //@NotBlank(message = "latitude requerida")
    @NotBlank(message= "{rfid.latitude.notblank}")
    private String latitude;

    //@NotBlank(message = "longitude requerida")
    @NotBlank(message= "{rfid.longitude.notblank}")
    private String longitude;

    @OneToOne(mappedBy = "rfid")
    @JsonBackReference
    private Moto moto;


}
