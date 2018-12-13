package yogi.server.gui.moduleinfo;

import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.applicationinfo.ApplicationInfo;

public interface ModuleInfo extends RelationshipObject
{
	ApplicationInfo getAppName();
	String getModuleName();
}