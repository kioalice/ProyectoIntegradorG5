package gr5.talentsypymes.db;

import gr5.talentsypymes.model.Usuario;
import gr5.talentsypymes.repository.UsuarioRepository;
import gr5.talentsypymes.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UsuariosServiceJpa implements IUsuariosService {

    @Autowired
    private UsuarioRepository usuariosRepository;

    @Override
    public void guardar(Usuario usuario) {
        usuariosRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        usuariosRepository.deleteById(idUsuario);
    }


    @Override
    public List<Usuario> buscarTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuariosRepository.findByUsername(username);
    }
}
