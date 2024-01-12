package gr5.talentsypymes.controller;


import gr5.talentsypymes.model.VacantePymes;
import gr5.talentsypymes.service.ICategoriasService;
import gr5.talentsypymes.service.IPymesService;
import gr5.talentsypymes.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pymes")
public class VacanteController {

    @Value("${talents&pymesapp.ruta.imagenes}")
    private String ruta;

    @Autowired
    private IPymesService iPymesService;

    @Autowired
    private ICategoriasService categoriasService;

    @GetMapping("/index")
    public String mostrarIndex(Model model){
        List<VacantePymes> lista = iPymesService.buscarTodas();
        model.addAttribute("VacantePymes", lista);
        return "pymes/listPymes";
    }

    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<VacantePymes>lista = iPymesService.buscarTodas(page);
        model.addAttribute("VacantePymes", lista);
        return "pymes/listPymes";
    }


    @GetMapping("/crear")
    public String crear(VacantePymes vacantePymes, Model model){
        model.addAttribute("vacantePymes", new VacantePymes());
        return "pymes/formPymes";
    }


    @GetMapping("/borrar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes attributes){
        System.out.println("Borrando oferta de pyme con id: " + id);
        iPymesService.eliminar(id);
        attributes.addFlashAttribute("msg", "La vacante fue eliminada! \uD83D\uDE00");
        return "redirect:/pymes/index";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        VacantePymes vacantePymes = iPymesService.buscarPorId(id);
        model.addAttribute("vacantePymes", vacantePymes);
        return "pymes/formPymes";
    }


    @GetMapping("/vista/{id}")
    public String verDetalle(@PathVariable int id, Model model) {
        VacantePymes vacantePymes = iPymesService.buscarPorId(id);
        System.out.println("vacantePymes: "+ vacantePymes);
        model.addAttribute("vacantePymes", vacantePymes);
        return "detalle";

    }

    @ModelAttribute
    public void setGenericos(Model model) {
        model.addAttribute("categorias", categoriasService.buscarTodas());
    }


    @PostMapping("/guardar")
    public String guardar(VacantePymes vacantePymes, BindingResult result, RedirectAttributes attributes,
                          @RequestParam("archivoImagen") MultipartFile multiPart){
        if(result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Ocurrió un error: " + error.getDefaultMessage());
            }
            return "pymes/formPymes";

        }

        if (!multiPart.isEmpty()) {
            //String ruta = "/pymes/img-pymes/"; // Linux/MAC
            //String ruta = "c:/pymes/img-pymes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreImagen != null){ // La imagen si se subio
            // Procesamos la variable nombreImagen
                vacantePymes.setImagen(nombreImagen);
            }
        }



        iPymesService.guardar(vacantePymes);
        attributes.addFlashAttribute("msg","Registro Guardado con éxito \uD83D\uDE00");
        System.out.println("Pyme: " + vacantePymes);

        return "redirect:/pymes/index";
    }

    @InitBinder
    public void  initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
    }



    /*
     ---post de pruebas---
    @PostMapping("/guardar")
    public String guardar(@RequestParam String nombre){

        System.out.println("Nombre de empresa: " + nombre);
        return "pymes/listPymes";
    }
     */





}
