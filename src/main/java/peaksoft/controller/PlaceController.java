package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Movie;
import peaksoft.model.Place;
import peaksoft.service.MovieService;
import peaksoft.service.PlaceService;
@Controller
@RequestMapping("/place")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/save")
    public String save(Model model){
       Place place = new Place();
       model.addAttribute("place", place);
        return "placeHtml/place";

    }
    @PostMapping("/save_place")
    public String saveMovie(@ModelAttribute Place place){
        placeService.save(place);
        return "redirect:/place/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("all_places", placeService.findAll());
        return "placeHtml/all_places";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam int id, Model model){
        model.addAttribute("find_by_id", placeService.findById(id));
        return "placeHtml/find_by_id";
    }

    @GetMapping("/update/{place_id}")
    public String update(Model model , @PathVariable("place_id") int id){

       Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "placeHtml/update_place";

    }
    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Place place, @PathVariable int id){
        placeService.update(id, place);
        return "redirect:/place/find_all";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        placeService.deleteById(id);
        return "redirect:/place/find_all";

    }


}
