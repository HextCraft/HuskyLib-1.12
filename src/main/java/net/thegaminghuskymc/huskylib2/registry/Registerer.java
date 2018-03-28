package net.thegaminghuskymc.huskylib2.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.thegaminghuskymc.huskylib2.HuskyLib;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class Registerer {

    private static String modID;
    private static Class modItemRegistryClass, modBlockRegistryClass;

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        HuskyLib.logger.info("Searching for items to register");
        int registeredItems = 0;
        try {
            for (Field field : modItemRegistryClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(RegisterItem.class) && field.getType().isAssignableFrom(Item.class)) {
                    RegisterItem details = field.getAnnotation(RegisterItem.class);
                    Item item = (Item) field.get(null);
                    if (item == null) {
                        item = (Item) field.getType().newInstance();
                        field.set(null, item);
                    }
                    item.setRegistryName(new ResourceLocation(modID, details.registryName()));
                    if (!details.unlocalizedName().isEmpty())
                        item.setUnlocalizedName(details.unlocalizedName());
                    else
                        item.setUnlocalizedName(details.registryName());
                    event.getRegistry().register(item);
                    registeredItems++;
                }
            }

            for (Field field : modBlockRegistryClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(RegisterItem.class) && field.getType().isAssignableFrom(Block.class)) {
                    RegisterItem details = field.getAnnotation(RegisterItem.class);
                    Block block = (Block) field.get(null);
                    if (block == null) {
                        block = (Block) field.getType().newInstance();
                        field.set(null, block);
                    }

                    ItemBlock item;
                    try {
                        Class clazz = Class.forName(details.itemBlockClassName());
                        item = (ItemBlock) clazz.getDeclaredConstructor(Block.class).newInstance(block);
                    } catch (ClassNotFoundException e) {
                        HuskyLib.logger.warn("Could not register custom item block as class did not exist. " + details.itemBlockClassName());
                        continue;
                    } catch (Exception e) {
                        HuskyLib.logger.catching(e);
                        continue;
                    }

                    item.setRegistryName(new ResourceLocation(modID, details.registryName()));
                    if (!details.unlocalizedName().isEmpty())
                        item.setUnlocalizedName(details.unlocalizedName());
                    else
                        item.setUnlocalizedName(details.registryName());
                    event.getRegistry().register(item);
                    registeredItems++;
                }
            }
        } catch (Exception e) {
            HuskyLib.logger.catching(e);
        }
        HuskyLib.logger.info("Successfully registered " + registeredItems + " items!");
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        HuskyLib.logger.info("Searching for blocks to register");
        int registeredBlocks = 0;
        try {
            for (Field field : modBlockRegistryClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(RegisterBlock.class) && field.getType().isAssignableFrom(Block.class)) {
                    RegisterBlock details = field.getAnnotation(RegisterBlock.class);
                    Block block = (Block) field.get(null);
                    if (block == null) {
                        block = (Block) field.getType().newInstance();
                        field.set(null, block);
                    }
                    block.setRegistryName(new ResourceLocation(modID, details.registryName()));
                    if (!details.unlocalizedName().isEmpty())
                        block.setUnlocalizedName(details.unlocalizedName());
                    else
                        block.setUnlocalizedName(details.registryName());
                    event.getRegistry().register(block);
                    registeredBlocks++;
                }
            }
        } catch (Exception e) {
            HuskyLib.logger.catching(e);
        }
        HuskyLib.logger.info("Successfully registered " + registeredBlocks + " blocks!");
    }

    public static void setModID(String modID) {
        Registerer.modID = modID;
    }

    public static void setModBlockRegistryClass(Class modBlockRegistryClass) {
        Registerer.modBlockRegistryClass = modBlockRegistryClass;
    }

    public static void setModItemRegistryClass(Class modItemRegistryClass) {
        Registerer.modItemRegistryClass = modItemRegistryClass;
    }

}