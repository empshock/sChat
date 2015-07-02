package me.empshock.schat;

import java.io.PrintStream;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
        extends JavaPlugin
        implements Listener
{
    Main plugin;
    private FileConfiguration config;

    public void onDisable()
    {
        System.out.println("[sChat] Disabled.");
    }

    public void onEnable()
    {
        this.config = getConfig();
        this.config.options().copyDefaults(true);
        System.out.println("[sChat] Enabled.");
        saveConfig();
        this.plugin = this;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("schat")) if (p.hasPermission("schat.talk")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Correct usage: /schat <message>");
                return true;
            }
            String message = "";
            String prefix = this.plugin.getConfig().getString("Prefix");
            for (int i1 = 0; i1 < args.length; i1++) {
                message = message + args[i1] + " ";
            }
            Player[] arrayOfPlayer;
            for (Player t : Bukkit.getOnlinePlayers()) {
                if (t.hasPermission("schat.talk")) {
                    message = ChatColor.translateAlternateColorCodes('&', message);
                    t.sendMessage(prefix + p.getName() + message);
                }
            }
        } else {
            p.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
            return true;
        }
        return false;
    }
}


