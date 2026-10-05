package com.example.ChaosSimulator.service;


import com.example.ChaosSimulator.dto.Player;
import com.example.ChaosSimulator.repository.PlayerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepo playerRepository;

    public PlayerService(PlayerRepo playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(String playerId) {
        return playerRepository.findById(playerId).orElse(null);    
    }

    public Player updatePlayer(String id, Player player) {
        Player existingPlayer = playerRepository.findById(id).orElse(null);
        if (existingPlayer == null) {
            return null;
        }
        existingPlayer.setName(player.getName());
        existingPlayer.setEnergy(player.getEnergy());
        existingPlayer.setHealth(player.getHealth());
        return playerRepository.save(existingPlayer);
    }

    public Player deletePlayer(String id) {
        Player existingPlayer = playerRepository.findById(id).orElse(null);
        if (existingPlayer == null) {
            return null;
        }
        playerRepository.delete(existingPlayer);
        return existingPlayer;
    }   
}