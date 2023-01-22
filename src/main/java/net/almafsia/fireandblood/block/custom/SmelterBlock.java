package net.almafsia.fireandblood.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class SmelterBlock extends Block{

    public static final BooleanProperty FUELED = BooleanProperty.create("fueled");
    public static final BooleanProperty CONTAINS_FLUID=BooleanProperty.create("contains_fuild");
    public static final DirectionProperty FACING=HorizontalDirectionalBlock.FACING;

//	public static final EnumProperty<MetalColor> METAL_COLOR = EnumProperty.create("metal_color", MetalColor.class);

    public static final BlockBehaviour.Properties SMELTER_BEHAVIOUR = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).strength(3.0f)
            .sound(SoundType.METAL).requiresCorrectToolForDrops().emissiveRendering((state, getter, pos) -> {return true;}).lightLevel(state->{return 4;}).dynamicShape();

    public SmelterBlock(Properties properties) {
        super(properties);
        // TODO Auto-generated constructor stub
        this.registerDefaultState(this.defaultBlockState().setValue(FUELED, false).setValue(CONTAINS_FLUID, false).setValue(FACING, Direction.NORTH));
//				.setValue(METAL_COLOR, MetalColor.NONE)


    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FUELED, CONTAINS_FLUID, FACING);
        super.createBlockStateDefinition(pBuilder);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

}
