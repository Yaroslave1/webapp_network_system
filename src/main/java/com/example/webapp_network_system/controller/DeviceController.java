package com.example.webapp_network_system.controller;

import com.example.webapp_network_system.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/devices")
    public String listDevices(Model model, Authentication authentication) {
        model.addAttribute("devices", deviceService.getAllDevices());

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "admin/devices";
        } else {
            return "devices";
        }
    }
}