package net.almafsia.fireandblood.item.custom;

import net.almafsia.fireandblood.item.base.ValyrianArmorItem;
import net.minecraft.world.entity.EquipmentSlot;

public class ValyrianLeggingsItem extends ValyrianArmorItem {
    public static final String NAME = ValyrianArmorItem.NAME + "leggings";

    public ValyrianLeggingsItem() {
        super(EquipmentSlot.LEGS);
    }
}
