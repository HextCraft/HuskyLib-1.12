package net.thegaminghuskymc.huskylib2;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thegaminghuskymc.huskylib2.client.ModelHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.thegaminghuskymc.huskylib2.utils.Refs.*;

@Mod(modid = MODID, name = NAME, version = VERSION, acceptedMinecraftVersions = ACC_MC)
public class HuskyLib {

    public static final Logger logger = LogManager.getFormatterLogger(MODID);

    @Mod.Instance(value = MODID)
    public static HuskyLib instance = new HuskyLib();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(ModelHandler.class);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

}
