package br.com.fiap.motoconnect.services;

import br.com.fiap.motoconnect.repositories.UsuarioRepository;
import br.com.fiap.motoconnect.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para listar todos os usuários
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para buscar um usuário pelo ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Método para salvar um novo usuário
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para atualizar um usuário existente
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioDetails.getNome());
                    usuario.setEmail(usuarioDetails.getEmail());
                    // Atualize outros campos conforme necessário
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
    }

    // Método para deletar um usuário
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


}
