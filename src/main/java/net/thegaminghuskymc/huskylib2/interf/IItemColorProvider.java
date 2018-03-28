package net.thegaminghuskymc.huskylib2.interf;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemColorProvider {

    @SideOnly(Side.CLIENT)
    IItemColor getItemColor();

}
