package org.gestion.bq.demo.web;

import org.gestion.bq.demo.entities.Operation;
import org.gestion.bq.demo.metiers.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private IBanqueMetier banqueMetier;

    @RequestMapping("/home")
    public String home(Model model
                        ,@RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "size",defaultValue = "5")int size){
        Integer clientN=banqueMetier.getNumClient();
        Integer compteN=banqueMetier.getNumCompte();
        Integer OpRetrait=banqueMetier.getOpRetrait();
        Integer OpVercement=banqueMetier.getOpVercement();
        model.addAttribute("nombreClient",clientN);
        model.addAttribute("nombreCompte",compteN);
        model.addAttribute("nombreOpR",OpRetrait);
        model.addAttribute("nombreOpV",OpVercement);

        Page<Operation> pageOperation=banqueMetier.ListOperationJour(page, size);
        model.addAttribute("ListOperation",pageOperation.getContent());
        int[] pages=new int[pageOperation.getTotalPages()];
        model.addAttribute("pages",pages);
        model.addAttribute("Currentpages",page);
        return "home";
    }
}
