package com.joey.minemarble;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.TeamArgument;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static com.joey.minemarble.Marble.isStarted;
import static com.joey.minemarble.Marble.startLocation;

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
                .executesPlayer(MarbleCommand::playGame)
            )
            .withSubcommand(new CommandAPICommand("end")
                .executesPlayer((player, args) -> {
                    if (isStarted) isStarted = false;
                    else player.sendMessage(ChatColor.YELLOW + "Game is not started");
                })
            ).register();
    }

    public static void playGame(Player player, Object[] args) {
        if (!isStarted) {
            isStarted = true;

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (isStarted) {

                    }
                    else cancel();
                }
            }.runTaskTimer(new Main(), 0, 1);
        }
        else {
            player.sendMessage(ChatColor.YELLOW + "Game is already started");
        }
    }
}
