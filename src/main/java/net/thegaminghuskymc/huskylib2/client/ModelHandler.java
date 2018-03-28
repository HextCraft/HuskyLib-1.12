package net.thegaminghuskymc.huskylib2.client;


import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thegaminghuskymc.huskylib2.interf.*;
import net.thegaminghuskymc.huskylib2.items.ItemMod;

import java.util.HashMap;
import java.util.Objects;

@Mod.EventBusSubscriber
public final class ModelHandler {

    private static final HashMap<String, ModelResourceLocation> resourceLocations = new HashMap<>();

    @SubscribeEvent
    public static void onRegister(ModelRegistryEvent event) {
        for (IVariantHolder holder : ItemMod.variantHolders)
            registerModels(holder);
    }

    public static void init() {
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();

        for (IVariantHolder holder : ItemMod.variantHolders) {
            if (holder instanceof IItemColorProvider)
                itemColors.registerItemColorHandler(((IItemColorProvider) holder).getItemColor(), (Item) holder);

            if (holder instanceof ItemBlock && ((ItemBlock) holder).getBlock() instanceof IBlockColorProvider) {
                Block block = ((ItemBlock) holder).getBlock();
                blockColors.registerBlockColorHandler(((IBlockColorProvider) block).getBlockColor(), block);
                itemColors.registerItemColorHandler(((IBlockColorProvider) block).getItemColor(), block);
            }
        }
    }

    private static void registerModels(IVariantHolder holder) {
        String unique = holder.getUniqueModel();
        String prefix = holder.getPrefix();
        Item i = (Item) holder;

        ItemMeshDefinition def = holder.getCustomMeshDefinition();
        if (def != null)
            ModelLoader.setCustomMeshDefinition((Item) holder, def);
        else registerModels(i, prefix, holder.getVariants(), unique, false);

        if (holder instanceof IExtraVariantHolder) {
            IExtraVariantHolder extra = (IExtraVariantHolder) holder;
            registerModels(i, prefix, extra.getExtraVariants(), unique, true);
        }
    }

    private static void registerModels(Item item, String prefix, String[] variants, String uniqueVariant, boolean extra) {
        if (item instanceof ItemBlock && ((ItemBlock) item).getBlock() instanceof IModBlock) {
            IModBlock quarkBlock = (IModBlock) ((ItemBlock) item).getBlock();
            Class clazz = quarkBlock.getVariantEnum();

            IProperty variantProp = quarkBlock.getVariantProp();
            boolean ignoresVariant = false;

            IStateMapper mapper = quarkBlock.getStateMapper();
            IProperty[] ignored = quarkBlock.getIgnoredProperties();
            if (mapper != null || ignored != null && ignored.length > 0) {
                if (mapper != null)
                    ModelLoader.setCustomStateMapper((Block) quarkBlock, mapper);
                else {
                    StateMap.Builder builder = new StateMap.Builder();
                    for (IProperty p : ignored) {
                        if (p == variantProp)
                            ignoresVariant = true;
                        builder.ignore(p);
                    }

                    ModelLoader.setCustomStateMapper((Block) quarkBlock, builder.build());
                }
            }

            if (clazz != null && !ignoresVariant) {
                registerVariantsDefaulted(item, (Block) quarkBlock, clazz, variantProp.getName());
                return;
            }
        }

        for (int i = 0; i < variants.length; i++) {
            String var = variants[i];
            if (!extra && uniqueVariant != null)
                var = uniqueVariant;

            String name = prefix + ":" + var;
            ModelResourceLocation loc = new ModelResourceLocation(name, "inventory");
            if (!extra) {
                ModelLoader.setCustomModelResourceLocation(item, i, loc);
                resourceLocations.put(getKey(item, i), loc);
            } else {
                ModelBakery.registerItemVariants(item, loc);
                resourceLocations.put(variants[i], loc);
            }
        }
    }

    private static <T extends Enum<T> & IStringSerializable> void registerVariantsDefaulted(Item item, Block b, Class<T> enumclazz, String variantHeader) {
        String baseName = Objects.requireNonNull(b.getRegistryName()).toString();
        for (T e : enumclazz.getEnumConstants()) {
            String variantName = variantHeader + "=" + e.getName();
            ModelResourceLocation loc = new ModelResourceLocation(baseName, variantName);
            int i = e.ordinal();
            ModelLoader.setCustomModelResourceLocation(item, i, loc);
            resourceLocations.put(getKey(item, i), loc);
        }
    }

    private static String getKey(Item item, int meta) {
        return "i_" + item.getRegistryName() + "@" + meta;
    }

}