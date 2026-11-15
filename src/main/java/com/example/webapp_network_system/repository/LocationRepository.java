package com.example.webapp_network_system.repository;

import com.example.webapp_network_system.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
