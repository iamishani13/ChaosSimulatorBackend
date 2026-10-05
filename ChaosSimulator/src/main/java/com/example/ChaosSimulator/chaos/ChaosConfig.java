package com.example.ChaosSimulator.chaos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "chaos")
public class ChaosConfig {
    private boolean enabled = false;
    private long delay = 0;
    private int failurePercentage = 0;
    private boolean timeoutEnabled = false;
    private long timeout = 0;
}
