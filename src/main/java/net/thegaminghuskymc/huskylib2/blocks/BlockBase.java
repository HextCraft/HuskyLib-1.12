package net.thegaminghuskymc.huskylib2.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.thegaminghuskymc.huskylib2.utils.ProxyRegistry;

import java.util.Random;

public class BlockBase extends Block {

    public BlockBase(String modID, String name, CreativeTabs creativetab) {
        super(Material.ROCK);
        setHardness(5.0F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(creativetab);
        if (registerInConstruction()) setUnlocalizedName(modID, name);
    }

    public BlockBase(String modID, String name, CreativeTabs creativetab, Material material, MapColor mapColor) {
        super(material, mapColor);
        setHardness(5.0F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(creativetab);
        if (registerInConstruction()) setUnlocalizedName(modID, name);
    }

    public BlockBase(String modID, String name, CreativeTabs creativetab, Material material) {
        super(material, material.getMaterialMapColor());
        setHardness(5.0F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(creativetab);
        if (registerInConstruction()) setUnlocalizedName(modID, name);
    }

    public boolean registerInConstruction() {
        return true;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.CUTOUT_MIPPED || layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public String getUnlocalizedName() {
        return "tile." + getRegistryName().toString().substring(4);
    }

    public Block setUnlocalizedName(String modId, String name) {
        super.setUnlocalizedName(name);
        setRegistryName(modId, name);
        ProxyRegistry.register(this);
        ProxyRegistry.register(new ItemBlock(this).setRegistryName(new ResourceLocation(modId, name)));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        return this;
    }

}
