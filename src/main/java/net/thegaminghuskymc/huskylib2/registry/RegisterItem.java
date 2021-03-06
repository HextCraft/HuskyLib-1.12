package net.thegaminghuskymc.huskylib2.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface RegisterItem {

    String registryName();

    String unlocalizedName() default "";

    String itemBlockClassName() default "net.minecraft.item.ItemBlock";

}