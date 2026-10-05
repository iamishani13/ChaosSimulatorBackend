package com.example.ChaosSimulator.battle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BattleResult {

    private String attackerId;
    private String attackerName;

    private String targetId;
    private String targetName;

    private int damage;

    private int targetHealthBefore;
    private int targetHealthAfter;

    private boolean targetAlive;

    private String message;
}