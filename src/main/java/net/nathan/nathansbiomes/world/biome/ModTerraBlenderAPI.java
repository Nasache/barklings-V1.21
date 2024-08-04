package net.nathan.nathansbiomes.world.biome;

import net.minecraft.util.Identifier;
import net.nathan.nathansbiomes.NathansBiomes;
import net.nathan.nathansbiomes.world.biome.surface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModCommonBiomes(Identifier.of(NathansBiomes.MOD_ID, "common"), 8));
        Regions.register(new ModRareBiomes(Identifier.of(NathansBiomes.MOD_ID, "rare"), 1));


        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, NathansBiomes.MOD_ID, ModMaterialRules.makeRules());
    }
}
