package com.zeralin.mmo;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockMechanics implements Listener{

	public Main main;
	
	public BlockMechanics(Main plugin){
		main = plugin;
	}
	
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
    	if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
    		if (e.getClickedBlock().getType() == Material.ITEM_FRAME){
    			e.setCancelled(true);
    		}
    	}
    }
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL){
			e.setCancelled(true);
			if (e.getPlayer().getItemInHand() != null){
				e.getPlayer().getItemInHand().setDurability((short) 0);
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL){
			e.setCancelled(true);
		}
	}
	
}
