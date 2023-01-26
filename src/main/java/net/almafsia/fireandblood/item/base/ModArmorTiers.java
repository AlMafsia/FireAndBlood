package net.almafsia.fireandblood.item.base;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class ModArmorTiers {
    public static final ArmorMaterial VALYRIAN = new ModArmorMaterial(
            "valyrian",
            2000,
            new int[] { 20, 40, 50, 10},
            300,
            SoundEvents.ARMOR_EQUIP_GENERIC,
            1.0f,
            1.0f,
            ()-> Ingredient.EMPTY);
}