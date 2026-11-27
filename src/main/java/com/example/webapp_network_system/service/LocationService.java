package com.example.webapp_network_system.service;

import com.example.webapp_network_system.entity.Location;
import com.example.webapp_network_system.repository.DeviceRepository;
import com.example.webapp_network_system.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final DeviceRepository deviceRepository;
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }
    public Location getLocationByID(Long id) {
        return locationRepository.findById(id).orElse(null);
    }
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }
    public void deleteLocation(Long id) {
        if (deviceRepository.countByLocationId(id) > 0) {
            throw new IllegalStateException("Невозможно удалить локацию: к ней привязаны устройства!");
        }
        locationRepository.deleteById(id);
    }
}
