package yogi.report.compare;

import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.compare.template.BaseCompareReportTemplate;

public class KeyColumnComparator<T> extends ColumnComparator<T> {
	 
    public KeyColumnComparator(BaseCompareReportTemplate<T> reportTemplate) {
          super();
          for(ColumnDefinition<T> columnDefinition: reportTemplate.getColumns())
          {	  
              int index = columnDefinition.getIndex();
              if(reportTemplate.getCompareFunctions(index).isEmpty())
            	  addColumn(columnDefinition);
          }
    }
}