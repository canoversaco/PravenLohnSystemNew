package de.can.pravenlohnsystem.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import net.milkbowl.vault.economy.Economy;

public class StaatskasseUtils implements Listener {
	private FileConfiguration config;
	
	 public void StaatsKasse(FileConfiguration banken) {
	        this.config = banken;
	    }
}
