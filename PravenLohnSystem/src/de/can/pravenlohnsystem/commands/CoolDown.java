package de.can.pravenlohnsystem.commands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.Bukkit;
import java.util.Iterator;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class CoolDown
{
    private static Map<UUID, Map<String, Long>> cooldowns;
    
    static {
        CoolDown.cooldowns = new HashMap<UUID, Map<String, Long>>();
    }
    
    public static void addCoolDown(final Player player, final String command, final long seconds) {
        Map<String, Long> playerCooldowns = CoolDown.cooldowns.get(player.getUniqueId());
        if (playerCooldowns == null) {
            playerCooldowns = new HashMap<String, Long>();
            CoolDown.cooldowns.put(player.getUniqueId(), playerCooldowns);
        }
        final long expirationTime = System.currentTimeMillis() + seconds * 1000L;
        playerCooldowns.put(command, expirationTime);
    }
    
    public static int getTimeLeft(final Player player, final String command) {
        final Map<String, Long> playerCooldowns = CoolDown.cooldowns.get(player.getUniqueId());
        if (playerCooldowns == null) {
            return 0;
        }
        final Long expirationTime = playerCooldowns.get(command);
        if (expirationTime == null) {
            return 0;
        }
        final long remainingTime = expirationTime - System.currentTimeMillis();
        if (remainingTime > 0L) {
            return (int)(remainingTime / 1000L);
        }
        playerCooldowns.remove(command);
        return 0;
    }
    
    public static void saveToFile(final File file) {
        final FileConfiguration cooldownconfig = (FileConfiguration)new YamlConfiguration();
        for (final Map.Entry<UUID, Map<String, Long>> entry : CoolDown.cooldowns.entrySet()) {
            final UUID player = entry.getKey();
            final Map<String, Long> playerCooldowns = entry.getValue();
            for (final Map.Entry<String, Long> commandEntry : playerCooldowns.entrySet()) {
                final String command = commandEntry.getKey();
                final long expirationTime = commandEntry.getValue();
                cooldownconfig.set(player + "." + command, (Object)expirationTime);
            }
        }
        try {
            cooldownconfig.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadFromFile(final File file) {
    	final FileConfiguration cooldownconfig = (FileConfiguration)new YamlConfiguration();
        for (final String uuid : cooldownconfig.getKeys(false)) {
            final Player player = Bukkit.getPlayer(UUID.fromString(uuid));
            if (player == null) {
                continue;
            }
            final ConfigurationSection playerSection = cooldownconfig.getConfigurationSection(uuid);
            for (final String command : playerSection.getKeys(false)) {
                final int secondsLeft = playerSection.getInt(command);
                final long expirationTime = System.currentTimeMillis() + secondsLeft * 1000;
                addCoolDown(player, command, expirationTime);
            }
        }
    }
}