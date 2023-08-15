package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.service.CinemaService;

@Controller
@RequestMapping("/cinema")

public class CinemaController {

    private  CinemaService cinemaService;


    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }


    @GetMapping("/save")
    public String save(Model model){
        Cinema cinema = new Cinema();
        model.addAttribute("cinema", cinema);
        return "cinemaHtml/cinema";

    }
    @PostMapping("/save_cinema")
    public String saveCinema(@ModelAttribute Cinema cinema){
        cinemaService.save(cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("all_cinemas", cinemaService.findAll());
        return "cinemaHtml/all_cinemas";
    }

    @GetMapping("/find_by_id")
    public   String  findById(@RequestParam int id, Model model){
        model.addAttribute("find_by_id", cinemaService.findById(id));
        return "cinemaHtml/find_by_id";
    }

    @GetMapping("/update/{cinema_id}")
    public String update(Model model , @PathVariable("cinema_id") int id){
        Cinema cinema = cinemaService.findById(id);
        model.addAttribute("cinema", cinema);
        return "cinemaHtml/update_cinema";

    }
    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Cinema cinema, @PathVariable int id){
        cinemaService.update(id, cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        cinemaService.deleteById(id);
        return "redirect:/cinema/find_all";

    }
    @GetMapping("/front_page")
    public String getFrontPage(){
        return "cinemaHtml/frontPage";
    }
    @GetMapping("/after")
    public String getAfter4() {
        return "cinemaHtml/after4";
    }
    @GetMapping("/chan")
    public String getChan(){
        return "cinemaHtml/chan";

    }




}
