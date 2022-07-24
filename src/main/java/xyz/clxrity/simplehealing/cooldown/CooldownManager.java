package xyz.clxrity.simplehealing.cooldown;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class CooldownManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();


    public static long DEFAULT_COOLDOWN = 60;


    public void setCooldown(UUID player, long time){
        if(time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public long getCooldown(UUID player){
        long defaultValue = 0;
        return cooldowns.getOrDefault(player, defaultValue);
    }

}