package com.wollit.jelly.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wollit.jelly.JellyMod;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BasicAlloyFurnaceRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public BasicAlloyFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (recipeItems.get(0).test(container.getItem(0))) {
            return recipeItems.get(1).test(container.getItem(1));
        }
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainer container) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return BasicAlloyFurnaceRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return BasicAlloyFurnaceRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<BasicAlloyFurnaceRecipe> {
        private Type() {}
        public static BasicAlloyFurnaceRecipe.Type INSTANCE = new BasicAlloyFurnaceRecipe.Type();
        public static final String ID = "basic_alloy_furnace";
    }

    public static class Serializer implements RecipeSerializer<BasicAlloyFurnaceRecipe> {
        public static final BasicAlloyFurnaceRecipe.Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(JellyMod.MOD_ID, "basic_alloy_furnace");

        @Override
        public BasicAlloyFurnaceRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe. itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); ++i) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new BasicAlloyFurnaceRecipe(id, output, inputs);
        }

        @Nullable
        @Override
        public BasicAlloyFurnaceRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); ++i) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new BasicAlloyFurnaceRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BasicAlloyFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return BasicAlloyFurnaceRecipe.Serializer.castClass(RecipeSerializer.class);
        }

        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
