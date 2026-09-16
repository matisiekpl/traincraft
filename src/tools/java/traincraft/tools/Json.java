package traincraft.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/** JSON encoding shared by capture tools and resource generators. */
final class Json {
    private static final Gson GSON =
            new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private Json() {}

    static String write(Object value) {
        return GSON.toJson(value) + "\n";
    }

    static Map<String, Object> parseObject(String text) {
        Map<String, Object> result =
                GSON.fromJson(text, new TypeToken<Map<String, Object>>() {}.getType());
        if (result == null) throw new IllegalArgumentException("Expected a JSON object");
        return result;
    }

    @SuppressWarnings("unchecked")
    static Map<String, Object> asObject(Object value) {
        return value instanceof Map<?, ?> map ? (Map<String, Object>) map : null;
    }

    @SuppressWarnings("unchecked")
    static List<Object> asArray(Object value) {
        return value instanceof List<?> list ? (List<Object>) list : List.of();
    }
}
