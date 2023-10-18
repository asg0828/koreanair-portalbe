package com.bdp.ap.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.utils.StringUtils;

@Slf4j
@Component
public class ParseUtil {

	/**
	 * csv 형식의 파일을 파싱하여 반환
	 * @param Path path 파일 경로
	 * @param String[] headers 항목명
	 * @param Character quote 항목 시작 및 끝 문자
	 * @param Character seperator 항목 구분자
	 * @param String encoding 파일 인코딩
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> parseCSV(Path path, String[] headers, Character quote, Character seperator, String encoding) throws Exception {
		List<Map<String,String>> result = new ArrayList<>();
		CSVFormat csvForm = null;
		CSVParser parser = null;
		try {
			csvForm = CSVFormat.TDF.withTrim().withHeader(headers).withQuote(quote).withDelimiter(seperator);
			parser = CSVParser.parse(path.toFile(), Charset.forName(encoding), csvForm);
			parser.forEach(recode -> {
				result.add(recode.toMap());
			});
			parser.close();
		} catch(Exception e) {
			if (parser != null) {
				parser.close();
			}
			throw new Exception(e.getMessage());
		}
		return result;
	}

	/**
	 * 엑셀 파일을 파싱하여 반환
	 * @param Path path 파일 경로
	 * @param String[] headers 항목명
	 * @param int sheetNo 파싱할 시트 번호 0 부터 시작
	 * @param int rowNo 파싱 시작할 ROW 번호 0 부터 시작
	 */
	public List<Map<String,String>> parseExcel(Path path, String[] headers, int sheetNo, int rowNo) {
		List<Map<String,String>> result = null;
		try {
			InputStream in = Files.newInputStream(path);
			result = parseExcel(in, headers, sheetNo, rowNo);
		} catch (IOException e) {
			log.warn("Excel 파일 변환 중 오류 발생");
			log.warn(e.getMessage());
			result = new ArrayList<>();
		}
		return result;
	}

	/**
	 * 엑셀 파일 파일하여 반환
	 * @param InputStream in 엑셀 파일 데이터
	 * @param String[] headers 항목명
	 * @param int sheetNo 파싱할 시트 번호 0 부터 시작
	 * @param int rowNo 파싱 시작할 ROW 번호 0 부터 시작
	 */
	public List<Map<String,String>> parseExcel(InputStream in, String[] headers, int sheetNo, int rowNo) {
		List<Map<String,String>> result = new ArrayList<>();

		try {
			Workbook workbook = WorkbookFactory.create(in);
			DataFormatter df = new DataFormatter();
			FormulaEvaluator fe = workbook.getCreationHelper().createFormulaEvaluator();

			Sheet sheet = workbook.getSheetAt(sheetNo);
			int rowSize = sheet.getPhysicalNumberOfRows();
			if (rowSize >= rowNo) {
				for (int r=rowNo; r<rowSize; r++) {
					Row row = sheet.getRow(r);
					Map<String,String> map = new HashMap<>();
					for (int c=0; c<headers.length; c++) {
						if (StringUtils.isNotBlank(headers[c])) {
							Cell cell = row.getCell(c);
							switch(cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								map.put(headers[c], df.formatCellValue(cell));
								break;
							case Cell.CELL_TYPE_NUMERIC:
								map.put(headers[c], df.formatCellValue(cell));
								break;
							case Cell.CELL_TYPE_FORMULA:
								map.put(headers[c], df.formatCellValue(cell,fe));
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								map.put(headers[c], df.formatCellValue(cell));
								break;
							case Cell.CELL_TYPE_BLANK:
								map.put(headers[c], null);
								break;
							case Cell.CELL_TYPE_ERROR:
								map.put(headers[c], df.formatCellValue(cell));
								break;
							default:
								map.put(headers[c], null);
								break;
							}
						}
					}
					if (!map.isEmpty()) {
						result.add(map);
					}
				}
			}
			in.close();
		} catch (IOException | InvalidFormatException e) {
			log.warn("Excel 파일 파싱 중 오류 발생");
			log.warn(e.getMessage());
		}

		return result;
	}

	/**
	 * 엑셀 생성 시 데이터 셀 스타일
	 * @param workbook
	 * @return
	 */
	public CellStyle getCellStyle(Workbook workbook) {
		CellStyle result = workbook.createCellStyle();
		result.setBorderTop(CellStyle.BORDER_THIN);
		result.setBorderLeft(CellStyle.BORDER_THIN);
		result.setBorderRight(CellStyle.BORDER_THIN);
		result.setBorderBottom(CellStyle.BORDER_THIN);
		return result;
	}

	/**
	 * 엑셀 생성 시 헤더 셀 스타일
	 * @param workbook
	 * @return
	 */
	public CellStyle getCellStyleHeader(Workbook workbook) {
		CellStyle result = getCellStyle(workbook);
		result.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		result.setAlignment(CellStyle.ALIGN_CENTER);
		result.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return result;
	}

	/**
	 * 엑셀 생성
	 * @param headers
	 * @param keys
	 * @param data
	 * @return
	 */
	public byte[] createExcel(String[] headers, String[] keys, JSONArray data) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			
			CellStyle cellStyleHeader = getCellStyleHeader(workbook);
			//header
			Row row = sheet.createRow(0);
			Cell cell = null;
			for (int c=0; c<headers.length; c++) {
				cell = row.createCell(c);
				cell.setCellValue(headers[c]);
				cell.setCellStyle(cellStyleHeader);
			}
			
			CellStyle cellStyle = getCellStyle(workbook);
			//data
			for (int r=0; r<data.length(); r++) {
				row = sheet.createRow(r+1);
				for (int c=0; c<keys.length; c++) {
					cell = row.createCell(c);
					if (StringUtils.isBlank(keys[c])) {
						if (c == 0) {
							cell.setCellValue(data.length()-r);
						} else {
							cell.setCellValue("");
						}
					} else {
						cell.setCellValue(data.getJSONObject(r).optString(keys[c]));
					}
					cell.setCellStyle(cellStyle);
				}
			}
			
			workbook.write(bytes);
		} catch (Exception e) {
			log.warn("엑셀 파일 생성 중 오류 발생");
			log.warn(e.getMessage());
		}
		return bytes.toByteArray();
	}
}
