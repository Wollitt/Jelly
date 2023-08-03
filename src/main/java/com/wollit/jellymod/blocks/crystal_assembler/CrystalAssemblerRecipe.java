package com.wollit.jellymod.blocks.crystal_assembler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wollit.jellymod.JellyMod;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CrystalAssemblerRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public CrystalAssemblerRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (!level.isClientSide) {
            if (recipeItems.get(0).test(container.getItem(0))) {
                if (recipeItems.get(1).test(container.getItem(1))) {
                    return recipeItems.get(2).test(container.getItem(2));
                }
            }
        }

        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess p_267165_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CrystalAssemblerRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "crystal_assembling";
    }

    public static class Serializer implements RecipeSerializer<CrystalAssemblerRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(JellyMod.MOD_ID, "crystal_assembling");

        @Override
        public CrystalAssemblerRecipe fromJson(ResourceLocation recipeId, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); ++i) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new CrystalAssemblerRecipe(recipeId, output, inputs);
        }

        @Override
        public @Nullable CrystalAssemblerRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); ++i) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output =  buf.readItem();
            return new CrystalAssemblerRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CrystalAssemblerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeItemStack(recipe.getResultItem(null), false);
        }
    }

}
