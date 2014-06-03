package com.joshargent.NicePM;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class NicePM extends JavaPlugin implements Listener {
	
	public void onEnable()
	{
		if(!this.getConfig().isSet("message-to"))
		{
			this.saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable()
	{
		
	}

	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
	{
		
		return false;
	}
	
}
