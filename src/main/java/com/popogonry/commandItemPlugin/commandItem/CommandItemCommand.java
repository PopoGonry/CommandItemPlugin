package com.popogonry.commandItemPlugin.commandItem;

import com.popogonry.commandItemPlugin.Reference;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!commandSender.isOp()) return false;

        CommandItemService commandItemService = new CommandItemService();

        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("list")) {
                commandItemService.printCommandItems(commandSender);
            }
//            else if (strings[0].equalsIgnoreCase("storeAll")) {
//                killEffectRepository.storeKillEffectSet();
//                killEffectRepository.storeAllKillEffects();
//                commandSender.sendMessage(Reference.prefix_opMessage + "Store Complete.");
//            } else if (strings[0].equalsIgnoreCase("loadAll")) {
//                killEffectRepository.loadKillEffectSet();
//                killEffectRepository.loadAllKillEffects();
//                commandSender.sendMessage(Reference.prefix_opMessage + "Load Complete.");
//
//
//            } else if (strings[0].equalsIgnoreCase("saveAll")) {
//                killEffectRepository.saveKillEffectSet();
//                killEffectRepository.saveAllKillEffects();
//                commandSender.sendMessage(Reference.prefix_opMessage + "Save Complete.");
//
//            } else if (strings[0].equalsIgnoreCase("test")) {
//            }
        }
        else if(strings.length == 2) {
            if (strings[0].equalsIgnoreCase("create")) {
                if (commandItemService.createCommandItem(new CommandItem(strings[1], "", null))) {
                    commandSender.sendMessage(Reference.prefix_opMessage + "생성 되었습니다.");
                }
                else {
                    commandSender.sendMessage(Reference.prefix_error + "생성 실패하였습니다.");
                }
            }
            else if (strings[0].equalsIgnoreCase("delete")) {
                if (commandItemService.deleteCommandItem(strings[1])) {
                    commandSender.sendMessage(Reference.prefix_opMessage + "삭제 되었습니다.");
                }
                else {
                    commandSender.sendMessage(Reference.prefix_error + "삭제 실패하였습니다.");
                }
            }
        }

        return false;
    }
}
