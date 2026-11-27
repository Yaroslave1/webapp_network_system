package com.example.webapp_network_system.controller;

import com.example.webapp_network_system.entity.Location;
import com.example.webapp_network_system.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/locations")
@RequiredArgsConstructor
public class AdminLocationController {
    private final LocationService locationService;

    @GetMapping
    public String Locations(Model model) {
        model.addAttribute("locations", locationService.getLocations());
        return "admin/locations";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("location", new Location());
        return "admin/locations-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Location location = locationService.getLocationByID(id);
        model.addAttribute("location", location);
        return "admin/locations-form";
    }

    @PostMapping("/save")
    public String saveLocation(@ModelAttribute Location location) {
        locationService.saveLocation(location);
        return "redirect:/admin/locations";
    }

    @PostMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            locationService.deleteLocation(id);
            redirectAttributes.addFlashAttribute("success", "Локация успешно удалена");
        }
        catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/locations";
    }
}
