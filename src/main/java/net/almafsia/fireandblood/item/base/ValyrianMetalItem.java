package net.almafsia.fireandblood.item.base;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ValyrianMetalItem extends Item {
    private static Properties properties =new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant();
    private ValyrianMetalCarrier carrier;
    public String name="valyrian_metal";

    public ValyrianMetalItem(ValyrianMetalCarrier carrier) {
        super(properties);
        this.carrier=carrier;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
    }
}
