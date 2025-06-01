package com.popogonry.commandItemPlugin.commandItem;

import com.popogonry.commandItemPlugin.Reference;
import com.popogonry.commandItemPlugin.commandItem.gui.CommandItemGUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;

public class CommandItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!commandSender.isOp()) return false;

        CommandItemService commandItemService = new CommandItemService();

        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("list")) {
                commandItemService.printCommandItems(commandSender);
            } else if (strings[0].equalsIgnoreCase("gui")) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(Reference.prefix_error + "플레이어 전용 명령어입니다.");
                    return false;
                }
                Player player = (Player) commandSender;

                CommandItemGUI commandItemGUI = new CommandItemGUI();
                commandItemGUI.openCommandItemGUI(player, 1);
            } else if (strings[0].equalsIgnoreCase("check")) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(Reference.prefix_error + "플레이어 전용 명령어입니다.");
                    return false;
                }
                Player player = (Player) commandSender;

                if (player.getItemInHand().getType() == Material.AIR || player.getItemInHand() == null) {
                    player.sendMessage("들고있는 아이템이 없습니다.");
                    return false;
                }

                String commandFromItemStack = commandItemService.getCommandFromItemStack(player.getItemInHand());

                player.sendMessage(Reference.prefix_opMessage + commandFromItemStack + " : 입니다.");
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
                if (commandItemService.createCommandItem(new CommandItem(strings[1], Collections.emptyList(), null))) {
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
            else if (strings[0].equalsIgnoreCase("set")) {
                if(!(commandSender instanceof Player)) {
                    commandSender.sendMessage(Reference.prefix_error + "플레이어 전용 명령어입니다.");
                    return false;
                }
                Player player = (Player) commandSender;

                if(player.getItemInHand().getType() == Material.AIR || player.getItemInHand() == null) {
                    player.sendMessage("들고있는 아이템이 없습니다.");
                    return false;
                }
                ItemStack itemStack = commandItemService.applyCommandItemStack(player.getItemInHand(), strings[1]);
                player.setItemInHand(new ItemStack(itemStack));

                CommandItem commandItem = CommandItemRepository.commandItemHashMap.get(strings[1]);
                commandItem.setItemStack(itemStack);
                commandItemService.modifyCommandItem(strings[1], commandItem);


                player.sendMessage(Reference.prefix_opMessage + "적용 되었습니다.");
            }
        }
        else if(strings.length >= 3 && strings[0].equalsIgnoreCase("command")) {
            if(!CommandItemRepository.commandItemHashMap.containsKey(strings[1])) {
                return false;
            }
            String cmd = String.join(" ", Arrays.copyOfRange(strings, 2, strings.length));
            CommandItem commandItem = CommandItemRepository.commandItemHashMap.get(strings[1]);

            commandItem.getCommandList().add(cmd);

            commandSender.sendMessage(commandItem.getCommandList().toString());

            commandItemService.modifyCommandItem(strings[1], commandItem);

        }

        return false;
    }
}
