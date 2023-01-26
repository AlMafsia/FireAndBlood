package net.almafsia.fireandblood.item.base;


import com.google.common.collect.ImmutableMap;
import net.almafsia.fireandblood.client.renderer.item.armor.ValyrianArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;
import java.util.function.Consumer;

public class ValyrianArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final Properties VALYRIAN_ARMOR_PROPERTIES = new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant();
    protected static String NAME = "valyrian_";

    public ValyrianArmorItem( EquipmentSlot slot) {
        super(ModArmorTiers.VALYRIAN, slot, VALYRIAN_ARMOR_PROPERTIES);
    }

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorTiers.VALYRIAN,
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 1)).build();

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
//        if(!world.isClientSide()) {
//            if(hasFullSuitOfArmorOn(player)) {
//                evaluateArmorEffects(player);
//            }
//        }
    }

//    private void evaluateArmorEffects(Player player) {
//        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
//            ArmorMaterial mapArmorMaterial = entry.getKey();
//            MobEffectInstance mapStatusEffect = entry.getValue();
//
//            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
//                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
//            }
//        }
//    }
//
//    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
//                                            MobEffectInstance mapStatusEffect) {
//        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());
//
//        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
//            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
//                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
//
//            //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
//            //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
//            //}
//        }
//    }
//
//    private boolean hasFullSuitOfArmorOn(Player player) {
//        ItemStack boots = player.getInventory().getArmor(0);
//        ItemStack leggings = player.getInventory().getArmor(1);
//        ItemStack breastplate = player.getInventory().getArmor(2);
//        ItemStack helmet = player.getInventory().getArmor(3);
//
//        return !helmet.isEmpty() && !breastplate.isEmpty()
//                && !leggings.isEmpty() && !boots.isEmpty();
//    }
//
//    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
//        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
//        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
//        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
//        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());
//
//        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
//                leggings.getMaterial() == material && boots.getMaterial() == material;
//    }


    // Create our armor model/renderer for forge and return it
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new ValyrianArmorRenderer();

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    // Let's add our animation controller
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            // Apply our generic idle animation.
            // Whether it plays or not is decided down below.
            state.setAnimation(DefaultAnimations.IDLE);
            return PlayState.STOP;
//
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
