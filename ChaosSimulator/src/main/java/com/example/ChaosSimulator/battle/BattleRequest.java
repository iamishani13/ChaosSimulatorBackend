package com.example.ChaosSimulator.battle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter     
public class BattleRequest {

    private String zombieId;
    private String playerId;
    public enum AttackerType {
        ZOMBIE, PLAYER
    }

    private AttackerType attackerType;

    
}