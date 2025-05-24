package com.popogonry.commandItemPlugin.commandItem.dataConfig;

import com.popogonry.commandItemPlugin.Config;
import com.popogonry.commandItemPlugin.commandItem.CommandItem;

public class CommandItemDataConfig extends Config {
    public CommandItemDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
    }

    public void storeCommandItemData(CommandItem commandItem) {
        getConfig().set(commandItem.getName(), commandItem);
        super.store();
    }

    public CommandItem loadCommandItemData(String commandItemName) {
        return (CommandItem) getConfig().get(commandItemName);
    }

    public boolean hasCommandItemData(String commandItemName) {
        return getConfig().contains(commandItemName);
    }

    public void removeCommandItemData(String commandItemName) {
        getConfig().set(commandItemName, null);
        super.store();
    }

    @Override
    public void loadDefaults() {
    }

    @Override
    public void applySettings() {
        getConfig().options().copyDefaults(true);
    }
}
