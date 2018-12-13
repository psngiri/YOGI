package yogi.server.gui.moduleinfo;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.applicationinfo.ApplicationInfoManager;

public class ModuleInfoManager extends RelationshipManager<ModuleInfo>
{
	private static ModuleInfoManager itsInstance = new ModuleInfoManager();
	private static ModuleInfoCreator moduleNameCreator = new ModuleInfoCreator();
	public static ModuleInfo NULL = new ModuleInfoImpl(ApplicationInfoManager.NULL,"");
	public static ModuleInfo ALL = new ModuleInfoImpl(ApplicationInfoManager.ALL,"ALL");
	private static OneToManyInverseRelationship<ApplicationInfo,ModuleInfo> appModuleRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(ApplicationInfo.class, ModuleInfo.class, "ModuleName");  

	protected ModuleInfoManager()
	{
		super();
	}

	public static ModuleInfoManager get()
	{
		return itsInstance;
	}
	
	@Override
	protected void buildRelationships(ModuleInfo module) {
		this.buildRelationship(module.getAppName(), module, appModuleRel);
	}

	@Override
	protected void deleteRelationships(ModuleInfo module) {
		this.deleteRelationship(module.getAppName(), module, appModuleRel);
	}
	
	public ImmutableList<ModuleInfo> getModules(ApplicationInfo appName) {
		return this.getRelationship(appName, appModuleRel);
	}

	public ModuleInfo getModule(ApplicationInfo appName, String module)
	{
		if ((ApplicationInfoManager.NULL == appName) || (null == module || module.trim().isEmpty())) {
			return NULL;
		}
		module = module.trim();
		if ((ApplicationInfoManager.ALL == appName) && (module.equalsIgnoreCase("ALL"))) {
			return ALL;
		}
		ImmutableList<ModuleInfo> modules = getModules(appName);
		for(ModuleInfo moduleName : modules){
			if(moduleName.getModuleName().equalsIgnoreCase(module)) return moduleName;
		}
		return createModuleName(appName, module);
	}
	
	public ModuleInfo getModule(ApplicationInfo appName)
	{
		return getModule(appName, "ALL");
	}
	
	private synchronized ModuleInfo createModuleName(ApplicationInfo appName, String module)
	{
		ImmutableList<ModuleInfo> modules = getModules(appName);
		for(ModuleInfo moduleName : modules){
			if(moduleName.getModuleName().equalsIgnoreCase(module)) return moduleName;
		}
		moduleNameCreator.setApplicationName(appName);
		moduleNameCreator.setModuleName(module);
		return ModuleInfoFactory.get().create(moduleNameCreator);
	}
}