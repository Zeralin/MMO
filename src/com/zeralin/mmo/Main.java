package com.zeralin.mmo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.inventivetalent.bossbar.BossBarAPI;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	public Plugin plugin;
	HealthMechanics healthMechanics;
	
	@Override
	public void onEnable(){
		plugin = this;
		setupListeners();
		setupCommands();
	    setupClassReference();
	    setupScoreboard();
	    
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
 			@Override
 			public void run() {
 				for (Player player : Bukkit.getOnlinePlayers()){
 				BossBarAPI.setMessage(player, 
 				 ChatColor.LIGHT_PURPLE + "HP " + (int) player.getHealth() + "/" + (int) player.getMaxHealth());
 			   }
 			}
 	    }, 1L, 1L);
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
	}
	
	public void setupScoreboard(){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("HP", "Health");
		obj.setDisplayName("HP");
		obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
		for (Player player : Bukkit.getOnlinePlayers()){
		    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable(){
				@Override
				public void run() {
					player.setScoreboard(board);
					player.setHealth(player.getHealth());
				}	
		    }, 1L, 1L);
		}
	}
	
	public void setupCommands(){
		
	}
	
	public void setupClassReference(){
		healthMechanics = new HealthMechanics(this);
	}
	
	public Plugin getPlugin(){
		return plugin;
	}
}
