package gr5.talentsypymes.service;


import gr5.talentsypymes.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriasServiceImpl implements ICategoriasService{

    private List<Categoria> lista = null;


    public CategoriasServiceImpl() {
        lista = new LinkedList<Categoria>();


    }

    @Override
    public void guardar(Categoria categoria) {

        lista.add(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {

        return lista;
    }

    @Override
    public Page<Categoria> buscarTodas(Pageable page) {
        return null;
    }


    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        for (Categoria cat : lista) {
            if (cat.getIdCategoria()==idCategoria) {
                return cat;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer pymeId) {

    }
}
