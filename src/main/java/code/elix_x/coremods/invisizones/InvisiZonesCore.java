package code.elix_x.coremods.invisizones;

import java.io.File;
import java.util.Map;

import code.elix_x.coremods.invisizones.core.InvisiZonesTransformer;
import code.elix_x.coremods.invisizones.core.InvisiZonesTranslator;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@Name(value = "invizizones")
@TransformerExclusions(value = "code.elix_x.coremods")
@MCVersion(value = "1.7.10")
public final class InvisiZonesCore implements IFMLLoadingPlugin{

	//-Dfml.coreMods.load=code.elix_x.coremods.invisizones.InvisiZonesCore
	
	public static final String Transformer = InvisiZonesTransformer.class.getName();
	
	public static final String[] transformers = new String[]{Transformer};
	
	public static File mcDir;
	
	@Override
	public String[] getASMTransformerClass() {
		return transformers;
//		return new String[]{};
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return InvisiZonesTranslator.class.getName();
	}

	@Override
	public void injectData(Map<String, Object> data) {
		mcDir = (File) data.get("mcLocation");
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
