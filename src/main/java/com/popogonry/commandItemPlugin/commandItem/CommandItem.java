package com.popogonry.commandItemPlugin.commandItem;


import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class CommandItem implements ConfigurationSerializable {
    private String name;
    private String command;
    private ItemStack itemStack;

    public CommandItem(String name, String command, ItemStack itemStack) {
        this.name = name;
        this.command = command;
        this.itemStack = itemStack;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", name);
        map.put("command", command);
        map.put("itemStack", itemStack);

        return map;
    }

    public static CommandItem deserialize(Map<String, Object> map) {
        return new CommandItem(
                (String) map.get("name"),
                (String) map.get("command"),
                (ItemStack) map.get("itemStack")

        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public String toString() {
        return "CommandItem{" +
                "name='" + name + '\'' +
                ", command='" + command + '\'' +
                ", itemStack=" + itemStack +
                '}';
    }
    public String toKoreanString() {
        return "커맨드아이템 {" +
                "이름: " + name + '\'' +
                ", 커맨드: " + command + '\'' +
                ", 아이템스택: " + itemStack +
                '}';
    }
}
