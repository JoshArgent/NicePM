package com.joshargent.NicePM;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class NicePM extends JavaPlugin implements Listener {
	
	private MessageHandler messageHandler;
	
	public void onEnable()
	{
		if(!this.getConfig().isSet("message-to"))
		{
			this.saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(this, this);
		
		messageHandler = new MessageHandler(this);
	}
	
	public void onDisable()
	{
		
	}
	
	public MessageHandler getMessageHandler()
	{
		return messageHandler;
	}

	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
	{
		
		return false;
	}
	
}
