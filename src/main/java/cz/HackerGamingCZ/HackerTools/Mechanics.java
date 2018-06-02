package cz.HackerGamingCZ.HackerTools;

import org.bukkit.ChatColor;

import java.util.Arrays;

public class Mechanics {

    private ChatColor[] colors = ChatColor.values();

    public String argConvert(String[] args, int from, int to) {
        StringBuilder m = new StringBuilder();
        String[] text = Arrays.copyOfRange(args, from, to);
        for (String part : text) {
            m.append(part).append(" ");
        }
        m = new StringBuilder(m.substring(0, m.length() - 1));
        return m.toString();
    }

    public String argConvert(String[] args, int from) {
        return argConvert(args, from, args.length);
    }

    public String color(String text) {
        if (text == null) {
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String[] color(String[] array) {
        String[] copy = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = color(array[i]);
        }
        return copy;
    }

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
