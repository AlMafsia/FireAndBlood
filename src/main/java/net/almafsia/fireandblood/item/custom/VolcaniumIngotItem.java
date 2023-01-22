package net.almafsia.fireandblood.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;

public class VolcaniumIngotItem extends Item{

    public static final Properties VOLCANIUM_INGOT_PROPERTIES= new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).fireResistant();

    public VolcaniumIngotItem(Properties p_41383_) {
        super(p_41383_);
        // TODO Auto-generated constructor stub
    }

}
