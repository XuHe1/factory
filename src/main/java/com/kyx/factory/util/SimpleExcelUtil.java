package com.kyx.factory.util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * SimpleExcelUtil is based on jxl. It is very simple and fast.
 * 
 */
public class SimpleExcelUtil {
	public static void createExcelWithSingleSheet(String fileName,
			String[] titles, HttpServletResponse response, FillDataCallback cb) {
		try {
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="+ fileName + ".xls");
			response.setContentType("application/msexcel");

			WritableWorkbook wbook = null;
			wbook = Workbook.createWorkbook(out);

			WritableSheet wsheet = wbook.createSheet("sheet", 0);
			for (int i = 0; i < titles.length; i++) {
				Label excelTitle = new Label(i, 0, titles[i].trim());
				wsheet.addCell(excelTitle);
			}

			cb.fill(wsheet);

			wbook.write();
			wbook.close();
			out.close();
		} catch (Exception e) {
			//
		}
	}
	
	public static void createExcelWithMultiSheets(String fileName,
			HttpServletResponse response, FillDataCallback cb) {
		try {
			OutputStream out = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="+ fileName + ".xls");
			response.setContentType("application/msexcel");

			WritableWorkbook wbook = null;
			wbook = Workbook.createWorkbook(out);

			/*WritableSheet wsheet = wbook.createSheet("sheet", 0);
			for (int i = 0; i < titles.length; i++) {
				Label excelTitle = new Label(i, 0, titles[i].trim());
				wsheet.addCell(excelTitle);
			}
*/
			cb.fill(wbook);

			wbook.write();
			wbook.close();
			out.close();
		} catch (Exception e) {
			//
		}
	}
}
