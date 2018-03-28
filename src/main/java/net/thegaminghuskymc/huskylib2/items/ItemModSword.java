package net.thegaminghuskymc.huskylib2.items;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.thegaminghuskymc.huskylib2.interf.IVariantHolder;
import net.thegaminghuskymc.huskylib2.utils.ProxyRegistry;

public abstract class ItemModSword extends ItemSword implements IVariantHolder {

    private final String[] variants;
    private final String bareName, modid;

    protected ItemModSword(String name, String modid, ToolMaterial material, String... variants) {
        super(material);
        if (variants.length > 1)
            setHasSubtypes(true);

        if (variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;
        this.modid = modid;
        setUnlocalizedName(name);
        ItemMod.variantHolders.add(this);
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
    public Item setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(getPrefix() + name));
        ProxyRegistry.register(this);

        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int dmg = par1ItemStack.getItemDamage();
        String[] variants = getVariants();

        String name;
        if (dmg >= variants.length)
            name = bareName;
        else name = variants[dmg];

        return "item." + getPrefix() + name;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }

}
