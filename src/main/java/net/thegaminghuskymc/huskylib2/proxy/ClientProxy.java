package net.thegaminghuskymc.huskylib2.proxy;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;
import java.util.UUID;

public class ClientProxy extends CommonProxy {

    private static Map<UUID, ResourceLocation> SKIN_CACHE;

    @Override
    public ResourceLocation getAuthorSkin(UUID uuid) {
        return SKIN_CACHE.get(uuid);
    }

}