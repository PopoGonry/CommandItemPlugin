package com.popogonry.commandItemPlugin.commandItem;

import com.popogonry.commandItemPlugin.Config;

public class CommandItemDataConfig extends Config {
    public CommandItemDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
    }

    public void storeCommandItemData(CommandItem commandItem) {
        getConfig().set("Name", killEffect.getName());
        getConfig().set("MysticMob-Name", killEffect.getMysticmobName());
        getConfig().set("Lore", killEffect.getLore());
        getConfig().set("Cooldown", killEffect.getCooldown());
        getConfig().set("ActiveType List", "( MOB / PLAYER / ALL )");
        getConfig().set("Active-Type", killEffect.getActiveType().toString());

        getConfig().set("Name", commandItem.getName());

        super.store();
    }

    public CommandItem loadCommandItemData() {
        return new KillEffect(
                getConfig().getString("Name"),
                getConfig().getString("MysticMob-Name"),
                getConfig().getString("Lore"),
                (Double) getConfig().get("Cooldown"),
                KillEffectActiveType.valueOf(getConfig().getString("Active-Type"))
        );
    }

    @Override
    public void loadDefaults() {
    }

    @Override
    public void applySettings() {
        getConfig().options().copyDefaults(true);
    }




}
