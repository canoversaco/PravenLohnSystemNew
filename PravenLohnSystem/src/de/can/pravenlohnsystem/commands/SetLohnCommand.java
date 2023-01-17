package de.can.pravenlohnsystem.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SetLohnCommand implements Listener {

	String SYS_MSG = "§7[§aPravenLohnSystem§7] §r";
	
	 ConfigurationSection loehneSection = LohnCommand.config.getConfigurationSection("Loehne");
     ConfigurationSection steuernSection = LohnCommand.config.getConfigurationSection("Steuern");
     ConfigurationSection koenigsection = LohnCommand.config.getConfigurationSection("Könige");
     int HarenaebuergerLohn = loehneSection.getInt("Harenae.Buerger");
     int HarenaeredaktionschefLohn = loehneSection.getInt("Harenae.Redaktionschef");
     int HarenaeRessourcensammlerLohn = loehneSection.getInt("Harenae.Ressourcensammler");
     int HarenaeLandwirtLohn = loehneSection.getInt("Harenae.Landwirt");
     int HarenaeMarktführerLohn = loehneSection.getInt("Harenae.Marktführer");
     int HarenaeWächterLohn = loehneSection.getInt("Harenae.Wächter");
     int HarenaeRichterLohn = loehneSection.getInt("Harenae.Richter");
     int HarenaeWachtmeisterLohn = loehneSection.getInt("Harenae.Wachtmeister");
     int HarenaeArchitektLohn = loehneSection.getInt("Harenae.Architekt");
     int HarenaeWirtLohn = loehneSection.getInt("Harenae.Wirt");
     int HarenaeKoenigLohn = loehneSection.getInt("Harenae.Koenig");
     int HarenaeFinanzministerLohn = loehneSection.getInt("Harenae.Finanzminister");
     int AkanonbuergerLohn = loehneSection.getInt("Akanon.Buerger");
     int AkanonredaktionschefLohn = loehneSection.getInt("Akanon.Redaktionschef");
     int AkanonRessourcensammlerLohn = loehneSection.getInt("Akanon.Ressourcensammler");
     int AkanonLandwirtLohn = loehneSection.getInt("Akanon.Landwirt");
     int AkanonMarktführerLohn = loehneSection.getInt("Akanon.Marktführer");
     int AkanonWächterLohn = loehneSection.getInt("Akanon.Wächter");
     int AkanonRichterLohn = loehneSection.getInt("Akanon.Richter");
     int AkanonWachtmeisterLohn = loehneSection.getInt("Akanon.Wachtmeister");
     int AkanonArchitektLohn = loehneSection.getInt("Akanon.Architekt");
     int AkanonWirtLohn = loehneSection.getInt("Akanon.Wirt");
     int AkanonKoenigLohn = loehneSection.getInt("Akanon.Koenig");
     int AkanonFinanzministerLohn = loehneSection.getInt("Akanon.Finanzminister");
     int GlaciesbuergerLohn = loehneSection.getInt("Glacies.Buerger");
     int GlaciesredaktionschefLohn = loehneSection.getInt("Glacies.Redaktionschef");
     int GlaciesRessourcensammlerLohn = loehneSection.getInt("Glacies.Ressourcensammler");
     int GlaciesLandwirtLohn = loehneSection.getInt("Glacies.Landwirt");
     int GlaciesMarktführerLohn = loehneSection.getInt("Glacies.Marktführer");
     int GlaciesWächterLohn = loehneSection.getInt("Glacies.Wächter");
     int GlaciesRichterLohn = loehneSection.getInt("Glacies.Richter");
     int GlaciesWachtmeisterLohn = loehneSection.getInt("Glacies.Wachtmeister");
     int GlaciesArchitektLohn = loehneSection.getInt("Glacies.Architekt");
     int GlaciesWirtLohn = loehneSection.getInt("Glacies.Wirt");
     int GlaciesKoenigLohn = loehneSection.getInt("Glacies.Koenig");
     int GlaciesFinanzministerLohn = loehneSection.getInt("Glacies.Finanzminister");
     double harenaeSteuersatz = steuernSection.getDouble("Steuersatzharenae");
     double akanonSteuersatz = steuernSection.getDouble("Steuersatzakanon");
     double glaciesSteuersatz = steuernSection.getDouble("Steuersatzglacies");
     String HarenaeKoenig = koenigsection.getString("Könige.Harenae");
     String AkanonKoenig = koenigsection.getString("Könige.Akanon");
     String GlaciesKoenig = koenigsection.getString("Könige.Glacies");
     
     
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player)sender;
		
		// TODO Auto-generated method 
			if(cmd.getName().equalsIgnoreCase("setlohn") && args.length == 1 && p.hasPermission("pravenlohnsystem.lohnsetzen.harenae")) {
				if (args[0].equalsIgnoreCase("Redaktionschef") && args[1].matches("[45-60]+")) {
					LohnCommand.config.set("Loehne.Harenae.Redaktionschef", args[1]);
					try {
						LohnCommand.config.save(LohnCommand.config);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
					
				} else {
					p.sendMessage(SYS_MSG + "§cDu musst eine Zahl für den Lohn eingeben!");
				}
			} else {
				p.sendMessage(SYS_MSG + "§cDas darf nur der König!");
			}
			
			if(cmd.getName().equalsIgnoreCase("setlohn") && args.length == 1 && p.hasPermission("pravenlohnsystem.lohnsetzen.harenae")) {
				if (args[0].equalsIgnoreCase("Bürger") && args[1].matches("[20-35]+")) {
					LohnCommand.config.set("Harenae.Buerger", args[1]);
					try {
						LohnCommand.config.save(LohnCommand.config);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
					
				} else {
					p.sendMessage(SYS_MSG + "§cDu musst eine Zahl für den Lohn eingeben!");
				}
			} else {
				p.sendMessage(SYS_MSG + "§cDas darf nur der König!");
			}
					return false;
	}

}
