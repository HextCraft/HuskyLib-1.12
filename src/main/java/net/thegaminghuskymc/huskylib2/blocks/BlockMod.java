package net.thegaminghuskymc.huskylib2.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thegaminghuskymc.huskylib2.interf.IModBlock;
import net.thegaminghuskymc.huskylib2.items.blocks.ItemModBlock;
import net.thegaminghuskymc.huskylib2.utils.ProxyRegistry;

import java.util.Random;

public abstract class BlockMod extends Block implements IModBlock {

    private final String[] variants;
    private String bareName, modid;

    public BlockMod(Material material, String modid, String name, String... variants) {
        super(material);

        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;
        this.modid = modid;

        if (registerInConstruction())
            setUnlocalizedName(name);

        setHardness(1.5F);
        setResistance(30F);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    public Block setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        this.setRegistryName(getPrefix(), name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(createItemBlock(new ResourceLocation(getPrefix(), name)));
        return this;
    }

    protected ItemBlock createItemBlock(ResourceLocation res) {
        return new ItemModBlock(this, res);
    }

    public boolean registerInConstruction() {
        return true;
    }

    @Override
    public String getPrefix() {
        return this.modid;
    }

    @Override
    public String getModNamespace() {
        return this.modid;
    }

    @Override
    public String getBareName() {
        return bareName;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT_MIPPED || layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    @Override
    public IProperty getVariantProp() {
        return null;
    }

    @Override
    public Class getVariantEnum() {
        return null;
    }

}