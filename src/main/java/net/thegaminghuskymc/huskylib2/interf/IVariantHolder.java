package net.thegaminghuskymc.huskylib2.interf;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IVariantHolder {

    String[] getVariants();

    @SideOnly(Side.CLIENT)
    ItemMeshDefinition getCustomMeshDefinition();

    default String getUniqueModel() {
        return null;
    }

    String getModNamespace();

    String getPrefix();

}
