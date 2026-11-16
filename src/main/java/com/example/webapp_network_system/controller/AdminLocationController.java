package com.example.webapp_network_system.controller;

import com.example.webapp_network_system.entity.Location;
import com.example.webapp_network_system.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/locations")
@RequiredArgsConstructor
public class AdminLocationController {
    private final LocationRepository locationRepository;

    @GetMapping
    public String Locations(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        return "admin/locations";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("location", new Location());
        return "admin/locations-form";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Location location = locationRepository.findById(id).orElseThrow(null);
        model.addAttribute("location", location);
        return "admin/locations-form";
    }
    @PostMapping("/save")
    public String saveLocation(@ModelAttribute Location location) {
        locationRepository.save(location);
        return "redirect:/admin/locations";
    }
    @PostMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationRepository.deleteById(id);
        return "redirect:/admin/locations";
    }
}
