package com.example.ChaosSimulator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "zombies")
@Getter
@Setter
@NoArgsConstructor
public class Zombie {

    @Id
    private String id;
    private int health;
    private int damage;
    private int speed;

    public enum ZombieType {
        NORMAL, RUNNER, TANK, SPITTER
    }

    public enum ZombieLevel {
        LEVEL_1, LEVEL_2, LEVEL_3, BOSS
    }

    private ZombieType type;
    private ZombieLevel level;
    private boolean alive;

}