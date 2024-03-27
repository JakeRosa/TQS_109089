package bus.booking.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bus.booking.entities.StaticResult;

@Controller
public class PageController {

    @GetMapping("/")
    public String index(Model model) {
        List<String> cities = Arrays.asList("Lisboa", "Porto", "Madrid", "Barcelona", "Paris", "Berlim");

        model.addAttribute("cities", cities);

        return "index";
    }

    @GetMapping("/results")
    public String results(@RequestParam String origin, @RequestParam String destination, @RequestParam String date,
            Model model) {
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("date", date);

        List<StaticResult> resultsList = Arrays.asList(new StaticResult("1", "08:00", "12:00", "2 hours", "20€"),
                new StaticResult("2", "09:00", "13:00", "2 hours", "25€"),
                new StaticResult("3", "10:00", "14:00", "2 hours", "30€"));

        model.addAttribute("results", resultsList);

        return "results";
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam("date") String date,
            @RequestParam("tripId") Long tripId,
            @RequestParam("departureTime") String departureTime,
            @RequestParam("arrivalTime") String arrivalTime,
            @RequestParam("duration") String duration,
            @RequestParam("price") String price,
            Model model) {

        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("date", date);
        model.addAttribute("tripId", tripId);
        model.addAttribute("departureTime", departureTime);
        model.addAttribute("arrivalTime", arrivalTime);
        model.addAttribute("duration", duration);
        model.addAttribute("price", price);

        return "checkout";
    }
}
