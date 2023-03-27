package org.gestion.bq.demo.web;

import org.gestion.bq.demo.entities.Client;
import org.gestion.bq.demo.entities.Compte;
import org.gestion.bq.demo.entities.Operation;
import org.gestion.bq.demo.entities.Roles;
import org.gestion.bq.demo.metiers.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class CompteController {
    @Autowired
    private IBanqueMetier banqueMetier;

    @RequestMapping(value = "/ListCompte",method = RequestMethod.GET)
    public String ListCompte(Model model,String keyword,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5")int size
                          ){

        Page<Compte> pageCompte=banqueMetier.ListComptes(keyword,page,size);
        model.addAttribute("ListCompte",pageCompte.getContent());
        int[] pages=new int[pageCompte.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("Currentpages",page);
        model.addAttribute("keyword",keyword);
        return "ListCompte";
    }

    @GetMapping("/showNewCompte")
    public String showNewCompte(Model model){
        Compte compte=new Compte();
        model.addAttribute("compte",compte);
        model.addAttribute("client",new Client());
        model.addAttribute("clients",banqueMetier.getAllClient());

        return "New_Compte";
    }
    @PostMapping("/saveCompte")
    public String saveClient(Model model, @Valid Compte compte, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "codeCt")String codeCt,
            @RequestParam(defaultValue = "DTcreation")Date DTcreation,
            @RequestParam(defaultValue = "solde")double solde,
            @RequestParam(defaultValue = "client")String client



    ){
        if(bindingResult.hasErrors()){return "New_Compte";}
        banqueMetier.saveClientTocompte(codeCt,DTcreation,solde,client);
        return "redirect:/ListCompte?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/editCompte")
    public String edit(Model model,String codeCt ,String keyword,int page){
        Compte compte=banqueMetier.editCompte(codeCt);
        model.addAttribute("compte",compte);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editCompte";

    }
}
