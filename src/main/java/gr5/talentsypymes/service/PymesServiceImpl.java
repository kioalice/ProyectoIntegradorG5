package gr5.talentsypymes.service;

import gr5.talentsypymes.model.VacantePymes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class PymesServiceImpl implements IPymesService{

    private List<VacantePymes> lista =null;


    public PymesServiceImpl(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<VacantePymes>();
        try{
            VacantePymes vacante1 = new VacantePymes();
            vacante1.setPymeId(1);
            vacante1.setNombre("Programador web");
            vacante1.setDescripcion("Se requiere de programador web con conocimientos en php");
            vacante1.setFecha(sdf.parse("08-02-2023"));
            vacante1.setAporte(50000);
            vacante1.setDestacado(1);
            vacante1.setImagen("empresa1.png");

            lista.add(vacante1);

        }catch (ParseException e) {
            System.out.println("Error " + e.getMessage());
        }

    }


    @Override
    public List<VacantePymes> buscarTodas() {
        return lista;
    }

    @Override
    public VacantePymes buscarPorId(Integer pymeId) {

        for (VacantePymes v : lista){
            if(v.getPymeId()==pymeId){
                return v;
            }
        }

        return null;
    }

    @Override
    public void guardar(VacantePymes vacantePymes) {
        lista.add(vacantePymes);
    }

    @Override
    public List<VacantePymes> buscarDestacadas() {
        return null;
    }

    @Override
    public void eliminar(Integer pymeId) {

    }

    @Override
    public List<VacantePymes> buscarPorExample(Example<VacantePymes> example) {
        return null;
    }

    @Override
    public Page<VacantePymes> buscarTodas(Pageable page) {
        return null;
    }
}
