package net.almafsia.fireandblood.item;

import net.almafsia.fireandblood.FireAndBlood;
import net.almafsia.fireandblood.item.custom.DarkSisterItem;
import net.almafsia.fireandblood.item.custom.VolcaniumIngotItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FireAndBlood.MOD_ID);

    public static final RegistryObject<Item> VOLCANIUM_INGOT = ITEMS.register("volcanium_ingot", () -> new VolcaniumIngotItem(VolcaniumIngotItem.VOLCANIUM_INGOT_PROPERTIES));
    public static final RegistryObject<Item> DARK_SISTER = ITEMS.register("dark_sister", () -> new DarkSisterItem(Tiers.VALYRIAN, 5, 1.0f, DarkSisterItem.DARK_SISTER_PROPERTIES));





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

class Tiers {

    public static final Tier VALYRIAN = new ForgeTier(4, 2517, 10.0f, 4.0f, 30, null, ()-> Ingredient.EMPTY);

}
