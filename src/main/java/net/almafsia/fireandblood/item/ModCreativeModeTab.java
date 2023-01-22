package net.almafsia.fireandblood.item;

import net.almafsia.fireandblood.FireAndBlood;
import net.almafsia.fireandblood.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FireAndBlood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab FORGERY_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        FORGERY_TAB = event.registerCreativeModeTab(new ResourceLocation(FireAndBlood.MOD_ID, "forgery_tab"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.SMELTER.get())).title(Component.literal("Forgery Tab")).build());
    }
}
