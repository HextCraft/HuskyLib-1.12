package net.thegaminghuskymc.huskylib2.blocks;

import net.minecraft.block.material.Material;

public class BlockLightSource extends BlockMod {

    public BlockLightSource(String modid, String name) {
        super(Material.ROCK, modid, name);
        this.setTickRandomly(true);
        setLightLevel(1.0F);
    }

}