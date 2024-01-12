package gr5.talentsypymes.repository;


import gr5.talentsypymes.model.VacantePymes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PymeRepository extends JpaRepository<VacantePymes, Integer> {

    //List<VacantePymes> findByEstatus(String estatus);

    List<VacantePymes> findByDestacadoAndEstatusOrderByPymeIdDesc(int destacado, String estatus);

    //List<VacantePymes> findByAporteBetween(int a1, int a2);

    //List<VacantePymes> findByEstatusIn(String[] estatus);

}
