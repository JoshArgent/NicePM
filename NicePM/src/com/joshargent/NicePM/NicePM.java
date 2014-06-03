package com.joshargent.NicePM;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
		if(cmd.getName().equalsIgnoreCase("tell") || cmd.getName().equalsIgnoreCase("msg"))
		{
			if(sender instanceof Player)
			{
				if(args.length >= 2)
				{
					try {
						getMessageHandler().sendMessage((Player) sender, Bukkit.getPlayer(args[0]), Utils.combineArgs(args, 1, " "));
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED + e.getMessage());
					}
				}
				else sender.sendMessage(ChatColor.RED + "You specified too few arguements. Try /tell <to> <message>");
			}
			else sender.sendMessage(ChatColor.RED + "You need to be a player to use that command!");
		}
		if(cmd.getName().equalsIgnoreCase("reply") || cmd.getName().equalsIgnoreCase("r"))
		{
			if(sender instanceof Player)
			{
				if(args.length >= 1)
				{
					try {
						getMessageHandler().reply((Player) sender, Utils.combineArgs(args, 0, " "));
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED + e.getMessage());
					}
				}
				else sender.sendMessage(ChatColor.RED + "You specified too few arguements. Try /reply <message>");
			}
			else sender.sendMessage(ChatColor.RED + "You need to be a player to use that command!");
		}
		return false;
	}
	
}
