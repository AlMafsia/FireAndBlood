package net.almafsia.fireandblood.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;

public class VolcaniumIngotItem extends Item{
    public static final Properties VOLCANIUM_INGOT_PROPERTIES= new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).fireResistant();
    public static final String NAME = "volcanium_ingot";

    public VolcaniumIngotItem() {
        super(VOLCANIUM_INGOT_PROPERTIES);
        // TODO Auto-generated constructor stub
    }

}
