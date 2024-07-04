package it.einjojo.akani.util.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ItemBuilder {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.builder().build();
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private List<Component> lore = new LinkedList<>();

    public ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        var existing = itemMeta.lore();
        if (existing != null) {
            lore.addAll(existing);
        }
    }

    public ItemBuilder customModelData(int customModelData) {
        this.itemMeta.setCustomModelData(customModelData);
        return this;
    }

    public ItemBuilder displayNameMiniMessage(String displayNameMiniMessage) {
        this.itemMeta.displayName(MINI_MESSAGE.deserialize(displayNameMiniMessage));
        return this;
    }

    public ItemBuilder displayName(Component displayName) {
        this.itemMeta.displayName(displayName);
        return this;
    }

    public ItemBuilder loreMiniMessage(List<String> loreMiniMessage) {
        for (String current : loreMiniMessage) {
            this.lore.add(MINI_MESSAGE.deserialize(current));
        }
        return this;
    }

    public ItemBuilder lore(List<Component> lore) {
        this.lore = lore;
        return this;
    }


    public ItemBuilder addLoreMiniMessage(String minimessage) {
        this.lore.add(MINI_MESSAGE.deserialize(minimessage));
        return this;
    }


    public ItemBuilder loreMiniMessage(String... lore) {
        this.lore.clear();
        for (String current : lore) {
            this.lore.add(MINI_MESSAGE.deserialize(current));
        }
        return this;
    }

    public ItemBuilder loreAddAll(Collection<String> lore) {
        for (String current : lore) {
            this.lore.add(MINI_MESSAGE.deserialize(current));
        }
        return this;
    }


    public ItemBuilder unbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }


    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }


    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
        return this;
    }


    public <T, V> ItemBuilder persistentData(NamespacedKey key, PersistentDataType<T, V> type, V value) {
        this.itemMeta.getPersistentDataContainer().set(key, type, value);
        return this;
    }


    public ItemBuilder addEnchantment(Enchantment enchantment) {
        this.itemMeta.addEnchant(enchantment, 100, true);
        return this;
    }


    public ItemBuilder addEnchantments(Enchantment... enchantments) {
        for (Enchantment current : enchantments) {
            this.addEnchantment(current);
        }
        return this;
    }


    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        this.itemMeta.removeEnchant(enchantment);
        return this;
    }


    public ItemBuilder removeAllEnchantments() {
        for (Enchantment current : this.itemMeta.getEnchants().keySet()) {
            this.removeEnchantment(current);
        }
        return this;
    }


    public ItemStack build() {
        this.itemMeta.lore(this.lore);
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }


}
