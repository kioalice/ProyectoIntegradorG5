package gr5.talentsypymes.service;

import gr5.talentsypymes.model.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISolicitudService {

    void guardar(Solicitud solicitud);

    void eliminar(Integer idSolicitud);

    List<Solicitud> buscarTodas();

    Solicitud buscarPorId(Integer id);

    Page<Solicitud> buscarTodas(Pageable page);

}
