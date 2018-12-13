package yogi.report;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.MultiLineFormatter;
import yogi.report.group.Group;

public abstract class GroupObjectFormatter<T extends Group> implements MultiLineFormatter<T>{

	public List<String> format(T group) {
		List<String> rtnValue = new ArrayList<String>();
		for(int i = group.getStartIndex(); i <= group.getEndIndex(); i++)
		{
			int indexInGroup = group.getIndexInGroup(i);
			if(!group.isValid(indexInGroup)) continue;
			rtnValue.add(format(group, indexInGroup));
		}
		return rtnValue;
	}

	public abstract String format(T group, int indexInGroup);
	
}
