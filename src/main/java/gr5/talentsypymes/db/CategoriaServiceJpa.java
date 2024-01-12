package gr5.talentsypymes.db;

import gr5.talentsypymes.model.Categoria;
import gr5.talentsypymes.repository.CategoriaRepository;
import gr5.talentsypymes.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Primary
public class CategoriaServiceJpa implements ICategoriasService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void guardar(Categoria categoria) {

        categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Page<Categoria> buscarTodas(Pageable page) {
        return null;
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> optional = categoriaRepository.findById(idCategoria);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Integer pymeId) {
        categoriaRepository.deleteById(pymeId);
    }
}
