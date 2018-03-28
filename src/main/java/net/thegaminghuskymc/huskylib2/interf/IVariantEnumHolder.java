package net.thegaminghuskymc.huskylib2.interf;

import net.minecraft.util.IStringSerializable;

public interface IVariantEnumHolder<T extends Enum<T> & IStringSerializable> {

    Class<T> getVariantEnum();

}
