package xyz.clxrity.simplehealing.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.clxrity.simplehealing.cooldown.CooldownManager;

import java.util.concurrent.TimeUnit;

public class FeedCommand implements CommandExecutor {


    private final CooldownManager cooldownManager = new CooldownManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId());
            if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= CooldownManager.DEFAULT_COOLDOWN) {
                cooldownManager.setCooldown(player.getUniqueId(), System.currentTimeMillis());

                if(args.length == 0){
                    if(player.hasPermission("SimpleHealing.feed")){
                        player.setFoodLevel(20);
                        player.setSaturation(20);
                        player.sendMessage(ChatColor.GREEN + "Your hunger has been satisfied!");
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have permission to use this!");
                    }
                } else {
                    if(player.hasPermission("SimpleHealing.feed")) {
                        String targetString = args[0];
                        Player target = Bukkit.getServer().getPlayerExact(targetString);
                        target.setFoodLevel(20);
                        target.setSaturation(20);
                        target.sendMessage(ChatColor.GREEN + "Someone fed you!");
                        player.sendMessage(ChatColor.DARK_AQUA + targetString + ChatColor.GREEN + " has been fed!");
                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have permission to use this!");
                    }
                }
            } else {
                long playerTimeLeftLong = CooldownManager.DEFAULT_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft);
                player.sendMessage(ChatColor.DARK_RED.toString() + playerTimeLeftLong + ChatColor.RED + " seconds until you may use this command again!");
            }
        } else {
            if(args.length == 0){
                System.out.println("You have to specify who you'd like to feed!");
            } else {
                String targetString = args[0];
                Player target = Bukkit.getServer().getPlayerExact(targetString);
                target.setFoodLevel(20);
                target.setSaturation(20);
                target.sendMessage(ChatColor.GREEN + "Someone fed you!");
                System.out.println(targetString + " has been fed!");
            }
        }
        return true;
    }
}
