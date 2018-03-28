package net.thegaminghuskymc.huskylib2.blocks;

import net.minecraft.item.EnumDyeColor;

public class BlockColoredLightSource extends BlockColored {

    public BlockColoredLightSource(String modid, String name, EnumDyeColor color) {
        super(modid, name, color);
        this.setTickRandomly(true);
        setLightLevel(1.0F);
    }

}