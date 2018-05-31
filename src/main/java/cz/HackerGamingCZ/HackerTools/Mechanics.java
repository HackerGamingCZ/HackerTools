package cz.HackerGamingCZ.HackerTools;

import org.bukkit.ChatColor;

public class Mechanics {

    private ChatColor[] colors = ChatColor.values();

    public float getRealMoveSpeed(final float userSpeed) {
        final float defaultSpeed = 0.2F;
        float maxSpeed = 1f;

        if (userSpeed < 1f) {
            return defaultSpeed * userSpeed;
        } else {
            float ratio = ((userSpeed - 1) / 9) * (maxSpeed - defaultSpeed);
            return ratio + defaultSpeed;
        }
    }

    public String convertToSpigotColors(String text) {
        if (text == null) {
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String convertFromSpigotColors(String text) {
        if (text == null) {
            return null;
        }
        return text.replace("§", "&");
    }

    public ChatColor[] getColors() {
        return colors;
    }
}
