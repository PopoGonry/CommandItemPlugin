package com.popogonry.commandItemPlugin;

import com.popogonry.commandItemPlugin.commandItem.CommandItem;
import com.popogonry.commandItemPlugin.commandItem.CommandItemCommand;
import com.popogonry.commandItemPlugin.commandItem.CommandItemRepository;
import com.popogonry.commandItemPlugin.commandItem.CommandItemService;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandItemPlugin extends JavaPlugin {
    private static CommandItemPlugin serverInstance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        serverInstance = this;

        ConfigurationSerialization.registerClass(CommandItem.class);

        getServer().getPluginCommand("ci").setExecutor(new CommandItemCommand());


        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "CommandItem Data Load Start...");
        CommandItemRepository commandItemRepository = new CommandItemRepository();

        commandItemRepository.loadCommandItemSet();
        commandItemRepository.loadAllCommandItem();

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "CommandItem Data Load Complete!");


        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "CommandItem Plugin Enabled (Developer: PopoGonry)");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "CommandItem Data Save Start...");
        CommandItemRepository commandItemRepository = new CommandItemRepository();

        commandItemRepository.saveAllCommandItem();
        commandItemRepository.saveCommandItemSet();

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "CommandItem Data Save Complete!");

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "CommandItem Plugin Disabled (Developer: PopoGonry)");
    }

    public static CommandItemPlugin getServerInstance() {
        return serverInstance;
    }

}
