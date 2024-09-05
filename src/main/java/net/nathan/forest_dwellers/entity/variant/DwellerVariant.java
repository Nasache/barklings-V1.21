package net.nathan.forest_dwellers.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum DwellerVariant {
    OAK(0),
    OAK_MOSS(1),

    BIRCH(2),
    BIRCH_MOSS(3),
    BIRCH_SMUSH(4),
    BIRCH_SMUSH_MOSS(5),
    BIRCH_BMUSH(6),
    BIRCH_BMUSH_MOSS(7),
    BIRCH_DMUSH(8),
    BIRCH_DMUSH_MOSS(9),

    DARK_OAK(10),
    DARK_OAK_MOSS(11),
    DARK_OAK_BMUSH(12),
    DARK_OAK_BMUSH_MOSS(13),
    DARK_OAK_RMUSH(14),
    DARK_OAK_RMUSH_MOSS(15),
    DARK_OAK_DMUSH(16),
    DARK_OAK_DMUSH_MOSS(17),

    SPRUCE(18),
    SPRUCE_SNOW(19),
    SPRUCE_SMUSH(20),
    SPRUCE_SMUSH_SNOW(21),
    SPRUCE_BMUSH(22),
    SPRUCE_BMUSH_SNOW(23),
    SPRUCE_DMUSH(24),
    SPRUCE_DMUSH_SNOW(25);

    private static final DwellerVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            DwellerVariant::getId)).toArray(DwellerVariant[]::new);
    private final int id;


    DwellerVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static DwellerVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}
