package it.einjojo.akani.util.gson.adapter;

import com.google.gson.*;
import it.einjojo.akani.util.item.ItemStackUtils;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;

public class ItemStackAdapter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {
    @Override
    public JsonElement serialize(ItemStack itemStack, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(ItemStackUtils.toNBTString(itemStack));
    }

    @Override
    public ItemStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ItemStackUtils.fromNBTString(jsonElement.getAsString());
    }
}