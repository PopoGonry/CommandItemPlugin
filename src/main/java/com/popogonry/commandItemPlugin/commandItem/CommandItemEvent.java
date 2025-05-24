package com.popogonry.commandItemPlugin.commandItem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CommandItemEvent implements Listener {

    @EventHandler
    public void onRightClickCommandItem(PlayerInteractEvent event) {
        if(!event.getAction().isRightClick()) return;
        if(event.getPlayer().getItemInHand().getType() == Material.AIR || event.getPlayer().getItemInHand() == null) return;
        CommandItemService commandItemService = new CommandItemService();

        String commandItemNameFromItemStack = commandItemService.getCommandFromItemStack(event.getPlayer().getItemInHand());

        if(commandItemNameFromItemStack == null) return;

        if(!CommandItemRepository.commandItemHashMap.containsKey(commandItemNameFromItemStack)) return;

        CommandItem commandItem = CommandItemRepository.commandItemHashMap.get(commandItemNameFromItemStack);

        if(commandItem.getCommand() == null || commandItem.getCommand().equals("")) return;



        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandItem.getCommand().replaceAll("%player%", event.getPlayer().getName()));
        event.getPlayer().getItemInHand().setAmount(event.getPlayer().getItemInHand().getAmount() - 1);
    }
}
