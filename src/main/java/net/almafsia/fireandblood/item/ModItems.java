package net.almafsia.fireandblood.item;

import net.almafsia.fireandblood.FireAndBlood;
import net.almafsia.fireandblood.item.base.ValyrianMetalCarrier;
import net.almafsia.fireandblood.item.base.ValyrianMetalItem;
import net.almafsia.fireandblood.item.custom.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FireAndBlood.MOD_ID);

    public static final RegistryObject<Item> VOLCANIUM_INGOT = ITEMS.register(VolcaniumIngotItem.NAME, () -> new VolcaniumIngotItem());
    public static final RegistryObject<Item> DARK_SISTER = ITEMS.register(DarkSisterItem.NAME, () -> new DarkSisterItem());

    public static final RegistryObject<ArmorItem> VALYRIAN_HELMET = ITEMS.register(ValyrianHelmetItem.NAME, () -> new ValyrianHelmetItem());
    public static final RegistryObject<ArmorItem> VALYRIAN_CHESTPLATE = ITEMS.register(ValyrianChestplateItem.NAME, () -> new ValyrianChestplateItem());
    public static final RegistryObject<ArmorItem> VALYRIAN_LEGGINGS = ITEMS.register(ValyrianLeggingsItem.NAME, () -> new ValyrianLeggingsItem());
    public static final RegistryObject<ArmorItem> VALYRIAN_BOOTS = ITEMS.register(ValyrianBootsItem.NAME, () -> new ValyrianBootsItem());

    public static final RegistryObject<Item> VALYRIAN_METAL = ITEMS.register(ValyrianMetalItem.NAME, () -> new ValyrianMetalItem(new ValyrianMetalCarrier(null)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}



