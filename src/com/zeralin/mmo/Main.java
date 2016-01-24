package com.zeralin.mmo;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public Plugin plugin;
	HealthMechanics healthMechanics;
	CombatMechanics combatMechanics;
	
	@Override
	public void onEnable(){
		plugin = this;
		setupListeners();
		setupCommands();
	    setupClassReference();
	    
	    healthMechanics.regenHealth();
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
