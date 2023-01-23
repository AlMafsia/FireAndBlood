package net.almafsia.fireandblood.item.base;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ValyrianArmorItem extends ArmorItem {
    public static final Properties VALYRIAN_ARMOR_PROPERTIES = new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant();
    protected static String NAME = "valyrian_";

    public ValyrianArmorItem( EquipmentSlot slot) {
        super(ArmorTiers.VALYRIAN, slot, VALYRIAN_ARMOR_PROPERTIES);
    }
}
