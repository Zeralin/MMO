package com.zeralin.mmo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	public Plugin plugin;
	CombatMechanics combatMechanics;
	HealthMechanics healthMechanics;
	
	@Override
	public void onEnable(){
		plugin = this;
		setupListeners();
		setupCommands();
	    setupClassReference();
	    setupScoreboard();
	    
	    healthMechanics.regenHP();
	}

	@EventHandler
	public void onServerPing(ServerListPingEvent e){
		e.setMotd(ChatColor.AQUA + "Zeralin Alpha");
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public void setupListeners(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new PlayerMechanics(this), this);
		pm.registerEvents(new ItemMechanics(this), this);
		pm.registerEvents(new HealthMechanics(this), this);
		pm.registerEvents(new MobMechanics(this), this);
		pm.registerEvents(new InventoryMechanics(this), this);
		pm.registerEvents(new CombatMechanics(this), this);
		pm.registerEvents(new BlockMechanics(this), this);
	}
	
	public void setupScoreboard(){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("showhp", "health");
		obj.setDisplayName("HP");
		obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
		for (Player player : Bukkit.getOnlinePlayers()){
			        player.setHealth(player.getHealth());
					player.setScoreboard(board);
		}
	}
	
	public void setupCommands(){
		
	}
	
	public void setupClassReference(){
		healthMechanics = new HealthMechanics(this);
		combatMechanics = new CombatMechanics(this);
	}
	
	public Plugin getPlugin(){
		return plugin;
	}
}
