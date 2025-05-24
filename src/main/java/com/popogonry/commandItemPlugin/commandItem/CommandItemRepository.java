package com.popogonry.commandItemPlugin.commandItem;

import com.popogonry.commandItemPlugin.CommandItemPlugin;
import com.popogonry.commandItemPlugin.commandItem.dataConfig.CommandItemDataConfig;
import com.popogonry.commandItemPlugin.commandItem.dataConfig.CommandItemSetDataConfig;

import java.util.*;

public class CommandItemRepository {
    private static final String COMMAND_ITEM_FILE_NAME = "commandItem.yml";
    private static final String COMMAND_ITEM_SET_FILE_NAME = "commandItemSet.yml";

    private final String configBasePath;

    private final CommandItemDataConfig commandItemDataConfig;
    private final CommandItemSetDataConfig commandItemSetDataConfig;

    public final static HashMap<String, CommandItem> commandItemHashMap = new HashMap<>();
    public final static HashSet<String> commandItemSet = new HashSet<>();

    public CommandItemRepository() {
        this.configBasePath = CommandItemPlugin.getServerInstance().getDataFolder().getAbsolutePath();
        this.commandItemDataConfig = new CommandItemDataConfig(configBasePath, COMMAND_ITEM_FILE_NAME);
        this.commandItemSetDataConfig = new CommandItemSetDataConfig(configBasePath, COMMAND_ITEM_SET_FILE_NAME, "commandItemSet");
    }

    public void reloadConfig() {
        commandItemDataConfig.reload();
    }

    public void saveConfig() {
        commandItemDataConfig.store();
    }

    public boolean hasCommandItem(String commandItemName) {
        return commandItemDataConfig.hasCommandItemData(commandItemName);
    }
    public void storeCommandItem(String commandItemName) {
        commandItemDataConfig.storeCommandItemData(commandItemHashMap.get(commandItemName));
        commandItemHashMap.remove(commandItemName);
    }
    public void saveCommandItem(String commandItemName) {
        commandItemDataConfig.storeCommandItemData(commandItemHashMap.get(commandItemName));
    }
    public void loadCommandItem(String commandItemName) {
        commandItemHashMap.put(commandItemName, commandItemDataConfig.loadCommandItemData(commandItemName));
    }
    public void removeCommandItem(String commandItemName) {
        commandItemDataConfig.removeCommandItemData(commandItemName);
    }
    public void storeAllCommandItem() {
        for (String commandItemName : commandItemHashMap.keySet()) {
            storeCommandItem(commandItemName);
        }
    }
    public void saveAllCommandItem() {
        for (String commandItemName : commandItemHashMap.keySet()) {
            saveCommandItem(commandItemName);
        }
    }
    public void loadAllCommandItem() {
        for (String commandItemName : commandItemSet) {
            loadCommandItem(commandItemName);
        }
    }
    public void storeCommandItemSet() {
        commandItemSetDataConfig.storeCommandItemSet(commandItemSet);
        commandItemSet.clear();
    }
    public void saveCommandItemSet() {
        commandItemSetDataConfig.storeCommandItemSet(commandItemSet);
    }

    public void loadCommandItemSet() {
        HashSet<String> set = commandItemSetDataConfig.loadCommandItemSet();
        commandItemSet.clear();
        commandItemSet.addAll(set);
    }
    public void removeCommandItemSet() {
        commandItemSetDataConfig.removeCommandItemSet();
    }
}
