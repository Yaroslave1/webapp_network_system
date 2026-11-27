package com.example.webapp_network_system.service;

import com.example.webapp_network_system.entity.Device;
import com.example.webapp_network_system.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
    public Device getDeviceByID(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }
    public void saveDevice(Device device) {
        if (device.getId() == null && deviceRepository.existsBySerialNumber(device.getSerialNumber())) {
            throw new IllegalArgumentException("Устройства с таким серийным номером не существует");
        }
        deviceRepository.save(device);
    }
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}
