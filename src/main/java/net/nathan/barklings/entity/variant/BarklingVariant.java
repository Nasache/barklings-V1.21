package net.nathan.barklings.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BarklingVariant {
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
    SPRUCE_DMUSH_SNOW(25),

    CHERRY(26),
    CHERRY_MOSS(27),
    CHERRY_HONEY(28),

    MANGROVE(29),
    MANGROVE_MOSS(30),
    MANGROVE_BMUSH(31),
    MANGROVE_BMUSH_MOSS(32),
    MANGROVE_RMUSH(33),
    MANGROVE_RMUSH_MOSS(34),
    MANGROVE_DMUSH(35),
    MANGROVE_DMUSH_MOSS(36),

    JUNGLE(37),
    JUNGLE_MOSS(38),
    JUNGLE_VINES(39),

    ACACIA(40),
    ACACIA_MOSS(41),
    ACACIA_VINES(42),

    CRIMSON(43),
    CRIMSON_SHROOM(44),
    CRIMSON_WART(45),
    CRIMSON_WART_SHROOM(46),

    WARPED(47),
    WARPED_SHROOM(48),
    WARPED_WART(49),
    WARPED_WART_SHROOM(50),

    MUSHROOM(51),
    RED_MUSHROOM(52),
    BROWN_MUSHROOM(53),
    MUSHROOM_RMUSH(54),
    MUSHROOM_BMUSH(55),

    AZALEA(56),
    FLOWER_AZALEA(57),
    AZALEA_TREE(58),
    FLOWER_AZALEA_TREE(59);

    private static final BarklingVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            BarklingVariant::getId)).toArray(BarklingVariant[]::new);
    private final int id;


    BarklingVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BarklingVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}
