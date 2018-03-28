package net.thegaminghuskymc.huskylib2.json_generation;

import java.util.List;

public interface ICustomJsonGenerationBlock {

    String getBlockStateText();

    String getBlockModelText(int meta);

    String getFileNameFromMeta(int meta);

    void getMetas(List<Integer> list);

    String getBlockModelForItem();
}