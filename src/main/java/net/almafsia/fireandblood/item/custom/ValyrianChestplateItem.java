package net.almafsia.fireandblood.item.custom;

import net.almafsia.fireandblood.item.base.ValyrianArmorItem;
import net.minecraft.world.entity.EquipmentSlot;

public class ValyrianChestplateItem extends ValyrianArmorItem {
    public static final String NAME = ValyrianArmorItem.NAME + "chestplate";

    public ValyrianChestplateItem() {
        super(EquipmentSlot.CHEST);
    }
}
