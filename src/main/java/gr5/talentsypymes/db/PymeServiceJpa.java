package gr5.talentsypymes.db;

import gr5.talentsypymes.model.VacantePymes;
import gr5.talentsypymes.repository.PymeRepository;
import gr5.talentsypymes.service.IPymesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PymeServiceJpa implements IPymesService {

    @Autowired
    private PymeRepository pymeRepository;

    @Override
    public List<VacantePymes> buscarTodas() {
        return pymeRepository.findAll();
    }

    @Override
    public VacantePymes buscarPorId(Integer pymeId) {
        Optional<VacantePymes> optional = pymeRepository.findById(pymeId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardar(VacantePymes vacantePymes) {
        pymeRepository.save(vacantePymes);
    }

    @Override
    public List<VacantePymes> buscarDestacadas() {

        return pymeRepository.findByDestacadoAndEstatusOrderByPymeIdDesc(1, "aprobada");
    }

    @Override
    public void eliminar(Integer pymeId) {
        pymeRepository.deleteById(pymeId);
    }

    @Override
    public List<VacantePymes> buscarPorExample(Example<VacantePymes> example) {
        return pymeRepository.findAll(example);
    }

    @Override
    public Page<VacantePymes> buscarTodas(Pageable page) {
        return pymeRepository.findAll(page);
    }
}
