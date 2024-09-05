package net.nathan.forest_dwellers.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum DwellerVariant {
    PLAIN_OAK(0),
    BOTH_OAK(1),
    RED_OAK(2),
    BROWN_OAK(3),
    DARK_OAK(4);

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
