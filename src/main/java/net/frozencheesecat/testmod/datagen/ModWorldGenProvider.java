package net.frozencheesecat.testmod.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.worldgen.ModConfiguredFeatures;
import net.frozencheesecat.testmod.worldgen.ModPlacedFeatures;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.CONFIGURED_FEATURE, b -> ModConfiguredFeatures.bootstrap(b))
        .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<Provider> registries) {
        super(output, registries, BUILDER, Set.of(TestMod.MODID));
    }
    
}
