package gr5.talentsypymes.controller;

import gr5.talentsypymes.model.Perfil;
import gr5.talentsypymes.model.Usuario;
import gr5.talentsypymes.model.VacantePymes;
import gr5.talentsypymes.service.ICategoriasService;
import gr5.talentsypymes.service.IPymesService;
import gr5.talentsypymes.service.IUsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ICategoriasService categoriasService;

    @Autowired
    private IPymesService iPymesService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String mostrarIndex(Authentication authentication, HttpSession session) {

        // Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
        String username = authentication.getName();

        for(GrantedAuthority rol: authentication.getAuthorities()) {
            System.out.println("ROL: " + rol.getAuthority());
        }

        if (session.getAttribute("usuario") == null){
            Usuario usuario = usuariosService.buscarPorUsername(username);
            //System.out.println("Usuario: " + usuario);
            session.setAttribute("usuario", usuario);
        }

        return "redirect:/";
    }


    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {

        String pwdPlano = usuario.getPassword();
        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
        usuario.setPassword(pwdEncriptado);
        usuario.setEstatus(1);
        usuario.setFechaRegistro(new Date());
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(3);
        usuario.agregar(perfil);


        usuariosService.guardar(usuario);
        attributes.addFlashAttribute("msg", "Listo, ya puedes ingresar! \uD83D\uDE00");
        return "redirect:/login";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model){
        List<VacantePymes> lista = iPymesService.buscarTodas();
        model.addAttribute("VacantePymes",lista);
        return "tabla";

    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model){
        VacantePymes vacantePymes = new VacantePymes();
        vacantePymes.setNombre("Ingeniero informatico");
        vacantePymes.setDescripcion("Se requiere ingeniero en informatica que ayude con los procesos de pagina web");
        vacantePymes.setFecha(new Date());
        vacantePymes.setAporte(50000);
        model.addAttribute("vacantePymes", vacantePymes);
        return "detalle";
    }

    @GetMapping("/listado")
    public String mostrarListado(Model model){
        List<String> lista = new LinkedList<String>();
        lista.add("Ingeniero en informatica");
        lista.add("Publicista");
        lista.add("Auxiliar de Contabilidad");
        model.addAttribute("talentos", lista);
        return "listado";

    }

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody //Al hacer peticion en este metodo no regresara vistas solo regresara el texto directamente
    public String encriptar(@PathVariable String texto){
        return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
    }

    @GetMapping("/login")
    public String mostrarLogin(){

        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler =
        new SecurityContextLogoutHandler();
        logoutHandler.logout(request,null,null);
        return "redirect:/";

    }

    @GetMapping("/")
    public String mostrarHome(Model model){
        return "home";

    }

    /**
    private List<VacantePymes> getVacantesPymes(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<VacantePymes> lista = new LinkedList<VacantePymes>();
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
        return lista;
    }**/

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") VacantePymes vacantePymes, Model model){

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion",
                ExampleMatcher.GenericPropertyMatchers.contains());
        Example<VacantePymes> example = Example.of(vacantePymes, matcher);
        List<VacantePymes> lista = iPymesService.buscarPorExample(example);
        model.addAttribute("VacantePymes",lista);
        return "home";
    }

    /**
     * InitBinder si los string los encuentra vacios en el data binding los settea a nulo
     * @param binder
     */
    @InitBinder
    public void inicioBinder(WebDataBinder binder){
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

    }


    @ModelAttribute()
    public void setGenericos(Model model) {
        VacantePymes pymeSearch = new VacantePymes();
        pymeSearch.reset();
        model.addAttribute("VacantePymes",iPymesService.buscarDestacadas());
        model.addAttribute("categorias",categoriasService.buscarTodas());
        model.addAttribute("search",pymeSearch);


    }
    @GetMapping("/resenas")
    public String resenias(Model model){
        return "resenas";
    }

    @GetMapping("/acerca")
    public String acerca(Model model) {
        return "acerca"; }


    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto"; }
}




