package com.zeralin.mmo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class CombatMechanics implements Listener{
	
	public Main main;
	
	public CombatMechanics(Main plugin){
		main = plugin;
	}
	
	public List<String> tag = new ArrayList<String>();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		e.setDeathMessage(null);
	}
	
	@EventHandler
	public void onCombatTag(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			if (tag.contains(player.getName())){
				tag.remove(player.getName());
				tag.add(player.getName());
				Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
					@Override
					public void run() {
						tag.remove(player.getName());
					}
				}, 20L * 10);
			} else {
				tag.add(player.getName());
				Bukkit.getServer().getScheduler().runTaskLater(main.getPlugin(), new Runnable(){
					@Override
					public void run() {
						tag.remove(player.getName());
					}
				}, 20L * 10);
			}
			
		    } else if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){
				Player player = (Player) e.getDamager();
				tag.add(player.getName());
		}
	}
	
	@EventHandler
	public void onWorldDamage(EntityDamageEvent e){
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if (e.getDamage() <= 0) {
				return;
			}
			if (player.getNoDamageTicks() <= player.getMaximumNoDamageTicks() / 2 && player.getHealth() > 0.0D) {
				int dmg = (int) e.getDamage();
				if (dmg <= 0) {
					return;
				}
				int health = 0;
				if (player.getHealth() - (double) dmg > 0.0D) {
					health = (int) player.getHealth() - dmg;
				}
				if (health >= 1){
					player.sendMessage(ChatColor.RED + "" + dmg + " -> " + ChatColor.WHITE + "[" + 
				                ChatColor.GREEN + health + ChatColor.WHITE + "]");
				}
			}
			}
	}
	
	@EventHandler
	public void onDamageTake(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Player)){
			Player player = (Player) e.getEntity();
			Creature mob = (Creature) e.getDamager();
			
			if (e.getDamage() == 0){
				return;
			} else {
				
				int dmg = (int) e.getDamage();
				int hp = (int) player.getHealth() - dmg;
				
				if (hp > 0){
					player.setHealth(hp);
				} else {
					player.setHealth(0);
				}
				
				e.setDamage(0);
				if (hp >= 1){
					player.sendMessage(ChatColor.RED + "" + dmg + " -> " + ChatColor.WHITE + "[" + 
				                ChatColor.GREEN + hp + ChatColor.WHITE + "]");
					} else {
						player.sendMessage(ChatColor.RED + "" + dmg + " -> " + mob.getCustomName() + 
								ChatColor.WHITE + " [" + ChatColor.GREEN + "0" + ChatColor.WHITE + "]");
					}
			}
		} else if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player player = (Player) e.getEntity();
			Player mob = (Player) e.getDamager();
			
			if (e.getDamage() == 0){
				return;
			} else {
				
				int dmg = (int) e.getDamage();
				int hp = (int) player.getHealth() - dmg;
				
				if (hp > 0){
					player.setHealth(hp);
				} else {
					player.setHealth(0);
				}
				
				e.setDamage(0);
				if (hp >= 1){
					player.sendMessage(ChatColor.RED + "" + dmg + " -> " + ChatColor.WHITE + "[" + 
				                ChatColor.GREEN + hp + ChatColor.WHITE + "]");
					} else {
						player.sendMessage(ChatColor.RED + "" + dmg + " -> " + mob.getCustomName() + 
								ChatColor.WHITE + " [" + ChatColor.GREEN + "0" + ChatColor.WHITE + "]");
					}
				
				int health = (int) mob.getHealth() - dmg;
				
				if (health >= 1){
					mob.sendMessage(ChatColor.RED + "" + dmg + " -> " + ChatColor.WHITE + "[" + 
			                ChatColor.GREEN + health + ChatColor.WHITE + "]");
				} else {
					mob.sendMessage(ChatColor.RED + "" + dmg + " -> " + ChatColor.WHITE + "[" + 
			                ChatColor.GREEN + "0" + ChatColor.WHITE + "]");
				}
			}
		}
	}
	
	@EventHandler
	public void onOutgoingDamage(EntityDamageByEntityEvent e){
		if (e.getDamager() instanceof Player && !(e.getEntity() instanceof Player)){
			Player player = (Player) e.getDamager();
			Creature mob = (Creature) e.getEntity();
			
			int dmg = (int) e.getDamage();
			int hp = (int) mob.getHealth() - dmg;
			
			if (hp > 0){
				mob.setHealth(hp);
			} else {
				mob.setHealth(0);
			}

			e.setDamage(0);
			
			if (hp >= 1){
			player.sendMessage(ChatColor.RED + "" + dmg + " -> " + mob.getCustomName() + 
					ChatColor.WHITE + " [" + ChatColor.GREEN + hp + ChatColor.WHITE + "]");
			} else {
				
				player.sendMessage(ChatColor.RED + "" + dmg + " -> " + mob.getCustomName() + 
						ChatColor.WHITE + " [" + ChatColor.GREEN + "0" + ChatColor.WHITE + "]");
			}
		} else if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){
			Player player = (Player) e.getDamager();
			Player ent = (Player) e.getEntity();
			
			int dmg = (int) e.getDamage();
			int hp = (int) ent.getHealth() - dmg;
			
			if (hp > 0){
				ent.setHealth(hp);
			} else {
				ent.setHealth(0);
			}

			e.setDamage(0);
			
			if (hp >= 1){
			player.sendMessage(ChatColor.RED + "" + dmg + " -> " + ent.getName() + 
					ChatColor.WHITE + " [" + ChatColor.GREEN + hp + ChatColor.WHITE + "]");
			} else {
				
				player.sendMessage(ChatColor.RED + "" + dmg + " -> " + ent.getName() + 
						ChatColor.WHITE + " [" + ChatColor.GREEN + "0" + ChatColor.WHITE + "]");
			}
			
			int health = (int) ent.getHealth() - dmg;
			
			if (health >= 1){
				ent.sendMessage(ChatColor.RED + "" + dmg + " -> " + ChatColor.WHITE + "[" + 
		                ChatColor.GREEN + health + ChatColor.WHITE + "]");
			} else {
				ent.sendMessage(ChatColor.RED + "" + dmg + " -> " + ChatColor.WHITE + "[" + 
		                ChatColor.GREEN + "0" + ChatColor.WHITE + "]");
			}
		}
		
	}
}
