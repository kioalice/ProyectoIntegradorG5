package gr5.talentsypymes.db;

import gr5.talentsypymes.model.Solicitud;
import gr5.talentsypymes.repository.SolicitudesRepository;
import gr5.talentsypymes.service.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesServiceJpa implements ISolicitudService {

    @Autowired
    private SolicitudesRepository solicitudesRepository;

    @Override
    public void guardar(Solicitud solicitud) {
        solicitudesRepository.save(solicitud);

    }

    @Override
    public void eliminar(Integer id) {
        solicitudesRepository.deleteById(id);
    }

    @Override
    public List<Solicitud> buscarTodas() {
        return solicitudesRepository.findAll();
    }

    @Override
    public Solicitud buscarPorId(Integer id) {
        Optional<Solicitud> optional = solicitudesRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<Solicitud> buscarTodas(Pageable page) {
        return solicitudesRepository.findAll(page);
    }
}
