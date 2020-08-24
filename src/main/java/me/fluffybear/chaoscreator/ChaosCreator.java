package me.fluffybear.chaoscreator;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class ChaosCreator extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }

        // Slimefun4 also already comes with a bundled version of bStats
        // You can use bStats to collect usage data about your plugin
        // More info: https://bstats.org/getting-started
        // Set bStatsId to the id of your plugin
        int bStatsId = -1;
        new Metrics(this, bStatsId);

        // Create a new Category
        // This Category will use this ItemStack
        CustomItem categoryItem = new CustomItem(Material.CRYING_OBSIDIAN, "&4Chaos Creator");

        // Give your Category a unique id.
        NamespacedKey categoryId = new NamespacedKey(this, "chaoscreator");
        Category category = new Category(categoryId, categoryItem);

        // Create a new Slimefun ItemStack
        // This class has many constructors, it is very important that you give each item a unique id.
        SlimefunItemStack slimefunItem = new SlimefunItemStack("CHAOS_SPAWNER", Material.SPAWNER, "&4Chaos Spawner", "", "&c+20% Chaos");

        // The Recipe is an ItemStack Array with a length of 9.
        // It represents a Shaped Recipe in a 3x3 crafting grid
        // The machine in which this recipe is crafted in is specified further down
        ItemStack[] recipe = { new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD), null, new ItemStack(Material.DIAMOND), null, new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD) };

        // Now you just have to register the item
        // RecipeType.ENHANCED_CRAFTING_TABLE refers to the machine in which this item is crafted in.
        // Recipy Types from Slimefun itself will automatically add the recipe to that machine
        ChaosSpawner spawner = new ChaosSpawner(category, slimefunItem, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
        spawner.register(this);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

}
