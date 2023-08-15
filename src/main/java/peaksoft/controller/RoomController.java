package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Place;
import peaksoft.model.Room;
import peaksoft.service.RoomService;
@Controller
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/save")
    public String save(Model model){
        Room room = new Room();
        model.addAttribute("room", room);
        return "roomHtml/room";

    }
    @PostMapping("/save_room")
    public String saveRoom(@ModelAttribute Room room){
        roomService.save(room);
        return "redirect:/room/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("all_rooms", roomService.findAll());
        return "roomHtml/all_rooms";
    }


    @GetMapping("/find_by_id")
    public String findById(@RequestParam int id, Model model){
        model.addAttribute("find_by_id", roomService.findById(id));
        return "roomHtml/find_by_id";
    }

    @GetMapping("/update/{room_id}")
    public String update(Model model , @PathVariable("room_id") int id){

        Room room = roomService.findById(id);
        model.addAttribute("room",room);
        return "roomHtml/update_room";

    }
    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Room room, @PathVariable int id){
        roomService.update(id,room);
        return "redirect:/room/find_all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        roomService.deleteById(id);
        return "redirect:/room/find_all";
    }
    @GetMapping("/vip_room")
    public String getFrontPage() {
        return "roomHtml/rooms";

    }

}
