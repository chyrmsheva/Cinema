package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Movie;
import peaksoft.service.CinemaService;
import peaksoft.service.MovieService;

@Controller
@RequestMapping("/movie")

public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/save")
    public String save(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movieHtml/movie";

    }
    @PostMapping("/save_movie")
    public String saveMovie(@ModelAttribute Movie movie){
        movieService.save(movie);
        return "redirect:/movie/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("all_movies", movieService.findAll());
        return "movieHtml/all_movies";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam int id, Model model){
        model.addAttribute("find_by_id", movieService.findById(id));
        return "movieHtml/find_by_id";
    }

    @GetMapping("/update/{movie_id}")
    public String update(Model model , @PathVariable("movie_id") int id){
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "movieHtml/update_movie";

    }
    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Movie movie, @PathVariable int id){
        movieService.update(id, movie);
        return "redirect:/movie/find_all";
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        movieService.deleteById(id);
        return "redirect:/movie/find_all";
    }


}
