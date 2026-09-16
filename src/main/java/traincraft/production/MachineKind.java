package traincraft.production;

public enum MachineKind {
    WORKBENCH("train_workbench", 10, 9, 9, -1, "crafting_table"),
    ASSEMBLY_I("assembly_table_i", 26, 10, 10, -1, "ported_tier_iii", 1),
    ASSEMBLY_II("assembly_table_ii", 26, 10, 10, -1, "ported_tier_iii", 2),
    ASSEMBLY("assembly_table_iii", 26, 10, 10, -1, "ported_tier_iii", 3),
    HEARTH("open_hearth_furnace", 4, 2, 3, 2, "gui_open_hearth_furnace"),
    DISTILLERY("distillation_tower", 5, 1, 3, 1, "gui_distillation_tower2");

    public final String id;
    public final int slots, inputs, output, fuel;
    public final String texture;
    public final int tier;
    MachineKind(String id, int slots, int inputs, int output, int fuel, String texture) {
        this(id, slots, inputs, output, fuel, texture, 0);
    }
    MachineKind(String id, int slots, int inputs, int output, int fuel, String texture, int tier) {
        this.id = id; this.slots = slots; this.inputs = inputs; this.output = output;
        this.fuel = fuel; this.texture = texture; this.tier = tier;
    }
    public int[][] coordinates() {
        return switch (this) {
            case WORKBENCH -> new int[][] {{30,17},{48,17},{66,17},{30,35},{48,35},{66,35},{30,53},{48,53},{66,53},{124,35}};
            case HEARTH -> new int[][] {{56,17},{35,17},{47,53},{116,35}};
            case DISTILLERY -> new int[][] {{56,17},{56,53},{123,8},{116,60},{123,33}};
            case ASSEMBLY, ASSEMBLY_I, ASSEMBLY_II -> new int[][] {{25,27},{43,93},{79,93},{145,93},{79,27},{115,27},{79,61},{115,61},{25,61},{145,27},{92,128},{110,128},{128,128},{146,128},{92,146},{110,146},{128,146},{146,146},{8,128},{26,128},{44,128},{62,128},{8,146},{26,146},{44,146},{62,146}};
        };
    }
    public boolean assembly() { return tier > 0; }
    public boolean instantaneous() { return fuel < 0; }
}
