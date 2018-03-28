package net.thegaminghuskymc.huskylib2.interf;

import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModBlock extends IVariantHolder, IVariantEnumHolder, IStateMapperProvider {

    String getBareName();

    IProperty getVariantProp();

    IProperty[] getIgnoredProperties();

    default boolean shouldDisplayVariant(int variant) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    default IStateMapper getStateMapper() {
        return null;
    }

}
