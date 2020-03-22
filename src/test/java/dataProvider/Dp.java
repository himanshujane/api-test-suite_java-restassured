package dataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import genericLib.ExcelRW;

public class Dp {

	public final static String dataSheetPath = "./TestData.xlsx";

	/**
	 * @apiNote - Splits the calling Method Name and pass that to <commondp> method as Sheet name and script name
	 * @param Calling Method
	 * @return Object
	 * @throws Exception
	 */
	@DataProvider(name = "dp")
	public static Iterator<Object[]> singleDp(Method m) throws Exception {
		return commondp(m.getName().split("_")[0], m.getName().split("_")[1]);
	}

	/**
	 * @apiNote Iterate through the given sheetName and ScriptName rows in the excel
	 * and stores the values in HashMap which is stored in object and that object is stored in ArrayList. 
	 * @param sheetName
	 * @param scriptName
	 * @return
	 * @throws Exception
	 */
	public static Iterator<Object[]> commondp(String sheetName, String scriptName) throws Exception {

		ExcelRW ex = new ExcelRW(dataSheetPath);
		
		//Gets the Row and Column Count 
		int row = ex.getRowCount(sheetName);
		int col = ex.getColCount(sheetName);

		//Create Array List of Object
		List<Object[]> al = new ArrayList<Object[]>();

		for (int i = 1; i <= row; i++) {
			String execute = ex.readCellValue(sheetName, i, 2);
			String script = ex.readCellValue(sheetName, i, 1);
			if (execute.equals("y") && script.equals(scriptName)) {
				//Create object array
				Object[] x = new Object[1];
				// create hashmap
				Map<String, String> hm = new HashMap<String, String>();
				//Iterate column
				//Iterate column
				for (int j = 0; j < col; j++) {

					String key = ex.readCellValue(sheetName, 0, j);
					String val = ex.readCellValue(sheetName, i, j);
					hm.put(key, val);
				}
				x[0] = hm;
				al.add(x);
			}
		}
		return al.iterator();
	}
}