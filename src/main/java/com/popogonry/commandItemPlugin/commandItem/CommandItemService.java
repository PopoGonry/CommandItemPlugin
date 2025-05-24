package com.popogonry.commandItemPlugin.commandItem;

import com.popogonry.commandItemPlugin.CommandItemPlugin;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class CommandItemService {
    CommandItemRepository commandItemRepository = new CommandItemRepository();

    public boolean createCommandItem(CommandItem commandItem) {
        // 동일 이름의 커맨드 아이템 존재 예외
        if (CommandItemRepository.commandItemSet.contains(commandItem.getName())) {
            return false;
        }

        CommandItemRepository.commandItemSet.add(commandItem.getName());
        CommandItemRepository.commandItemHashMap.put(commandItem.getName(), commandItem);

        commandItemRepository.saveCommandItemSet();
        commandItemRepository.saveCommandItem(commandItem.getName());

        return true;
    }

    public CommandItem getCommandItem(String commandItemName) {
        // 이름의 커맨드 아이템 미 존재 예외
        if(!CommandItemRepository.commandItemSet.contains(commandItemName)) {
            return null;
        }
        return CommandItemRepository.commandItemHashMap.get(commandItemName);
    }

    public boolean modifyCommandItem(String commandItemName, CommandItem newCommandItem) {
        // 이름의 커맨드 아이템 미 존재 예외
        if (!CommandItemRepository.commandItemSet.contains(commandItemName)) {
            return false;
        }

        // 이름 다름 예외
        if (!commandItemName.equals(newCommandItem.getName())) {
            return false;
        }
        CommandItemRepository.commandItemHashMap.put(commandItemName, newCommandItem);
        commandItemRepository.saveCommandItem(commandItemName);
        return true;
    }

    public boolean deleteCommandItem(String commandItemName) {
        // 이름의 커맨드 아이템 미 존재 예외
        if (!CommandItemRepository.commandItemSet.contains(commandItemName)) {
            return false;
        }

        CommandItemRepository.commandItemSet.remove(commandItemName);
        CommandItemRepository.commandItemHashMap.remove(commandItemName);

        commandItemRepository.saveCommandItemSet();
        commandItemRepository.removeCommandItem(commandItemName);
        return true;
    }

    public void printCommandItems(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "--- 커맨드 아이템 리스트 ---");
        sender.sendMessage(CommandItemRepository.commandItemSet.toString());
        HashMap<String, CommandItem> commandItemHashMap = CommandItemRepository.commandItemHashMap;
        for (CommandItem commandItem : commandItemHashMap.values()) {
            sender.sendMessage(commandItem.toKoreanString());
        }
    }

    public ItemStack applyCommandItemStack(ItemStack itemStack, String commandItemName) {
        ItemStack newItemStack = new ItemStack(itemStack);

        ItemMeta itemMeta = newItemStack.getItemMeta();
        NamespacedKey key = new NamespacedKey(CommandItemPlugin.getServerInstance(), "command_item");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, commandItemName);

        newItemStack.setItemMeta(itemMeta);

        return newItemStack;
    }

    public String getCommandFromItemStack(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        NamespacedKey key = new NamespacedKey(CommandItemPlugin.getServerInstance(), "command_item");
        return itemMeta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }
}
