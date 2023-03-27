package org.gestion.bq.demo.web;

import org.gestion.bq.demo.dao.RolesRepository;
import org.gestion.bq.demo.dao.UserRepositoy;
import org.gestion.bq.demo.entities.Client;
import org.gestion.bq.demo.entities.Roles;
import org.gestion.bq.demo.entities.Utilisateurs;
import org.gestion.bq.demo.metiers.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IBanqueMetier banqueMetier;
    @Autowired
    private UserRepositoy userRepositoy;
    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/users")
    public String users(Model model,String keyword,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "5")int size
                        ){

        Page<Utilisateurs> utilisateursPage=banqueMetier.ConsulterByUsers(keyword, page, size);
        model.addAttribute("listUsers",utilisateursPage.getContent());

        int[] pages=new int[utilisateursPage.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("Currentpages",page);

        return "users";
    }

    @GetMapping("/deleteuser")
    public String deleteuser(Long id,String keyword,int page){
        banqueMetier.deleteUser(id);
        return "redirect:/users?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/edituser")
    public String edituser(Model model,Long id ,String keyword,int page){
        Utilisateurs utilisateurs=banqueMetier.editUser(id);
        model.addAttribute("utilisateurs",utilisateurs);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "edituser";

    }
    @GetMapping("/showNewUser")
    public String showNewUser(Model model){
        Utilisateurs utilisateurs=new Utilisateurs();
        model.addAttribute("utilisateurs",utilisateurs);
        model.addAttribute("roles",new Roles());
        model.addAttribute("role",banqueMetier.getAllRoles());
        return "New_User";
    }


    @PostMapping("/saveUser")
    public String saveUser(Model model, @Valid Utilisateurs utilisateurs, BindingResult bindingResult
            , @RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "") String keyword
            , @RequestParam(defaultValue = "username") String username
            ,@RequestParam(defaultValue = "lastname")String lastname
            ,@RequestParam(defaultValue = "email")String email
            ,@RequestParam(defaultValue = "password")String password
            ,@RequestParam(defaultValue = "role")String role){
        if(bindingResult.hasErrors()){return "New_User";}
        banqueMetier.saveRolesTouser(username,lastname,email,password,role);
        return "redirect:/users?page="+page+"&keyword="+keyword;
    }



}
