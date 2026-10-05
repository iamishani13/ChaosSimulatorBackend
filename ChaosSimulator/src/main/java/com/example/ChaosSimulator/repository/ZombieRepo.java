package com.example.ChaosSimulator.repository;

import com.example.ChaosSimulator.dto.Player;
import com.example.ChaosSimulator.dto.Zombie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ZombieRepo extends MongoRepository<Zombie, String> {
}
