package br.com.fiap.motoconnect.interfaces;

import br.com.fiap.motoconnect.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
}
