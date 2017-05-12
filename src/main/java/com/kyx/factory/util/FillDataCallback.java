package com.kyx.factory.util;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public interface FillDataCallback {
	void fill(WritableSheet wsheet);
	void fill(WritableWorkbook wbook);
}
