package net.almafsia.fireandblood.block.custom;

import net.almafsia.fireandblood.block.entity.ModBlockEntities;
import net.almafsia.fireandblood.block.entity.SmelterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class SmelterBlock extends BaseEntityBlock {

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

    /* BLOCK ENTITY*/

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SmelterBlockEntity) {
                ((SmelterBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof SmelterBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) player), ((SmelterBlockEntity) blockEntity), pos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SmelterBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.SMELTER.get(), SmelterBlockEntity::tick);

    }
}
