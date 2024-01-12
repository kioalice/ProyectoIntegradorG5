package gr5.talentsypymes.controller;



import gr5.talentsypymes.model.Usuario;
import gr5.talentsypymes.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService usuariosService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Usuario> lista = usuariosService.buscarTodos();
        model.addAttribute("usuarios", lista);
        return "usuarios/listUsuarios";
    }

    @GetMapping("/borrar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes attributes) {
        usuariosService.eliminar(id);
        attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
        return "redirect:/usuarios/index";
    }
}
