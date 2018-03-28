package net.thegaminghuskymc.huskylib2.client;

import net.minecraft.client.resources.I18n;

public class Translations {

    public static String mod_id;

    public static final String TOOLTIP_PRESS = translate(mod_id, "tooltip", "press");
    public static final String TOOLTIP_INFO = translate(mod_id, "tooltip", "info");
    public static final String TOOLTIP_SHIFT = translate(mod_id, "tooltip", "shift");

    public static final String TOOLTIP_CRAFT = translate(mod_id, "tooltip", "shift");
    public static final String TOOLTIP_CRAFT_WITH = translate(mod_id, "tooltip", "craft", "with");
    public static final String TOOLTIP_SMELT = translate(mod_id, "tooltip", "smelt");

    private static String translate(String modid, String prefix, String key) {
        mod_id = modid;
        return I18n.format(prefix + "." + modid+ "." + key + ".name");
    }

    private static String translate(String modid, String prefix, String key, String suffix) {
        mod_id = modid;
        return I18n.format(prefix + "." + modid + "." + key + "." + suffix);
    }

}