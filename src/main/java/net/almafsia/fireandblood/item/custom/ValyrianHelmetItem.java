package net.almafsia.fireandblood.item.custom;

import net.almafsia.fireandblood.item.base.ValyrianArmorItem;
import net.minecraft.world.entity.EquipmentSlot;

public class ValyrianHelmetItem extends ValyrianArmorItem {
    public static final String NAME = ValyrianArmorItem.NAME + "helmet";

    public ValyrianHelmetItem() {
        super(EquipmentSlot.HEAD);
    }
}
