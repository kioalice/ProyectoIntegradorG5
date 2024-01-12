package gr5.talentsypymes.service;


import gr5.talentsypymes.model.VacantePymes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IPymesService {

    List<VacantePymes> buscarTodas();

    VacantePymes buscarPorId(Integer pymeId);


    void guardar(VacantePymes vacantePymes);


    List<VacantePymes> buscarDestacadas();

    void eliminar(Integer pymeId);

    List<VacantePymes> buscarPorExample(Example<VacantePymes> example);

    Page<VacantePymes> buscarTodas(Pageable page);
}
