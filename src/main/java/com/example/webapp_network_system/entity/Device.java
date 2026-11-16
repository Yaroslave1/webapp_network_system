package com.example.webapp_network_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")

public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceType;
    private String model;
    private String serialNumber;
    private String ipAddress;
    private String macAddress;
    private String status;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
