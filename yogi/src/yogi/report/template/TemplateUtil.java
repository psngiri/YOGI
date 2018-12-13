package yogi.report.template;

public class TemplateUtil {
	public static String format(String item, int width)
	{
		if(item == null) item = "";
		if(width == 0) return item;
		return String.format("%-"+width+"s", item);
	}
	
	public static String drawLine(int width)
	{
		StringBuilder sb = new StringBuilder(width);
		for(int i = 0; i < width; i ++)
		{
			sb.append('_');
		}
		return sb.toString();
	}
}
