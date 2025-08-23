package br.com.fiap.motoconnect.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{user.name.notblank}")
    private String nome;

    //@Email(message = "email invalido")
    @Email(message = "{user.email}")
    //@NotBlank(message = "email obrigatorio")
    @NotBlank(message = "{user.email.notblank}")
    private String email;

    //@NotBlank(message = "senha origatoria")
    @NotBlank(message = "{user.password.notblank}")
    private String senha;

}
