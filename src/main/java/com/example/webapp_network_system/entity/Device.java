package com.example.webapp_network_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")

public class Device {
    private Long id;
    private String deviceType;
    private String model;
    private String serialNumber;
    private String ipAddress;
    private String macAddress;
    private String status;
    private Long locationId;
    private Location location;
}
