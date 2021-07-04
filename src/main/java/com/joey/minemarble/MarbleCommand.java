package com.joey.minemarble;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.TeamArgument;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.Set;

import static com.joey.minemarble.Marble.*;

public class MarbleCommand {
    public static void register() {
        new CommandAPICommand("minemarble")
            .withAliases("mm")
            .withSubcommand(
                new CommandAPICommand("set")
                    .withSubcommand(
                        new CommandAPICommand("startPoint")
                            .withArguments(new LocationArgument("location"))
                            .executesPlayer((player, args) -> {
                                Location location = (Location) args[0];

                                startLocation = location;

                                player.sendMessage(
                                        "set startpoint at ("
                                        + location.getX() + ","
                                        + location.getY() + ","
                                        + location.getZ() + ")"
                                );
                            })
                    )
            )
            .withSubcommand(new CommandAPICommand("start")
                .withArguments(new TeamArgument("team"))
                .executesPlayer((player, args) -> {
                    if (!isStarted) {
                        isStarted = true;

                        playGame(player, args);
                    }
                    else player.sendMessage(ChatColor.YELLOW + "Game is already started");
                })
            )
            .withSubcommand(new CommandAPICommand("end")
                .executesPlayer((player, args) -> {
                    if (isStarted) isStarted = false;
                    else player.sendMessage(ChatColor.YELLOW + "Game is not started");
                })
            ).register();
    }

    public static void playGame(Player player, Object[] args) {
        String teamName = (String) args[0];
        Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);

        Set<OfflinePlayer> players = team.getPlayers();

        for (OfflinePlayer i : players) {
            ItemStack stack = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (byte) 3);
            SkullMeta meta = (SkullMeta) stack.getItemMeta();
            meta.setOwner(player.getName());
            stack.setItemMeta(meta);

            ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(startLocation, EntityType.ARMOR_STAND);
            stand.setArms(true);
            stand.setHelmet(stack);

            playingPlayers.add((Player) i);
            playerWithArmorStand.put((Player) i, stand);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (isStarted) {
                }
                else cancel();
            }
        }.runTaskTimer(new Main(), 0, 1);
    }


}
