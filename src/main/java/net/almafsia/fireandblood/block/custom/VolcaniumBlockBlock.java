package net.almafsia.fireandblood.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class VolcaniumBlockBlock extends Block{

    public static final IntegerProperty LIGHT_LEVEL=IntegerProperty.create("light_level", 8, 12);

    public VolcaniumBlockBlock(Properties p_49795_) {
        super(p_49795_);

        // TODO Auto-generated constructor stub
    }

    private static boolean LIGHT_MOVING_UP=true;

    public static final BlockBehaviour.Properties VOLCANIUM_BLOCK_BEHAVIOUR = BlockBehaviour.Properties.of(Material.LAVA, MaterialColor.COLOR_ORANGE).strength(3.0f)
            .sound(SoundType.AMETHYST).requiresCorrectToolForDrops().emissiveRendering((state, getter, pos)->{
                return true;
            }).lightLevel(state -> {
                return getLightLevel(state);
            });

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource source) {
        // TODO Auto-generated method stub
        super.animateTick(state, level, pos, source);
        if(state.getLightEmission(level, pos)==12)LIGHT_MOVING_UP=false;
        else if(state.getLightEmission(level, pos)==8)LIGHT_MOVING_UP=true;

        int light;
        if(LIGHT_MOVING_UP) light = state.getLightEmission(level, pos)+1;
        else light = state.getLightEmission(level, pos)-1;

        level.setBlock(pos, state.setValue(LIGHT_LEVEL, Integer.valueOf(light)), 3);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
        // TODO Auto-generated method stub
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(LIGHT_LEVEL);
    }

    public static int getLightLevel(BlockState state) {
        return state.getValue(LIGHT_LEVEL);
    }
}