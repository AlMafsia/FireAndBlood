package net.almafsia.fireandblood.item.base;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ValyrianMetalItem extends Item {
    private static Properties properties =new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant();
    public static String NAME="valyrian_metal";
    private ValyrianMetalCarrier carrier;

    public ValyrianMetalItem(ValyrianMetalCarrier carrier) {
        super(properties);
        this.carrier=carrier;
    }

    public static ValyrianMetalItem baseMetal(Item metal){
        return new ValyrianMetalItem(new ValyrianMetalCarrier(metal));
    }

    public float getColor() {
        //In the future this will be a function that will tell between neutral looking, blueish, greenish and redish based on the Carrier. Those will be represented as 0.0F, 1.0F, 2.0F and 3.0F respectively.
        return ValyrianMetalCarrier.METALS_TO_COLORS.get(carrier.baseMetal);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
    }

    public ValyrianMetalCarrier getCarrier(){
        return this.carrier;
    }
}
