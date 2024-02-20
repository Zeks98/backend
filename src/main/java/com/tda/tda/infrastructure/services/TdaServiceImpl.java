package com.tda.tda.infrastructure.services;

import com.tda.tda.core.models.ExcelData;
import com.tda.tda.core.repositories.TdaRepository;
import com.tda.tda.core.services.TdaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

@Service
public class TdaServiceImpl implements TdaService {

    @Autowired
    private TdaRepository tdaRepository;

    @Override
    public boolean saveFile(String file) throws IOException {
        var result = this.tdaRepository.saveFile(file);

        // process file rows and columns
        var processed = this.processColumns(file);
        return result;
    }

    private static String removeTrailingSlash(String str) {
        if (str != null && str.endsWith("/")) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }
    private List<ExcelData> processColumns(String file) throws IOException {
        List<ExcelData> excelDataList = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(Base64.getDecoder().decode(file)));
        Sheet sheet = workbook.getSheetAt(0);

        // Read headers (first row) to determine column order
        Row headerRow = sheet.getRow(0);
        List<String> columnOrder = new ArrayList<>();
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            String header = cell == null ? "" : cell.getStringCellValue().trim(); // Trim any whitespace
            columnOrder.add(removeTrailingSlash(header));
        }

        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (currentRow.getRowNum() == 0) {
                continue; // Skip header row
            }

            ExcelData excelData = new ExcelData();
            excelData.setColumnOrder(columnOrder);
            for (int i = 0; i < columnOrder.size(); i++) {
                Cell cell = currentRow.getCell(i);
                if (cell != null) {
                    excelData.getColumns().put(columnOrder.get(i), cell.toString());
                } else {
                    excelData.getColumns().put(columnOrder.get(i), null);
                }
            }
            excelDataList.add(excelData);
        }

        workbook.close();
        return excelDataList;
    }
}
