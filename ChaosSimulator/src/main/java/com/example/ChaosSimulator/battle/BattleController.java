package com.example.ChaosSimulator.battle;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battle")
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/attack")
    public BattleResult attack(@RequestBody BattleRequest request) {
        if (request.getAttackerType() == BattleRequest.AttackerType.PLAYER) {
            return battleService.playerAttack(request);

        } else {
            return battleService.zombieAttack(request);

        }
    }
}