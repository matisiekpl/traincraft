package traincraft.development.scenario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Scenario {

    private static final Gson GSON = new GsonBuilder().setLenient().create();

    public String id = "unnamed";
    public String description = "";
    public long seed = 1234L;
    public String world = "flat_capture";

    public ClientOptions client = new ClientOptions();
    public List<String> setup = List.of();
    public List<BuildOp> build = List.of();
    public List<SpawnOp> spawn = List.of();
    public List<Step> steps = List.of();
    public List<Landmark> landmarks = List.of();
    public List<Assertion> assertions = List.of();

    public static class ClientOptions {
        public int guiScale = 3;
        public double fov = 70;
        public int renderDistance = 8;
        public String graphics = "FANCY";
        public boolean viewBobbing = false;
        public boolean entityShadows = false;
        public boolean clouds = false;
        public String particles = "MINIMAL";
        public boolean hideGui = true;
        public String backend = "OPENGL";
    }

    public static class BuildOp {
        public String op = "";
        public @Nullable String type;
        public int facing;
        public int @Nullable [] pos;
        public int @Nullable [] from;
        public int @Nullable [] to;
        public @Nullable String material;
    }

    public static class SpawnOp {
        public String op = "entity";
        public String id = "";
        public double[] pos = {0, 0, 0};
        public double yaw;
        public @Nullable String tag;

        /** Optional supported livery for visual regression captures. */
        public @Nullable String colour;

        /**
         * Seats the entity where a piece of rolling stock rests on the rail at {@code pos} -- two
         * tenths of a block proud, plus the 0.65 every position in the movement code carries.
         * Without it a scenario has to encode that offset itself, and encoding it in the scenario
         * is how the two rigs came to disagree about the locomotive's height in the first place.
         */
        public boolean onRail;

        /** Furnace burn time to put in the firebox, as if coal had already been shovelled in. */
        public int fuel;

        /** Millibuckets of water for the tank. */
        public int water;
    }

    public static class Step {
        public String name = "step";

        /** [x, y, z, yaw, pitch]; null leaves the camera where it was. */
        public double @Nullable [] camera;

        public @Nullable String openGui;
        public boolean closeGui;
        public @Nullable String removeTag;

        /**
         * Removes every track piece this scenario placed. An "empty" step doing this is what makes
         * silhouette masking possible: the mask is the difference between a shot with the subject
         * and the otherwise identical shot without it.
         */
        public boolean clearTracks;

        public int settleTicks = 20;
        public boolean shoot = true;

        /**
         * Holds a driving key for this step: {@code forward}, {@code backward}, {@code brake} or
         * {@code none}. Applied to every locomotive the scenario spawned, so a capture can show a
         * train actually running rather than a train standing on track that might work.
         */
        public @Nullable String throttle;

        public double @Nullable [] velocity;

        /**
         * Puts the harness player in the cab. The camera is the player, so this is the only way to
         * photograph the driver's panel: a HUD that only draws while riding cannot be captured from
         * outside.
         */
        public boolean ride;

        /**
         * Things the player does before this step is photographed: holding an item, right-clicking
         * a block face with it, breaking a block, interacting with an entity, hitting one. Run in
         * order, through the server's own interaction path.
         */
        public java.util.List<PlayerAction> actions = java.util.List.of();
    }

    /** One thing the player does. See {@link traincraft.development.scenario.PlayerActions}. */
    public static class PlayerAction {
        /**
         * {@code hold}, {@code useItemOn}, {@code breakBlock}, {@code interact}, {@code attack}.
         */
        public String op = "";

        public @Nullable String item;
        public int count = 1;
        public int @Nullable [] pos;

        /** Which face is clicked; {@code up} unless a scenario says otherwise. */
        public String face = "up";

        public float yaw;

        /** Crouching changes what a right-click on a locomotive does: ride, or open the menu. */
        public boolean crouching;

        /** Which spawned entity to act on, by the tag it was spawned with. */
        public @Nullable String tag;

        /** Optional supported livery for visual regression captures. */
        public @Nullable String colour;
    }

    public static class Landmark {
        public String name = "";
        public double[] world = {0, 0, 0};
    }

    public static class Assertion {
        public String kind = "";
        public int @Nullable [] pos;
        public @Nullable String id;
        public @Nullable String block;
        public @Nullable String value;
        public double minDistance;
    }

    public static Scenario load(Path path) throws IOException {
        String json = Files.readString(path, StandardCharsets.UTF_8);
        // "assert" is a Java keyword, so the field is named "assertions" here; rewrite the key
        // rather than distorting the shared schema for one consumer's language.
        json = json.replace("\"assert\"", "\"assertions\"");
        Scenario scenario = GSON.fromJson(json, Scenario.class);
        if (scenario == null) {
            throw new IOException("empty scenario: " + path);
        }
        return scenario;
    }
}
