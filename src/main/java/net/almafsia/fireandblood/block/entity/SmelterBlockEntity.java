package net.almafsia.fireandblood.block.entity;

import net.almafsia.fireandblood.item.ModItems;
import net.almafsia.fireandblood.item.base.ValyrianMetalCarrier;
import net.almafsia.fireandblood.item.base.ValyrianMetalItem;
import net.almafsia.fireandblood.screen.SmelterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SmelterBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private int metalContained = 0;
    private int maxMetalCapacity = ValyrianMetalCarrier.maxMetalsContained;
    private int additionContained = 0;
    private Supplier<Integer> maxAdditionCapacity = () -> this.metalContained;

    protected final ContainerData data;
    private int metalSmeltingProgress = 0;
    private int maxMetalSmeltingProgress = 78;
    private int additionSmeltingProgress = 0;
    private int maxAdditionSmeltingProgress = 78;



    public SmelterBlockEntity( BlockPos pos, BlockState state) {
        super(ModBlockEntities.SMELTER.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> metalContained;
                    case 1 ->maxMetalCapacity;
                    case 2 ->additionContained;
                    case 3 ->metalSmeltingProgress;
                    case 4 ->maxMetalSmeltingProgress;
                    case 5 ->additionSmeltingProgress;
                    case 6 ->maxAdditionSmeltingProgress;
                    default ->0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> metalContained=value;
                    case 1 ->maxMetalCapacity=value;
                    case 2 ->additionContained=value;
                    case 3 ->metalSmeltingProgress=value;
                    case 4 ->maxMetalSmeltingProgress=value;
                    case 5 ->additionSmeltingProgress=value;
                    case 6 ->maxAdditionSmeltingProgress=value;
                };
            }

            @Override
            public int getCount() {
                return 7;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Smelter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new SmelterMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("smelter_metal_progress", metalSmeltingProgress);
        nbt.putInt("smelter_addition_progress", additionSmeltingProgress);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        metalSmeltingProgress = nbt.getInt("smelter_metal_progress");
        additionSmeltingProgress = nbt.getInt("smelter_addition_progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i=0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, SmelterBlockEntity entity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasOriginalMetalRecipe(entity)) {
            entity.metalSmeltingProgress++;
            setChanged(level, blockPos, blockState);
            if(entity.metalSmeltingProgress>= entity.maxMetalSmeltingProgress){
                makeMetal(entity);
            }
        } else if(hasMetalRecipe(entity)) {
            entity.metalSmeltingProgress++;
            setChanged(level, blockPos, blockState);
            if(entity.metalSmeltingProgress>= entity.maxMetalSmeltingProgress){
                smeltMetal(entity);
            }
        } else {entity.resetMetalProgress();}
        if (hasAdditionRecipe(entity)) {
            entity.additionSmeltingProgress++;
            if(entity.additionSmeltingProgress>= entity.maxAdditionSmeltingProgress){
                smeltAddition(entity);
            }
        } else {entity.resetAdditionProgress();}
        setChanged(level, blockPos, blockState);
    }

    private static void makeMetal(SmelterBlockEntity entity) {
        Item metalItem = entity.itemHandler.getStackInSlot(1).getItem();
        entity.itemHandler.extractItem(1, 1, false);
        entity.itemHandler.setStackInSlot(3, new ItemStack(ValyrianMetalItem.baseMetal(metalItem)));

        entity.resetMetalProgress();
    }

    private static boolean hasOriginalMetalRecipe(SmelterBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(3).isEmpty() && ValyrianMetalCarrier.isMetal(entity.itemHandler.getStackInSlot(1).getItem());
    }

    private void resetAdditionProgress() {
        this.additionSmeltingProgress = 0;
    }

    private void resetMetalProgress() {
        this.metalSmeltingProgress = 0;
    }

    private static void smeltAddition(SmelterBlockEntity entity) {
        Item addition = entity.itemHandler.getStackInSlot(2).getItem();
        ValyrianMetalCarrier valMetalCarrier = ((ValyrianMetalItem) entity.itemHandler.getStackInSlot(3).getItem()).getCarrier();
        valMetalCarrier.addAddition(addition);
        entity.itemHandler.extractItem(2, 1, false);
        entity.itemHandler.setStackInSlot(3, new ItemStack(new ValyrianMetalItem(valMetalCarrier)));

        entity.resetMetalProgress();
    }

    private static void smeltMetal(SmelterBlockEntity entity) {
        Item metalItem = entity.itemHandler.getStackInSlot(1).getItem();
        ValyrianMetalCarrier valMetalCarrier = ((ValyrianMetalItem) entity.itemHandler.getStackInSlot(3).getItem()).getCarrier();
        valMetalCarrier.addMetal(metalItem);
        entity.itemHandler.extractItem(1, 1, false);
        entity.itemHandler.setStackInSlot(3, new ItemStack(new ValyrianMetalItem(valMetalCarrier)));

        entity.resetMetalProgress();
    }

    private static boolean hasAdditionRecipe(SmelterBlockEntity entity) {
        ValyrianMetalItem metal;

        if (entity.itemHandler.getStackInSlot(3).getItem() instanceof ValyrianMetalItem){
            metal = (ValyrianMetalItem) entity.itemHandler.getStackInSlot(3).getItem();
        } else {
            return false;
        }

        boolean hasAcceptableAdditionInSecondSlot = metal.getCarrier().canBeAdded(entity.itemHandler.getStackInSlot(2).getItem());

        return hasAcceptableAdditionInSecondSlot;
    }

    public static boolean hasMetalRecipe(SmelterBlockEntity entity){
        if (entity.itemHandler.getStackInSlot(3).getItem() instanceof ValyrianMetalItem) return ValyrianMetalCarrier.isMetal(entity.itemHandler.getStackInSlot(1).getItem()) && (((ValyrianMetalItem) entity.itemHandler.getStackInSlot(3).getItem()).getCarrier().getMetalsContained()<ValyrianMetalCarrier.maxMetalsContained);
        else return false;
    }
}
