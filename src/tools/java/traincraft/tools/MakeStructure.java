package traincraft.tools;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;

final class MakeStructure {

    private static final int DATA_VERSION = 4907;

    private MakeStructure() {}

    static int run(int sizeX, int sizeY, int sizeZ, Path out) throws IOException {
        Files.createDirectories(out.getParent());
        try (DataOutputStream data =
                new DataOutputStream(new GZIPOutputStream(Files.newOutputStream(out)))) {
            // The outer compound has no name in the modern format, but the tag and its empty name
            // are still written -- readers expect the pair.
            data.writeByte(10);
            writeString(data, "");

            writeIntList(data, "size", sizeX, sizeY, sizeZ);

            // palette: [ { Name: "minecraft:air" } ]
            data.writeByte(9);
            writeString(data, "palette");
            data.writeByte(10);
            data.writeInt(1);
            data.writeByte(8);
            writeString(data, "Name");
            writeString(data, "minecraft:air");
            data.writeByte(0);

            writeEmptyList(data, "blocks");
            writeEmptyList(data, "entities");

            data.writeByte(3);
            writeString(data, "DataVersion");
            data.writeInt(DATA_VERSION);

            data.writeByte(0);
        }
        System.out.println("wrote " + out + " (" + sizeX + "x" + sizeY + "x" + sizeZ + ")");
        return 0;
    }

    private static void writeIntList(DataOutputStream data, String name, int... values)
            throws IOException {
        data.writeByte(9);
        writeString(data, name);
        data.writeByte(3);
        data.writeInt(values.length);
        for (int value : values) {
            data.writeInt(value);
        }
    }

    /** An empty list still needs an element type; the game writes 0 (TAG_End) for one. */
    private static void writeEmptyList(DataOutputStream data, String name) throws IOException {
        data.writeByte(9);
        writeString(data, name);
        data.writeByte(0);
        data.writeInt(0);
    }

    private static void writeString(DataOutputStream data, String value) throws IOException {
        byte[] bytes = value.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        data.writeShort(bytes.length);
        data.write(bytes);
    }
}
