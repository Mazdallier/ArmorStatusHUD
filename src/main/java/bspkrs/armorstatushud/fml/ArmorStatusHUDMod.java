package bspkrs.armorstatushud.fml;

import bspkrs.armorstatushud.ArmorStatusHUD;
import bspkrs.bspkrscore.fml.bspkrsCoreMod;
import bspkrs.util.Const;
import bspkrs.util.ModVersionChecker;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "ArmorStatusHUD", name = "ArmorStatusHUD", version = ArmorStatusHUD.VERSION_NUMBER, dependencies = "required-after:bspkrsCore", useMetadata = true)
public class ArmorStatusHUDMod
{
    public ModVersionChecker        versionChecker;
    private String                  versionURL = Const.VERSION_URL + "/Minecraft/" + Const.MCVERSION + "/armorStatusHUD.version";
    private String                  mcfTopic   = "http://www.minecraftforum.net/topic/1114612-";
    
    private boolean                 isEnabled  = true;
    
    @Metadata(value = "ArmorStatusHUD")
    public static ModMetadata       metadata;
    
    @Instance(value = "ArmorStatusHUD")
    public static ArmorStatusHUDMod instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        metadata = event.getModMetadata();
        ArmorStatusHUD.loadConfig(event.getSuggestedConfigurationFile());
        
        if (bspkrsCoreMod.instance.allowUpdateCheck)
        {
            versionChecker = new ModVersionChecker(metadata.name, metadata.version, versionURL, mcfTopic);
            versionChecker.checkVersionWithLogging();
        }
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(new ASHGameTicker());
        FMLCommonHandler.instance().bus().register(new ASHRenderTicker());
    }
    
    public void setEnabled(boolean bol)
    {
        isEnabled = bol;
    }
    
    public boolean isEnabled()
    {
        return isEnabled;
    }
}
