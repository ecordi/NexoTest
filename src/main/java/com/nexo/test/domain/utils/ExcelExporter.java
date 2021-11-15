package com.nexo.test.domain.utils;

import com.nexo.test.domain.Direccion.DireccionModel;
import com.nexo.test.domain.Persona.PersonaModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet xssfSheet;
    private List<PersonaModel> list;

    private void headerLine() {
        xssfSheet = xssfWorkbook.createSheet("Personas");
        Row row = xssfSheet.createRow(0);
        CellStyle cellStyle = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeight(16);
        cellStyle.setFont(font);
        createCell(row, 0, "id", cellStyle);
        createCell(row, 1, "nombre", cellStyle);
        createCell(row, 2, "apellido", cellStyle);
        createCell(row, 3, "edad", cellStyle);
        createCell(row, 4, "foto", cellStyle);
        createCell(row, 5, "direccionList", cellStyle);
    }

    private void createCell(Row row, int countColumn, Object value, CellStyle cellStyle) {
        xssfSheet.autoSizeColumn(countColumn);
        Cell cell = row.createCell(countColumn);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);

        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);

        } else if (value instanceof DireccionModel) {
            cell.setCellValue((RichTextString) value);

        } else if (value instanceof List) {
            cell.setCellValue(String.valueOf((List) value));

        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(cellStyle);
    }

    private void writeDataLines() {
        int rowCount = 1;
        CellStyle style = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeight(18);
        style.setFont(font);
        for (PersonaModel persona : list) {
            Row row = xssfSheet.createRow(rowCount++);
            int countColumn = 0;
            createCell(row, countColumn++, persona.getDni(), style);
            createCell(row, countColumn++, persona.getNombre(), style);
            createCell(row, countColumn++, persona.getApellido(), style);
            createCell(row, countColumn++, persona.getEdad(), style);
            createCell(row, countColumn++, persona.getFoto(), style);
            createCell(row, countColumn++, persona.getDireccionList(), style);
        }
    }

    public ExcelExporter(List<PersonaModel> list) {
        this.list = list;
        xssfWorkbook = new XSSFWorkbook();
    }

    public void exportData(HttpServletResponse response) throws IOException {
        headerLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        xssfWorkbook.write(outputStream);
        xssfWorkbook.close();
        outputStream.close();
    }
}