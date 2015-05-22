package pl.shg.commons.util;

import java.util.Random;
import net.md_5.bungee.api.ChatColor;

/**
 *
 * @author Filip
 */
public class Colors {
    private static final Random random = new Random();
    private static final String[] colors = new String[] {
        ChatColor.translateAlternateColorCodes('&', "&a"),
        ChatColor.translateAlternateColorCodes('&', "&b"),
        ChatColor.translateAlternateColorCodes('&', "&c"),
        ChatColor.translateAlternateColorCodes('&', "&d"),
        ChatColor.translateAlternateColorCodes('&', "&e"),
        ChatColor.translateAlternateColorCodes('&', "&f"),
        ChatColor.translateAlternateColorCodes('&', "&0"),
        ChatColor.translateAlternateColorCodes('&', "&1"),
        ChatColor.translateAlternateColorCodes('&', "&2"),
        ChatColor.translateAlternateColorCodes('&', "&3"),
        ChatColor.translateAlternateColorCodes('&', "&4"),
        ChatColor.translateAlternateColorCodes('&', "&5"),
        ChatColor.translateAlternateColorCodes('&', "&6"),
        ChatColor.translateAlternateColorCodes('&', "&7"),
        ChatColor.translateAlternateColorCodes('&', "&8"),
        ChatColor.translateAlternateColorCodes('&', "&9")
    };
    
    public static String random(int length) {
        String name = "";
        for (int i = 0; i < length; i++) {
            name += colors[random.nextInt(colors.length)];
        }
        return name;
    }
}
