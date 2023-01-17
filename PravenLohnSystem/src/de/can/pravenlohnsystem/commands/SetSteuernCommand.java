package de.can.pravenlohnsystem.commands;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SetSteuernCommand implements Listener, CommandExecutor {

	String SYS_MSG = "§7[§aPravenLohnSystem§7] §r";
		
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player)sender;

		//Command für Harenae
		if(cmd.getName().equalsIgnoreCase("setsteuern") && p.hasPermission("pravenlohnsystem.steuern.harenae")) {
			p.sendMessage(SYS_MSG + "§cDu musst den Steuersatz zwischen 0.25 und 0.6 Setzen! (0.25 = 25%)");
			
		 if (args[0].matches("[0.25-0.6]")) {
			 p.sendMessage(SYS_MSG + "§aDu hast die Steuern erfolgreich geändert!");
			LohnCommand.config.set("Steuern.Harenaesteuersatz", args[0]);
			LohnCommand.config.save(new (LohnCommand.config);
			
		} else {
			p.sendMessage(SYS_MSG + "§cDu musst den Steuersatz zwischen 0.25 und 0.6 Setzen! (0.25 = 25%)");
		}
		 
		} else {
			p.sendMessage(SYS_MSG + "§cDas darf nur der König!");
		}
		
		//Command für Akanon
		if(cmd.getName().equalsIgnoreCase("setsteuern") && p.hasPermission("pravenlohnsystem.steuern.akanon")) {
			p.sendMessage(SYS_MSG + "§cDu musst den Steuersatz zwischen 0.25 und 0.6 Setzen! (0.25 = 25%)");
			
		 if (args[0].matches("[0.25-0.6]")) {
			 p.sendMessage(SYS_MSG + "§aDu hast die Steuern erfolgreich geändert!");
			LohnCommand.config.set("Steuern.Akanonsteuersatz", args[0]);
			
		} else {
			p.sendMessage(SYS_MSG + "§cDu musst den Steuersatz zwischen 0.25 und 0.6 Setzen! (0.25 = 25%)");
		}
		 
		} else {
			p.sendMessage(SYS_MSG + "§cDas darf nur der König!");
		}
		
		//Command für Glacies
		if(cmd.getName().equalsIgnoreCase("setsteuern") && p.hasPermission("pravenlohnsystem.steuern.glacies")) {
			p.sendMessage(SYS_MSG + "§cDu musst den Steuersatz zwischen 0.25 und 0.6 Setzen! (0.25 = 25%)");
			
		 if (args[0].matches("[0.25-0.6]")) {
			 p.sendMessage(SYS_MSG + "§aDu hast die Steuern erfolgreich geändert!");
			LohnCommand.config.set("Steuern.Glaciessteuersatz", args[0]);
		} else {
			p.sendMessage(SYS_MSG + "§cDu musst den Steuersatz zwischen 0.25 und 0.6 Setzen! (0.25 = 25%)");
		}
		 
		} else {
			p.sendMessage(SYS_MSG + "§cDas darf nur der König!");
		}
		
		return false;
	}

}
