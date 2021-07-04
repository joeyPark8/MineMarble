package com.joey.minemarble;

import com.joey.minemarble.block.MarbleBlock;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Marble {
    public static Location startLocation = null;
    public static List<MarbleBlock> blocks = new ArrayList<>();
    public static boolean isStarted = false;
    public static List<Player> playingPlayers = new ArrayList<>();
    public static Map<Player, ArmorStand> playerWithArmorStand = new HashMap<>();
}
