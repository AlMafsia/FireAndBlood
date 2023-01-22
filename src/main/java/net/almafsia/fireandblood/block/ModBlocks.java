package net.almafsia.fireandblood.block;

import net.almafsia.fireandblood.FireAndBlood;
import net.almafsia.fireandblood.block.custom.BlackstoneVolcaniumOreBlock;
import net.almafsia.fireandblood.block.custom.BlockOfVolcaniumBlock;
import net.almafsia.fireandblood.block.custom.DeepslateVolcaniumOreBlock;
import net.almafsia.fireandblood.block.custom.SmelterBlock;
import net.almafsia.fireandblood.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FireAndBlood.MOD_ID);


    public static final RegistryObject<Block> BLOCK_OF_VOLCANIUM = registerBlock("block_of_volcanium",
            () -> new BlockOfVolcaniumBlock(BlockOfVolcaniumBlock.VOLCANIUM_BLOCK_BEHAVIOUR));

    public static final RegistryObject<Block> DEEPSLATE_VOLCANIUM_ORE = registerBlock("deepslate_volcanium_ore",
            () -> new DeepslateVolcaniumOreBlock(DeepslateVolcaniumOreBlock.DEEPSLATE_VOLCANIUM_ORE_BEHAVIOUR, UniformInt.of(3, 7)));

    public static final RegistryObject<Block> BLACKSTONE_VOLCANIUM_ORE = registerBlock("blackstone_volcanium_ore",
            () -> new BlackstoneVolcaniumOreBlock(BlackstoneVolcaniumOreBlock.DEEPSLATE_VOLCANIUM_ORE_BEHAVIOUR, UniformInt.of(3, 7)));

    public static final RegistryObject<Block> SMELTER = registerBlock("smelter",
            () -> new SmelterBlock(SmelterBlock.SMELTER_BEHAVIOUR));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
