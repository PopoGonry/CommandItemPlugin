package com.popogonry.commandItemPlugin.commandItem;


import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommandItem implements ConfigurationSerializable {
    private String name;
    private List<String> commandList;
    private ItemStack itemStack;

    public CommandItem(String name, List<String> commandList, ItemStack itemStack) {
        this.name = name;
        this.commandList = new ArrayList<>(commandList);
        this.itemStack = itemStack;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", name);
        map.put("commandList", commandList);
        map.put("itemStack", itemStack);

        return map;
    }

    public static CommandItem deserialize(Map<String, Object> map) {
        return new CommandItem(
                (String) map.get("name"),
                (List<String>) map.get("commandList"),
                (ItemStack) map.get("itemStack")

        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<String> commandList) {
        this.commandList = new ArrayList<>(commandList);
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
                ", commandList=" + commandList +
                ", itemStack=" + itemStack +
                '}';
    }

    public String toKoreanString() {
        return "커맨드아이템 {" +
                "이름: " + name + '\'' +
                ", 커맨드: " + commandList + '\'' +
                ", 아이템스택: " + itemStack +
                '}';
    }
}
