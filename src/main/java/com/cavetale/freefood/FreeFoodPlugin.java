package com.cavetale.freefood;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FreeFoodPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(this, this::tick, 5L, 5L);
    }

    @Override
    public void onDisable() {
    }

    private void tick() {
        final var world = Bukkit.getWorld("spawn");
        if (world == null) return;
        for (var player : world.getPlayers()) {
            final var food = player.getFoodLevel();
            if (food < 20) {
                player.setFoodLevel(food + 1);
            }
            final var sat = player.getSaturation();
            if (sat < (float) food) {
                player.setSaturation(Math.min((float) food, sat + 1f));
            }
        }
    }
}
