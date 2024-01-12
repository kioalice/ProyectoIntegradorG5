package gr5.talentsypymes.service;

import gr5.talentsypymes.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriasService {

    void guardar(Categoria categoria);

    List<Categoria> buscarTodas();

    Page<Categoria> buscarTodas(Pageable page);

    Categoria buscarPorId(Integer idCategoria);

    void eliminar(Integer pymeId);
}
