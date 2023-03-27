package org.gestion.bq.demo.web;

import org.gestion.bq.demo.entities.Client;
import org.gestion.bq.demo.entities.Operation;
import org.gestion.bq.demo.metiers.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ClientController {
    @Autowired
    private IBanqueMetier banqueMetier;

   @RequestMapping(value = "/clients",method = RequestMethod.GET)
    public String clients(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5")int size,
                          @RequestParam(name = "keyword",defaultValue = "")String keyword){

       Page<Client> pageClient=banqueMetier.ConsulterClient(keyword,page,size);
        model.addAttribute("listeClients",pageClient.getContent());
       int[] pages=new int[pageClient.getTotalPages()];
       model.addAttribute("pages",pages);
       model.addAttribute("Currentpages",page);
       model.addAttribute("keyword",keyword);
        return "clients";
    }
    @GetMapping("/showNewClient")
    public String showNewClient(Model model){
        Client client=new Client();
        model.addAttribute("client",client);

        return "New_Client";
    }
    @PostMapping("/saveClient")
    public String saveClient(Model model, @Valid Client client, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page
                                                , @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()){return "New_Client";}
        banqueMetier.saveClients(client);
        return "redirect:/clients?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/delete")
    public String delete(Long codeCL,String keyword,int page){
        banqueMetier.deleteClient(codeCL);
        return "redirect:/clients?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editClient")
    public String edit(Model model,Long codeCL ,String keyword,int page){
       Client client=banqueMetier.editClient(codeCL);
       model.addAttribute("client",client);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
       return "editClient";

    }
}
