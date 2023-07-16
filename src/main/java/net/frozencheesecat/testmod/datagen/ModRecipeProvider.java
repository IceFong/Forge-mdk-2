package net.frozencheesecat.testmod.datagen;

// import static net.minecraft.data.recipes.RecipeProvider.oreCooking;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.frozencheesecat.testmod.TestMod;
import net.frozencheesecat.testmod.block.ModBlock;
import net.frozencheesecat.testmod.item.ModItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, List.of(ModItem.RAW_BLACK_OPAL.get()), RecipeCategory.MISC,
            ModItem.BLACK_OPAL.get(), 0.7f, 200, "black_opal"
        );

        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, ModItem.BLACK_OPAL.get(), 
            RecipeCategory.BUILDING_BLOCKS, ModBlock.BLACK_OPAL_BLOCK.get(), "_to_", null, "_to_", null
        );

        planksFromLog(consumer, ModBlock.EBONY_PLANKS.get(), ModBlock.EBONY_LOG.get(), 4);
        
    }

    ////////////////////
    //Method Redefined//
    ////////////////////
    protected static void planksFromLog(Consumer<FinishedRecipe> consumer, ItemLike planks, ItemLike log, int p_259471_) {
      ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, p_259471_).requires(log).group("planks").unlockedBy("has_log", has(log))
        .save(consumer, new ResourceLocation(TestMod.MODID, getItemName(planks)));
   }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
      oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
      for(ItemLike itemlike : p_249619_) {
         SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_)
            .group(p_251450_).unlockedBy(getHasName(itemlike), has(itemlike))
            .save(p_250791_, new ResourceLocation(TestMod.MODID, getItemName(p_250066_)) + p_249236_ + "_" + getItemName(itemlike));
      }
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, RecipeCategory item_1_Category, ItemLike item_1, RecipeCategory item_2_Category, ItemLike item_2, String text_1, @Nullable String p_248641_, String text_2, @Nullable String p_250414_) {
      //Black Opals (9) -> Black Opal Block
      ShapelessRecipeBuilder.shapeless(item_1_Category, item_1, 9).requires(item_2).group(p_250414_)
        .unlockedBy(getHasName(item_2), has(item_2))
        .save(consumer, new ResourceLocation(TestMod.MODID, getItemName(item_2)) + text_1 + getItemName(item_1));
      //Black Opal Block -> Black Opals (9)
      ShapedRecipeBuilder.shaped(item_2_Category, item_2)
        .define('#', item_1)
        .pattern("###").pattern("###").pattern("###").group(p_248641_)
        .unlockedBy(getHasName(item_1), has(item_1))
        .save(consumer, new ResourceLocation(TestMod.MODID, getItemName(item_1)) + text_2 + getItemName(item_2));
    }
}
