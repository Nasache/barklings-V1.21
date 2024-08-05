package net.nathan.nathansbiomes.util;

import net.minecraft.state.property.IntProperty;

public class ModProperties {

    public static final int DISTANCE_1_10_MAX = 10;
    public static final IntProperty DISTANCE_1_10;

    static {
        DISTANCE_1_10 = IntProperty.of("distance", 1, DISTANCE_1_10_MAX);
    }


    private ModProperties() {
    }
}
