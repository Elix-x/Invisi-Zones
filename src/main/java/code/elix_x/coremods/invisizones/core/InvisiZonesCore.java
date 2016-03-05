package code.elix_x.coremods.invisizones.core;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@Name("invisizones")
@TransformerExclusions("code.elix_x.coremods.invisizones.core")
@MCVersion("1.7.10")
@SortingIndex(1001)
public final class InvisiZonesCore implements IFMLLoadingPlugin {

	//-Dfml.coreMods.load=code.elix_x.coremods.invisizones.core.InvisiZonesCore

	public static final String Transformer = "code.elix_x.coremods.invisizones.core.InvisiZonesTransformer";

	public static final String[] transformers = new String[]{Transformer};

	@Override
	public String[] getASMTransformerClass(){
		return transformers;
	}

	@Override
	public String getModContainerClass(){
		return null;
	}

	@Override
	public String getSetupClass(){
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data){

	}

	@Override
	public String getAccessTransformerClass(){
		return null;
	}

}
