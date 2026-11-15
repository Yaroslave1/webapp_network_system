package com.example.webapp_network_system.repository;

import com.example.webapp_network_system.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsBySerialNumber(String serialNumber);
}
