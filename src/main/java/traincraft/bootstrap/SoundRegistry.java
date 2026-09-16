package traincraft.bootstrap;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;

public final class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, Traincraft.MODID);

    /** The Alice's whistle. */
    public static final DeferredHolder<SoundEvent, SoundEvent> GERMAN_STEAM_HORN =
            register("german_steam_horn");

    public static final DeferredHolder<SoundEvent, SoundEvent> STEAM_RUN = register("steam_run");

    public static final DeferredHolder<SoundEvent, SoundEvent> MG_HORN = register("mg_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> VL10_IDLE = register("vl10_idle");

    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K5HLL = register("nathan_k5hll");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_GEVO_12_NOTCH8 = register("ge_gevo_12_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_GEVO_12_IDLE = register("ge_gevo_12_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> GP_HORN = register("gp_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> SD70_HORN = register("sd70_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> V60_HORN = register("v60_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHME3_IDLE = register("chme3_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EU07_HORN = register("eu07_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> MG_RUN = register("mg_run");
    public static final DeferredHolder<SoundEvent, SoundEvent> MG_IDLE = register("mg_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ASTERI = register("asteri");

    public static final DeferredHolder<SoundEvent, SoundEvent> BRITANNIAMEDIUMCHUFF = register("britanniamediumchuff");
    public static final DeferredHolder<SoundEvent, SoundEvent> BRITANNIAWHISTLE = register("britanniawhistle");
    public static final DeferredHolder<SoundEvent, SoundEvent> RW_TYPE_3 = register("rw_type_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> STANIERHOOTER = register("stanierhooter");
    public static final DeferredHolder<SoundEvent, SoundEvent> A4_WHISTLE = register("a4_whistle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ADLER_RUN = register("adler_run");
    public static final DeferredHolder<SoundEvent, SoundEvent> ADLER_WHISTLE = register("adler_whistle");
    public static final DeferredHolder<SoundEvent, SoundEvent> AMERICAN_STEAM_HORN = register("american_steam_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> CLASS62_HORN = register("class62_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> CROSBY_3CHIME = register("crosby_3chime");
    public static final DeferredHolder<SoundEvent, SoundEvent> HANCOCK_3CHIME = register("hancock_3chime");
    public static final DeferredHolder<SoundEvent, SoundEvent> LUKENHIMER_3CHIME = register("lukenhimer_3chime");
    public static final DeferredHolder<SoundEvent, SoundEvent> PE_CHIME = register("pe_chime");
    public static final DeferredHolder<SoundEvent, SoundEvent> PE_IDLE = register("pe_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> PE_RUN = register("pe_run");
    public static final DeferredHolder<SoundEvent, SoundEvent> SHAY_HORN = register("shay_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> SKOOKUM_WHISTLE = register("skookum_whistle");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEAM_HORN = register("steam_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> TYPE_2 = register("type_2");

    public static final DeferredHolder<SoundEvent, SoundEvent> SOUND_4300_HORN = register("4300_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUND_446HORN = register("446horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUND_742_HORN = register("742_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUND_742_MOTOR = register("742_motor");
    public static final DeferredHolder<SoundEvent, SoundEvent> SOUND_742_MOTOR_SLOW = register("742_motor_slow");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_12_251C_IDLE = register("alco_12_251c_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_12_251C_NOTCH8 = register("alco_12_251c_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_16_244_IDLE = register("alco_16_244_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_16_244_NOTCH8 = register("alco_16_244_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_16_251C_IDLE = register("alco_16_251c_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_16_251C_NOTCH8 = register("alco_16_251c_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_6_531_NOTCH8 = register("alco_6_531_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_6_539T_IDLE = register("alco_6_539t_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_6_539T_NOTCH8 = register("alco_6_539t_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_8_251F_IDLE = register("alco_8_251f_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ALCO_8_251F_NOTCH8 = register("alco_8_251f_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> CAT_8_D17000_IDLE = register("cat_8_d17000_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> CAT_8_D17000_NOTCH8 = register("cat_8_d17000_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> CLASS158HORN = register("class158horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> CLASS47HORN = register("class47horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> CLASS868790THRASH = register("class868790thrash");
    public static final DeferredHolder<SoundEvent, SoundEvent> CLASS90IDLE = register("class90idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_12_567B_NOTCH8 = register("emd_12_567b_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_12_567C_IDLE = register("emd_12_567c_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_12_567C_NOTCH8 = register("emd_12_567c_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_12_645E3_IDLE = register("emd_12_645e3_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_12_645E3_NOTCH8 = register("emd_12_645e3_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_12_645E_IDLE = register("emd_12_645e_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_12_645E_NOTCH8 = register("emd_12_645e_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567BC_IDLE = register("emd_16_567bc_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567BC_NOTCH8 = register("emd_16_567bc_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567B_IDLE = register("emd_16_567b_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567B_NOTCH8 = register("emd_16_567b_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567C_IDLE = register("emd_16_567c_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567C_NOTCH8 = register("emd_16_567c_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567D3_IDLE = register("emd_16_567d3_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_567D3_NOTCH8 = register("emd_16_567d3_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_645E3_IDLE = register("emd_16_645e3_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_645E3_NOTCH8 = register("emd_16_645e3_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_645E_IDLE = register("emd_16_645e_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_645E_NOTCH8 = register("emd_16_645e_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_710G3B_IDLE = register("emd_16_710g3b_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_16_710G3B_NOTCH8 = register("emd_16_710g3b_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_6_567A_IDLE = register("emd_6_567a_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_6_567A_NOTCH8 = register("emd_6_567a_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_8_567C_IDLE = register("emd_8_567c_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMD_8_567C_NOTCH8 = register("emd_8_567c_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> FM_38D_6_IDLE = register("fm_38d_6_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> FM_38D_6_NOTCH8 = register("fm_38d_6_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_7FDL_12_IDLE = register("ge_7fdl_12_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_7FDL_12_NOTCH8 = register("ge_7fdl_12_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_7FDL_16_IDLE = register("ge_7fdl_16_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_7FDL_16_NOTCH8 = register("ge_7fdl_16_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_7FDL_8_IDLE = register("ge_7fdl_8_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_7FDL_8_NOTCH8 = register("ge_7fdl_8_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_FDL16_IDLE = register("ge_fdl16_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> GE_FDL16_NOTCH8 = register("ge_fdl16_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> HSTHORN = register("hsthorn");
    public static final DeferredHolder<SoundEvent, SoundEvent> MILW_IDLE = register("milw_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> MILW_NOTCH8 = register("milw_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> MAYBACH_MD870_16_IDLE = register("maybach_md870_16_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> MAYBACH_MD870_16_NOTCH8 = register("maybach_md870_16_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> PENDOLINOHORN = register("pendolinohorn");
    public static final DeferredHolder<SoundEvent, SoundEvent> SILENCE = register("silence");
    public static final DeferredHolder<SoundEvent, SoundEvent> BR155_CHIME = register("br155_chime");
    public static final DeferredHolder<SoundEvent, SoundEvent> BRITISH_TWO_TONE = register("british_two_tone");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHME3_HORN = register("chme3_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> DSTOCK_WHISTLE = register("dstock_whistle");
    public static final DeferredHolder<SoundEvent, SoundEvent> HIGH_SPEED_HORN = register("high_speed_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_A125 = register("leslie_a125");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_A200 = register("leslie_a200");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_A200_2 = register("leslie_a200_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_RS3K_2 = register("leslie_rs3k_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_RS5T = register("leslie_rs5t");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_S3 = register("leslie_s3");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_S3L = register("leslie_s3l");
    public static final DeferredHolder<SoundEvent, SoundEvent> LESLIE_S3LR = register("leslie_s3lr");
    public static final DeferredHolder<SoundEvent, SoundEvent> METRO2000_RUNNING = register("metro2000_running");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K3HA = register("nathan_k3ha");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K3LA = register("nathan_k3la");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K3LA_2 = register("nathan_k3la_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K3LA_3 = register("nathan_k3la_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K3LA_4 = register("nathan_k3la_4");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K5LA = register("nathan_k5la");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K5LA_3 = register("nathan_k5la_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K5LA_4 = register("nathan_k5la_4");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_K5LA_5 = register("nathan_k5la_5");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_M3 = register("nathan_m3");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_M3H = register("nathan_m3h");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_M5 = register("nathan_m5");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P01235 = register("nathan_p01235");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P14R2 = register("nathan_p14r2");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P2 = register("nathan_p2");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P3 = register("nathan_p3");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P3_2 = register("nathan_p3_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P3_3 = register("nathan_p3_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P5 = register("nathan_p5");
    public static final DeferredHolder<SoundEvent, SoundEvent> NATHAN_P6 = register("nathan_p6");
    public static final DeferredHolder<SoundEvent, SoundEvent> SM42_CHIME = register("sm42_chime");
    public static final DeferredHolder<SoundEvent, SoundEvent> SM42_IDLE = register("sm42_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> SM42_RUN = register("sm42_run");
    public static final DeferredHolder<SoundEvent, SoundEvent> SUBWAY_HORN = register("subway_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> TRAM_BELL = register("tram_bell");
    public static final DeferredHolder<SoundEvent, SoundEvent> TRAM_HORN = register("tram_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> VL10_HORN = register("vl10_horn");
    public static final DeferredHolder<SoundEvent, SoundEvent> WABCO_E2 = register("wabco_e2");
    public static final DeferredHolder<SoundEvent, SoundEvent> QSK19C_IDLE = register("qsk19c_idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> QSK19C_NOTCH8 = register("qsk19c_notch8");
    public static final DeferredHolder<SoundEvent, SoundEvent> WHISTLE = register("whistle");
    public static final DeferredHolder<SoundEvent, SoundEvent> BELL = register("bell");

    private SoundRegistry() {}

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Traincraft.MODID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
