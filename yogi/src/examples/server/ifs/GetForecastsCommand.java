package examples.server.ifs;

import java.util.ArrayList;
import java.util.List;

import yogi.remote.client.app.CommandAdapter;

public class GetForecastsCommand extends CommandAdapter<Object> {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public GetForecastsCommand(String userId) {
		super(userId);
	}

	@Override
	public Object execute() {
		List<Object> forecasts = new ArrayList<Object>();
		forecasts.add(new Forecast("Forecast1","Forecast1"));
		forecasts.add(new Forecast("Forecast2","Forecast2"));
		forecasts.add(new Forecast("Forecast3","Forecast3"));
		forecasts.add(new Forecast("Forecast4","Forecast4"));
		forecasts.add(new Forecast("Forecast5","Forecast5"));
		forecasts.add(new Forecast("Forecast6","Forecast6"));
		forecasts.add(new Forecast("Forecast7","Forecast7"));
		forecasts.add(new Forecast("Forecast8","Forecast8"));
		forecasts.add(new Forecast("Forecast9","Forecast9"));
		forecasts.add(new Forecast("Forecast10","Forecast10"));
		return forecasts;
		
	}
	
	class Forecast{
		private String subNbr;
		private String value;
		public Forecast(String subNbr, String value) {
			super();
			this.subNbr = subNbr;
			this.value = value;
		}
		public String getSubNbr() {
			return subNbr;
		}
		public String getValue() {
			return value;
		}
		
	}

}
