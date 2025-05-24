package com.popogonry.commandItemPlugin.commandItem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class CommandItemService {
    CommandItemRepository commandItemRepository;

    public boolean createCommandItem(CommandItem commandItem) {
        // 동일 이름의 커맨드 아이템 존재 예외
        if (CommandItemRepository.commandItemSet.contains(commandItem)) {
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
        HashMap<String, CommandItem> commandItemHashMap = CommandItemRepository.commandItemHashMap;
        for (CommandItem commandItem : commandItemHashMap.values()) {
            sender.sendMessage(commandItem.toKoreanString());
        }
    }
}
