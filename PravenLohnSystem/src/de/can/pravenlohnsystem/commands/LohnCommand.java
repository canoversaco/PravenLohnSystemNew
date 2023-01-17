// 
// Decompiled by Procyon v0.5.36
// 

package de.can.pravenlohnsystem.commands;

import org.bukkit.configuration.ConfigurationSection;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.HashMap;
import org.bukkit.entity.Player;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class LohnCommand implements Listener, CommandExecutor
{
    String SYS_MSG;
    public static FileConfiguration config;
    Map<Player, Long> cooldownTimes;
    int COOLDOWN_TIME;
    
    
    
    
    
    public LohnCommand(final FileConfiguration cooldownconfig) {
        this.SYS_MSG = "§7[§aPravenLohnSystem§7] ";
        this.cooldownTimes = new HashMap<Player, Long>();
        this.COOLDOWN_TIME = 86400;
        this.config = cooldownconfig;
    }
    
    
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!command.getName().equalsIgnoreCase("lohn")) {
            return false;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(String.valueOf(this.SYS_MSG) + "§cNur ein Spieler kann diesen Befehl ausführen.");
            return true;
        }
        final Player player = (Player)sender;
        final int timeLeft = CoolDown.getTimeLeft(player, "lohn");
        if (timeLeft > 0) {
            final long hours = timeLeft / 3600;
            final long minutes = timeLeft % 3600 / 60;
            final long seconds = timeLeft % 3600 % 60;
            final StringBuilder message = new StringBuilder();
            message.append(String.valueOf(this.SYS_MSG) + "§cDu musst noch ");
            if (hours > 0L) {
                message.append("§c" + hours + " §cStunden, ");
            }
            else {
                message.append("warten,");
            }
            if (minutes > 0L) {
                message.append("§c" + minutes + "§c Minuten, ");
            }
            else {
                message.append("warten,");
            }
            if (seconds > 0L) {
                message.append("§cund " + seconds + "§c Sekunden warten, ");
            }
            message.append("§cbevor du deinen Lohn erneut abholen kannst.");
            player.sendMessage(message.toString());
            return true;
        }
        final ConfigurationSection loehneSection = this.config.getConfigurationSection("Loehne");
        final ConfigurationSection steuernSection = this.config.getConfigurationSection("Steuern");
        final ConfigurationSection koenigsection = this.config.getConfigurationSection("Könige");
        final int buergerLohn = loehneSection.getInt("Buerger");
        final int redaktionschefLohn = loehneSection.getInt("Redaktionschef");
        final int RessourcensammlerLohn = loehneSection.getInt("Ressourcensammler");
        final int LandwirtLohn = loehneSection.getInt("Landwirt");
        final int MarktführerLohn = loehneSection.getInt("Marktfüchrer");
        final int WächterLohn = loehneSection.getInt("Wächter");
        final int RichterLohn = loehneSection.getInt("Richter");
        final int WachtmeisterLohn = loehneSection.getInt("Wachtmeister");
        final int ArchitektLohn = loehneSection.getInt("Architekt");
        final int WirtLohn = loehneSection.getInt("Wirt");
        final int KoenigLohn = loehneSection.getInt("Koenig");
        final int FinanzministerLohn = loehneSection.getInt("Finanzminister");
        final double harenaeSteuersatz = steuernSection.getDouble("Steuersatzharenae");
        final double akanonSteuersatz = steuernSection.getDouble("Steuersatzakanon");
        final double glaciesSteuersatz = steuernSection.getDouble("Steuersatzglacies");
        final String HarenaeKoenig = koenigsection.getString("Könige.Harenae");
        final String AkanonKoenig = koenigsection.getString("Könige.Akanon");
        final String GlaciesKoenig = koenigsection.getString("Könige.Glacies");
        if (player.hasPermission("pravenlohnsystem.buerger")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + buergerLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + buergerLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + buergerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + buergerLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + buergerLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + buergerLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + buergerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + buergerLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + buergerLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + buergerLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + buergerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + buergerLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.koenig")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + KoenigLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + KoenigLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + KoenigLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + KoenigLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + KoenigLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + KoenigLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + KoenigLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + KoenigLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + KoenigLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + KoenigLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + KoenigLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + KoenigLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.wirt")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WirtLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + WirtLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WirtLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + WirtLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WirtLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + WirtLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WirtLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + WirtLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WirtLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + WirtLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WirtLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + WirtLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.architekt")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + ArchitektLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + ArchitektLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + ArchitektLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + ArchitektLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + ArchitektLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + ArchitektLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + ArchitektLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + ArchitektLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + ArchitektLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + ArchitektLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + ArchitektLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + ArchitektLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.wachtmeister")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WachtmeisterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + HarenaeKoenig + " " + WachtmeisterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WachtmeisterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WachtmeisterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + AkanonKoenig + " " + WachtmeisterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WachtmeisterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WachtmeisterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + GlaciesKoenig + " " + WachtmeisterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WachtmeisterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
        }
        if (player.hasPermission("pravenlohnsystem.richter")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + RichterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + HarenaeKoenig + " " + RichterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + RichterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + RichterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + AkanonKoenig + " " + RichterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + RichterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + RichterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + GlaciesKoenig + " " + RichterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + RichterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
        }
        if (player.hasPermission("pravenlohnsystem.wache")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WächterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + HarenaeKoenig + " " + WächterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WächterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WächterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + AkanonKoenig + " " + WächterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WächterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + WächterLohn);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank remove " + GlaciesKoenig + " " + WächterLohn);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + WächterLohn + "$ erhalten! Da du für den Staat arbeitest, zahlst du keine Steuern! ");
            }
        }
        if (player.hasPermission("pravenlohnsystem.finanzminister")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + FinanzministerLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + FinanzministerLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + FinanzministerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + FinanzministerLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + FinanzministerLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + FinanzministerLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + FinanzministerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + FinanzministerLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + FinanzministerLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + FinanzministerLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + FinanzministerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + FinanzministerLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.marktführer")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + MarktführerLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + MarktführerLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + MarktführerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + MarktführerLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + MarktführerLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + MarktführerLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + MarktführerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + MarktführerLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + MarktführerLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + MarktführerLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + MarktführerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + MarktführerLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.landwirt")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + LandwirtLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + LandwirtLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + LandwirtLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + LandwirtLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + LandwirtLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + LandwirtLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + LandwirtLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + LandwirtLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + LandwirtLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + LandwirtLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + LandwirtLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + LandwirtLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.ressourcensammler")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + RessourcensammlerLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + RessourcensammlerLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + RessourcensammlerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + RessourcensammlerLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + RessourcensammlerLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + RessourcensammlerLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + RessourcensammlerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + RessourcensammlerLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + RessourcensammlerLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + RessourcensammlerLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + RessourcensammlerLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + RessourcensammlerLohn * glaciesSteuersatz + "$.");
            }
        }
        if (player.hasPermission("pravenlohnsystem.redaktionschef")) {
            if (player.hasPermission("pravenlohnsystem.harenaesteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + redaktionschefLohn * harenaeSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + HarenaeKoenig + " " + redaktionschefLohn * harenaeSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + redaktionschefLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + redaktionschefLohn * harenaeSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.akanonsteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + redaktionschefLohn * akanonSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + AkanonKoenig + " " + redaktionschefLohn * akanonSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + redaktionschefLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + redaktionschefLohn * akanonSteuersatz + "$.");
            }
            else if (player.hasPermission("pravenlohnsystem.glaciessteuer")) {
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "eco give " + player.getName() + " " + redaktionschefLohn * glaciesSteuersatz);
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "rec sharedbank add " + GlaciesKoenig + " " + redaktionschefLohn * glaciesSteuersatz);
                player.sendMessage(String.valueOf(this.SYS_MSG) + "§aDu hast deinen Lohn von " + redaktionschefLohn + "$ erhalten! Dein Nettolohn beträgt bei dem Aktuellen Steuersatz " + redaktionschefLohn * glaciesSteuersatz + "$.");
            }
        }
        CoolDown.addCoolDown(player, "lohn", this.COOLDOWN_TIME);
        final File file = new File("PravenLohnSystem/cooldowns.yml");
        CoolDown.saveToFile(file);
        return true;
    }
    
    
    
}
