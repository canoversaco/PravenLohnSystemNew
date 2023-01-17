// 
// Decompiled by Procyon v0.5.36
// 

package de.can.pravenlohnsystem;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import de.can.pravenlohnsystem.commands.CoolDown;
import org.bukkit.command.CommandExecutor;
import de.can.pravenlohnsystem.commands.LohnCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public FileConfiguration config;
    private File cooldownFile;
    public static HashMap<Player, Long> cooldowns;
    private static Main instance;
    
    static {
        Main.cooldowns = new HashMap<Player, Long>();
    }
    
    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§7-----------------------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage("             §aPravenLohnSystem");
        Bukkit.getServer().getConsoleSender().sendMessage("§a             Version: 1.0");
        Bukkit.getServer().getConsoleSender().sendMessage("              §aProgrammiert von");
        Bukkit.getServer().getConsoleSender().sendMessage("                 §c§l§nCano");
        Bukkit.getServer().getConsoleSender().sendMessage("       §aPlugin erfolgreich gestartet");
        Bukkit.getServer().getConsoleSender().sendMessage("§7-----------------------------------------");
        this.saveDefaultConfig();
        this.config = this.getConfig();
        this.getCommand("lohn").setExecutor((CommandExecutor)new LohnCommand(this.config));
        CoolDown.loadFromFile(this.cooldownFile = new File(this.getDataFolder(), "cooldowns.yml"));
    }
    
    public void onPlayerQuit(final PlayerQuitEvent event) {
        CoolDown.saveToFile(this.cooldownFile);
    }
    
    public void onPlayerJoin(final PlayerJoinEvent event) {
        CoolDown.loadFromFile(this.cooldownFile);
    }
    
    public void onLoad() {
        Main.instance = this;
        CoolDown.loadFromFile(this.cooldownFile);
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public void onDisable() {
        CoolDown.saveToFile(this.cooldownFile);
    }
}
