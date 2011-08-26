/************************************************************************
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * 
 * Copyright 2009 IBM. All rights reserved.
 * 
 * Use is subject to license terms.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0. You can also
 * obtain a copy of the License at http://odftoolkit.org/docs/license.txt
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ************************************************************************/
package org.odftoolkit.odfdom.doc.table;

import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.doc.number.OdfNumberDateStyle;
import org.odftoolkit.odfdom.doc.number.OdfNumberStyle;
import org.odftoolkit.odfdom.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.doc.style.OdfStyle;
import org.odftoolkit.odfdom.doc.style.OdfStyleParagraphProperties;
import org.odftoolkit.odfdom.dom.element.draw.DrawFrameElement;
import org.odftoolkit.odfdom.dom.element.draw.DrawImageElement;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.type.Color;
import org.odftoolkit.odfdom.utils.ResourceUtilities;

public class TableCellTest {

	final static String SAMPLE_SPREADSHEET = "TestSpreadsheetTable";
	final static String SAMPLE_TEXT = "TestTextTable";
	OdfSpreadsheetDocument odsdoc;
	OdfTextDocument odtdoc;
	OdfTable odsTable, odtTable;

	@Before
	public void setUp() {
		try {
			odsdoc = (OdfSpreadsheetDocument) OdfSpreadsheetDocument
					.loadDocument(ResourceUtilities
							.getTestResourceAsStream(SAMPLE_SPREADSHEET
									+ ".ods"));
			odtdoc = (OdfTextDocument) OdfTextDocument
					.loadDocument(ResourceUtilities
							.getTestResourceAsStream(SAMPLE_TEXT + ".odt"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveods() {
		try {
			odsdoc.save(ResourceUtilities.newTestOutputFile(SAMPLE_SPREADSHEET
					+ "Output.ods"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveodt() {
		try {
			odtdoc.save(ResourceUtilities.newTestOutputFile(SAMPLE_TEXT
					+ "Output.odt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetIndexInRowColumn() {
		int rowindex = 2, columnindex = 1;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell cell = table.getCellByPosition(columnindex, rowindex);

		Assert.assertEquals(rowindex, cell.getRowIndex());
		Assert.assertEquals(columnindex, cell.getColumnIndex());

		OdfTable table3 = odtdoc.getTableByName("Table3");
		OdfTableCell cell1 = table.getCellByPosition(0, 1);
		Assert.assertEquals(1, cell1.getRowIndex());
		Assert.assertEquals(0, cell1.getColumnIndex());
	}

	@Test
	public void testGetSetHoriAlignment() {
		int rowindex = 3, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		String align = fcell.getHorizontalAlignment();
		Assert.assertEquals("center", align);
		
		fcell.setHorizontalAlignment(null);
		String newAlign = fcell.getHorizontalAlignment();
		Assert.assertEquals(null, newAlign);

		fcell.setHorizontalAlignment("start");
		align = fcell.getHorizontalAlignment();
		Assert.assertEquals("start", align);

		// "left" and "right" should be mapped as "start" and "end".
		fcell.setHorizontalAlignment("left");
		align = fcell.getHorizontalAlignment();
		Assert.assertEquals("start", align);
		fcell.setHorizontalAlignment("right");
		align = fcell.getHorizontalAlignment();
		Assert.assertEquals("end", align);
		saveods();

		OdfSpreadsheetDocument ods;
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell = tbl.getCellByPosition(0, 0);
			String horizonAlignment = cell.getHorizontalAlignment();
			Assert.assertEquals(null, horizonAlignment);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testGetSetVertAlignment() {
		int rowindex = 3, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		String align = fcell.getVerticalAlignment();
		Assert.assertEquals("top", align);

		fcell.setVerticalAlignment(null);
		String newAlign = fcell.getVerticalAlignment();
		Assert.assertEquals(null, newAlign);

		fcell.setVerticalAlignment("bottom");
		align = fcell.getVerticalAlignment();
		Assert.assertEquals("bottom", align);
		saveods();
		OdfSpreadsheetDocument ods;
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell = tbl.getCellByPosition(0, 0);
			String verticalAlignment = cell.getVerticalAlignment();
			Assert.assertEquals(null, verticalAlignment);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testGetSetValueType() {
		int rowindex = 3, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		boolean illegalArgumentFlag = false;
		try {
			fcell.setValueType(null);
		} catch (IllegalArgumentException ie) {
			if ("type shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);

		fcell.setValueType("date");
		String valueType = fcell.getValueType();
		Assert.assertEquals("date", valueType);
		saveods();
		
		OdfSpreadsheetDocument ods;
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell = tbl.getCellByPosition(0, 0);
			valueType= cell.getValueType();
			Assert.assertEquals(null, valueType);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testGetSetWrapOption() {
		int rowindex = 5, columnindex = 8;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		boolean wrap = fcell.isTextWrapped();
		Assert.assertEquals(true, wrap);

		fcell.setTextWrapped(false);
		wrap = fcell.isTextWrapped();
		Assert.assertEquals(false, wrap);
		saveods();
	}

	@Test
	public void testGetSetTextValue() {
		int rowindex = 5, columnindex = 8;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		String text = fcell.getDisplayText();
		Assert.assertEquals("this is a big cell with a big table", text);

		fcell.setDisplayText("changed");
		text = fcell.getDisplayText();
		Assert.assertEquals("changed", text);
		// reproduce bug 150.
		fcell.setDisplayText(null);
		text = fcell.getDisplayText();
		Assert.assertEquals("", text);
		fcell.setDisplayText(null, "automatic7777");
		text = fcell.getDisplayText();
		Assert.assertEquals("", text);
		saveods();

		OdfTable table1 = odtdoc.getTableByName("Table1");
		OdfTableCell fcell2 = table1.getCellByPosition(0, 1);
		text = fcell2.getDisplayText();
		Assert.assertEquals("Aabbccddee", text);
	}

	@Test
	public void testSetGetFormat() {
		int rowindex = 3, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		fcell.setFormatString("#0.0");
		String displayvalue = fcell.getDisplayText();
		Assert.assertEquals("300"
				+ (new DecimalFormatSymbols()).getDecimalSeparator() + "0",
				displayvalue);
		String format = fcell.getFormatString();
		Assert.assertEquals("#0.0", format);

		OdfTableCell dcell = table.getCellByPosition(3, 2);
		format = dcell.getFormatString();
		Assert.assertEquals("MMM d, yy", format);

		dcell.setFormatString("yyyy-MM-dd");
		displayvalue = dcell.getDisplayText();
		Assert.assertEquals("2008-12-23", displayvalue);

		OdfTableCell pcell = table.getCellByPosition("B2");
		format = pcell.getFormatString();
		Assert.assertEquals("#0%", format);

		pcell.setFormatString("#0.00%");
		displayvalue = pcell.getDisplayText();
		Assert.assertEquals("200"
				+ (new DecimalFormatSymbols()).getDecimalSeparator() + "00%",
				displayvalue);
		try {
			OdfTableRow tablerow = table.getRowByIndex(6);
			OdfTableCell cell = tablerow.getCellByIndex(3);
			Calendar currenttime = Calendar.getInstance();
			cell.setDateValue(currenttime);
			cell.setFormatString("yyyy-MM-dd");
			tablerow = table.getRowByIndex(7);
			cell = tablerow.getCellByIndex(3);
			cell.setTimeValue(currenttime);
			cell.setFormatString("HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		saveods();
		
		//test value type adapt function.
		OdfSpreadsheetDocument ods;
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell;
			for (int i = 1; i <= 10; i++) {
				cell = tbl.getCellByPosition("A" + i);
				cell.setDoubleValue(new Double(i));
			}
			cell = tbl.getCellByPosition("A11");
			cell.setFormula("=sum(A1:A10)");
			//contains '#' should be adapted as float.
			cell.setFormatString("#,###");
			Assert.assertEquals("float", cell.getValueType());
			cell = tbl.getCellByPosition("A12");
			cell.setFormula("=sum(A1:A10)");
			//contains '0' should be adapted as float.
			cell.setFormatString("0.00");
			Assert.assertEquals("float", cell.getValueType());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell;
			for (int i = 1; i <= 10; i++) {
				cell = tbl.getCellByPosition("A" + i);
				cell.setPercentageValue(0.1);
			}
			cell = tbl.getCellByPosition("A11");
			cell.setFormula("=sum(A1:A10)");
			//contains '%'should be adapted as percentage.
			cell.setFormatString("###.0%");
			Assert.assertEquals("percentage", cell.getValueType());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell;
			for (int i = 1; i <= 10; i++) {
				cell = tbl.getCellByPosition("A" + i);
				cell.setDateValue(Calendar.getInstance());
				cell.setFormatString("yyyy.MM.dd");
			}
			cell = tbl.getCellByPosition("A11");
			cell.setFormula("=max(A1:A10)");
			//contains 'y' 'M' 'd' should be adapted as date.
			cell.setFormatString("yyyy.MM.dd");
			Assert.assertEquals("date", cell.getValueType());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell;
			for (int i = 1; i <= 10; i++) {
				cell = tbl.getCellByPosition("A" + i);
				cell.setTimeValue(Calendar.getInstance());
				cell.setFormatString("yyyy.MM.dd HH:mm:ss");
			}
			cell = tbl.getCellByPosition("A11");
			cell.setFormula("=max(A1:A10)");
			//contains 'H' 'm' 's' should be adapted as time.
			cell.setFormatString("yyyy.MM.dd HH:mm:ss");
			Assert.assertEquals("time", cell.getValueType());
			cell = tbl.getCellByPosition("A12");
			cell.setFormula("=max(A1:A10)");
			//contains 'H' 'm' 's' should be adapted as time.
			cell.setFormatString("HH:mm:ss");
			Assert.assertEquals("time", cell.getValueType());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	private void loadOutputSpreadsheet() {
		try {
			odsdoc = (OdfSpreadsheetDocument) OdfSpreadsheetDocument
					.loadDocument(ResourceUtilities
							.getTestResourceAsStream(SAMPLE_SPREADSHEET
									+ "Output.ods"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSetCellBackgroundColor() throws Exception {
		int rowindex = 2, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		fcell.setCellBackgroundColor("#ffffff");
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		// set color as DEFAULT_BACKGROUND_COLOR #FFFFFF
		Assert.assertEquals("#ffffff", fcell.getCellBackgroundColorString());

		Color expectedColor = Color.valueOf("#000000");
		fcell.setCellBackgroundColor(expectedColor);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expectedColor.toString(), fcell.getCellBackgroundColor()
				.toString());
		
		OdfSpreadsheetDocument ods;
		try {
			ods = OdfSpreadsheetDocument.newSpreadsheetDocument();
			OdfTable tbl = ods.getTableByName("Sheet1");
			OdfTableCell cell = tbl.getCellByPosition(0, 0);
			Color actualBackColor = cell.getCellBackgroundColor();
			Assert.assertEquals("#ffffff", actualBackColor.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testGetSetColumnSpannedNumber() throws Exception {
		int rowindex = 2, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		fcell.setColumnSpannedNumber(-2);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		// set column spanned number as DEFAULT_COLUMN_SPANNED_NUMBER 1.
		Assert.assertEquals(1, fcell.getColumnSpannedNumber());

		fcell.setColumnSpannedNumber(0);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		// set column spanned number as DEFAULT_COLUMN_SPANNED_NUMBER 1.
		Assert.assertEquals(1, fcell.getColumnSpannedNumber());

		fcell.setColumnSpannedNumber(2);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(2, fcell.getColumnSpannedNumber());
	}

	@Test
	public void testGetSetRowSpannedNumber() throws Exception {
		int rowindex = 2, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		fcell.setRowSpannedNumber(-2);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		// set row spanned number as DEFAULT_ROW_SPANNED_NUMBER 1.
		Assert.assertEquals(1, fcell.getRowSpannedNumber());

		fcell.setRowSpannedNumber(0);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		// set row spanned number as DEFAULT_ROW_SPANNED_NUMBER 1.
		Assert.assertEquals(1, fcell.getRowSpannedNumber());

		fcell.setRowSpannedNumber(2);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(2, fcell.getRowSpannedNumber());

	}

	@Test
	public void testGetSetColumnsRepeatedNumber() throws Exception {
		int rowindex = 3, columnindex = 1;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		fcell.setColumnsRepeatedNumber(-2);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		// set columns repeated number as DEFAULT_COLUMNS_REPEATED_NUMBER 1.
		Assert.assertEquals(1, fcell.getColumnsRepeatedNumber());

		fcell.setColumnsRepeatedNumber(0);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		// set columns repeated number as DEFAULT_COLUMNS_REPEATED_NUMBER 1.
		Assert.assertEquals(1, fcell.getColumnsRepeatedNumber());

		fcell.setColumnsRepeatedNumber(2);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(2, fcell.getColumnsRepeatedNumber());
	}

	@Test
	public void testGetSetDateValue() {
		int rowindex = 7, columnindex = 7;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		boolean illegalArgumentFlag = false;
		try {
			fcell.setDateValue(null);
		} catch (IllegalArgumentException ie) {
			if ("date shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);
		Calendar expectedCalendar = new GregorianCalendar(2010, 1, 30);
		fcell.setDateValue(expectedCalendar);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert
				.assertEquals(0, fcell.getDateValue().compareTo(
						expectedCalendar));
	}

	@Test
	public void testGetSetStringValue() {
		int rowindex = 6, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		fcell.setStringValue(null);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals("", fcell.getStringValue());

		String expectedString = "hello world";
		fcell.setStringValue(expectedString);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expectedString, fcell.getStringValue());
	}

	@Test
	public void testGetSetBooleanValue() {
		int rowindex = 5, columnindex = 5;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		boolean expected = false;
		fcell.setBooleanValue(expected);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertFalse(fcell.getBooleanValue());
	}

	@Test
	public void testGetSetCurrencyValue() {
		int rowindex = 5, columnindex = 5;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		fcell.setValueType("currency");
		fcell.setCurrencyFormat("$", "#,##0.00");
		Double actualValue=fcell.getCurrencyValue();
		Assert.assertNull(actualValue);
		
		double expected = 100.00;
		boolean illegalArgumentFlag = false;
		try {
			fcell.setCurrencyValue(expected, null);
		} catch (IllegalArgumentException ie) {
			if ("currency shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);

		fcell.setCurrencyValue(expected, "USD");
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expected, fcell.getCurrencyValue());
	}

	@Test
	public void testGetSetCurrencyDesc() {
		int rowindex = 1, columnindex = 2;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		boolean illegalArgumentFlag = false;
		try {
			fcell.setCurrencyCode(null);
		} catch (IllegalArgumentException ie) {
			if ("Currency code of cell should not be null.".equals(ie
					.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);

		fcell = table.getCellByPosition(columnindex, rowindex);
		String expected = "USD";
		fcell.setCurrencyCode(expected);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expected, fcell.getCurrencyCode());
	}

	@Test
	public void testGetSetPercentageValue() {
		int rowindex = 5, columnindex = 5;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		double expected = 56.98;
		fcell.setPercentageValue(expected);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expected, fcell.getPercentageValue());
	}

	@Test
	public void testGetSetTimeValue() {
		int rowindex = 0, columnindex = 4;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		boolean illegalArgumentFlag = false;
		try {
			fcell.setTimeValue(null);
		} catch (IllegalArgumentException ie) {
			if ("time shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);
		Calendar expected = Calendar.getInstance();
		fcell.setTimeValue(expected);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);

		SimpleDateFormat simpleFormat = new SimpleDateFormat(
				"'PT'HH'H'mm'M'ss'S'");
		String expectedString = simpleFormat.format(expected.getTime());
		String targetString = simpleFormat.format(fcell.getTimeValue()
				.getTime());
		Assert.assertEquals(expectedString, targetString);
	}

	@Test
	public void testGetSetFormula() {
		int rowindex = 1, columnindex = 10;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		boolean illegalArgumentFlag = false;
		try {
			fcell.setFormula(null);
		} catch (IllegalArgumentException ie) {
			if ("formula shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);

		String expected = "of:=[.I2]*4";
		fcell.setFormula(expected);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expected, fcell.getFormula());
	}

	@Test
	public void testGetStyleName() {
		int rowindex = 2, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		String expected = "ce2";
		fcell.getStyleName();

		Assert.assertEquals(expected, fcell.getStyleName());
	}

	@Test
	public void testGetTableColumn() {
		int rowindex = 2, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		Assert.assertNotNull(fcell.getTableColumn());
		Assert.assertEquals(columnindex, fcell.getTableColumn()
				.getColumnIndex());
	}

	@Test
	public void testGetTableRow() {
		int rowindex = 2, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		Assert.assertNotNull(fcell.getTableRow());
		Assert.assertEquals(rowindex, fcell.getTableRow().getRowIndex());
	}

	@Test
	public void testRemoveContent() {
		int rowindex = 5, columnindex = 8;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		Assert.assertTrue(fcell.mCellElement.getChildNodes().getLength() > 0);
		fcell.removeContent();
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(0, fcell.mCellElement.getChildNodes().getLength());
	}

	@Test
	public void testRemoveTextContent() throws Exception {
		int rowindex = 5, columnindex = 8;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);

		// how to test?
		fcell.removeContent();
		Assert.assertEquals(0, fcell.mCellElement.getChildNodes().getLength());

		fcell.setDisplayText("hello");
		DrawFrameElement drawEle = new DrawFrameElement(odsdoc.getContentDom());
		DrawImageElement imageEle = drawEle.newDrawImageElement();
		fcell.mCellElement.appendChild(drawEle);

		Assert.assertEquals(2, fcell.mCellElement.getChildNodes().getLength());

		fcell.removeTextContent();
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(1, fcell.mCellElement.getChildNodes().getLength());
	}

	@Test
	public void testGetSetDisplayText() {
		int rowindex = 5, columnindex = 5;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		String expected = "display text";

		// Assert.assertEquals(expected, fcell.getDisplayText());

		fcell.setDisplayText(expected);
		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expected, fcell.getDisplayText());
	}

	@Test
	public void testGetSetFormatString() {
		int rowindex = 3, columnindex = 0;
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell fcell = table.getCellByPosition(columnindex, rowindex);
		boolean illegalArgumentFlag = false;
		try {
			fcell.setFormatString(null);
		} catch (IllegalArgumentException ie) {
			if ("formatStr shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);
		// float format string
		String expected = "#0.0";
		fcell.setFormatString(expected);
		// date format string
		// String expected="MMM d, yy";
		// String expected="yyyy-MM-dd";

		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		fcell = table.getCellByPosition(columnindex, rowindex);
		Assert.assertEquals(expected, fcell.getFormatString());
	}

	@Test
	public void testGetCurrencySymbol() {
		OdfTable table = odsdoc.getTableByName("Sheet1");
		OdfTableCell cell1 = table.getCellByPosition("C2");
		Assert.assertEquals("$", cell1.getCurrencySymbol());
		OdfTableCell cell2 = table.getCellByPosition("C3");
		Assert.assertEquals("CNY", cell2.getCurrencySymbol());
	}

	@Test
	public void testGetSetCurrencyFormat() {
		OdfTable table = odsdoc.getTableByName("Sheet1");
		String[] formats = { "$#,##0.00", "#,##0.00 CNY", "$#,##0.0" };

		OdfTableCell cell = table.getCellByPosition("J1");
		boolean illegalArgumentFlag = false;
		try {
			cell.setCurrencyFormat(null, formats[0]);
		} catch (IllegalArgumentException ie) {
			if ("currencySymbol shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);

		try {
			cell.setCurrencyFormat("$", null);
		} catch (IllegalArgumentException ie) {
			if ("format shouldn't be null.".equals(ie.getMessage())) {
				illegalArgumentFlag = true;
			}
		}
		Assert.assertTrue(illegalArgumentFlag);

		cell.setCurrencyValue(32.12, "USD");
		cell.setCurrencyFormat("$", formats[0]);

		cell = table.getCellByPosition("J2");
		cell.setCurrencyValue(new Double(32), "CNY");
		cell.setCurrencyFormat("CNY", formats[1]);

		cell = table.getCellByPosition("J3");
		cell.setCurrencyValue(-32.12, "USD");
		cell.setCurrencyFormat("$", formats[2]);

		saveods();
		// reload
		loadOutputSpreadsheet();
		table = odsdoc.getTableByName("Sheet1");
		for (int i = 1; i <= 3; i++) {
			OdfTableCell newcell = table.getCellByPosition("J" + i);
			Assert.assertEquals(formats[i - 1], newcell.getFormatString());
		}
	}
	
	@Test
	public void testSetDefaultCellStyle() {
		OdfSpreadsheetDocument outputDocument;
		OdfFileDom contentDom; // the document object model for content.xml
		OdfFileDom stylesDom; // the document object model for styles.xml
		// the office:automatic-styles element in content.xml
		OdfOfficeAutomaticStyles contentAutoStyles;
		// the office:styles element in styles.xml
		OdfOfficeStyles stylesOfficeStyles;
		OdfStyle style;
		String noaaDateStyleName;
		String noaaTempStyleName;

		try {
			outputDocument = OdfSpreadsheetDocument.newSpreadsheetDocument();
			contentDom = outputDocument.getContentDom();
			contentAutoStyles = contentDom.getOrCreateAutomaticStyles();

			OdfNumberDateStyle dateStyle = new OdfNumberDateStyle(contentDom,
					"yyyy-MM-dd", "numberDateStyle", null);
	        OdfNumberStyle numberStyle = new OdfNumberStyle(contentDom,
	                "#0.00", "numberTemperatureStyle");

			contentAutoStyles.appendChild(dateStyle);
			contentAutoStyles.appendChild(numberStyle);

			style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
			noaaDateStyleName = style.getStyleNameAttribute();
			style.setStyleDataStyleNameAttribute("numberDateStyle");

			// and for time cells
	        style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
	        noaaTempStyleName = style.getStyleNameAttribute();
	        style.setStyleDataStyleNameAttribute("numberTemperatureStyle");
	        style.setProperty(OdfStyleParagraphProperties.TextAlign, "end");

			OdfTable table = OdfTable.newTable(outputDocument);
			List<OdfTableColumn> columns = table.insertColumnsBefore(0, 3);
			OdfTableColumn column = columns.get(0);
			column.setDefaultCellStyle(
					contentAutoStyles.getStyle(noaaDateStyleName,
							OdfStyleFamily.TableCell));
			OdfTableCell aCell = column.getCellByIndex(0);
			aCell.setValueType("date");
			String format = aCell.getFormatString();
			Assert.assertEquals("yyyy-MM-dd", format);

			List<OdfTableRow> rows = table.insertRowsBefore(0, 1);
			OdfTableRow row = rows.get(0);
			row.setDefaultCellStyle(contentAutoStyles.getStyle(noaaTempStyleName,
					OdfStyleFamily.TableCell));
			OdfTableCell bCell = row.getCellByIndex(0);
			bCell.setValueType("float");
			String bformat = bCell.getFormatString();
			Assert.assertEquals("#0.00", bformat);
			Assert.assertEquals("end", bCell.getHorizontalAlignment());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
