package yogi.report.condition;

import java.util.ArrayList;
import java.util.Scanner;

import yogi.base.util.XString;

public class BaseLikeCondition<C> extends ConditionBaseImpl<C> {
	
	ArrayList<String> includes = new ArrayList<String>();
	ArrayList<String> excludes = new ArrayList<String>();
	
	public BaseLikeCondition(String value) {
		this(value, ',');
	}
	
	public BaseLikeCondition(String value, char separator) {
		super(value);
		Scanner scanner = new Scanner(value);
		scanner.useDelimiter(String.valueOf(separator));
		while(scanner.hasNext())
		{
			String token = scanner.next().trim();
			if(!token.contains("%"))
			{
				if(token.startsWith("~"))
				{
					token.replace('~', '%');
					token = "~" + token + "%";
				}
				else
				{
					token = "%" + token + "%";
				}
			}
			if(token.startsWith("~")) {
				excludes.add(token);
			} else {
				includes.add(token);
			}
		}
		scanner.close();
	}

	public boolean satisfied(C data) {
		return handleIncludes(data) && handleExcludes(data);
	}
	
	public boolean handleIncludes(C data) {
		if(data == null) return false;
		if(includes.isEmpty()) return true;
		for(String value : includes) {
			if(checkPattern(this.getFormatter().format(data), value)) return true;
		}		
		return false;
	}
	

	public boolean handleExcludes(C data) {
		if(data == null) return false;
		if(excludes.isEmpty()) return true;
		for(String value : excludes) {
			if(!checkPattern(this.getFormatter().format(data), value)) return false;
		}		
		return true;
	}
	
	protected boolean checkPattern(String compareStr, String pattern) {
		return XString.isLike(compareStr, pattern);
	}
	
	@Override
	public String getSqlValue(String columnName) {
		StringBuilder sb = new StringBuilder();
		sb.append(' ');
		String[] vals = getValue().split(",");
		sb.append(columnName).append(" like ");
		for(int j=0;j<vals.length;j++){
			sb.append("'").append(vals[j]).append("'");
			if(j!=vals.length-1){
				sb.append(" or ").append(columnName).append(" like ");
			}
		}
		return sb.toString();
	}

}
