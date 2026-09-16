package traincraft.bootstrap;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import traincraft.Traincraft;
import traincraft.vehicle.entity.AliceLocomotiveEntity;
import traincraft.vehicle.entity.ZeppelinEntity;
import traincraft.vehicle.entity.Br80LocomotiveEntity;
import traincraft.vehicle.entity.Br185LocomotiveEntity;
import traincraft.vehicle.entity.BrE69LocomotiveEntity;
import traincraft.vehicle.entity.E10LocomotiveEntity;
import traincraft.vehicle.entity.Es44LocomotiveEntity;
import traincraft.vehicle.entity.Sd40LocomotiveEntity;
import traincraft.vehicle.entity.Sd70LocomotiveEntity;
import traincraft.vehicle.entity.V60LocomotiveEntity;
import traincraft.vehicle.entity.FreightCartYellowEntity;
import traincraft.vehicle.entity.A4LocomotiveEntity;
import traincraft.vehicle.entity.HallClassLocomotiveEntity;
import traincraft.vehicle.entity.Berk1225LocomotiveEntity;
import traincraft.vehicle.entity.Berk765LocomotiveEntity;
import traincraft.vehicle.entity.FowlerLocomotiveEntity;
import traincraft.vehicle.entity.KingClassLocomotiveEntity;
import traincraft.vehicle.entity.MilwClassALocomotiveEntity;
import traincraft.vehicle.entity.CherepanovLocomotiveEntity;
import traincraft.vehicle.entity.Steam440LocomotiveEntity;
import traincraft.vehicle.entity.SmallLocomotiveEntity;
import traincraft.vehicle.entity.Lssp7LocomotiveEntity;
import traincraft.vehicle.entity.HeavyLocomotiveEntity;
import traincraft.vehicle.entity.C62LocomotiveEntity;
import traincraft.vehicle.entity.D51ShortLocomotiveEntity;
import traincraft.vehicle.entity.D51LongLocomotiveEntity;
import traincraft.vehicle.entity.Br01LocomotiveEntity;
import traincraft.vehicle.entity.CoranationClassLocomotiveEntity;
import traincraft.vehicle.entity.Gs4LocomotiveEntity;
import traincraft.vehicle.entity.ErUssrLocomotiveEntity;
import traincraft.vehicle.entity.C41LocomotiveEntity;
import traincraft.vehicle.entity.C41080LocomotiveEntity;
import traincraft.vehicle.entity.AlcoSc4LocomotiveEntity;
import traincraft.vehicle.entity.Southern1102LocomotiveEntity;
import traincraft.vehicle.entity.UsatcUsLocomotiveEntity;
import traincraft.vehicle.entity.UsatcUkLocomotiveEntity;
import traincraft.vehicle.entity.C41tLocomotiveEntity;
import traincraft.vehicle.entity.AsteriLocomotiveEntity;
import traincraft.vehicle.entity.ForneyLocomotiveEntity;
import traincraft.vehicle.entity.MogulLocomotiveEntity;
import traincraft.vehicle.entity.ShayLocomotiveEntity;
import traincraft.vehicle.entity.VbShayLocomotiveEntity;
import traincraft.vehicle.entity.ClimaxLocomotiveEntity;
import traincraft.vehicle.entity.PannierLocomotiveEntity;
import traincraft.vehicle.entity.GlynLocomotiveEntity;
import traincraft.vehicle.entity.Steam262tLocomotiveEntity;
import traincraft.vehicle.entity.Steam040vbLocomotiveEntity;
import traincraft.vehicle.entity.AdlerLocomotiveEntity;
import traincraft.vehicle.entity.VbShay2truckLocomotiveEntity;
import traincraft.vehicle.entity.Climax2truckLocomotiveEntity;
import traincraft.vehicle.entity.C11LocomotiveEntity;
import traincraft.vehicle.entity.StarClassLocomotiveEntity;
import traincraft.vehicle.entity.OnionLocomotiveEntity;
import traincraft.vehicle.entity.PolarExpressLocomotiveEntity;
import traincraft.vehicle.entity.GarrattFrontLocomotiveEntity;
import traincraft.vehicle.entity.GarrattRearLocomotiveEntity;
import traincraft.vehicle.entity.GarrattMidLocomotiveEntity;
import traincraft.vehicle.entity.SkookumLocomotiveEntity;
import traincraft.vehicle.entity.Shay3truckLocomotiveEntity;
import traincraft.vehicle.entity.BrBlack5LocomotiveEntity;
import traincraft.vehicle.entity.RwType3LocomotiveEntity;
import traincraft.vehicle.entity.RwType2LocomotiveEntity;
import traincraft.vehicle.entity.Gwr42xxLocomotiveEntity;
import traincraft.vehicle.entity.Gwr72xxLocomotiveEntity;
import traincraft.vehicle.entity.Gwr101ClassLocomotiveEntity;
import traincraft.vehicle.entity.Wwcp062tLocomotiveEntity;
import traincraft.vehicle.entity.BrBritanniaLocomotiveEntity;
import traincraft.vehicle.entity.J50LocomotiveEntity;
import traincraft.vehicle.entity.SentinelY3LocomotiveEntity;
import traincraft.vehicle.entity.MwClass88LocomotiveEntity;
import traincraft.vehicle.entity.MrCompoundLocomotiveEntity;
import traincraft.vehicle.entity.SmallTenderEntity;
import traincraft.vehicle.entity.HeavyTenderEntity;
import traincraft.vehicle.entity.Gs4TenderEntity;
import traincraft.vehicle.entity.Gwr4000GallonTenderEntity;
import traincraft.vehicle.entity.FowlerTenderEntity;
import traincraft.vehicle.entity.Berk1225TenderEntity;
import traincraft.vehicle.entity.Tender440TenderEntity;
import traincraft.vehicle.entity.A4TenderEntity;
import traincraft.vehicle.entity.Br01TenderEntity;
import traincraft.vehicle.entity.CoranationClassTenderEntity;
import traincraft.vehicle.entity.ErUssrTenderEntity;
import traincraft.vehicle.entity.C62TenderEntity;
import traincraft.vehicle.entity.D51TenderEntity;
import traincraft.vehicle.entity.AdlerTenderEntity;
import traincraft.vehicle.entity.C41TenderEntity;
import traincraft.vehicle.entity.Southern1102TenderEntity;
import traincraft.vehicle.entity.MilwTenderEntity;
import traincraft.vehicle.entity.BrBlack5TenderEntity;
import traincraft.vehicle.entity.Br1TenderEntity;
import traincraft.vehicle.entity.RwType2TenderEntity;
import traincraft.vehicle.entity.StarClassTenderEntity;
import traincraft.vehicle.entity.OnionTenderEntity;
import traincraft.vehicle.entity.PolarExpressTenderEntity;
import traincraft.vehicle.entity.SkookumTenderEntity;
import traincraft.vehicle.entity.Shay3truckTenderEntity;
import traincraft.vehicle.entity.ShuntingUkTenderEntity;
import traincraft.vehicle.entity.MrCompoundTenderEntity;
import traincraft.vehicle.entity.SnowPlowLocomotiveEntity;
import traincraft.vehicle.entity.KofLocomotiveEntity;
import traincraft.vehicle.entity.Gp40LocomotiveEntity;
import traincraft.vehicle.entity.Chme3LocomotiveEntity;
import traincraft.vehicle.entity.Gp7RedLocomotiveEntity;
import traincraft.vehicle.entity.ShunterLocomotiveEntity;
import traincraft.vehicle.entity.Ic4DsbMgLocomotiveEntity;
import traincraft.vehicle.entity.MilwH1044LocomotiveEntity;
import traincraft.vehicle.entity.Emdf7LocomotiveEntity;
import traincraft.vehicle.entity.Emdf3LocomotiveEntity;
import traincraft.vehicle.entity.EwsClass66LocomotiveEntity;
import traincraft.vehicle.entity.DelticLocomotiveEntity;
import traincraft.vehicle.entity.Dd35aLocomotiveEntity;
import traincraft.vehicle.entity.Loco44TonSwitcherLocomotiveEntity;
import traincraft.vehicle.entity.BambooLocomotiveEntity;
import traincraft.vehicle.entity.Wls40LocomotiveEntity;
import traincraft.vehicle.entity.FolM1LocomotiveEntity;
import traincraft.vehicle.entity.FolM1bLocomotiveEntity;
import traincraft.vehicle.entity.Cf7LocomotiveEntity;
import traincraft.vehicle.entity.Gp15LocomotiveEntity;
import traincraft.vehicle.entity.Sw8LocomotiveEntity;
import traincraft.vehicle.entity.Cd814LocomotiveEntity;
import traincraft.vehicle.entity.Cd810LocomotiveEntity;
import traincraft.vehicle.entity.Sm42LocomotiveEntity;
import traincraft.vehicle.entity.Ge44tonLocomotiveEntity;
import traincraft.vehicle.entity.BapF7aLocomotiveEntity;
import traincraft.vehicle.entity.BapF7bLocomotiveEntity;
import traincraft.vehicle.entity.H1044LocomotiveEntity;
import traincraft.vehicle.entity.Gp13LocomotiveEntity;
import traincraft.vehicle.entity.BapB23LocomotiveEntity;
import traincraft.vehicle.entity.C424LocomotiveEntity;
import traincraft.vehicle.entity.C425LocomotiveEntity;
import traincraft.vehicle.entity.Gp7uLocomotiveEntity;
import traincraft.vehicle.entity.Gp7LocomotiveEntity;
import traincraft.vehicle.entity.Gp7bLocomotiveEntity;
import traincraft.vehicle.entity.Gp9LocomotiveEntity;
import traincraft.vehicle.entity.Gp30LocomotiveEntity;
import traincraft.vehicle.entity.Gp38dash2LocomotiveEntity;
import traincraft.vehicle.entity.KofIiiLocomotiveEntity;
import traincraft.vehicle.entity.KofIiiMLocomotiveEntity;
import traincraft.vehicle.entity.U36cLocomotiveEntity;
import traincraft.vehicle.entity.Gp49LocomotiveEntity;
import traincraft.vehicle.entity.BapGp15LocomotiveEntity;
import traincraft.vehicle.entity.Sd9LocomotiveEntity;
import traincraft.vehicle.entity.Sd40dash2LocomotiveEntity;
import traincraft.vehicle.entity.U23bLocomotiveEntity;
import traincraft.vehicle.entity.U18bLocomotiveEntity;
import traincraft.vehicle.entity.Hh660LocomotiveEntity;
import traincraft.vehicle.entity.KrauttLocomotiveEntity;
import traincraft.vehicle.entity.Dash840bLocomotiveEntity;
import traincraft.vehicle.entity.Class44LocomotiveEntity;
import traincraft.vehicle.entity.Sw1500LocomotiveEntity;
import traincraft.vehicle.entity.Sw1LocomotiveEntity;
import traincraft.vehicle.entity.Dash840cLocomotiveEntity;
import traincraft.vehicle.entity.Sw1200LocomotiveEntity;
import traincraft.vehicle.entity.Rsd15LocomotiveEntity;
import traincraft.vehicle.entity.Sd70macLocomotiveEntity;
import traincraft.vehicle.entity.Dash944cwLocomotiveEntity;
import traincraft.vehicle.entity.Dash840bbLocomotiveEntity;
import traincraft.vehicle.entity.Dash840bwLocomotiveEntity;
import traincraft.vehicle.entity.Dh643LocomotiveEntity;
import traincraft.vehicle.entity.BapCf7LocomotiveEntity;
import traincraft.vehicle.entity.Cf7roundLocomotiveEntity;
import traincraft.vehicle.entity.Gp38dash9wLocomotiveEntity;
import traincraft.vehicle.entity.AlcoS2LocomotiveEntity;
import traincraft.vehicle.entity.BeepLocomotiveEntity;
import traincraft.vehicle.entity.Class158LocomotiveEntity;
import traincraft.vehicle.entity.Class153LocomotiveEntity;
import traincraft.vehicle.entity.Class156LocomotiveEntity;
import traincraft.vehicle.entity.Class47LocomotiveEntity;
import traincraft.vehicle.entity.AlcoPa1LocomotiveEntity;
import traincraft.vehicle.entity.AlcoPb1LocomotiveEntity;
import traincraft.vehicle.entity.Emde8aLocomotiveEntity;
import traincraft.vehicle.entity.Emde8bLocomotiveEntity;
import traincraft.vehicle.entity.Class43LocomotiveEntity;
import traincraft.vehicle.entity.C415hLocomotiveEntity;
import traincraft.vehicle.entity.C415sLocomotiveEntity;
import traincraft.vehicle.entity.C415lLocomotiveEntity;
import traincraft.vehicle.entity.Ge25tonLocomotiveEntity;
import traincraft.vehicle.entity.H2466LocomotiveEntity;
import traincraft.vehicle.entity.H2466lLocomotiveEntity;
import traincraft.vehicle.entity.Emde7aLocomotiveEntity;
import traincraft.vehicle.entity.Emde7bLocomotiveEntity;
import traincraft.vehicle.entity.Class175LocomotiveEntity;
import traincraft.vehicle.entity.H1666LocomotiveEntity;
import traincraft.vehicle.entity.Class34LocomotiveEntity;
import traincraft.vehicle.entity.Class121BubblecarLocomotiveEntity;
import traincraft.vehicle.entity.Class117LocomotiveEntity;
import traincraft.vehicle.entity.Class143FrontLocomotiveEntity;
import traincraft.vehicle.entity.Cd754LocomotiveEntity;
import traincraft.vehicle.entity.Class142FrontLocomotiveEntity;
import traincraft.vehicle.entity.BagnallLocomotiveEntity;
import traincraft.vehicle.entity.Class205LocomotiveEntity;
import traincraft.vehicle.entity.MineTrainLocomotiveEntity;
import traincraft.vehicle.entity.SpeedZeroEdLocomotiveEntity;
import traincraft.vehicle.entity.Ice1LocomotiveEntity;
import traincraft.vehicle.entity.TramYellowLocomotiveEntity;
import traincraft.vehicle.entity.TramNyLocomotiveEntity;
import traincraft.vehicle.entity.E103LocomotiveEntity;
import traincraft.vehicle.entity.Class85LocomotiveEntity;
import traincraft.vehicle.entity.Cd151LocomotiveEntity;
import traincraft.vehicle.entity.Bp4LocomotiveEntity;
import traincraft.vehicle.entity.Renfe446MotorLocomotiveEntity;
import traincraft.vehicle.entity.Pch120LocomotiveEntity;
import traincraft.vehicle.entity.LuEngineLocomotiveEntity;
import traincraft.vehicle.entity.DstockEngineLocomotiveEntity;
import traincraft.vehicle.entity.Class345LocomotiveEntity;
import traincraft.vehicle.entity.BnlrvALocomotiveEntity;
import traincraft.vehicle.entity.Tw305LocomotiveEntity;
import traincraft.vehicle.entity.Metro2000LocomotiveEntity;
import traincraft.vehicle.entity.Renfe450MotorLocomotiveEntity;
import traincraft.vehicle.entity.Br155LocomotiveEntity;
import traincraft.vehicle.entity.Loco440rFrontLocomotiveEntity;
import traincraft.vehicle.entity.Db143LocomotiveEntity;
import traincraft.vehicle.entity.Ef1LocomotiveEntity;
import traincraft.vehicle.entity.Ef1bLocomotiveEntity;
import traincraft.vehicle.entity.Ep1aLocomotiveEntity;
import traincraft.vehicle.entity.IlmaLocomotiveEntity;
import traincraft.vehicle.entity.IlmbLocomotiveEntity;
import traincraft.vehicle.entity.Feve3300frontLocomotiveEntity;
import traincraft.vehicle.entity.Eu07LocomotiveEntity;
import traincraft.vehicle.entity.Gm6cLocomotiveEntity;
import traincraft.vehicle.entity.Class319EngineLocomotiveEntity;
import traincraft.vehicle.entity.Kvb2300LocomotiveEntity;
import traincraft.vehicle.entity.BrMk2fDbsoLocomotiveEntity;
import traincraft.vehicle.entity.BrMk3DvtLocomotiveEntity;
import traincraft.vehicle.entity.BrMk4DvtLocomotiveEntity;
import traincraft.vehicle.entity.Class90LocomotiveEntity;
import traincraft.vehicle.entity.Class91LocomotiveEntity;
import traincraft.vehicle.entity.Class321LocomotiveEntity;
import traincraft.vehicle.entity.NmbsHle18LocomotiveEntity;
import traincraft.vehicle.entity.Fgv4300MotorLocomotiveEntity;
import traincraft.vehicle.entity.InterurbanSeries100LocomotiveEntity;
import traincraft.vehicle.entity.Metro3000LocomotiveEntity;
import traincraft.vehicle.entity.Cq310LocomotiveEntity;
import traincraft.vehicle.entity.Class162EngineLocomotiveEntity;
import traincraft.vehicle.entity.MetalTramLocomotiveEntity;
import traincraft.vehicle.entity.B80cALocomotiveEntity;
import traincraft.vehicle.entity.Ma100LocoLocomotiveEntity;
import traincraft.vehicle.entity.Class390FrontLocomotiveEntity;
import traincraft.vehicle.entity.DuewagT4erLocomotiveEntity;
import traincraft.vehicle.entity.DuewagGt6zrLocomotiveEntity;
import traincraft.vehicle.entity.M8cLocomotiveEntity;
import traincraft.vehicle.entity.Class416LocoLocomotiveEntity;
import traincraft.vehicle.entity.Db420LocoLocomotiveEntity;
import traincraft.vehicle.entity.Class401EngineLocomotiveEntity;
import traincraft.vehicle.entity.Class230EngineLocomotiveEntity;
import traincraft.vehicle.entity.DuewagGt6erLocomotiveEntity;
import traincraft.vehicle.entity.Class374FrontLocomotiveEntity;
import traincraft.vehicle.entity.Class387FrontLocomotiveEntity;
import traincraft.vehicle.entity.Class378FrontLocomotiveEntity;
import traincraft.vehicle.entity.Class389FrontLocomotiveEntity;
import traincraft.vehicle.entity.Class442DtsLocomotiveEntity;
import traincraft.vehicle.entity.M8Dnf1LocomotiveEntity;
import traincraft.vehicle.entity.Vl10LocomotiveEntity;
import traincraft.vehicle.entity.CartBlueCarEntity;
import traincraft.vehicle.entity.CartBlackSmallCarEntity;
import traincraft.vehicle.entity.LongGreenCarEntity;
import traincraft.vehicle.entity.ShortGreenCarEntity;
import traincraft.vehicle.entity.Car1classDbCarEntity;
import traincraft.vehicle.entity.Car2classDbCarEntity;
import traincraft.vehicle.entity.HighSpeedZeroEdCarEntity;
import traincraft.vehicle.entity.TramNyCarEntity;
import traincraft.vehicle.entity.AdlerCarEntity;
import traincraft.vehicle.entity.DbOrientalCarEntity;
import traincraft.vehicle.entity.Ic4DsbFgCarEntity;
import traincraft.vehicle.entity.Ic4DsbFhCarEntity;
import traincraft.vehicle.entity.Ice1Class1CarEntity;
import traincraft.vehicle.entity.Ice1Class2CarEntity;
import traincraft.vehicle.entity.Ice1RestaurantCarEntity;
import traincraft.vehicle.entity.Gs4CarEntity;
import traincraft.vehicle.entity.Gs4ObservatoryCarEntity;
import traincraft.vehicle.entity.Gs4TailCarEntity;
import traincraft.vehicle.entity.DenverRioGrangeCarEntity;
import traincraft.vehicle.entity.DenverRioGrandeComboCarEntity;
import traincraft.vehicle.entity.RheingoldCarEntity;
import traincraft.vehicle.entity.RheingoldPanoramaCarEntity;
import traincraft.vehicle.entity.MilwCarEntity;
import traincraft.vehicle.entity.MilwTailCarEntity;
import traincraft.vehicle.entity.BambooCarEntity;
import traincraft.vehicle.entity.Renfe446CoachCarEntity;
import traincraft.vehicle.entity.CabooseRenfe446TailCarEntity;
import traincraft.vehicle.entity.Pch120coachCarEntity;
import traincraft.vehicle.entity.LUpassengerCarEntity;
import traincraft.vehicle.entity.DstockPassengerCarEntity;
import traincraft.vehicle.entity.Class345CoachCarEntity;
import traincraft.vehicle.entity.Ps52SeatCoachCarEntity;
import traincraft.vehicle.entity.PScenterDinerCarEntity;
import traincraft.vehicle.entity.PsAnotherDinerCarEntity;
import traincraft.vehicle.entity.BnlrvBCarEntity;
import traincraft.vehicle.entity.Bw305CarEntity;
import traincraft.vehicle.entity.Metro2000CarEntity;
import traincraft.vehicle.entity.Renfe450CoachCarEntity;
import traincraft.vehicle.entity.CabooseRenfe450TailCarEntity;
import traincraft.vehicle.entity.Cd014CarEntity;
import traincraft.vehicle.entity.Cd914CarEntity;
import traincraft.vehicle.entity.Cd010CarEntity;
import traincraft.vehicle.entity.AmfleetCarEntity;
import traincraft.vehicle.entity.Amfleet2CarEntity;
import traincraft.vehicle.entity.StarCarFatCarEntity;
import traincraft.vehicle.entity.StarCarNotFatCarEntity;
import traincraft.vehicle.entity.Car440RMidCarEntity;
import traincraft.vehicle.entity.Car440RRearCarEntity;
import traincraft.vehicle.entity.Feve3300rearCarEntity;
import traincraft.vehicle.entity.Class158CoachCarEntity;
import traincraft.vehicle.entity.Class153CoachCarEntity;
import traincraft.vehicle.entity.PsSleeper565CarEntity;
import traincraft.vehicle.entity.PsSleeper565DrgwCarEntity;
import traincraft.vehicle.entity.SncbM6CarEntity;
import traincraft.vehicle.entity.SncbM6TailCarEntity;
import traincraft.vehicle.entity.Class319MiddleCarEntity;
import traincraft.vehicle.entity.Class319PantoCarEntity;
import traincraft.vehicle.entity.Class319TailCarEntity;
import traincraft.vehicle.entity.Kvb2300BCarEntity;
import traincraft.vehicle.entity.BrMk2CBsoCarEntity;
import traincraft.vehicle.entity.BrMk2CCoachCarEntity;
import traincraft.vehicle.entity.BrMk2FBsoCarEntity;
import traincraft.vehicle.entity.BrMk2FCoachCarEntity;
import traincraft.vehicle.entity.BrMk3BuffetCarEntity;
import traincraft.vehicle.entity.BrMk3CoachCarEntity;
import traincraft.vehicle.entity.BrMk3aCoachCarEntity;
import traincraft.vehicle.entity.BrMk3PantographCarEntity;
import traincraft.vehicle.entity.BrMk4CoachCarEntity;
import traincraft.vehicle.entity.BrMk4BuffetCarEntity;
import traincraft.vehicle.entity.Class321MotorCarEntity;
import traincraft.vehicle.entity.Class321CoachCarEntity;
import traincraft.vehicle.entity.MinetrainCarEntity;
import traincraft.vehicle.entity.PsLunchCounterLoungeCarEntity;
import traincraft.vehicle.entity.Ps30SeatParlorCarEntity;
import traincraft.vehicle.entity.Ps54SeatCoachLoungeCarEntity;
import traincraft.vehicle.entity.Ps58SeatCoachObservationCarEntity;
import traincraft.vehicle.entity.Psbm56SeatCoachCarEntity;
import traincraft.vehicle.entity.PsbmCombineCarEntity;
import traincraft.vehicle.entity.PsbmDinerLoungeCarEntity;
import traincraft.vehicle.entity.BrMk1BsoCarEntity;
import traincraft.vehicle.entity.BrMk1TsoCarEntity;
import traincraft.vehicle.entity.BrMk1BuffetCarEntity;
import traincraft.vehicle.entity.BrMk1BaggageCarEntity;
import traincraft.vehicle.entity.Class175CoachCarEntity;
import traincraft.vehicle.entity.Acfgn60SeatCoachCarEntity;
import traincraft.vehicle.entity.Fgv4300CoachCarEntity;
import traincraft.vehicle.entity.Fgv4300TailCarEntity;
import traincraft.vehicle.entity.Metro3000CarEntity;
import traincraft.vehicle.entity.Metro3000TailCarEntity;
import traincraft.vehicle.entity.Class162CoachBCarEntity;
import traincraft.vehicle.entity.Class162CoachACarEntity;
import traincraft.vehicle.entity.Class162TailCarEntity;
import traincraft.vehicle.entity.MetalTramCoachCarEntity;
import traincraft.vehicle.entity.B80CBCarEntity;
import traincraft.vehicle.entity.WoodenTramCoachCarEntity;
import traincraft.vehicle.entity.Ma100TailCarEntity;
import traincraft.vehicle.entity.Class390CoachCarEntity;
import traincraft.vehicle.entity.Class390PantoCarEntity;
import traincraft.vehicle.entity.Class121TrailerCarEntity;
import traincraft.vehicle.entity.Class117MiddleCarEntity;
import traincraft.vehicle.entity.BrBrakeVanCarEntity;
import traincraft.vehicle.entity.DuewagGt6ZrTailCarEntity;
import traincraft.vehicle.entity.M8CTailCarEntity;
import traincraft.vehicle.entity.Class416TailCarEntity;
import traincraft.vehicle.entity.Db420MiddleCarEntity;
import traincraft.vehicle.entity.Db420TailCarEntity;
import traincraft.vehicle.entity.Class401TailCarEntity;
import traincraft.vehicle.entity.Car10tonBrakeVanCarEntity;
import traincraft.vehicle.entity.Class230MiddleCarEntity;
import traincraft.vehicle.entity.DuewagGt6ErTailCarEntity;
import traincraft.vehicle.entity.Class143RearCarEntity;
import traincraft.vehicle.entity.Class143MiddleCarEntity;
import traincraft.vehicle.entity.Class374PremierPantoCarEntity;
import traincraft.vehicle.entity.Class374StandardPantoCarEntity;
import traincraft.vehicle.entity.Class374BuffetCarEntity;
import traincraft.vehicle.entity.Class387CoachCarEntity;
import traincraft.vehicle.entity.Class387PantoCarEntity;
import traincraft.vehicle.entity.Class387TailCarEntity;
import traincraft.vehicle.entity.Class378MiddleCarEntity;
import traincraft.vehicle.entity.Class378TailCarEntity;
import traincraft.vehicle.entity.Class142TailCarEntity;
import traincraft.vehicle.entity.Class389MiddleCarEntity;
import traincraft.vehicle.entity.Class389TailCarEntity;
import traincraft.vehicle.entity.Class442TsCarEntity;
import traincraft.vehicle.entity.Class442MblsCarEntity;
import traincraft.vehicle.entity.Class205tsoCarEntity;
import traincraft.vehicle.entity.Class205TailCarEntity;
import traincraft.vehicle.entity.M8Dnf1MiddlelongCarEntity;
import traincraft.vehicle.entity.M8Dnf1MiddleshortCarEntity;
import traincraft.vehicle.entity.M8Dnf1TailCarEntity;
import traincraft.vehicle.entity.CartRedFreightEntity;
import traincraft.vehicle.entity.WoodFreightEntity;
import traincraft.vehicle.entity.HopperFreightEntity;
import traincraft.vehicle.entity.KClassRailBoxFreightEntity;
import traincraft.vehicle.entity.ShortCoveredHopperFreightEntity;
import traincraft.vehicle.entity.LongCoveredHopperFreightEntity;
import traincraft.vehicle.entity.OpenWagonFreightEntity;
import traincraft.vehicle.entity.HopperUsFreightEntity;
import traincraft.vehicle.entity.Car100TonHopperFreightEntity;
import traincraft.vehicle.entity.FlatCartWoodUsFreightEntity;
import traincraft.vehicle.entity.BulkheadFlatCartWoodFreightEntity;
import traincraft.vehicle.entity.CartUsFreightEntity;
import traincraft.vehicle.entity.BoxCartUsFreightEntity;
import traincraft.vehicle.entity.BoxCartPrrFreightEntity;
import traincraft.vehicle.entity.CartSmallFreightEntity;
import traincraft.vehicle.entity.Minetrain2FreightEntity;
import traincraft.vehicle.entity.GtngFreightEntity;
import traincraft.vehicle.entity.FlatCartWoodLogsFreightEntity;
import traincraft.vehicle.entity.ClosedRedBrownFreightEntity;
import traincraft.vehicle.entity.OpenRedBrownFreightEntity;
import traincraft.vehicle.entity.WagenDbFreightEntity;
import traincraft.vehicle.entity.FlatCarRailsDbFreightEntity;
import traincraft.vehicle.entity.AstfAutorackFreightEntity;
import traincraft.vehicle.entity.FlatCarLogsDbFreightEntity;
import traincraft.vehicle.entity.SlateWagonFreightEntity;
import traincraft.vehicle.entity.IceWagonFreightEntity;
import traincraft.vehicle.entity.CartGs4FreightEntity;
import traincraft.vehicle.entity.GondolaDbFreightEntity;
import traincraft.vehicle.entity.CenterBeamEmptyFreightEntity;
import traincraft.vehicle.entity.CenterBeamWood1FreightEntity;
import traincraft.vehicle.entity.CenterBeamWood2FreightEntity;
import traincraft.vehicle.entity.WellcarFreightEntity;
import traincraft.vehicle.entity.TrailerFreightEntity;
import traincraft.vehicle.entity.DenverRioGrange2FreightEntity;
import traincraft.vehicle.entity.MilwBaggageFreightEntity;
import traincraft.vehicle.entity.HeavyweightFreightEntity;
import traincraft.vehicle.entity.CartBambooFreightEntity;
import traincraft.vehicle.entity.GermanPostFreightEntity;
import traincraft.vehicle.entity.DepressedFlatbedFreightEntity;
import traincraft.vehicle.entity.CarLFreightEntity;
import traincraft.vehicle.entity.Heavyweight2FreightEntity;
import traincraft.vehicle.entity.RoundHopperFreightEntity;
import traincraft.vehicle.entity.RibbedHopperFreightEntity;
import traincraft.vehicle.entity.Bap40highcubeFreightEntity;
import traincraft.vehicle.entity.BapWoodchipHopperFreightEntity;
import traincraft.vehicle.entity.BapOreJennyFreightEntity;
import traincraft.vehicle.entity.BapMillGondolaFreightEntity;
import traincraft.vehicle.entity.BapMilw40boxcarFreightEntity;
import traincraft.vehicle.entity.Bap60centerbeamFreightEntity;
import traincraft.vehicle.entity.Bap66centerbeamFreightEntity;
import traincraft.vehicle.entity.Bap73centerbeamFreightEntity;
import traincraft.vehicle.entity.BapPs140FreightEntity;
import traincraft.vehicle.entity.BapPs150FreightEntity;
import traincraft.vehicle.entity.BapPs160FreightEntity;
import traincraft.vehicle.entity.BapVersaLongiFreightEntity;
import traincraft.vehicle.entity.BapVersaTransFreightEntity;
import traincraft.vehicle.entity.Hicube60footFreightEntity;
import traincraft.vehicle.entity.BnsfGonFreightEntity;
import traincraft.vehicle.entity.Hopper5201FreightEntity;
import traincraft.vehicle.entity.Hopper6260FreightEntity;
import traincraft.vehicle.entity.SkeletonFreightEntity;
import traincraft.vehicle.entity.Ps73BaggageFreightEntity;
import traincraft.vehicle.entity.Ps85BaggageFreightEntity;
import traincraft.vehicle.entity.Reefer64FreightEntity;
import traincraft.vehicle.entity.PsrpopmFreightEntity;
import traincraft.vehicle.entity.PsrpoFreightEntity;
import traincraft.vehicle.entity.BoulderWagonFreightEntity;
import traincraft.vehicle.entity.Gsi60FootBulkheadFreightEntity;
import traincraft.vehicle.entity.Gsc60FootFlatcarFreightEntity;
import traincraft.vehicle.entity.Car5PlankFreightEntity;
import traincraft.vehicle.entity.BrMk1TpoStowageFreightEntity;
import traincraft.vehicle.entity.Acfgnrpo30FreightEntity;
import traincraft.vehicle.entity.HopperUkFreightEntity;
import traincraft.vehicle.entity.ExpressFreightVanFreightEntity;
import traincraft.vehicle.entity.TipperUkFreightEntity;
import traincraft.vehicle.entity.MineralwagonFreightEntity;
import traincraft.vehicle.entity.VentilatedVanFreightEntity;
import traincraft.vehicle.entity.TankWagonDbTankEntity;
import traincraft.vehicle.entity.TankThreeDomeTankEntity;
import traincraft.vehicle.entity.TankWagonUsTankEntity;
import traincraft.vehicle.entity.TankWagonGreyTankEntity;
import traincraft.vehicle.entity.TankCartLavaTankEntity;
import traincraft.vehicle.entity.TankWagonYellowTankEntity;
import traincraft.vehicle.entity.BapDot11111000TankEntity;
import traincraft.vehicle.entity.BapDot11120600TankEntity;
import traincraft.vehicle.entity.BapDot11129080TankEntity;
import traincraft.vehicle.entity.TankTankerUkTankEntity;
import traincraft.vehicle.entity.RheingoldDining1WorkCartEntity;
import traincraft.vehicle.entity.RheingoldDining2WorkCartEntity;
import traincraft.vehicle.entity.GwrBrakeVanWorkCartEntity;
import traincraft.vehicle.entity.WorkCartWorkCartEntity;
import traincraft.vehicle.entity.WorkCabooseWorkCartEntity;
import traincraft.vehicle.entity.CabooseLoggingWorkCartEntity;
import traincraft.vehicle.entity.CabooseLoggingPrrWorkCartEntity;
import traincraft.vehicle.entity.MailWagenDbWorkCartEntity;
import traincraft.vehicle.entity.StockCarCarEntity;
import traincraft.vehicle.entity.DrwgStockCarCarEntity;
import traincraft.vehicle.entity.JukeBoxCartCarEntity;
import traincraft.vehicle.entity.TracksBuilderCarEntity;
import traincraft.vehicle.entity.CattleVanCarEntity;
import traincraft.vehicle.entity.CabooseRedCarEntity;
import traincraft.vehicle.entity.CabooseBlackCarEntity;
import traincraft.vehicle.entity.BapWVcabooseCarEntity;
import traincraft.vehicle.entity.DrgwCabooseCarEntity;
import traincraft.vehicle.entity.FlatCartCarEntity;
import traincraft.vehicle.entity.FlatCartSuCarEntity;
import traincraft.vehicle.entity.FlatCartUsCarEntity;
import traincraft.vehicle.entity.FlatCarDbCarEntity;
import traincraft.vehicle.entity.PropagandaUsCarEntity;
import traincraft.vehicle.entity.PropagandaUssrCarEntity;
import traincraft.vehicle.entity.PropagandaJapanCarEntity;
import traincraft.vehicle.entity.PropagandaBritainCarEntity;
import traincraft.vehicle.entity.BUnitEmdf7TankEntity;
import traincraft.vehicle.entity.BUnitEmdf3TankEntity;
import traincraft.vehicle.entity.BUnitDd35TankEntity;
import traincraft.vehicle.entity.BapPEcoachCarEntity;
import traincraft.vehicle.entity.BapPEobserveCarEntity;
import traincraft.vehicle.entity.PsCombineCarEntity;
import traincraft.vehicle.entity.LightCraneCarEntity;
import traincraft.vehicle.entity.Nre3gs21bLocomotiveEntity;
import traincraft.vehicle.entity.Cq310PassengerCarEntity;
import traincraft.vehicle.entity.RollingStockEntity;

/** Entity type registry. */
public final class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Traincraft.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<AliceLocomotiveEntity>>
            LOCO_STEAM_ALICE =
                    TYPES.register(
                            "loco_steam_alice",
                            name ->
                                    EntityType.Builder.<AliceLocomotiveEntity>of(
                                                    AliceLocomotiveEntity::new, MobCategory.MISC)
                                            .sized(
                                                    RollingStockEntity.WIDTH,
                                                    RollingStockEntity.HEIGHT)
                                            .clientTrackingRange(8)
                                            .updateInterval(3)
                                            .build(
                                                    ResourceKey.create(
                                                            Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<Br80LocomotiveEntity>>
            LOCO_STEAM_BR80 =
                    TYPES.register(
                            "loco_steam_br80",
                            name ->
                                    EntityType.Builder.<Br80LocomotiveEntity>of(
                                                    Br80LocomotiveEntity::new, MobCategory.MISC)
                                            .sized(
                                                    RollingStockEntity.WIDTH,
                                                    RollingStockEntity.HEIGHT)
                                            .clientTrackingRange(8)
                                            .updateInterval(3)
                                            .build(
                                                    ResourceKey.create(
                                                            Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<FreightCartYellowEntity>>
            FREIGHT_CART_YELLOW =
                    TYPES.register(
                            "freight_cart_yellow",
                            name ->
                                    EntityType.Builder.<FreightCartYellowEntity>of(
                                                    FreightCartYellowEntity::new, MobCategory.MISC)
                                            .sized(
                                                    RollingStockEntity.WIDTH,
                                                    RollingStockEntity.HEIGHT)
                                            .clientTrackingRange(8)
                                            .updateInterval(3)
                                            .build(
                                                    ResourceKey.create(
                                                            Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<Br185LocomotiveEntity>>
            LOCO_ELECTRIC_BR185 =
                    TYPES.register(
                            "loco_electric_br185",
                            name ->
                                    EntityType.Builder.<Br185LocomotiveEntity>of(
                                                    Br185LocomotiveEntity::new, MobCategory.MISC)
                                            .sized(
                                                    RollingStockEntity.WIDTH,
                                                    RollingStockEntity.HEIGHT)
                                            .clientTrackingRange(8)
                                            .updateInterval(3)
                                            .build(
                                                    ResourceKey.create(
                                                            Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<Es44LocomotiveEntity>>
            LOCO_DIESEL_ES44 =
                    TYPES.register(
                            "loco_diesel_es44",
                            name ->
                                    EntityType.Builder.<Es44LocomotiveEntity>of(
                                                    Es44LocomotiveEntity::new, MobCategory.MISC)
                                            .sized(
                                                    RollingStockEntity.WIDTH,
                                                    RollingStockEntity.HEIGHT)
                                            .clientTrackingRange(8)
                                            .updateInterval(3)
                                            .build(
                                                    ResourceKey.create(
                                                            Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<Sd40LocomotiveEntity>> LOCO_DIESEL_SD40 =
            TYPES.register(
                    "loco_diesel_sd40",
                    name ->
                            EntityType.Builder.<Sd40LocomotiveEntity>of(Sd40LocomotiveEntity::new, MobCategory.MISC)
                                    .sized(RollingStockEntity.WIDTH, RollingStockEntity.HEIGHT)
                                    .clientTrackingRange(8)
                                    .updateInterval(3)
                                    .build(ResourceKey.create(Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<Sd70LocomotiveEntity>> LOCO_DIESEL_SD70 =
            TYPES.register(
                    "loco_diesel_sd70",
                    name ->
                            EntityType.Builder.<Sd70LocomotiveEntity>of(Sd70LocomotiveEntity::new, MobCategory.MISC)
                                    .sized(RollingStockEntity.WIDTH, RollingStockEntity.HEIGHT)
                                    .clientTrackingRange(8)
                                    .updateInterval(3)
                                    .build(ResourceKey.create(Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<V60LocomotiveEntity>> LOCO_DIESEL_V60 =
            TYPES.register(
                    "loco_diesel_v60",
                    name ->
                            EntityType.Builder.<V60LocomotiveEntity>of(V60LocomotiveEntity::new, MobCategory.MISC)
                                    .sized(RollingStockEntity.WIDTH, RollingStockEntity.HEIGHT)
                                    .clientTrackingRange(8)
                                    .updateInterval(3)
                                    .build(ResourceKey.create(Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<BrE69LocomotiveEntity>> LOCO_ELECTRIC_BR_E69 =
            TYPES.register(
                    "loco_electric_br_e69",
                    name ->
                            EntityType.Builder.<BrE69LocomotiveEntity>of(BrE69LocomotiveEntity::new, MobCategory.MISC)
                                    .sized(RollingStockEntity.WIDTH, RollingStockEntity.HEIGHT)
                                    .clientTrackingRange(8)
                                    .updateInterval(3)
                                    .build(ResourceKey.create(Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<E10LocomotiveEntity>> LOCO_ELECTRIC_E10 =
            TYPES.register(
                    "loco_electric_e10",
                    name ->
                            EntityType.Builder.<E10LocomotiveEntity>of(E10LocomotiveEntity::new, MobCategory.MISC)
                                    .sized(RollingStockEntity.WIDTH, RollingStockEntity.HEIGHT)
                                    .clientTrackingRange(8)
                                    .updateInterval(3)
                                    .build(ResourceKey.create(Registries.ENTITY_TYPE, name)));

    public static final DeferredHolder<EntityType<?>, EntityType<A4LocomotiveEntity>> LOCO_STEAM_A4 =
            stock("loco_steam_a4", A4LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HallClassLocomotiveEntity>> LOCO_STEAM_HALL_CLASS =
            stock("loco_steam_hall_class", HallClassLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Berk1225LocomotiveEntity>> LOCO_STEAM_BERK_1225 =
            stock("loco_steam_berk_1225", Berk1225LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Berk765LocomotiveEntity>> LOCO_STEAM_BERK_765 =
            stock("loco_steam_berk_765", Berk765LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FowlerLocomotiveEntity>> LOCO_STEAM_FOWLER =
            stock("loco_steam_fowler", FowlerLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<KingClassLocomotiveEntity>> LOCO_STEAM_KING_CLASS =
            stock("loco_steam_king_class", KingClassLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MilwClassALocomotiveEntity>> LOCO_STEAM_MILW_CLASS_A =
            stock("loco_steam_milw_class_a", MilwClassALocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CherepanovLocomotiveEntity>> LOCO_STEAM_CHEREPANOV =
            stock("loco_steam_cherepanov", CherepanovLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Steam440LocomotiveEntity>> LOCO_STEAM_4_4_0 =
            stock("loco_steam_4_4_0", Steam440LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SmallLocomotiveEntity>> LOCO_STEAM_SMALL =
            stock("loco_steam_small", SmallLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Lssp7LocomotiveEntity>> LOCO_STEAM_LSSP7 =
            stock("loco_steam_lssp7", Lssp7LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HeavyLocomotiveEntity>> LOCO_STEAM_HEAVY =
            stock("loco_steam_heavy", HeavyLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C62LocomotiveEntity>> LOCO_STEAM_C62 =
            stock("loco_steam_c62", C62LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<D51ShortLocomotiveEntity>> LOCO_STEAM_D51_SHORT =
            stock("loco_steam_d51_short", D51ShortLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<D51LongLocomotiveEntity>> LOCO_STEAM_D51_LONG =
            stock("loco_steam_d51_long", D51LongLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Br01LocomotiveEntity>> LOCO_STEAM_BR01 =
            stock("loco_steam_br01", Br01LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CoranationClassLocomotiveEntity>> LOCO_STEAM_CORANATION_CLASS =
            stock("loco_steam_coranation_class", CoranationClassLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gs4LocomotiveEntity>> LOCO_STEAM_GS4 =
            stock("loco_steam_gs4", Gs4LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ErUssrLocomotiveEntity>> LOCO_STEAM_ER_USSR =
            stock("loco_steam_er_ussr", ErUssrLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C41LocomotiveEntity>> LOCO_STEAM_C41 =
            stock("loco_steam_c41", C41LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C41080LocomotiveEntity>> LOCO_STEAM_C41_080 =
            stock("loco_steam_c41_080", C41080LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AlcoSc4LocomotiveEntity>> LOCO_STEAM_ALCO_SC4 =
            stock("loco_steam_alco_sc4", AlcoSc4LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Southern1102LocomotiveEntity>> LOCO_STEAM_SOUTHERN_1102 =
            stock("loco_steam_southern_1102", Southern1102LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<UsatcUsLocomotiveEntity>> LOCO_STEAM_USATC_US =
            stock("loco_steam_usatc_us", UsatcUsLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<UsatcUkLocomotiveEntity>> LOCO_STEAM_USATC_UK =
            stock("loco_steam_usatc_uk", UsatcUkLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C41tLocomotiveEntity>> LOCO_STEAM_C41T =
            stock("loco_steam_c41t", C41tLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ForneyLocomotiveEntity>> LOCO_STEAM_FORNEY =
            stock("loco_steam_forney", ForneyLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AsteriLocomotiveEntity>> LOCO_STEAM_ASTERI =
            stock("loco_steam_asteri", AsteriLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MogulLocomotiveEntity>> LOCO_STEAM_MOGUL =
            stock("loco_steam_mogul", MogulLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ShayLocomotiveEntity>> LOCO_STEAM_SHAY =
            stock("loco_steam_shay", ShayLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<VbShayLocomotiveEntity>> LOCO_STEAM_VB_SHAY =
            stock("loco_steam_vb_shay", VbShayLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ClimaxLocomotiveEntity>> LOCO_STEAM_CLIMAX =
            stock("loco_steam_climax", ClimaxLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PannierLocomotiveEntity>> LOCO_STEAM_PANNIER =
            stock("loco_steam_pannier", PannierLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GlynLocomotiveEntity>> LOCO_STEAM_GLYN =
            stock("loco_steam_glyn", GlynLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Steam262tLocomotiveEntity>> LOCO_STEAM_262T =
            stock("loco_steam_262t", Steam262tLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Steam040vbLocomotiveEntity>> LOCO_STEAM_040VB =
            stock("loco_steam_040vb", Steam040vbLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AdlerLocomotiveEntity>> LOCO_STEAM_ADLER =
            stock("loco_steam_adler", AdlerLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<VbShay2truckLocomotiveEntity>> LOCO_STEAM_VB_SHAY_2TRUCK =
            stock("loco_steam_vb_shay_2truck", VbShay2truckLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Climax2truckLocomotiveEntity>> LOCO_STEAM_CLIMAX_2TRUCK =
            stock("loco_steam_climax_2truck", Climax2truckLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C11LocomotiveEntity>> LOCO_STEAM_C11 =
            stock("loco_steam_c11", C11LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<StarClassLocomotiveEntity>> LOCO_STEAM_STAR_CLASS =
            stock("loco_steam_star_class", StarClassLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<OnionLocomotiveEntity>> LOCO_STEAM_ONION =
            stock("loco_steam_onion", OnionLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PolarExpressLocomotiveEntity>> LOCO_STEAM_POLAR_EXPRESS =
            stock("loco_steam_polar_express", PolarExpressLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GarrattFrontLocomotiveEntity>> LOCO_STEAM_GARRATT_FRONT =
            stock("loco_steam_garratt_front", GarrattFrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GarrattRearLocomotiveEntity>> LOCO_STEAM_GARRATT_REAR =
            stock("loco_steam_garratt_rear", GarrattRearLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GarrattMidLocomotiveEntity>> LOCO_STEAM_GARRATT_MID =
            stock("loco_steam_garratt_mid", GarrattMidLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SkookumLocomotiveEntity>> LOCO_STEAM_SKOOKUM =
            stock("loco_steam_skookum", SkookumLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Shay3truckLocomotiveEntity>> LOCO_STEAM_SHAY_3TRUCK =
            stock("loco_steam_shay_3truck", Shay3truckLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrBlack5LocomotiveEntity>> LOCO_STEAM_BR_BLACK_5 =
            stock("loco_steam_br_black_5", BrBlack5LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RwType3LocomotiveEntity>> LOCO_STEAM_RW_TYPE_3 =
            stock("loco_steam_rw_type_3", RwType3LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RwType2LocomotiveEntity>> LOCO_STEAM_RW_TYPE_2 =
            stock("loco_steam_rw_type_2", RwType2LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gwr42xxLocomotiveEntity>> LOCO_STEAM_GWR_42XX =
            stock("loco_steam_gwr_42xx", Gwr42xxLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gwr72xxLocomotiveEntity>> LOCO_STEAM_GWR_72XX =
            stock("loco_steam_gwr_72xx", Gwr72xxLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gwr101ClassLocomotiveEntity>> LOCO_STEAM_GWR_101_CLASS =
            stock("loco_steam_gwr_101_class", Gwr101ClassLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Wwcp062tLocomotiveEntity>> LOCO_STEAM_WWCP_062T =
            stock("loco_steam_wwcp_062t", Wwcp062tLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrBritanniaLocomotiveEntity>> LOCO_STEAM_BR_BRITANNIA =
            stock("loco_steam_br_britannia", BrBritanniaLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<J50LocomotiveEntity>> LOCO_STEAM_J50 =
            stock("loco_steam_j50", J50LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SentinelY3LocomotiveEntity>> LOCO_STEAM_SENTINEL_Y3 =
            stock("loco_steam_sentinel_y3", SentinelY3LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MwClass88LocomotiveEntity>> LOCO_STEAM_MW_CLASS_88 =
            stock("loco_steam_mw_class_88", MwClass88LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MrCompoundLocomotiveEntity>> LOCO_STEAM_MR_COMPOUND =
            stock("loco_steam_mr_compound", MrCompoundLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SmallTenderEntity>> TENDER_SMALL =
            stock("tender_small", SmallTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HeavyTenderEntity>> TENDER_HEAVY =
            stock("tender_heavy", HeavyTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gs4TenderEntity>> TENDER_GS4 =
            stock("tender_gs4", Gs4TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gwr4000GallonTenderEntity>> TENDER_GWR_4000_GALLON =
            stock("tender_gwr_4000_gallon", Gwr4000GallonTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FowlerTenderEntity>> TENDER_FOWLER =
            stock("tender_fowler", FowlerTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Berk1225TenderEntity>> TENDER_BERK_1225 =
            stock("tender_berk_1225", Berk1225TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Tender440TenderEntity>> TENDER_4_4_0 =
            stock("tender_4_4_0", Tender440TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<A4TenderEntity>> TENDER_A4 =
            stock("tender_a4", A4TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Br01TenderEntity>> TENDER_BR01 =
            stock("tender_br01", Br01TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CoranationClassTenderEntity>> TENDER_CORANATION_CLASS =
            stock("tender_coranation_class", CoranationClassTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ErUssrTenderEntity>> TENDER_ER_USSR =
            stock("tender_er_ussr", ErUssrTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C62TenderEntity>> TENDER_C62 =
            stock("tender_c62", C62TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<D51TenderEntity>> TENDER_D51 =
            stock("tender_d51", D51TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AdlerTenderEntity>> TENDER_ADLER =
            stock("tender_adler", AdlerTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C41TenderEntity>> TENDER_C41 =
            stock("tender_c41", C41TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Southern1102TenderEntity>> TENDER_SOUTHERN_1102 =
            stock("tender_southern_1102", Southern1102TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MilwTenderEntity>> TENDER_MILW =
            stock("tender_milw", MilwTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrBlack5TenderEntity>> TENDER_BR_BLACK_5 =
            stock("tender_br_black_5", BrBlack5TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Br1TenderEntity>> TENDER_BR1 =
            stock("tender_br1", Br1TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RwType2TenderEntity>> TENDER_RW_TYPE_2 =
            stock("tender_rw_type_2", RwType2TenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<StarClassTenderEntity>> TENDER_STAR_CLASS =
            stock("tender_star_class", StarClassTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<OnionTenderEntity>> TENDER_ONION =
            stock("tender_onion", OnionTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PolarExpressTenderEntity>> TENDER_POLAR_EXPRESS =
            stock("tender_polar_express", PolarExpressTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SkookumTenderEntity>> TENDER_SKOOKUM =
            stock("tender_skookum", SkookumTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Shay3truckTenderEntity>> TENDER_SHAY_3TRUCK =
            stock("tender_shay_3truck", Shay3truckTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ShuntingUkTenderEntity>> TENDER_SHUNTING_UK =
            stock("tender_shunting_uk", ShuntingUkTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MrCompoundTenderEntity>> TENDER_MR_COMPOUND =
            stock("tender_mr_compound", MrCompoundTenderEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SnowPlowLocomotiveEntity>> LOCO_STEAM_SNOW_PLOW =
            stock("loco_steam_snow_plow", SnowPlowLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<KofLocomotiveEntity>> LOCO_DIESEL_KOF =
            stock("loco_diesel_kof", KofLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp40LocomotiveEntity>> LOCO_DIESEL_GP40 =
            stock("loco_diesel_gp40", Gp40LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Chme3LocomotiveEntity>> LOCO_DIESEL_CHME3 =
            stock("loco_diesel_chme3", Chme3LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp7RedLocomotiveEntity>> LOCO_DIESEL_GP7_RED =
            stock("loco_diesel_gp7_red", Gp7RedLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ShunterLocomotiveEntity>> LOCO_DIESEL_SHUNTER =
            stock("loco_diesel_shunter", ShunterLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ic4DsbMgLocomotiveEntity>> LOCO_DIESEL_IC4_DSB_MG =
            stock("loco_diesel_ic4_dsb_mg", Ic4DsbMgLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MilwH1044LocomotiveEntity>> LOCO_DIESEL_MILW_H1044 =
            stock("loco_diesel_milw_h1044", MilwH1044LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Emdf7LocomotiveEntity>> LOCO_DIESEL_EMDF7 =
            stock("loco_diesel_emdf7", Emdf7LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Emdf3LocomotiveEntity>> LOCO_DIESEL_EMDF3 =
            stock("loco_diesel_emdf3", Emdf3LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<EwsClass66LocomotiveEntity>> LOCO_DIESEL_EWS_CLASS66 =
            stock("loco_diesel_ews_class66", EwsClass66LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DelticLocomotiveEntity>> LOCO_DIESEL_DELTIC =
            stock("loco_diesel_deltic", DelticLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Dd35aLocomotiveEntity>> LOCO_DIESEL_DD35A =
            stock("loco_diesel_dd35a", Dd35aLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Loco44TonSwitcherLocomotiveEntity>> LOCO_DIESEL_44_TON_SWITCHER =
            stock("loco_diesel_44_ton_switcher", Loco44TonSwitcherLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BambooLocomotiveEntity>> LOCO_DIESEL_BAMBOO =
            stock("loco_diesel_bamboo", BambooLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Wls40LocomotiveEntity>> LOCO_DIESEL_WLS40 =
            stock("loco_diesel_wls40", Wls40LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FolM1LocomotiveEntity>> LOCO_DIESEL_FOL_M1 =
            stock("loco_diesel_fol_m1", FolM1LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FolM1bLocomotiveEntity>> LOCO_DIESEL_FOL_M1B =
            stock("loco_diesel_fol_m1b", FolM1bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cf7LocomotiveEntity>> LOCO_DIESEL_CF7 =
            stock("loco_diesel_cf7", Cf7LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp15LocomotiveEntity>> LOCO_DIESEL_GP15 =
            stock("loco_diesel_gp15", Gp15LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sw8LocomotiveEntity>> LOCO_DIESEL_SW8 =
            stock("loco_diesel_sw8", Sw8LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cd814LocomotiveEntity>> LOCO_DIESEL_CD814 =
            stock("loco_diesel_cd814", Cd814LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cd810LocomotiveEntity>> LOCO_DIESEL_CD810 =
            stock("loco_diesel_cd810", Cd810LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sm42LocomotiveEntity>> LOCO_DIESEL_SM42 =
            stock("loco_diesel_sm42", Sm42LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ge44tonLocomotiveEntity>> LOCO_DIESEL_GE44TON =
            stock("loco_diesel_ge44ton", Ge44tonLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapF7aLocomotiveEntity>> LOCO_DIESEL_BAP_F7A =
            stock("loco_diesel_bap_f7a", BapF7aLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapF7bLocomotiveEntity>> LOCO_DIESEL_BAP_F7B =
            stock("loco_diesel_bap_f7b", BapF7bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<H1044LocomotiveEntity>> LOCO_DIESEL_H1044 =
            stock("loco_diesel_h1044", H1044LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp13LocomotiveEntity>> LOCO_DIESEL_GP13 =
            stock("loco_diesel_gp13", Gp13LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapB23LocomotiveEntity>> LOCO_DIESEL_BAP_B23 =
            stock("loco_diesel_bap_b23", BapB23LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C424LocomotiveEntity>> LOCO_DIESEL_C424 =
            stock("loco_diesel_c424", C424LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C425LocomotiveEntity>> LOCO_DIESEL_C425 =
            stock("loco_diesel_c425", C425LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp7uLocomotiveEntity>> LOCO_DIESEL_GP7U =
            stock("loco_diesel_gp7u", Gp7uLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp7LocomotiveEntity>> LOCO_DIESEL_GP7 =
            stock("loco_diesel_gp7", Gp7LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp7bLocomotiveEntity>> LOCO_DIESEL_GP7B =
            stock("loco_diesel_gp7b", Gp7bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp9LocomotiveEntity>> LOCO_DIESEL_GP9 =
            stock("loco_diesel_gp9", Gp9LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp30LocomotiveEntity>> LOCO_DIESEL_GP30 =
            stock("loco_diesel_gp30", Gp30LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp38dash2LocomotiveEntity>> LOCO_DIESEL_GP38DASH2 =
            stock("loco_diesel_gp38dash2", Gp38dash2LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<KofIiiLocomotiveEntity>> LOCO_DIESEL_KOF_III =
            stock("loco_diesel_kof_iii", KofIiiLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<KofIiiMLocomotiveEntity>> LOCO_DIESEL_KOF_III_M =
            stock("loco_diesel_kof_iii_m", KofIiiMLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<U36cLocomotiveEntity>> LOCO_DIESEL_U36C =
            stock("loco_diesel_u36c", U36cLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp49LocomotiveEntity>> LOCO_DIESEL_GP49 =
            stock("loco_diesel_gp49", Gp49LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapGp15LocomotiveEntity>> LOCO_DIESEL_BAP_GP15 =
            stock("loco_diesel_bap_gp15", BapGp15LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sd9LocomotiveEntity>> LOCO_DIESEL_SD9 =
            stock("loco_diesel_sd9", Sd9LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sd40dash2LocomotiveEntity>> LOCO_DIESEL_SD40DASH2 =
            stock("loco_diesel_sd40dash2", Sd40dash2LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<U23bLocomotiveEntity>> LOCO_DIESEL_U23B =
            stock("loco_diesel_u23b", U23bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<U18bLocomotiveEntity>> LOCO_DIESEL_U18B =
            stock("loco_diesel_u18b", U18bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Hh660LocomotiveEntity>> LOCO_DIESEL_HH660 =
            stock("loco_diesel_hh660", Hh660LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<KrauttLocomotiveEntity>> LOCO_DIESEL_KRAUTT =
            stock("loco_diesel_krautt", KrauttLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Dash840bLocomotiveEntity>> LOCO_DIESEL_DASH8_40B =
            stock("loco_diesel_dash8_40b", Dash840bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class44LocomotiveEntity>> LOCO_DIESEL_CLASS44 =
            stock("loco_diesel_class44", Class44LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sw1500LocomotiveEntity>> LOCO_DIESEL_SW1500 =
            stock("loco_diesel_sw1500", Sw1500LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sw1LocomotiveEntity>> LOCO_DIESEL_SW1 =
            stock("loco_diesel_sw1", Sw1LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Dash840cLocomotiveEntity>> LOCO_DIESEL_DASH8_40C =
            stock("loco_diesel_dash8_40c", Dash840cLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sw1200LocomotiveEntity>> LOCO_DIESEL_SW1200 =
            stock("loco_diesel_sw1200", Sw1200LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Rsd15LocomotiveEntity>> LOCO_DIESEL_RSD15 =
            stock("loco_diesel_rsd15", Rsd15LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Sd70macLocomotiveEntity>> LOCO_DIESEL_SD70MAC =
            stock("loco_diesel_sd70mac", Sd70macLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Dash944cwLocomotiveEntity>> LOCO_DIESEL_DASH9_44CW =
            stock("loco_diesel_dash9_44cw", Dash944cwLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Dash840bbLocomotiveEntity>> LOCO_DIESEL_DASH8_40BB =
            stock("loco_diesel_dash8_40bb", Dash840bbLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Dash840bwLocomotiveEntity>> LOCO_DIESEL_DASH8_40BW =
            stock("loco_diesel_dash8_40bw", Dash840bwLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Dh643LocomotiveEntity>> LOCO_DIESEL_DH643 =
            stock("loco_diesel_dh643", Dh643LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapCf7LocomotiveEntity>> LOCO_DIESEL_BAP_CF7 =
            stock("loco_diesel_bap_cf7", BapCf7LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cf7roundLocomotiveEntity>> LOCO_DIESEL_CF7ROUND =
            stock("loco_diesel_cf7round", Cf7roundLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gp38dash9wLocomotiveEntity>> LOCO_DIESEL_GP38DASH9W =
            stock("loco_diesel_gp38dash9w", Gp38dash9wLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AlcoS2LocomotiveEntity>> LOCO_DIESEL_ALCO_S2 =
            stock("loco_diesel_alco_s2", AlcoS2LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BeepLocomotiveEntity>> LOCO_DIESEL_BEEP =
            stock("loco_diesel_beep", BeepLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class158LocomotiveEntity>> LOCO_DIESEL_CLASS158 =
            stock("loco_diesel_class158", Class158LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class153LocomotiveEntity>> LOCO_DIESEL_CLASS153 =
            stock("loco_diesel_class153", Class153LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class156LocomotiveEntity>> LOCO_DIESEL_CLASS156 =
            stock("loco_diesel_class156", Class156LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class47LocomotiveEntity>> LOCO_DIESEL_CLASS47 =
            stock("loco_diesel_class47", Class47LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AlcoPa1LocomotiveEntity>> LOCO_DIESEL_ALCO_PA1 =
            stock("loco_diesel_alco_pa1", AlcoPa1LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AlcoPb1LocomotiveEntity>> LOCO_DIESEL_ALCO_PB1 =
            stock("loco_diesel_alco_pb1", AlcoPb1LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Emde8aLocomotiveEntity>> LOCO_DIESEL_EMDE8A =
            stock("loco_diesel_emde8a", Emde8aLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Emde8bLocomotiveEntity>> LOCO_DIESEL_EMDE8B =
            stock("loco_diesel_emde8b", Emde8bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class43LocomotiveEntity>> LOCO_DIESEL_CLASS43 =
            stock("loco_diesel_class43", Class43LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C415hLocomotiveEntity>> LOCO_DIESEL_C415H =
            stock("loco_diesel_c415h", C415hLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C415sLocomotiveEntity>> LOCO_DIESEL_C415S =
            stock("loco_diesel_c415s", C415sLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<C415lLocomotiveEntity>> LOCO_DIESEL_C415L =
            stock("loco_diesel_c415l", C415lLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ge25tonLocomotiveEntity>> LOCO_DIESEL_GE25TON =
            stock("loco_diesel_ge25ton", Ge25tonLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<H2466LocomotiveEntity>> LOCO_DIESEL_H24_66 =
            stock("loco_diesel_h24_66", H2466LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<H2466lLocomotiveEntity>> LOCO_DIESEL_H24_66L =
            stock("loco_diesel_h24_66l", H2466lLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Emde7aLocomotiveEntity>> LOCO_DIESEL_EMDE7A =
            stock("loco_diesel_emde7a", Emde7aLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Emde7bLocomotiveEntity>> LOCO_DIESEL_EMDE7B =
            stock("loco_diesel_emde7b", Emde7bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class175LocomotiveEntity>> LOCO_DIESEL_CLASS175 =
            stock("loco_diesel_class175", Class175LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<H1666LocomotiveEntity>> LOCO_DIESEL_H16_66 =
            stock("loco_diesel_h16_66", H1666LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class34LocomotiveEntity>> LOCO_DIESEL_CLASS34 =
            stock("loco_diesel_class34", Class34LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class121BubblecarLocomotiveEntity>> LOCO_DIESEL_CLASS121_BUBBLECAR =
            stock("loco_diesel_class121_bubblecar", Class121BubblecarLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class117LocomotiveEntity>> LOCO_DIESEL_CLASS117 =
            stock("loco_diesel_class117", Class117LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class143FrontLocomotiveEntity>> LOCO_DIESEL_CLASS143_FRONT =
            stock("loco_diesel_class143_front", Class143FrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cd754LocomotiveEntity>> LOCO_DIESEL_CD754 =
            stock("loco_diesel_cd754", Cd754LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class142FrontLocomotiveEntity>> LOCO_DIESEL_CLASS142_FRONT =
            stock("loco_diesel_class142_front", Class142FrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BagnallLocomotiveEntity>> LOCO_DIESEL_BAGNALL =
            stock("loco_diesel_bagnall", BagnallLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class205LocomotiveEntity>> LOCO_DIESEL_CLASS205 =
            stock("loco_diesel_class205", Class205LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MineTrainLocomotiveEntity>> LOCO_ELECTRIC_MINE_TRAIN =
            stock("loco_electric_mine_train", MineTrainLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SpeedZeroEdLocomotiveEntity>> LOCO_ELECTRIC_SPEED_ZERO_ED =
            stock("loco_electric_speed_zero_ed", SpeedZeroEdLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ice1LocomotiveEntity>> LOCO_ELECTRIC_ICE1 =
            stock("loco_electric_ice1", Ice1LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TramYellowLocomotiveEntity>> LOCO_ELECTRIC_TRAM_YELLOW =
            stock("loco_electric_tram_yellow", TramYellowLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TramNyLocomotiveEntity>> LOCO_ELECTRIC_TRAM_NY =
            stock("loco_electric_tram_ny", TramNyLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<E103LocomotiveEntity>> LOCO_ELECTRIC_E103 =
            stock("loco_electric_e103", E103LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class85LocomotiveEntity>> LOCO_ELECTRIC_CLASS85 =
            stock("loco_electric_class85", Class85LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cd151LocomotiveEntity>> LOCO_ELECTRIC_CD151 =
            stock("loco_electric_cd151", Cd151LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Bp4LocomotiveEntity>> LOCO_ELECTRIC_BP4 =
            stock("loco_electric_bp4", Bp4LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Renfe446MotorLocomotiveEntity>> LOCO_ELECTRIC_RENFE446_MOTOR =
            stock("loco_electric_renfe446_motor", Renfe446MotorLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Pch120LocomotiveEntity>> LOCO_ELECTRIC_PCH120 =
            stock("loco_electric_pch120", Pch120LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<LuEngineLocomotiveEntity>> LOCO_ELECTRIC_LU_ENGINE =
            stock("loco_electric_lu_engine", LuEngineLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DstockEngineLocomotiveEntity>> LOCO_ELECTRIC_DSTOCK_ENGINE =
            stock("loco_electric_dstock_engine", DstockEngineLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class345LocomotiveEntity>> LOCO_ELECTRIC_CLASS345 =
            stock("loco_electric_class345", Class345LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BnlrvALocomotiveEntity>> LOCO_ELECTRIC_BNLRV_A =
            stock("loco_electric_bnlrv_a", BnlrvALocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Tw305LocomotiveEntity>> LOCO_ELECTRIC_TW305 =
            stock("loco_electric_tw305", Tw305LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Metro2000LocomotiveEntity>> LOCO_ELECTRIC_METRO2000 =
            stock("loco_electric_metro2000", Metro2000LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Renfe450MotorLocomotiveEntity>> LOCO_ELECTRIC_RENFE450_MOTOR =
            stock("loco_electric_renfe450_motor", Renfe450MotorLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Br155LocomotiveEntity>> LOCO_ELECTRIC_BR155 =
            stock("loco_electric_br155", Br155LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Loco440rFrontLocomotiveEntity>> LOCO_ELECTRIC_440R_FRONT =
            stock("loco_electric_440r_front", Loco440rFrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Db143LocomotiveEntity>> LOCO_ELECTRIC_DB143 =
            stock("loco_electric_db143", Db143LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ef1LocomotiveEntity>> LOCO_ELECTRIC_EF1 =
            stock("loco_electric_ef1", Ef1LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ef1bLocomotiveEntity>> LOCO_ELECTRIC_EF1B =
            stock("loco_electric_ef1b", Ef1bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ep1aLocomotiveEntity>> LOCO_ELECTRIC_EP1A =
            stock("loco_electric_ep1a", Ep1aLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<IlmaLocomotiveEntity>> LOCO_ELECTRIC_ILMA =
            stock("loco_electric_ilma", IlmaLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<IlmbLocomotiveEntity>> LOCO_ELECTRIC_ILMB =
            stock("loco_electric_ilmb", IlmbLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Feve3300frontLocomotiveEntity>> LOCO_ELECTRIC_FEVE3300FRONT =
            stock("loco_electric_feve3300front", Feve3300frontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Eu07LocomotiveEntity>> LOCO_ELECTRIC_EU07 =
            stock("loco_electric_eu07", Eu07LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gm6cLocomotiveEntity>> LOCO_ELECTRIC_GM6C =
            stock("loco_electric_gm6c", Gm6cLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class319EngineLocomotiveEntity>> LOCO_ELECTRIC_CLASS319_ENGINE =
            stock("loco_electric_class319_engine", Class319EngineLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Kvb2300LocomotiveEntity>> LOCO_ELECTRIC_KVB_2300 =
            stock("loco_electric_kvb_2300", Kvb2300LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk2fDbsoLocomotiveEntity>> LOCO_ELECTRIC_BR_MK2F_DBSO =
            stock("loco_electric_br_mk2f_dbso", BrMk2fDbsoLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk3DvtLocomotiveEntity>> LOCO_ELECTRIC_BR_MK3_DVT =
            stock("loco_electric_br_mk3_dvt", BrMk3DvtLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk4DvtLocomotiveEntity>> LOCO_ELECTRIC_BR_MK4_DVT =
            stock("loco_electric_br_mk4_dvt", BrMk4DvtLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class90LocomotiveEntity>> LOCO_ELECTRIC_CLASS90 =
            stock("loco_electric_class90", Class90LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class91LocomotiveEntity>> LOCO_ELECTRIC_CLASS91 =
            stock("loco_electric_class91", Class91LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class321LocomotiveEntity>> LOCO_ELECTRIC_CLASS321 =
            stock("loco_electric_class321", Class321LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<NmbsHle18LocomotiveEntity>> LOCO_ELECTRIC_NMBS_HLE_18 =
            stock("loco_electric_nmbs_hle_18", NmbsHle18LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Fgv4300MotorLocomotiveEntity>> LOCO_ELECTRIC_FGV4300_MOTOR =
            stock("loco_electric_fgv4300_motor", Fgv4300MotorLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<InterurbanSeries100LocomotiveEntity>> LOCO_ELECTRIC_INTERURBAN_SERIES_100 =
            stock("loco_electric_interurban_series_100", InterurbanSeries100LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Metro3000LocomotiveEntity>> LOCO_ELECTRIC_METRO3000 =
            stock("loco_electric_metro3000", Metro3000LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cq310LocomotiveEntity>> LOCO_ELECTRIC_CQ310 =
            stock("loco_electric_cq310", Cq310LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class162EngineLocomotiveEntity>> LOCO_ELECTRIC_CLASS162_ENGINE =
            stock("loco_electric_class162_engine", Class162EngineLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MetalTramLocomotiveEntity>> LOCO_ELECTRIC_METAL_TRAM =
            stock("loco_electric_metal_tram", MetalTramLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<B80cALocomotiveEntity>> LOCO_ELECTRIC_B80C_A =
            stock("loco_electric_b80c_a", B80cALocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ma100LocoLocomotiveEntity>> LOCO_ELECTRIC_MA100_LOCO =
            stock("loco_electric_ma100_loco", Ma100LocoLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class390FrontLocomotiveEntity>> LOCO_ELECTRIC_CLASS390_FRONT =
            stock("loco_electric_class390_front", Class390FrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DuewagT4erLocomotiveEntity>> LOCO_ELECTRIC_DUEWAG_T4ER =
            stock("loco_electric_duewag_t4er", DuewagT4erLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DuewagGt6zrLocomotiveEntity>> LOCO_ELECTRIC_DUEWAG_GT6ZR =
            stock("loco_electric_duewag_gt6zr", DuewagGt6zrLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<M8cLocomotiveEntity>> LOCO_ELECTRIC_M8C =
            stock("loco_electric_m8c", M8cLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class416LocoLocomotiveEntity>> LOCO_ELECTRIC_CLASS416_LOCO =
            stock("loco_electric_class416_loco", Class416LocoLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Db420LocoLocomotiveEntity>> LOCO_ELECTRIC_DB420_LOCO =
            stock("loco_electric_db420_loco", Db420LocoLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class401EngineLocomotiveEntity>> LOCO_ELECTRIC_CLASS401_ENGINE =
            stock("loco_electric_class401_engine", Class401EngineLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class230EngineLocomotiveEntity>> LOCO_ELECTRIC_CLASS230_ENGINE =
            stock("loco_electric_class230_engine", Class230EngineLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DuewagGt6erLocomotiveEntity>> LOCO_ELECTRIC_DUEWAG_GT6ER =
            stock("loco_electric_duewag_gt6er", DuewagGt6erLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class374FrontLocomotiveEntity>> LOCO_ELECTRIC_CLASS374_FRONT =
            stock("loco_electric_class374_front", Class374FrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class387FrontLocomotiveEntity>> LOCO_ELECTRIC_CLASS387_FRONT =
            stock("loco_electric_class387_front", Class387FrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class378FrontLocomotiveEntity>> LOCO_ELECTRIC_CLASS378_FRONT =
            stock("loco_electric_class378_front", Class378FrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class389FrontLocomotiveEntity>> LOCO_ELECTRIC_CLASS389_FRONT =
            stock("loco_electric_class389_front", Class389FrontLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class442DtsLocomotiveEntity>> LOCO_ELECTRIC_CLASS442_DTS =
            stock("loco_electric_class442_dts", Class442DtsLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<M8Dnf1LocomotiveEntity>> LOCO_ELECTRIC_M8_DNF1 =
            stock("loco_electric_m8_dnf1", M8Dnf1LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Vl10LocomotiveEntity>> LOCO_ELECTRIC_VL10 =
            stock("loco_electric_vl10", Vl10LocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CartBlueCarEntity>> PASSENGER_CART_BLUE =
            stock("passenger_cart_blue", CartBlueCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CartBlackSmallCarEntity>> PASSENGER_CART_BLACK_SMALL =
            stock("passenger_cart_black_small", CartBlackSmallCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<LongGreenCarEntity>> PASSENGER_LONG_GREEN =
            stock("passenger_long_green", LongGreenCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ShortGreenCarEntity>> PASSENGER_SHORT_GREEN =
            stock("passenger_short_green", ShortGreenCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Car1classDbCarEntity>> PASSENGER_1CLASS_DB =
            stock("passenger_1class_db", Car1classDbCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Car2classDbCarEntity>> PASSENGER_2CLASS_DB =
            stock("passenger_2class_db", Car2classDbCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HighSpeedZeroEdCarEntity>> PASSENGER_HIGH_SPEED_ZERO_ED =
            stock("passenger_high_speed_zero_ed", HighSpeedZeroEdCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TramNyCarEntity>> PASSENGER_TRAM_NY =
            stock("passenger_tram_ny", TramNyCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AdlerCarEntity>> PASSENGER_ADLER =
            stock("passenger_adler", AdlerCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DbOrientalCarEntity>> PASSENGER_DB_ORIENTAL =
            stock("passenger_db_oriental", DbOrientalCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ic4DsbFgCarEntity>> PASSENGER_IC4_DSB_FG =
            stock("passenger_ic4_dsb_fg", Ic4DsbFgCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ic4DsbFhCarEntity>> PASSENGER_IC4_DSB_FH =
            stock("passenger_ic4_dsb_fh", Ic4DsbFhCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ice1Class1CarEntity>> PASSENGER_ICE1_CLASS1 =
            stock("passenger_ice1_class1", Ice1Class1CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ice1Class2CarEntity>> PASSENGER_ICE1_CLASS2 =
            stock("passenger_ice1_class2", Ice1Class2CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ice1RestaurantCarEntity>> PASSENGER_ICE1_RESTAURANT =
            stock("passenger_ice1_restaurant", Ice1RestaurantCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gs4CarEntity>> PASSENGER_GS4 =
            stock("passenger_gs4", Gs4CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gs4ObservatoryCarEntity>> PASSENGER_GS4_OBSERVATORY =
            stock("passenger_gs4_observatory", Gs4ObservatoryCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gs4TailCarEntity>> PASSENGER_GS4_TAIL =
            stock("passenger_gs4_tail", Gs4TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DenverRioGrangeCarEntity>> PASSENGER_DENVER_RIO_GRANGE =
            stock("passenger_denver_rio_grange", DenverRioGrangeCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DenverRioGrandeComboCarEntity>> PASSENGER_DENVER_RIO_GRANDE_COMBO =
            stock("passenger_denver_rio_grande_combo", DenverRioGrandeComboCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RheingoldCarEntity>> PASSENGER_RHEINGOLD =
            stock("passenger_rheingold", RheingoldCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RheingoldPanoramaCarEntity>> PASSENGER_RHEINGOLD_PANORAMA =
            stock("passenger_rheingold_panorama", RheingoldPanoramaCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MilwCarEntity>> PASSENGER_MILW =
            stock("passenger_milw", MilwCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MilwTailCarEntity>> PASSENGER_MILW_TAIL =
            stock("passenger_milw_tail", MilwTailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BambooCarEntity>> PASSENGER_BAMBOO =
            stock("passenger_bamboo", BambooCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Renfe446CoachCarEntity>> PASSENGER_RENFE446_COACH =
            stock("passenger_renfe446_coach", Renfe446CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CabooseRenfe446TailCarEntity>> PASSENGER_CABOOSE_RENFE446_TAIL =
            stock("passenger_caboose_renfe446_tail", CabooseRenfe446TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Pch120coachCarEntity>> PASSENGER_PCH120COACH =
            stock("passenger_pch120coach", Pch120coachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<LUpassengerCarEntity>> PASSENGER_L_UPASSENGER =
            stock("passenger_l_upassenger", LUpassengerCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DstockPassengerCarEntity>> PASSENGER_DSTOCK_PASSENGER =
            stock("passenger_dstock_passenger", DstockPassengerCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class345CoachCarEntity>> PASSENGER_CLASS345_COACH =
            stock("passenger_class345_coach", Class345CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ps52SeatCoachCarEntity>> PASSENGER_PS52_SEAT_COACH =
            stock("passenger_ps52_seat_coach", Ps52SeatCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PScenterDinerCarEntity>> PASSENGER_P_SCENTER_DINER =
            stock("passenger_p_scenter_diner", PScenterDinerCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsAnotherDinerCarEntity>> PASSENGER_PS_ANOTHER_DINER =
            stock("passenger_ps_another_diner", PsAnotherDinerCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BnlrvBCarEntity>> PASSENGER_BNLRV_B =
            stock("passenger_bnlrv_b", BnlrvBCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Bw305CarEntity>> PASSENGER_BW305 =
            stock("passenger_bw305", Bw305CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Metro2000CarEntity>> PASSENGER_METRO2000 =
            stock("passenger_metro2000", Metro2000CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Renfe450CoachCarEntity>> PASSENGER_RENFE450_COACH =
            stock("passenger_renfe450_coach", Renfe450CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CabooseRenfe450TailCarEntity>> PASSENGER_CABOOSE_RENFE450_TAIL =
            stock("passenger_caboose_renfe450_tail", CabooseRenfe450TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cd014CarEntity>> PASSENGER_CD014 =
            stock("passenger_cd014", Cd014CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cd914CarEntity>> PASSENGER_CD914 =
            stock("passenger_cd914", Cd914CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cd010CarEntity>> PASSENGER_CD010 =
            stock("passenger_cd010", Cd010CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AmfleetCarEntity>> PASSENGER_AMFLEET =
            stock("passenger_amfleet", AmfleetCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Amfleet2CarEntity>> PASSENGER_AMFLEET2 =
            stock("passenger_amfleet2", Amfleet2CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<StarCarFatCarEntity>> PASSENGER_STAR_CAR_FAT =
            stock("passenger_star_car_fat", StarCarFatCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<StarCarNotFatCarEntity>> PASSENGER_STAR_CAR_NOT_FAT =
            stock("passenger_star_car_not_fat", StarCarNotFatCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Car440RMidCarEntity>> PASSENGER_440_R_MID =
            stock("passenger_440_r_mid", Car440RMidCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Car440RRearCarEntity>> PASSENGER_440_R_REAR =
            stock("passenger_440_r_rear", Car440RRearCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Feve3300rearCarEntity>> PASSENGER_FEVE3300REAR =
            stock("passenger_feve3300rear", Feve3300rearCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class158CoachCarEntity>> PASSENGER_CLASS158_COACH =
            stock("passenger_class158_coach", Class158CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class153CoachCarEntity>> PASSENGER_CLASS153_COACH =
            stock("passenger_class153_coach", Class153CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsSleeper565CarEntity>> PASSENGER_PS_SLEEPER565 =
            stock("passenger_ps_sleeper565", PsSleeper565CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsSleeper565DrgwCarEntity>> PASSENGER_PS_SLEEPER565_DRGW =
            stock("passenger_ps_sleeper565_drgw", PsSleeper565DrgwCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SncbM6CarEntity>> PASSENGER_SNCB_M6 =
            stock("passenger_sncb_m6", SncbM6CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SncbM6TailCarEntity>> PASSENGER_SNCB_M6_TAIL =
            stock("passenger_sncb_m6_tail", SncbM6TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class319MiddleCarEntity>> PASSENGER_CLASS319_MIDDLE =
            stock("passenger_class319_middle", Class319MiddleCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class319PantoCarEntity>> PASSENGER_CLASS319_PANTO =
            stock("passenger_class319_panto", Class319PantoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class319TailCarEntity>> PASSENGER_CLASS319_TAIL =
            stock("passenger_class319_tail", Class319TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Kvb2300BCarEntity>> PASSENGER_KVB_2300_B =
            stock("passenger_kvb_2300_b", Kvb2300BCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk2CBsoCarEntity>> PASSENGER_BR_MK2_C_BSO =
            stock("passenger_br_mk2_c_bso", BrMk2CBsoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk2CCoachCarEntity>> PASSENGER_BR_MK2_C_COACH =
            stock("passenger_br_mk2_c_coach", BrMk2CCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk2FBsoCarEntity>> PASSENGER_BR_MK2_F_BSO =
            stock("passenger_br_mk2_f_bso", BrMk2FBsoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk2FCoachCarEntity>> PASSENGER_BR_MK2_F_COACH =
            stock("passenger_br_mk2_f_coach", BrMk2FCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk3BuffetCarEntity>> PASSENGER_BR_MK3_BUFFET =
            stock("passenger_br_mk3_buffet", BrMk3BuffetCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk3CoachCarEntity>> PASSENGER_BR_MK3_COACH =
            stock("passenger_br_mk3_coach", BrMk3CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk3aCoachCarEntity>> PASSENGER_BR_MK3A_COACH =
            stock("passenger_br_mk3a_coach", BrMk3aCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk3PantographCarEntity>> PASSENGER_BR_MK3_PANTOGRAPH =
            stock("passenger_br_mk3_pantograph", BrMk3PantographCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk4CoachCarEntity>> PASSENGER_BR_MK4_COACH =
            stock("passenger_br_mk4_coach", BrMk4CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk4BuffetCarEntity>> PASSENGER_BR_MK4_BUFFET =
            stock("passenger_br_mk4_buffet", BrMk4BuffetCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class321MotorCarEntity>> PASSENGER_CLASS321_MOTOR =
            stock("passenger_class321_motor", Class321MotorCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class321CoachCarEntity>> PASSENGER_CLASS321_COACH =
            stock("passenger_class321_coach", Class321CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MinetrainCarEntity>> PASSENGER_MINETRAIN =
            stock("passenger_minetrain", MinetrainCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsLunchCounterLoungeCarEntity>> PASSENGER_PS_LUNCH_COUNTER_LOUNGE =
            stock("passenger_ps_lunch_counter_lounge", PsLunchCounterLoungeCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ps30SeatParlorCarEntity>> PASSENGER_PS30_SEAT_PARLOR =
            stock("passenger_ps30_seat_parlor", Ps30SeatParlorCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ps54SeatCoachLoungeCarEntity>> PASSENGER_PS54_SEAT_COACH_LOUNGE =
            stock("passenger_ps54_seat_coach_lounge", Ps54SeatCoachLoungeCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ps58SeatCoachObservationCarEntity>> PASSENGER_PS58_SEAT_COACH_OBSERVATION =
            stock("passenger_ps58_seat_coach_observation", Ps58SeatCoachObservationCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Psbm56SeatCoachCarEntity>> PASSENGER_PSBM56_SEAT_COACH =
            stock("passenger_psbm56_seat_coach", Psbm56SeatCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsbmCombineCarEntity>> PASSENGER_PSBM_COMBINE =
            stock("passenger_psbm_combine", PsbmCombineCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsbmDinerLoungeCarEntity>> PASSENGER_PSBM_DINER_LOUNGE =
            stock("passenger_psbm_diner_lounge", PsbmDinerLoungeCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk1BsoCarEntity>> PASSENGER_BR_MK1_BSO =
            stock("passenger_br_mk1_bso", BrMk1BsoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk1TsoCarEntity>> PASSENGER_BR_MK1_TSO =
            stock("passenger_br_mk1_tso", BrMk1TsoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk1BuffetCarEntity>> PASSENGER_BR_MK1_BUFFET =
            stock("passenger_br_mk1_buffet", BrMk1BuffetCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk1BaggageCarEntity>> PASSENGER_BR_MK1_BAGGAGE =
            stock("passenger_br_mk1_baggage", BrMk1BaggageCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class175CoachCarEntity>> PASSENGER_CLASS175_COACH =
            stock("passenger_class175_coach", Class175CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Acfgn60SeatCoachCarEntity>> PASSENGER_ACFGN60_SEAT_COACH =
            stock("passenger_acfgn60_seat_coach", Acfgn60SeatCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Fgv4300CoachCarEntity>> PASSENGER_FGV4300_COACH =
            stock("passenger_fgv4300_coach", Fgv4300CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Fgv4300TailCarEntity>> PASSENGER_FGV4300_TAIL =
            stock("passenger_fgv4300_tail", Fgv4300TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Metro3000CarEntity>> PASSENGER_METRO3000 =
            stock("passenger_metro3000", Metro3000CarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Metro3000TailCarEntity>> PASSENGER_METRO3000_TAIL =
            stock("passenger_metro3000_tail", Metro3000TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class162CoachBCarEntity>> PASSENGER_CLASS162_COACH_B =
            stock("passenger_class162_coach_b", Class162CoachBCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class162CoachACarEntity>> PASSENGER_CLASS162_COACH_A =
            stock("passenger_class162_coach_a", Class162CoachACarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class162TailCarEntity>> PASSENGER_CLASS162_TAIL =
            stock("passenger_class162_tail", Class162TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MetalTramCoachCarEntity>> PASSENGER_METAL_TRAM_COACH =
            stock("passenger_metal_tram_coach", MetalTramCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<B80CBCarEntity>> PASSENGER_B80_C_B =
            stock("passenger_b80_c_b", B80CBCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<WoodenTramCoachCarEntity>> PASSENGER_WOODEN_TRAM_COACH =
            stock("passenger_wooden_tram_coach", WoodenTramCoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ma100TailCarEntity>> PASSENGER_MA100_TAIL =
            stock("passenger_ma100_tail", Ma100TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class390CoachCarEntity>> PASSENGER_CLASS390_COACH =
            stock("passenger_class390_coach", Class390CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class390PantoCarEntity>> PASSENGER_CLASS390_PANTO =
            stock("passenger_class390_panto", Class390PantoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class121TrailerCarEntity>> PASSENGER_CLASS121_TRAILER =
            stock("passenger_class121_trailer", Class121TrailerCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class117MiddleCarEntity>> PASSENGER_CLASS117_MIDDLE =
            stock("passenger_class117_middle", Class117MiddleCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrBrakeVanCarEntity>> PASSENGER_BR_BRAKE_VAN =
            stock("passenger_br_brake_van", BrBrakeVanCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DuewagGt6ZrTailCarEntity>> PASSENGER_DUEWAG_GT6_ZR_TAIL =
            stock("passenger_duewag_gt6_zr_tail", DuewagGt6ZrTailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<M8CTailCarEntity>> PASSENGER_M8_C_TAIL =
            stock("passenger_m8_c_tail", M8CTailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class416TailCarEntity>> PASSENGER_CLASS416_TAIL =
            stock("passenger_class416_tail", Class416TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Db420MiddleCarEntity>> PASSENGER_DB420_MIDDLE =
            stock("passenger_db420_middle", Db420MiddleCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Db420TailCarEntity>> PASSENGER_DB420_TAIL =
            stock("passenger_db420_tail", Db420TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class401TailCarEntity>> PASSENGER_CLASS401_TAIL =
            stock("passenger_class401_tail", Class401TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Car10tonBrakeVanCarEntity>> PASSENGER_10TON_BRAKE_VAN =
            stock("passenger_10ton_brake_van", Car10tonBrakeVanCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class230MiddleCarEntity>> PASSENGER_CLASS230_MIDDLE =
            stock("passenger_class230_middle", Class230MiddleCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DuewagGt6ErTailCarEntity>> PASSENGER_DUEWAG_GT6_ER_TAIL =
            stock("passenger_duewag_gt6_er_tail", DuewagGt6ErTailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class143RearCarEntity>> PASSENGER_CLASS143_REAR =
            stock("passenger_class143_rear", Class143RearCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class143MiddleCarEntity>> PASSENGER_CLASS143_MIDDLE =
            stock("passenger_class143_middle", Class143MiddleCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class374PremierPantoCarEntity>> PASSENGER_CLASS374_PREMIER_PANTO =
            stock("passenger_class374_premier_panto", Class374PremierPantoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class374StandardPantoCarEntity>> PASSENGER_CLASS374_STANDARD_PANTO =
            stock("passenger_class374_standard_panto", Class374StandardPantoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class374BuffetCarEntity>> PASSENGER_CLASS374_BUFFET =
            stock("passenger_class374_buffet", Class374BuffetCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class387CoachCarEntity>> PASSENGER_CLASS387_COACH =
            stock("passenger_class387_coach", Class387CoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class387PantoCarEntity>> PASSENGER_CLASS387_PANTO =
            stock("passenger_class387_panto", Class387PantoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class387TailCarEntity>> PASSENGER_CLASS387_TAIL =
            stock("passenger_class387_tail", Class387TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class378MiddleCarEntity>> PASSENGER_CLASS378_MIDDLE =
            stock("passenger_class378_middle", Class378MiddleCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class378TailCarEntity>> PASSENGER_CLASS378_TAIL =
            stock("passenger_class378_tail", Class378TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class142TailCarEntity>> PASSENGER_CLASS142_TAIL =
            stock("passenger_class142_tail", Class142TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class389MiddleCarEntity>> PASSENGER_CLASS389_MIDDLE =
            stock("passenger_class389_middle", Class389MiddleCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class389TailCarEntity>> PASSENGER_CLASS389_TAIL =
            stock("passenger_class389_tail", Class389TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class442TsCarEntity>> PASSENGER_CLASS442_TS =
            stock("passenger_class442_ts", Class442TsCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class442MblsCarEntity>> PASSENGER_CLASS442_MBLS =
            stock("passenger_class442_mbls", Class442MblsCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class205tsoCarEntity>> PASSENGER_CLASS205TSO =
            stock("passenger_class205tso", Class205tsoCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Class205TailCarEntity>> PASSENGER_CLASS205_TAIL =
            stock("passenger_class205_tail", Class205TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<M8Dnf1MiddlelongCarEntity>> PASSENGER_M8_DNF1_MIDDLELONG =
            stock("passenger_m8_dnf1_middlelong", M8Dnf1MiddlelongCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<M8Dnf1MiddleshortCarEntity>> PASSENGER_M8_DNF1_MIDDLESHORT =
            stock("passenger_m8_dnf1_middleshort", M8Dnf1MiddleshortCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<M8Dnf1TailCarEntity>> PASSENGER_M8_DNF1_TAIL =
            stock("passenger_m8_dnf1_tail", M8Dnf1TailCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CartRedFreightEntity>> FREIGHT_CART_RED =
            stock("freight_cart_red", CartRedFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<WoodFreightEntity>> FREIGHT_WOOD =
            stock("freight_wood", WoodFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HopperFreightEntity>> FREIGHT_HOPPER =
            stock("freight_hopper", HopperFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<KClassRailBoxFreightEntity>> FREIGHT_K_CLASS_RAIL_BOX =
            stock("freight_k_class_rail_box", KClassRailBoxFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ShortCoveredHopperFreightEntity>> FREIGHT_SHORT_COVERED_HOPPER =
            stock("freight_short_covered_hopper", ShortCoveredHopperFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<LongCoveredHopperFreightEntity>> FREIGHT_LONG_COVERED_HOPPER =
            stock("freight_long_covered_hopper", LongCoveredHopperFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<OpenWagonFreightEntity>> FREIGHT_OPEN_WAGON =
            stock("freight_open_wagon", OpenWagonFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HopperUsFreightEntity>> FREIGHT_HOPPER_US =
            stock("freight_hopper_us", HopperUsFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Car100TonHopperFreightEntity>> FREIGHT_100_TON_HOPPER =
            stock("freight_100_ton_hopper", Car100TonHopperFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCartWoodUsFreightEntity>> FREIGHT_FLAT_CART_WOOD_US =
            stock("freight_flat_cart_wood_us", FlatCartWoodUsFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BulkheadFlatCartWoodFreightEntity>> FREIGHT_BULKHEAD_FLAT_CART_WOOD =
            stock("freight_bulkhead_flat_cart_wood", BulkheadFlatCartWoodFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CartUsFreightEntity>> FREIGHT_CART_US =
            stock("freight_cart_us", CartUsFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BoxCartUsFreightEntity>> FREIGHT_BOX_CART_US =
            stock("freight_box_cart_us", BoxCartUsFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BoxCartPrrFreightEntity>> FREIGHT_BOX_CART_PRR =
            stock("freight_box_cart_prr", BoxCartPrrFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CartSmallFreightEntity>> FREIGHT_CART_SMALL =
            stock("freight_cart_small", CartSmallFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Minetrain2FreightEntity>> FREIGHT_MINETRAIN_2 =
            stock("freight_minetrain_2", Minetrain2FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GtngFreightEntity>> FREIGHT_GTNG =
            stock("freight_gtng", GtngFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCartWoodLogsFreightEntity>> FREIGHT_FLAT_CART_WOOD_LOGS =
            stock("freight_flat_cart_wood_logs", FlatCartWoodLogsFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ClosedRedBrownFreightEntity>> FREIGHT_CLOSED_RED_BROWN =
            stock("freight_closed_red_brown", ClosedRedBrownFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<OpenRedBrownFreightEntity>> FREIGHT_OPEN_RED_BROWN =
            stock("freight_open_red_brown", OpenRedBrownFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<WagenDbFreightEntity>> FREIGHT_WAGEN_DB =
            stock("freight_wagen_db", WagenDbFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCarRailsDbFreightEntity>> FREIGHT_FLAT_CAR_RAILS_DB =
            stock("freight_flat_car_rails_db", FlatCarRailsDbFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<AstfAutorackFreightEntity>> FREIGHT_ASTF_AUTORACK =
            stock("freight_astf_autorack", AstfAutorackFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCarLogsDbFreightEntity>> FREIGHT_FLAT_CAR_LOGS_DB =
            stock("freight_flat_car_logs_db", FlatCarLogsDbFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SlateWagonFreightEntity>> FREIGHT_SLATE_WAGON =
            stock("freight_slate_wagon", SlateWagonFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<IceWagonFreightEntity>> FREIGHT_ICE_WAGON =
            stock("freight_ice_wagon", IceWagonFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CartGs4FreightEntity>> FREIGHT_CART_GS4 =
            stock("freight_cart_gs4", CartGs4FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GondolaDbFreightEntity>> FREIGHT_GONDOLA_DB =
            stock("freight_gondola_db", GondolaDbFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CenterBeamEmptyFreightEntity>> FREIGHT_CENTER_BEAM_EMPTY =
            stock("freight_center_beam_empty", CenterBeamEmptyFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CenterBeamWood1FreightEntity>> FREIGHT_CENTER_BEAM_WOOD1 =
            stock("freight_center_beam_wood1", CenterBeamWood1FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CenterBeamWood2FreightEntity>> FREIGHT_CENTER_BEAM_WOOD2 =
            stock("freight_center_beam_wood2", CenterBeamWood2FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<WellcarFreightEntity>> FREIGHT_WELLCAR =
            stock("freight_wellcar", WellcarFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TrailerFreightEntity>> FREIGHT_TRAILER =
            stock("freight_trailer", TrailerFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DenverRioGrange2FreightEntity>> FREIGHT_DENVER_RIO_GRANGE_2 =
            stock("freight_denver_rio_grange_2", DenverRioGrange2FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MilwBaggageFreightEntity>> FREIGHT_MILW_BAGGAGE =
            stock("freight_milw_baggage", MilwBaggageFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HeavyweightFreightEntity>> FREIGHT_HEAVYWEIGHT =
            stock("freight_heavyweight", HeavyweightFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CartBambooFreightEntity>> FREIGHT_CART_BAMBOO =
            stock("freight_cart_bamboo", CartBambooFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GermanPostFreightEntity>> FREIGHT_GERMAN_POST =
            stock("freight_german_post", GermanPostFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DepressedFlatbedFreightEntity>> FREIGHT_DEPRESSED_FLATBED =
            stock("freight_depressed_flatbed", DepressedFlatbedFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CarLFreightEntity>> FREIGHT_CAR_L =
            stock("freight_car_l", CarLFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Heavyweight2FreightEntity>> FREIGHT_HEAVYWEIGHT_2 =
            stock("freight_heavyweight_2", Heavyweight2FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RoundHopperFreightEntity>> FREIGHT_ROUND_HOPPER =
            stock("freight_round_hopper", RoundHopperFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RibbedHopperFreightEntity>> FREIGHT_RIBBED_HOPPER =
            stock("freight_ribbed_hopper", RibbedHopperFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Bap40highcubeFreightEntity>> FREIGHT_BAP40HIGHCUBE =
            stock("freight_bap40highcube", Bap40highcubeFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapWoodchipHopperFreightEntity>> FREIGHT_BAP_WOODCHIP_HOPPER =
            stock("freight_bap_woodchip_hopper", BapWoodchipHopperFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapOreJennyFreightEntity>> FREIGHT_BAP_ORE_JENNY =
            stock("freight_bap_ore_jenny", BapOreJennyFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapMillGondolaFreightEntity>> FREIGHT_BAP_MILL_GONDOLA =
            stock("freight_bap_mill_gondola", BapMillGondolaFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapMilw40boxcarFreightEntity>> FREIGHT_BAP_MILW40BOXCAR =
            stock("freight_bap_milw40boxcar", BapMilw40boxcarFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Bap60centerbeamFreightEntity>> FREIGHT_BAP60CENTERBEAM =
            stock("freight_bap60centerbeam", Bap60centerbeamFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Bap66centerbeamFreightEntity>> FREIGHT_BAP66CENTERBEAM =
            stock("freight_bap66centerbeam", Bap66centerbeamFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Bap73centerbeamFreightEntity>> FREIGHT_BAP73CENTERBEAM =
            stock("freight_bap73centerbeam", Bap73centerbeamFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapPs140FreightEntity>> FREIGHT_BAP_PS140 =
            stock("freight_bap_ps140", BapPs140FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapPs150FreightEntity>> FREIGHT_BAP_PS150 =
            stock("freight_bap_ps150", BapPs150FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapPs160FreightEntity>> FREIGHT_BAP_PS160 =
            stock("freight_bap_ps160", BapPs160FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapVersaLongiFreightEntity>> FREIGHT_BAP_VERSA_LONGI =
            stock("freight_bap_versa_longi", BapVersaLongiFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapVersaTransFreightEntity>> FREIGHT_BAP_VERSA_TRANS =
            stock("freight_bap_versa_trans", BapVersaTransFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Hicube60footFreightEntity>> FREIGHT_HICUBE60FOOT =
            stock("freight_hicube60foot", Hicube60footFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BnsfGonFreightEntity>> FREIGHT_BNSF_GON =
            stock("freight_bnsf_gon", BnsfGonFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Hopper5201FreightEntity>> FREIGHT_HOPPER5201 =
            stock("freight_hopper5201", Hopper5201FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Hopper6260FreightEntity>> FREIGHT_HOPPER6260 =
            stock("freight_hopper6260", Hopper6260FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<SkeletonFreightEntity>> FREIGHT_SKELETON =
            stock("freight_skeleton", SkeletonFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ps73BaggageFreightEntity>> FREIGHT_PS73_BAGGAGE =
            stock("freight_ps73_baggage", Ps73BaggageFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Ps85BaggageFreightEntity>> FREIGHT_PS85_BAGGAGE =
            stock("freight_ps85_baggage", Ps85BaggageFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Reefer64FreightEntity>> FREIGHT_REEFER64 =
            stock("freight_reefer64", Reefer64FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsrpopmFreightEntity>> FREIGHT_PSRPOPM =
            stock("freight_psrpopm", PsrpopmFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsrpoFreightEntity>> FREIGHT_PSRPO =
            stock("freight_psrpo", PsrpoFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BoulderWagonFreightEntity>> FREIGHT_BOULDER_WAGON =
            stock("freight_boulder_wagon", BoulderWagonFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gsi60FootBulkheadFreightEntity>> FREIGHT_GSI60_FOOT_BULKHEAD =
            stock("freight_gsi60_foot_bulkhead", Gsi60FootBulkheadFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Gsc60FootFlatcarFreightEntity>> FREIGHT_GSC60_FOOT_FLATCAR =
            stock("freight_gsc60_foot_flatcar", Gsc60FootFlatcarFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Car5PlankFreightEntity>> FREIGHT_5_PLANK =
            stock("freight_5_plank", Car5PlankFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BrMk1TpoStowageFreightEntity>> FREIGHT_BR_MK1_TPO_STOWAGE =
            stock("freight_br_mk1_tpo_stowage", BrMk1TpoStowageFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Acfgnrpo30FreightEntity>> FREIGHT_ACFGNRPO_30 =
            stock("freight_acfgnrpo_30", Acfgnrpo30FreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<HopperUkFreightEntity>> FREIGHT_HOPPER_UK =
            stock("freight_hopper_uk", HopperUkFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<ExpressFreightVanFreightEntity>> FREIGHT_EXPRESS_FREIGHT_VAN =
            stock("freight_express_freight_van", ExpressFreightVanFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TipperUkFreightEntity>> FREIGHT_TIPPER_UK =
            stock("freight_tipper_uk", TipperUkFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MineralwagonFreightEntity>> FREIGHT_MINERALWAGON =
            stock("freight_mineralwagon", MineralwagonFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<VentilatedVanFreightEntity>> FREIGHT_VENTILATED_VAN =
            stock("freight_ventilated_van", VentilatedVanFreightEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TankWagonDbTankEntity>> TANK_TANK_WAGON_DB =
            stock("tank_tank_wagon_db", TankWagonDbTankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TankThreeDomeTankEntity>> TANK_TANK_THREE_DOME =
            stock("tank_tank_three_dome", TankThreeDomeTankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TankWagonUsTankEntity>> TANK_TANK_WAGON_US =
            stock("tank_tank_wagon_us", TankWagonUsTankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TankWagonGreyTankEntity>> TANK_TANK_WAGON_GREY =
            stock("tank_tank_wagon_grey", TankWagonGreyTankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TankCartLavaTankEntity>> TANK_TANK_CART_LAVA =
            stock("tank_tank_cart_lava", TankCartLavaTankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TankWagonYellowTankEntity>> TANK_TANK_WAGON_YELLOW =
            stock("tank_tank_wagon_yellow", TankWagonYellowTankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapDot11111000TankEntity>> TANK_BAP_DOT11111000 =
            stock("tank_bap_dot11111000", BapDot11111000TankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapDot11120600TankEntity>> TANK_BAP_DOT11120600 =
            stock("tank_bap_dot11120600", BapDot11120600TankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapDot11129080TankEntity>> TANK_BAP_DOT11129080 =
            stock("tank_bap_dot11129080", BapDot11129080TankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TankTankerUkTankEntity>> TANK_TANK_TANKER_UK =
            stock("tank_tank_tanker_uk", TankTankerUkTankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RheingoldDining1WorkCartEntity>> WORK_RHEINGOLD_DINING1 =
            stock("work_rheingold_dining1", RheingoldDining1WorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<RheingoldDining2WorkCartEntity>> WORK_RHEINGOLD_DINING2 =
            stock("work_rheingold_dining2", RheingoldDining2WorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<GwrBrakeVanWorkCartEntity>> WORK_GWR_BRAKE_VAN =
            stock("work_gwr_brake_van", GwrBrakeVanWorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<WorkCartWorkCartEntity>> WORK_WORK_CART =
            stock("work_work_cart", WorkCartWorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<WorkCabooseWorkCartEntity>> WORK_WORK_CABOOSE =
            stock("work_work_caboose", WorkCabooseWorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CabooseLoggingWorkCartEntity>> WORK_CABOOSE_LOGGING =
            stock("work_caboose_logging", CabooseLoggingWorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CabooseLoggingPrrWorkCartEntity>> WORK_CABOOSE_LOGGING_PRR =
            stock("work_caboose_logging_prr", CabooseLoggingPrrWorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MailWagenDbWorkCartEntity>> WORK_MAIL_WAGEN_DB =
            stock("work_mail_wagen_db", MailWagenDbWorkCartEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<StockCarCarEntity>> PASSENGER_STOCK_CAR =
            stock("passenger_stock_car", StockCarCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DrwgStockCarCarEntity>> PASSENGER_DRWG_STOCK_CAR =
            stock("passenger_drwg_stock_car", DrwgStockCarCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<JukeBoxCartCarEntity>> PASSENGER_JUKE_BOX_CART =
            stock("passenger_juke_box_cart", JukeBoxCartCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<TracksBuilderCarEntity>> PASSENGER_TRACKS_BUILDER =
            stock("passenger_tracks_builder", TracksBuilderCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CattleVanCarEntity>> PASSENGER_CATTLE_VAN =
            stock("passenger_cattle_van", CattleVanCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CabooseRedCarEntity>> PASSENGER_CABOOSE_RED =
            stock("passenger_caboose_red", CabooseRedCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<CabooseBlackCarEntity>> PASSENGER_CABOOSE_BLACK =
            stock("passenger_caboose_black", CabooseBlackCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapWVcabooseCarEntity>> PASSENGER_BAP_W_VCABOOSE =
            stock("passenger_bap_w_vcaboose", BapWVcabooseCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<DrgwCabooseCarEntity>> PASSENGER_DRGW_CABOOSE =
            stock("passenger_drgw_caboose", DrgwCabooseCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCartCarEntity>> PASSENGER_FLAT_CART =
            stock("passenger_flat_cart", FlatCartCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCartSuCarEntity>> PASSENGER_FLAT_CART_SU =
            stock("passenger_flat_cart_su", FlatCartSuCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCartUsCarEntity>> PASSENGER_FLAT_CART_US =
            stock("passenger_flat_cart_us", FlatCartUsCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<FlatCarDbCarEntity>> PASSENGER_FLAT_CAR_DB =
            stock("passenger_flat_car_db", FlatCarDbCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PropagandaUsCarEntity>> PASSENGER_PROPAGANDA_US =
            stock("passenger_propaganda_us", PropagandaUsCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PropagandaUssrCarEntity>> PASSENGER_PROPAGANDA_USSR =
            stock("passenger_propaganda_ussr", PropagandaUssrCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PropagandaJapanCarEntity>> PASSENGER_PROPAGANDA_JAPAN =
            stock("passenger_propaganda_japan", PropagandaJapanCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PropagandaBritainCarEntity>> PASSENGER_PROPAGANDA_BRITAIN =
            stock("passenger_propaganda_britain", PropagandaBritainCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BUnitEmdf7TankEntity>> TANK_B_UNIT_EMDF7 =
            stock("tank_b_unit_emdf7", BUnitEmdf7TankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BUnitEmdf3TankEntity>> TANK_B_UNIT_EMDF3 =
            stock("tank_b_unit_emdf3", BUnitEmdf3TankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BUnitDd35TankEntity>> TANK_B_UNIT_DD35 =
            stock("tank_b_unit_dd35", BUnitDd35TankEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapPEcoachCarEntity>> PASSENGER_BAP_P_ECOACH =
            stock("passenger_bap_p_ecoach", BapPEcoachCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<BapPEobserveCarEntity>> PASSENGER_BAP_P_EOBSERVE =
            stock("passenger_bap_p_eobserve", BapPEobserveCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<PsCombineCarEntity>> PASSENGER_PS_COMBINE =
            stock("passenger_ps_combine", PsCombineCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<LightCraneCarEntity>> PASSENGER_LIGHT_CRANE =
            stock("passenger_light_crane", LightCraneCarEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Nre3gs21bLocomotiveEntity>> LOCO_DIESEL_NRE3GS21B =
            stock("loco_diesel_nre3gs21b", Nre3gs21bLocomotiveEntity::new);

    public static final DeferredHolder<EntityType<?>, EntityType<Cq310PassengerCarEntity>> PASSENGER_CQ310_PASSENGER =
            stock("passenger_cq310_passenger", Cq310PassengerCarEntity::new);

    private static <T extends RollingStockEntity> DeferredHolder<EntityType<?>, EntityType<T>> stock(
            String name, EntityType.EntityFactory<T> factory) {
        return TYPES.register(
                name,
                id ->
                        EntityType.Builder.of(factory, MobCategory.MISC)
                                .sized(RollingStockEntity.WIDTH, RollingStockEntity.HEIGHT)
                                .clientTrackingRange(8)
                                .updateInterval(3)
                                .build(ResourceKey.create(Registries.ENTITY_TYPE, id)));
    }

    public static final DeferredHolder<EntityType<?>, EntityType<ZeppelinEntity>> ZEPPELIN = zeppelin("zeppelin", false);
    public static final DeferredHolder<EntityType<?>, EntityType<ZeppelinEntity>> AIRSHIP = zeppelin("airship", true);

    private static DeferredHolder<EntityType<?>, EntityType<ZeppelinEntity>> zeppelin(String name, boolean twoBalloons) {
        return TYPES.register(name, key -> EntityType.Builder.<ZeppelinEntity>of((type, level) -> new ZeppelinEntity(type, level, twoBalloons), MobCategory.MISC)
                .sized(twoBalloons ? 2.0F : 3.4F, 0.8F).clientTrackingRange(10).updateInterval(3)
                .build(ResourceKey.create(Registries.ENTITY_TYPE, key)));
    }

    private EntityRegistry() {}
}
