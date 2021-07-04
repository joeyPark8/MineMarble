package com.joey.minemarble;

import com.joey.minemarble.util.Dice;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.joey.minemarble.Marble.playerWithArmorStand;
import static com.joey.minemarble.Marble.playingPlayers;

public class MarbleEvent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (playingPlayers.contains(player)) {
            if (player.getInventory().getItemInMainHand().getType() == Material.COOKIE) {
                if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    Dice dice = new Dice(2, 0, 6);

                    int result = dice.roll();

                    player.sendMessage("You get " + result);

                    ArmorStand stand = playerWithArmorStand.get(player);


                    e.setCancelled(true);
                }
            }
        }
    }
}
