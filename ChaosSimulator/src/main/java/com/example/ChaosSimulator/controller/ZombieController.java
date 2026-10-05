package com.example.ChaosSimulator.controller;

import com.example.ChaosSimulator.dto.Player;
import com.example.ChaosSimulator.dto.Zombie;
import com.example.ChaosSimulator.service.PlayerService;
import com.example.ChaosSimulator.service.ZombieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zombies")
public class ZombieController {

    private final ZombieService zombieService;

    public ZombieController(ZombieService zombieService) {
        this.zombieService = zombieService;
    }

    @PostMapping
    public Zombie createZombie(@RequestBody Zombie zombie) {
        return zombieService.createZombie(zombie);
    }

    @GetMapping
    public List<Zombie> getAllZombies() {
        return zombieService.getAllZombies();
    }

    @GetMapping("/{id}")
    public Zombie getZombieById(@PathVariable String id) {
        return zombieService.getZombieById(id);
    }

    @PutMapping("/{id}")
    public Zombie updateZombie(@PathVariable String id, @RequestBody Zombie zombie) {
        return zombieService.updateZombie(id, zombie);
    }

    @DeleteMapping("/{id}")
    public Zombie deleteZombie(@PathVariable String id) {
        return zombieService.deleteZombie(id);
    }
}
