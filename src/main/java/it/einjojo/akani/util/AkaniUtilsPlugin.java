package it.einjojo.akani.util;


import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class AkaniUtilsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new InventoryAPI(this).init();
    }

    @Override
    public void onDisable() {

    }
}
