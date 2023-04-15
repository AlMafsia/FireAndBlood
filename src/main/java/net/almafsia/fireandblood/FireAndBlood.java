package net.almafsia.fireandblood;

import com.mojang.logging.LogUtils;
import net.almafsia.fireandblood.block.ModBlocks;
import net.almafsia.fireandblood.block.entity.ModBlockEntities;
import net.almafsia.fireandblood.item.ModCreativeModeTab;
import net.almafsia.fireandblood.item.ModItems;
import net.almafsia.fireandblood.item.base.ValyrianMetalItem;
import net.almafsia.fireandblood.networking.ModMessages;
import net.almafsia.fireandblood.screen.ModMenuTypes;
import net.almafsia.fireandblood.screen.SmelterScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.example.registry.ItemRegistry;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FireAndBlood.MOD_ID)
public class FireAndBlood {
    public static final String MOD_ID = "fireandblood";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FireAndBlood() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        GeckoLib.initialize();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() ->
            {
                ItemProperties.register(ModItems.VALYRIAN_METAL.get(),
                        new ResourceLocation(FireAndBlood.MOD_ID, "color"), (stack, level, living, id) -> {
                            ValyrianMetalItem val_met = (ValyrianMetalItem) stack.getItem();
                            return val_met.getColor();
                        });
            });

            MenuScreens.register(ModMenuTypes.SMELTER_MENU.get(), SmelterScreen::new);
        }
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == ModCreativeModeTab.FORGERY_TAB){
            event.accept(ModItems.VOLCANIUM_INGOT);
            event.accept(ModItems.DARK_SISTER);

            event.accept(ModItems.VALYRIAN_HELMET);
            event.accept(ModItems.VALYRIAN_CHESTPLATE);
            event.accept(ModItems.VALYRIAN_LEGGINGS);
            event.accept(ModItems.VALYRIAN_BOOTS);

            event.accept(ModBlocks.VOLCANIUM_BLOCK);
            event.accept(ModBlocks.BLACKSTONE_VOLCANIUM_ORE);
            event.accept(ModBlocks.DEEPSLATE_VOLCANIUM_ORE);
            event.accept(ModBlocks.SMELTER);
        }
    }
}
