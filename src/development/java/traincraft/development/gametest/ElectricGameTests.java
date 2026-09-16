package traincraft.development.gametest;

import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import traincraft.bootstrap.EntityRegistry;
import traincraft.production.*;
import traincraft.vehicle.entity.Br185LocomotiveEntity;
import traincraft.vehicle.simulation.HeatState;

final class ElectricGameTests {
    static void register(Map<String, Consumer<GameTestHelper>> tests) {
        tests.put("br185_redstone_capacity", ElectricGameTests::redstone);
        tests.put("br185_no_boiler", ElectricGameTests::noBoiler);
        tests.put("br185_assembly", ElectricGameTests::assembly);
        tests.put("br185_hearth_blocked_output", ElectricGameTests::hearth);
        tests.put("br185_distillation", ElectricGameTests::distillation);
    }
    private static Br185LocomotiveEntity loco(GameTestHelper h) {
        var loco = EntityRegistry.LOCO_ELECTRIC_BR185.get().create(h.getLevel(), EntitySpawnReason.COMMAND);
        var p = h.absolutePos(new BlockPos(2, 3, 2));
        loco.setPos(p.getX()+.5,p.getY(),p.getZ()+.5);
        return loco;
    }
    private static MachineBlockEntity machine(GameTestHelper h, MachineKind kind) {
        var pos=h.absolutePos(new BlockPos(2,2,2));
        h.getLevel().setBlock(pos, ProductionRegistry.BLOCKS.get(kind).get().defaultBlockState(), 3);
        return (MachineBlockEntity)h.getLevel().getBlockEntity(pos);
    }
    private static void redstone(GameTestHelper h) {
        var loco=loco(h);
        loco.setItem(0,new ItemStack(Items.REDSTONE_BLOCK,2));
        loco.tick();
        h.assertTrue(loco.getFuel()==18000 && loco.getItem(0).getCount()==1,"Redstone block supplies exactly 18000");
        loco.tick();
        h.assertTrue(loco.getFuel()==18000 && loco.getItem(0).getCount()==1,"Oversized refill must not consume its item");
        loco.setItem(0,new ItemStack(Items.REDSTONE,2));
        loco.tick();
        h.assertTrue(loco.getFuel()==20000 && loco.getItem(0).getCount()==1,"Redstone fills the remaining 2000");
        loco.tick();
        h.assertTrue(loco.getItem(0).getCount()==1,"Full storage must leave its item");
        h.succeed();
    }
    private static void noBoiler(GameTestHelper h) {
        var loco=loco(h);
        loco.setItem(0,new ItemStack(Items.COAL));
        loco.tick();
        h.assertTrue(loco.getFuel()==0 && loco.getItem(0).getCount()==1,"Electric locomotive must reject furnace fuel");
        h.assertTrue(!loco.canOverheat() && loco.getState()==HeatState.HOT,"Electric train needs no boiler or warmup");
        loco.restoreFuel(1); loco.setEngineOn(true);
        for(int i=0;i<101;i++) loco.tick();
        h.assertTrue(loco.getFuel()==0 && !loco.isEngineOn(),"Exhausting energy switches off the motor");
        h.succeed();
    }
    private static void assembly(GameTestHelper h) {
        var machine=machine(h,MachineKind.ASSEMBLY);
        String[] parts={"controls","bogie","steel_frame",null,null,"steel_cab","transformer","electric_motor"};
        for(int i=0;i<parts.length;i++) if(parts[i]!=null) machine.setItem(i,new ItemStack(ProductionRegistry.item(parts[i]),i==5?1:2));
        machine.setItem(8,new ItemStack(Items.REDSTONE,4));
        machine.setItem(9,new ItemStack(Items.DYE.pick(net.minecraft.world.item.DyeColor.BLUE)));
        var recipe=machine.recipe();
        h.assertTrue(recipe!=null,"CE assembly inputs must resolve");
        var result=recipe.assemble(new MachineRecipe.Inventory(machine.kind,machine));
        h.assertTrue(result.is(traincraft.bootstrap.ItemRegistry.LOCO_ELECTRIC_BR185.get()),"Assembly produces BR 185");
        h.assertTrue(result.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA).copyTag().getStringOr("trainColor","").equals("Blue"),"Dye sets the manufactured livery");
        machine.setItem(3,new ItemStack(Items.STICK));
        h.assertTrue(machine.recipe()==null,"CE requires unused assembly inputs to be empty");
        h.succeed();
    }
    private static void hearth(GameTestHelper h) {
        var machine=machine(h,MachineKind.HEARTH);
        machine.setItem(0,new ItemStack(Items.IRON_INGOT));
        machine.setItem(1,new ItemStack(ProductionRegistry.item("graphite")));
        machine.setItem(2,new ItemStack(Items.COAL));
        machine.setItem(3,new ItemStack(ProductionRegistry.item("steel_ingot"),64));
        for(int i=0;i<1001;i++) machine.tick();
        h.assertTrue(machine.getItem(0).getCount()==1 && machine.getItem(2).getCount()==1,"Blocked output must not consume ingredients or ignite fuel");
        machine.setItem(3,ItemStack.EMPTY);
        for(int i=0;i<1000;i++) machine.tick();
        h.assertTrue(machine.getItem(3).is(ProductionRegistry.item("steel_ingot")) && machine.getItem(0).isEmpty() && machine.getItem(1).isEmpty(),"Steel takes 1000 burning ticks and one of each input");
        h.succeed();
    }
    private static void distillation(GameTestHelper h) {
        var machine=machine(h,MachineKind.DISTILLERY);
        machine.setItem(0,new ItemStack(Items.WHEAT));
        machine.setItem(1,new ItemStack(Items.COAL));
        for(int i=0;i<400;i++) machine.tick();
        h.assertTrue(machine.diesel==1000 && machine.getItem(0).isEmpty(),"Wheat distills into 1000 mB in 400 ticks");
        machine.setItem(2,new ItemStack(ProductionRegistry.item("empty_canister")));
        machine.tick();
        h.assertTrue(machine.diesel==0 && machine.getItem(4).is(ProductionRegistry.item("diesel")),"Canister drains the distillation tank");
        h.succeed();
    }
}
