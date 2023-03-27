package org.gestion.bq.demo.web;

import org.gestion.bq.demo.entities.Compte;
import org.gestion.bq.demo.entities.Operation;
import org.gestion.bq.demo.metiers.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
    @Autowired
    private IBanqueMetier banqueMetier;

    @RequestMapping("/operations")
    public String index(){
        return "comptes";
    }

    @RequestMapping("/consulterCompte")
    public String consulter(Model model,String codeCt,
                            @RequestParam(name = "page",defaultValue = "0") int page,
                            @RequestParam(name = "size",defaultValue = "5")int size){
        model.addAttribute("codeCt",codeCt);
        try {
            Compte cp=banqueMetier.ConsulterCompte(codeCt);
            Page<Operation> pageOperation=banqueMetier.ListOperation(codeCt,page,size);
            model.addAttribute("ListOperation",pageOperation.getContent());
            int[] pages=new int[pageOperation.getTotalPages()];
            model.addAttribute("pages",pages);
            model.addAttribute("Currentpages",page);
            model.addAttribute("compte",cp);
        }catch (Exception e){
        model.addAttribute("exception",e);}
        return "comptes";
    }
    @RequestMapping(value = "/saveOperation",method = RequestMethod.POST)
    public String saveOperation(Model model,String typeOperation,String codeCt,double montant,String codeCt2){
       try {
           if(typeOperation.equals("VERS")){
               banqueMetier.vercer(codeCt,montant);
           }else if(typeOperation.equals("RET")){
               banqueMetier.retirer(codeCt,montant);
           }else if(typeOperation.equals("VIR")){
               banqueMetier.virement(codeCt,codeCt2,montant);
           }
       }catch (Exception e){
           model.addAttribute("error",e);
           return "redirect:/consulterCompte?codeCt="+codeCt+"&error"+e.getMessage();

       }

        return "redirect:/consulterCompte?codeCt="+codeCt;
    }
}
