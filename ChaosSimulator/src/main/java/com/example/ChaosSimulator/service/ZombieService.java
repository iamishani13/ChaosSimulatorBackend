package com.example.ChaosSimulator.service;


import com.example.ChaosSimulator.dto.Player;
import com.example.ChaosSimulator.dto.Zombie;
import com.example.ChaosSimulator.repository.PlayerRepo;
import com.example.ChaosSimulator.repository.ZombieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZombieService {

    private final ZombieRepo zombieRepository;

    public ZombieService(ZombieRepo zombieRepository) {
        this.zombieRepository = zombieRepository;
    }

    public Zombie createZombie(Zombie zombie) {
        zombie.setAlive(true);
        return zombieRepository.save(zombie);
    }

    public List<Zombie> getAllZombies() {
        return zombieRepository.findAll();
    }

    public Zombie getZombieById(String zombieId) {
        return zombieRepository.findById(zombieId).orElse(null);    
    }

    public Zombie updateZombie(String id, Zombie zombie) {
        Zombie existingZombie = zombieRepository.findById(id).orElse(null);
        if (existingZombie == null) {
            return null;
        }
        existingZombie.setHealth(zombie.getHealth());
        existingZombie.setDamage(zombie.getDamage());
        existingZombie.setSpeed(zombie.getSpeed());
        existingZombie.setType(zombie.getType());
        existingZombie.setLevel(zombie.getLevel());
        existingZombie.setAlive(zombie.isAlive());
        return zombieRepository.save(existingZombie);
    }

    public Zombie deleteZombie(String id) {
        Zombie existingZombie = zombieRepository.findById(id).orElse(null);
        if (existingZombie == null) {
            return null;
        }
        zombieRepository.delete(existingZombie);
        return existingZombie;
    }   
}