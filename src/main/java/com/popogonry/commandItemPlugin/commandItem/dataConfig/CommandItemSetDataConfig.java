package com.popogonry.commandItemPlugin.commandItem.dataConfig;

import com.popogonry.commandItemPlugin.Config;

import java.util.ArrayList;
import java.util.HashSet;

public class CommandItemSetDataConfig extends Config {

    private final String saveName;

    public CommandItemSetDataConfig(String basePath, String fileName, String saveName) {
        super(basePath, fileName);
        this.saveName = saveName;
        loadDefaults();
    }

    public void storeCommandItemSet(HashSet<String> commandItemSet) {
        getConfig().set(saveName, new ArrayList<>(commandItemSet));
        super.store();
    }

    public HashSet<String> loadCommandItemSet() {
        return new HashSet<>(getConfig().getStringList(saveName));
    }

    public boolean hasCommandItemSet() {
        return getConfig().contains(saveName);
    }

    public void removeCommandItemSet() {
        getConfig().set(saveName, null);
    }

    @Override
    public void loadDefaults() {
    }

    @Override
    public void applySettings() {
        getConfig().options().copyDefaults(true);
    }

}
