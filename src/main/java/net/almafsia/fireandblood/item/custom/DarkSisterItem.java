package net.almafsia.fireandblood.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class DarkSisterItem extends SwordItem {

    public static final Properties DARK_SISTER_PROPERTIES= new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant();


    public DarkSisterItem(Tier tier, int attack, float speed, Properties properties) {
        super(tier, attack, speed, properties);
        // TODO Auto-generated constructor stub
    }


}
