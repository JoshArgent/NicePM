package com.joshargent.NicePM;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.joshargent.NicePM.exceptions.NoReplyException;
import com.joshargent.NicePM.exceptions.PlayerNotOnlineException;
import com.joshargent.NicePM.exceptions.SelfMessageException;

public class MessageHandler {
	
	private JavaPlugin plugin;
	private String formatTo;
	private String formatFrom;
	private boolean logMessages;
	private Map<UUID, UUID> replies = new HashMap<UUID, UUID>();
	
	public MessageHandler(JavaPlugin plugin)
	{
		this.plugin = plugin;
		this.formatTo = plugin.getConfig().getString("message-to");
		this.formatFrom = plugin.getConfig().getString("message-from");
		this.logMessages = plugin.getConfig().getBoolean("log-messages");
	}
	
	public void reply(Player from, String message) throws NoReplyException, PlayerNotOnlineException, SelfMessageException
	{
		if(replies.containsKey(from.getUniqueId()))
		{
			Player to = Bukkit.getPlayer(replies.get(from.getUniqueId()));
			if(to != null)
			{
				sendMessage(from, to, message);
			}
			else throw new PlayerNotOnlineException();
		}
		else throw new NoReplyException();
	}
	
	public void sendMessage(Player from, Player to, String message) throws PlayerNotOnlineException, SelfMessageException
	{
		if(from == null || to == null) throw new PlayerNotOnlineException();
		if(from.isOnline() && to.isOnline())
		{
			if(from != to)
			{
				String messageTo = formatTo;
				if(messageTo.contains("%f"))
				{
					messageTo = messageTo.replace("%f", from.getName());
				}
				if(messageTo.contains("%t"))
				{
					messageTo = messageTo.replace("%t", to.getName());
				}
				if(messageTo.contains("%m"))
				{
					messageTo = messageTo.replace("%m", message);
				}
				from.sendMessage(ChatColor.translateAlternateColorCodes('&', messageTo));
				
				String messageFrom = formatFrom;
				if(messageFrom.contains("%f"))
				{
					messageFrom = messageFrom.replace("%f", from.getName());
				}
				if(messageFrom.contains("%t"))
				{
					messageFrom = messageFrom.replace("%t", to.getName());
				}
				if(messageFrom.contains("%m"))
				{
					messageFrom = messageFrom.replace("%m", message);
				}
				to.sendMessage(ChatColor.translateAlternateColorCodes('&', messageFrom));
				
				if(logMessages)
				{
					logMessage(from, to, message);
				}
				
				setReply(from, to);
			}
			else throw new SelfMessageException();
		}
		else throw new PlayerNotOnlineException();
	}
	
	private void logMessage(Player from, Player to, String message)
	{
		String output = from.getName() + " -> " + to.getName() + " : " + message;
		plugin.getLogger().log(Level.INFO, output);
	}
	
	private void setReply(Player from, Player to)
	{
		if(replies.containsKey(to.getUniqueId())) replies.remove(to.getUniqueId());
		replies.put(to.getUniqueId(), from.getUniqueId());
	}
	
}
