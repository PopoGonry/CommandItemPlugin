package com.popogonry.commandItemPlugin.commandItem.gui;

import com.popogonry.commandItemPlugin.Reference;
import com.popogonry.commandItemPlugin.commandItem.CommandItem;
import com.popogonry.commandItemPlugin.commandItem.CommandItemRepository;
import com.popogonry.commandItemPlugin.commandItem.CommandItemService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandItemGUIEvent implements Listener {
    @EventHandler
    public static void onClickShopListGUI(InventoryClickEvent event) {
        if(event.getView().getTitle().equalsIgnoreCase(Reference.prefix_normal + "CommandItem List GUI")
                && event.getCurrentItem() != null
                && event.getCurrentItem().getType() != Material.AIR) {

            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();

            int slot = event.getRawSlot();

            CommandItemGUI commandItemGUI = new CommandItemGUI();
            CommandItemService commandItemService = new CommandItemService();

            String[] strings1 = inventory.getItem(49).getItemMeta().getDisplayName().split("/");
            String[] strings2 = strings1[0].split(" ");
            int page = Integer.parseInt(strings2[1].replaceAll(" ", ""));

            // Item List
            if(0 <= slot && slot <= 44) {
                ItemStack itemStack = inventory.getItem(slot);
                String commandFromItemStack = commandItemService.getCommandFromItemStack(itemStack);

                if(commandFromItemStack == null) {
                    player.sendMessage(Reference.prefix_error + "지급 불가 커맨드 아이템입니다.");
                    return;
                }
                if (!CommandItemRepository.commandItemHashMap.containsKey(commandFromItemStack)) {
                    return;
                }
                CommandItem commandItem = CommandItemRepository.commandItemHashMap.get(commandFromItemStack);
                player.getInventory().addItem(new ItemStack(commandItem.getItemStack()));

            }

            else if(48 <= slot && slot <= 50) {
                ItemStack itemStack = inventory.getItem(slot);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if(itemMeta.getDisplayName().contains("To")) {
                    String[] strings = itemMeta.getDisplayName().split(" ");
                    commandItemGUI.openCommandItemGUI(player, Integer.parseInt(strings[1]));
                }
            }

            // Player Inventory
            else if(54 <= slot && slot <= 89) {

            }


        }
    }
}
