package com.example.ChaosSimulator.chaos;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class ChaosInterceptor implements HandlerInterceptor {

    private final ChaosConfig chaosConfig;

    public ChaosInterceptor(ChaosConfig chaosConfig) {
        this.chaosConfig = chaosConfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws InterruptedException {
        if(!chaosConfig.isEnabled()) {
            return true;
        }

        if(chaosConfig.getDelay() > 0) {
            Thread.sleep(chaosConfig.getDelay());
        }

        if(shouldFail()) {
            throw new RuntimeException("💥 Chaos Engine: Random failure!");
        }

        if (chaosConfig.isTimeoutEnabled() && chaosConfig.getTimeout() > 0) {

            Thread.sleep(chaosConfig.getTimeout());

            throw new RuntimeException("💥 Chaos Engine: Request timeout!");
        }

        return true;
    }

    private boolean shouldFail() {
        int rand = ThreadLocalRandom.current().nextInt(100);
        return rand < chaosConfig.getFailurePercentage();
    }

}
