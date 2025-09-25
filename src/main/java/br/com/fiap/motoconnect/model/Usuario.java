package br.com.fiap.motoconnect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = {
    @UniqueConstraint(name = "uk_usuario_email", columnNames = {"email"})
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O e-mail não pode estar em branco.")
    @Email(message = "Formato de e-mail inválido.")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres.")
    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @NotBlank(message = "O perfil não pode estar em branco.")
    @Size(max = 50, message = "O perfil deve ter no máximo 50 caracteres.")
    @Column(name = "role", nullable = false, length = 50)
    private String role;
}
