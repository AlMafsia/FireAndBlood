package net.almafsia.fireandblood.block.custom;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlackstoneVolcaniumOreBlock extends DropExperienceBlock{

    @SuppressWarnings("deprecation")
    public static final Properties DEEPSLATE_VOLCANIUM_ORE_BEHAVIOUR = Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.0f)
            .sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().emissiveRendering((state, getter, pos)->{
                return false;
            }).lightLevel(state -> {
                return 0;
            });

    public BlackstoneVolcaniumOreBlock(Properties p_221083_, IntProvider p_221084_) {
        super(p_221083_, p_221084_);
    }
}
