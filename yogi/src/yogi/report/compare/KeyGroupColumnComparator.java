package yogi.report.compare;

import yogi.report.column.ColumnDefinition;
import yogi.report.column.GroupColumnComparator;
import yogi.report.compare.template.BaseCompareReportTemplate;

public class KeyGroupColumnComparator<T> extends GroupColumnComparator<T> {

	public KeyGroupColumnComparator(BaseCompareReportTemplate<T> reportTemplate) {
        super();
        for(ColumnDefinition<T> columnDefinition: reportTemplate.getColumns())
        {	  
            int index = columnDefinition.getIndex();
            if(reportTemplate.getCompareFunctions(index).isEmpty())
          	  addColumn(columnDefinition);
        }
  }
}
