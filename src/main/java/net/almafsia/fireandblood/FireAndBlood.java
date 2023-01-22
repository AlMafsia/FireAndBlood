package net.almafsia.fireandblood;

import com.mojang.logging.LogUtils;
import net.almafsia.fireandblood.block.ModBlocks;
import net.almafsia.fireandblood.item.ModCreativeModeTab;
import net.almafsia.fireandblood.item.ModItems;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FireAndBlood.MOD_ID)
public class FireAndBlood {
    public static final String MOD_ID = "fireandblood";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FireAndBlood() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == ModCreativeModeTab.FORGERY_TAB){
            event.accept(ModItems.VOLCANIUM_INGOT);
            event.accept(ModItems.DARK_SISTER);

            event.accept(ModBlocks.BLOCK_OF_VOLCANIUM);
            event.accept(ModBlocks.BLACKSTONE_VOLCANIUM_ORE);
            event.accept(ModBlocks.DEEPSLATE_VOLCANIUM_ORE);
            event.accept(ModBlocks.SMELTER);
        }
    }
}
