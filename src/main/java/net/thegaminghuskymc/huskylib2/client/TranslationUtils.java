package net.thegaminghuskymc.huskylib2.client;

import net.minecraft.client.resources.I18n;

public class TranslationUtils {

    public static String translate(String modid, String prefix, String key) {
        return I18n.format(prefix + "." + modid + "." + key + ".name");
    }

}
