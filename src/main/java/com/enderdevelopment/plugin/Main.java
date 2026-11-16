package com.enderdevelopment.plugin;

import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements TabExecutor {
    @Override
    public void onEnable() {
        var command = getCommand("techpack");
        if (command == null) {
            getLogger().severe("The techpack command is missing from plugin.yml.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        command.setExecutor(this);
        command.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sendOverview(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start" -> sendStarter(sender);
            case "progression" -> sendProgression(sender);
            default -> sender.sendMessage(Component.text(
                    "Usage: /" + label + " [start|progression|help]", NamedTextColor.RED));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) {
            return List.of();
        }
        String prefix = args[0].toLowerCase();
        return List.of("start", "progression", "help").stream()
                .filter(option -> option.startsWith(prefix))
                .toList();
    }

    private void sendOverview(CommandSender sender) {
        sender.sendMessage(Component.text("Technology Pack Guide", NamedTextColor.GOLD));
        sender.sendMessage(Component.text(
                "Focused on Create, Create Aeronautics, Applied Energistics 2, and layered tech progression.",
                NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Use /techpack start or /techpack progression.", NamedTextColor.YELLOW));
    }

    private void sendStarter(CommandSender sender) {
        sender.sendMessage(Component.text("Getting Started", NamedTextColor.GOLD));
        sender.sendMessage(Component.text("1. Gather basic resources and establish a safe workshop.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("2. Build early Create machines for renewable, automated production.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("3. Expand power and processing before scaling your factory.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("4. Set up AE2 storage when item variety and logistics become difficult.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("5. Explore Aeronautics after your workshop can support large builds.", NamedTextColor.GRAY));
    }

    private void sendProgression(CommandSender sender) {
        sender.sendMessage(Component.text("Suggested Progression", NamedTextColor.GOLD));
        sender.sendMessage(Component.text("Early game: resource gathering, hand tools, and compact Create automation.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Factory era: scalable processing, reliable power, and organized logistics.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Network era: AE2 storage, crafting automation, and interconnected production.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Expansion era: Create Aeronautics projects backed by mature infrastructure.", NamedTextColor.GRAY));
        sender.sendMessage(Component.text("Endgame: optimize throughput, automate complex chains, and build larger systems.", NamedTextColor.GRAY));
    }
}
