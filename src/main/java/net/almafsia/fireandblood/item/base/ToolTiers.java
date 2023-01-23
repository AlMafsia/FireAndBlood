package net.almafsia.fireandblood.item.base;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ToolTiers {
    public static final Tier VALYRIAN = new ForgeTier(
            4,
            2517,
            10.0f,
            4.0f,
            30,
            null,
            ()-> Ingredient.EMPTY);
}