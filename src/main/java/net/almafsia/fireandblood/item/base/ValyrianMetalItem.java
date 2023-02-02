package net.almafsia.fireandblood.item.base;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ValyrianMetalItem extends Item {
    private static Properties properties =new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant();
    private ValyrianMetalCarrier carrier;

    public ValyrianMetalItem(ValyrianMetalCarrier carrier) {
        super(properties);
        this.carrier=carrier;
    }

}
