package com.example.webapp_network_system.controller;

import com.example.webapp_network_system.entity.Device;
import com.example.webapp_network_system.repository.LocationRepository;
import com.example.webapp_network_system.service.DeviceService;
import com.example.webapp_network_system.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/devices")
@RequiredArgsConstructor
public class AdminDeviceController {
    private final DeviceService deviceService;
    private final LocationService locationService;
    @GetMapping
    public String listDevices(Model model) {
        model.addAttribute("devices", deviceService.getAllDevices());
        return "admin/devices";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("locations", locationService.getLocations());
        return "admin/device-form";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("device", deviceService.getDeviceByID(id));
        model.addAttribute("locations", locationService.getLocations());
        return "admin/device-form";
    }
    @PostMapping("/save")
    public String saveDevice(@ModelAttribute Device device) {
        deviceService.saveDevice(device);
        return "redirect:/admin/devices";
    }
    @PostMapping("/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return "redirect:/admin/devices";
    }

}
