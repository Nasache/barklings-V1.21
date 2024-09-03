package net.nathan.forest_dwellers.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGeneration() {
        ModFlowerGeneration.generateFlowers();

        ModTreeGeneration.generateTrees();

        ModEntitySpawns.addSpawns();
    }
}