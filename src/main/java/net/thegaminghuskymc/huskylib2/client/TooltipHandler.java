package net.thegaminghuskymc.huskylib2.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


public final class TooltipHandler {

    public TooltipHandler() {
    }

    @SideOnly(Side.CLIENT)
    public static void tooltipIfShift(String modid, List<String> tooltip, Runnable r) {
        if (GuiScreen.isShiftKeyDown()) {
            r.run();
        } else {
            addToTooltip(tooltip, modid + ".misc.shiftForInfo");
        }

    }

    @SideOnly(Side.CLIENT)
    private static void addToTooltip(List<String> tooltip, String s, Object... format) {
        s = local(s).replaceAll("&", "§");
        Object[] formatVals = new String[format.length];

        for (int i = 0; i < format.length; ++i) {
            formatVals[i] = local(format[i].toString()).replaceAll("&", "§");
        }

        if (formatVals.length > 0) {
            s = String.format(s, formatVals);
        }

        tooltip.add(s);
    }

    @SideOnly(Side.CLIENT)
    private static String local(String s) {
        return I18n.translateToLocal(s);
    }
}
