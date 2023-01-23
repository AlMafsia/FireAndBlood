package net.almafsia.fireandblood.item.base;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;

public class ValyrianSwordItem extends SwordItem {
    public static final Properties VALYRIAN_SWORD_PROPERTIES= new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant();

    public ValyrianSwordItem( int attack, float speed) {
        super(ToolTiers.VALYRIAN, attack, speed, VALYRIAN_SWORD_PROPERTIES);
    }
}
