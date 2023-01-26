package net.almafsia.fireandblood.client.renderer.item.armor;

import net.almafsia.fireandblood.FireAndBlood;
import net.almafsia.fireandblood.item.base.ValyrianArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class ValyrianArmorRenderer extends GeoArmorRenderer<ValyrianArmorItem> {
    public ValyrianArmorRenderer () {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(FireAndBlood.MOD_ID, "armor/valyrian_armor")));

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}
