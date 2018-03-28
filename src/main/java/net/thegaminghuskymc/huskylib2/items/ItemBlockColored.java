package net.thegaminghuskymc.huskylib2.items;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.thegaminghuskymc.huskylib2.items.blocks.ItemModBlock;

public class ItemBlockColored extends ItemModBlock implements IItemColored {

    public ItemBlockColored(Block block, ResourceLocation name) {
        super(block, name);
    }

    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        return EnumDyeColor.values()[tintIndex].getColorValue();
    }

}
