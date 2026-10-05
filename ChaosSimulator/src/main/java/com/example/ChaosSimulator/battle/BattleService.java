package com.example.ChaosSimulator.battle;

import com.example.ChaosSimulator.dto.Player;
import com.example.ChaosSimulator.dto.Zombie;
import com.example.ChaosSimulator.exception.DeadEntityException;
import com.example.ChaosSimulator.exception.InsufficientEnergyException;
import com.example.ChaosSimulator.exception.ResourceNotFoundException;
import com.example.ChaosSimulator.repository.PlayerRepo;
import com.example.ChaosSimulator.repository.ZombieRepo;
import org.springframework.stereotype.Service;

@Service
public class BattleService {

    private final PlayerRepo playerRepository;
    private final ZombieRepo zombieRepository;

    public BattleService(
            PlayerRepo playerRepository,
            ZombieRepo zombieRepository) {

        this.playerRepository = playerRepository;
        this.zombieRepository = zombieRepository;
    }

    public BattleResult zombieAttack(BattleRequest request) {

        // 1. Find zombie
        Zombie zombie = zombieRepository.findById(request.getZombieId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Zombie not found"));

        // 2. Find player
        Player player = playerRepository.findById(request.getPlayerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player not found"));

        // 3. Check zombie is alive
        if (!zombie.isAlive()) {
            throw new DeadEntityException("Dead zombie cannot attack");
        }

        // 4. Check player is alive
        if (player.getHealth() <= 0) {
            throw new DeadEntityException("Dead player cannot be attacked");
        }

        // 5. Store old health
        int healthBefore = player.getHealth();

        // 6. Zombie attacks
        int damage = zombie.getDamage();

        int newHealth = healthBefore - damage;

        // 7. Health cannot go below 0
        newHealth = Math.max(newHealth, 0);

        player.setHealth(newHealth);

        // 8. Check whether player is alive
        boolean playerAlive = newHealth > 0;

        // 9. Save player
        playerRepository.save(player);

        String message;

        if (playerAlive) {
            message = "🧟 " + zombie.getId()
                    + " attacked "
                    + player.getName() + "!";
        } else {
            message = "💀 " + player.getName()
                    + " was killed by "
                    + zombie.getId() + "!";
        }

        // 10. Return result
        return new BattleResult(
                zombie.getId(),
                zombie.getId(),
                player.getId(),
                player.getName(),
                damage,
                healthBefore,
                newHealth,
                playerAlive,
                message
        );
    }

    public BattleResult playerAttack(BattleRequest request) {

        // 1. Find zombie
        Zombie zombie = zombieRepository.findById(request.getZombieId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Zombie not found"));

        // 2. Find player
        Player player = playerRepository.findById(request.getPlayerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player not found"));

        // 3. Check zombie is alive
        if (!zombie.isAlive()) {
            throw new DeadEntityException("Cannot attack a dead zombie");
        }

        // 4. Check player is alive
        if (player.getHealth() <= 0) {
            throw new DeadEntityException("Dead player cannot attack");
        }

        // 5. Check player has enough energy
        if (player.getEnergy() < 40) {
            throw new InsufficientEnergyException("Insufficient player energy");
        }

        // 6. Store zombie's old health
        int healthBefore = zombie.getHealth();

        // 7. Get player's damage
        int damage = player.getDamage();

        // 8. Calculate new health
        int newHealth = healthBefore - damage;

        // 9. Health cannot go below 0
        newHealth = Math.max(newHealth, 0);

        zombie.setHealth(newHealth);

        // 10. Reduce player energy
        player.setEnergy(player.getEnergy() - 40);

        // 11. Check zombie alive
        boolean zombieAlive = newHealth > 0;

        if (!zombieAlive) {
            zombie.setAlive(false);
        }

        // 12. Save
        zombieRepository.save(zombie);
        playerRepository.save(player);

        // 13. Message
        String message;

        if (zombieAlive) {
            message = "⚔️ " + player.getName()
                    + " attacked zombie "
                    + zombie.getId() + "!";
        } else {
            message = "💀 " + player.getName()
                    + " killed zombie "
                    + zombie.getId() + "!";
        }

        // 14. Return result
        return new BattleResult(
                player.getId(),
                player.getName(),
                zombie.getId(),
                zombie.getId(),
                damage,
                healthBefore,
                newHealth,
                zombieAlive,
                message
        );
    }
}