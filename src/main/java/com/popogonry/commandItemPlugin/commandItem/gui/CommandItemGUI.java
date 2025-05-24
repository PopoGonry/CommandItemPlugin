package com.popogonry.commandItemPlugin.commandItem.gui;

import com.popogonry.commandItemPlugin.GUI;
import com.popogonry.commandItemPlugin.Reference;
import com.popogonry.commandItemPlugin.commandItem.CommandItem;
import com.popogonry.commandItemPlugin.commandItem.CommandItemRepository;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandItemGUI {
    public boolean openCommandItemGUI(Player player, int page) {
        Inventory inventory = Bukkit.createInventory(player, 54, Reference.prefix_normal + "CommandItem List GUI");

        ArrayList<String> commandItemNameList = new ArrayList<>(CommandItemRepository.commandItemSet);
        Collections.sort(commandItemNameList);

        int continueNumber = 0;

        for (int i = 0 + (45*(page-1)); i < 45 + (45*(page-1)) && i < commandItemNameList.size(); i++) {
            CommandItem commandItem = CommandItemRepository.commandItemHashMap.get(commandItemNameList.get(i));
            if (commandItem == null) {
                continueNumber++;
                continue;
            }

            ItemStack itemStack;
            if (commandItem.getItemStack() == null) {
                itemStack = new ItemStack(Material.BOOK);
            }
            else {
                itemStack = new ItemStack(commandItem.getItemStack());
            }
            ItemMeta itemMeta = itemStack.getItemMeta();

            List<String> lore = new ArrayList<>();

            lore.add(ChatColor.GOLD + "이름: " + commandItem.getName());
            lore.add(ChatColor.GOLD + "명령어: " + commandItem.getCommand());

            lore.add(ChatColor.WHITE + "---------------------");
            lore.add(ChatColor.GOLD + "- 좌클릭: 커맨드 아이템 지급");

            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(itemStack);
        }


        // 48 49 50
        int maxPage = commandItemNameList.size() / 45;
        maxPage += commandItemNameList.size() % 45 == 0 ? 0 : 1;

        inventory.setItem(49, GUI.getCustomItemStack(Material.EMERALD, Reference.prefix + "Page " + page + " / " + maxPage, Collections.singletonList(ChatColor.GOLD + "Amount of CommandItem: " + (commandItemNameList.size() - continueNumber))));

        if(page > 1) {
            inventory.setItem(48, GUI.getCustomItemStack(Material.PAPER, Reference.prefix + "To " + (page - 1)));
        }

        if(page < maxPage) {
            inventory.setItem(50, GUI.getCustomItemStack(Material.PAPER, Reference.prefix + "To " + (page + 1)));
        }

        player.openInventory(inventory);

        return true;
    }
}
