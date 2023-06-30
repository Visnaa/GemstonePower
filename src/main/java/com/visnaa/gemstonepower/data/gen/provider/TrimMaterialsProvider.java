package com.visnaa.gemstonepower.data.gen.provider;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public abstract class TrimMaterialsProvider implements DataProvider
{
    protected final PackOutput output;
    private final String modid;
    private final Set<Data> data = new HashSet<>();
    private final Path path;

    public TrimMaterialsProvider(PackOutput output, String modid)
    {
        this.output = output;
        this.modid = modid;
        this.path = output.getOutputFolder(PackOutput.Target.DATA_PACK);
    }

    public void trim(String name, int color, ItemLike ingredient, float modelIndex)
    {
        this.trim(name, color, this.modid, ingredient, modelIndex, false);
    }

    public void trim(String name, int color, String modid, ItemLike ingredient, float modelIndex, boolean override)
    {
        Data trim = new Data(name, color, modid, ingredient, modelIndex, override);
        if (!data.add(trim)) throw new IllegalStateException("Duplicate trim " + trim.assetName + ".json");
    }

    public CompletableFuture<?> run(CachedOutput cache)
    {
        List<CompletableFuture<?>> list = new ArrayList<>();
        this.addTrimMaterials();
        this.data.forEach(data -> list.add(save(cache, this.path.resolve(data.modid).resolve("trim_material").resolve(data.assetName + ".json"), data)));
        return CompletableFuture.allOf(list.toArray(size -> new CompletableFuture[size]));
    }

    private CompletableFuture<?> save(CachedOutput cache, Path target, Data data)
    {
        if (data == null)
            throw new IllegalArgumentException("Data is null - " + target.getFileName());

        JsonObject json = new JsonObject();
        json.addProperty("asset_name", data.assetName);
        JsonObject description = new JsonObject();
        description.addProperty("color", data.color);
        description.addProperty("translate", data.translate);
        json.add("description", description);
        json.addProperty("ingredient", data.ingredient);
        json.addProperty("item_model_index", data.modelIndex);
        if (data.override)
        {
            JsonObject override = new JsonObject();
            override.addProperty(data.assetName, data.assetName + "_darker");
            json.add("override_armor_materials", override);
        }

        return DataProvider.saveStable(cache, json, target);
    }

    protected abstract void addTrimMaterials();

    public final String getName() {
        return "Trim Materials";
    }

    private class Data
    {
        public final String modid;
        private final String assetName;
        private final String color;
        private final String translate;
        private final String ingredient;
        private final float modelIndex;
        private final boolean override;

        public Data(String assetName, int color, String modid, ItemLike ingredient, float modelIndex, boolean override)
        {
            this.modid = modid;
            this.assetName = assetName;
            this.color = ("#" + Integer.toHexString(color)).toUpperCase();
            this.translate = "trim_material." + modid + "." + assetName;
            this.ingredient = ForgeRegistries.ITEMS.getKey(ingredient.asItem()).toString();
            this.modelIndex = modelIndex;
            this.override = override;
        }
    }
}
