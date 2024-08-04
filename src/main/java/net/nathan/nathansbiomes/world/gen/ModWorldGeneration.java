package net.nathan.nathansbiomes.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGeneration() {
        ModFlowerGeneration.generateFlowers();

        ModTreeGeneration.generateTrees();

        ModEntitySpawns.addSpawns();
    }
}