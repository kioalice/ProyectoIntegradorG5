package gr5.talentsypymes.controller;

import gr5.talentsypymes.model.Solicitud;
import gr5.talentsypymes.model.Usuario;
import gr5.talentsypymes.model.VacantePymes;
import gr5.talentsypymes.service.IPymesService;
import gr5.talentsypymes.service.ISolicitudService;
import gr5.talentsypymes.service.IUsuariosService;
import gr5.talentsypymes.util.Utileria;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Value("${talents&pymesapp.ruta.cv}")
    private String ruta;

    @Autowired
    private IPymesService pymesService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private ISolicitudService solicitudService;

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, @PageableDefault(sort= {"id"},direction= Sort.Direction.DESC, size=20) Pageable page) {
        Page<Solicitud> lista = solicitudService.buscarTodas(page);
        model.addAttribute("solicitudes", lista);
        return "solicitudes/listSolicitudes";
    }

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Solicitud> lista = solicitudService.buscarTodas();
        model.addAttribute("solicitudes", lista);
        return "solicitudes/listSolicitudes";
    }

    @GetMapping("/crear/{id}")
    public String crear(Solicitud solicitud, @PathVariable Integer id, Model model) {
        // Traemos los detalles de la Vacante seleccionada para despues mostrarla en la vista
        VacantePymes vacantePymes = pymesService.buscarPorId(id);
        System.out.println("idpyme: " + id);
        model.addAttribute("vacantePymes", vacantePymes);
        return "solicitudes/formSolicitud";
    }

    @PostMapping("/guardar")
    public String guardar(Solicitud solicitud, BindingResult result, Model model, HttpSession session,
                          @RequestParam("archivoCV") MultipartFile multiPart, RedirectAttributes attributes, Authentication authentication) {

        // Recuperamos el username que inicio sesión
        String username = authentication.getName();

        if (result.hasErrors()){

            System.out.println("Existieron errores");
            return "solicitudes/formSolicitud";
        }

        if (!multiPart.isEmpty()) {
            //String ruta = "/empleos/files-cv/"; // Linux/MAC
            //String ruta = "c:/empleos/files-cv/"; // Windows
            String nombreArchivo = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreArchivo!=null){ // El archivo (CV) si se subio
                solicitud.setArchivo(nombreArchivo); // Asignamos el nombre de la imagen
            }
        }

        // Buscamos el objeto Usuario en BD
        Usuario usuario = usuariosService.buscarPorUsername(username);
        solicitud.setUsuario(usuario); // Referenciamos la solicitud con el usuario


        // Guadamos el objeto solicitud en la bd
        solicitudService.guardar(solicitud);
        attributes.addFlashAttribute("msg", "Gracias por enviar tus datos!");
        model.addAttribute("solicitud", solicitud);
        System.out.println("Solicitud:" + solicitud);
        return "redirect:/";
    }




    @GetMapping("/borrar/{id}")
    public String eliminar(@PathVariable("id") int idSolicitud, RedirectAttributes attributes) {

        // Eliminamos la solicitud.
        solicitudService.eliminar(idSolicitud);

        attributes.addFlashAttribute("msg", "La solicitud fue eliminada!.");
        //return "redirect:/solicitudes/index";
        return "redirect:/solicitudes/indexPaginate";
    }


}
