package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Session;
import peaksoft.service.SessionService;
@Controller
@RequestMapping("/sess")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/save")
    public String save(Model model){
        Session session = new Session();
        model.addAttribute("sess", session);
        return "sessionHtml/saveSess";

    }
    @PostMapping("/save_session")
    public String saveMovie(@ModelAttribute Session session){
        sessionService.save(session);
        return "redirect:/sess/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("all_sessions", sessionService.findAll());
        return "sessionHtml/all_sessions";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam("  ") int id, Model model){
        model.addAttribute("   ", sessionService.findById(id));
        return "   ";
    }

    @GetMapping("/update/{sess_id}")
    public String update(Model model , @PathVariable("sess_id") int id){

      Session session = sessionService.findById(id);
        model.addAttribute("sess", session);
        return "sessionHtml/update_session";

    }
    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Session session, @PathVariable int id){
        sessionService.update(id, session);
        return "redirect:/sess/find_all";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        sessionService.deleteById(id);
        return "redirect:/sess/find_all";
    }

}
