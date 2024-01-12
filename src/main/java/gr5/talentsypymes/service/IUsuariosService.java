package gr5.talentsypymes.service;



import gr5.talentsypymes.model.Usuario;

import java.util.List;

public interface IUsuariosService {

    List<Usuario> buscarTodos();

    void guardar(Usuario vacantePymes);


    void eliminar(Integer pymeId);


    Usuario buscarPorUsername(String username);




}
