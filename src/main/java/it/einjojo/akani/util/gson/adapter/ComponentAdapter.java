package it.einjojo.akani.util.gson.adapter;

import com.google.gson.*;
import it.einjojo.akani.util.text.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.lang.reflect.Type;

public class ComponentAdapter implements JsonSerializer<Component>, JsonDeserializer<Component> {
    @Override
    public JsonElement serialize(Component component, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(MiniMessage.miniMessage().serialize(component));
    }

    @Override
    public Component deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return MiniMessage.miniMessage().deserialize(TextUtil.transformToMiniMessage(json.getAsString()));
    }
}