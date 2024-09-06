package net.nathan.forest_dwellers.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent CHERRIES = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent BANANA = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent ORANGE = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent POMEGRANATE = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent PEAR = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent PLUM = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent STARFRUIT = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();

    public static final FoodComponent STRAWBERRY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();

    public static final FoodComponent LEEK = new FoodComponent.Builder().nutrition(2).saturationModifier(4f).build();
    public static final FoodComponent ROASTED_LEEK = new FoodComponent.Builder().nutrition(6).saturationModifier(6.5f).build();

    public static final FoodComponent TOMATO = new FoodComponent.Builder().nutrition(4).saturationModifier(0.1f).snack().build();
    public static final FoodComponent LETTUCE_LEAF = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2f).snack().build();


}
