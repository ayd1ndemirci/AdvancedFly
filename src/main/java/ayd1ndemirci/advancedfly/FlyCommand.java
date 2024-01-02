package ayd1ndemirci.advancedfly;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Bu komutu oyun içinde kullan!");
            return true;
        }

        Player player = (Player) sender;

        if (hasPermission(player, "advancedfly.permission")) {
            if (args.length == 0) {
                toggleFlight(player);
                return true;
            } else if (args.length == 1 && player.isOp()) {
                Player targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer != null) {
                    toggleFlight(targetPlayer);
                    if (targetPlayer.getAllowFlight()) {
                        sender.sendMessage(ChatColor.GREEN + targetPlayer.getName() + " adlı oyuncunun fly'ı açıldı!");
                    } else sender.sendMessage(ChatColor.RED + targetPlayer.getName() + " adlı oyuncunun fly'ı kapatıldı!");
                } else sender.sendMessage(ChatColor.RED + "Oyuncu bulunamadı!");
                return true;
            } else sender.sendMessage(ChatColor.RED + "Komutu kullanmak için gerekli yetkiye sahip değilsiniz!");
        } else sender.sendMessage(ChatColor.RED + "Yetkin yok!");


        return false;
    }

    private boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permission) || player.isOp();
    }

    private void toggleFlight(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(ChatColor.RED + "Fly kapandı!");
        } else {
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.GREEN + "Fly açıldı!");
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (s.equalsIgnoreCase("fly") && strings.length == 1) {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        }
        return null;
    }
}
