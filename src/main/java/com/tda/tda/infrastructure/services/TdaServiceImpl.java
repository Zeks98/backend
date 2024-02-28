package com.tda.tda.infrastructure.services;

import com.tda.tda.core.models.ExcelData;
import com.tda.tda.core.models.TdaSingle;
import com.tda.tda.core.repositories.TdaFileRepository;
import com.tda.tda.core.repositories.TdaRepository;
import com.tda.tda.core.services.TdaService;
import com.tda.tda.core.models.Tda;
import com.tda.tda.infrastructure.sql.entities.TdaEntity;
import com.tda.tda.infrastructure.sql.entities.TdaFileEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

@Service
public class TdaServiceImpl implements TdaService {

    @Autowired
    private TdaRepository tdaRepository;

    @Autowired
    private TdaFileRepository tdaFileRepository;

    @Autowired
    @Qualifier("mapper")
    private ModelMapper mapper;

    public TdaServiceImpl() {

    }

    @Override
    public List<TdaSingle> getAllFiles() {
        // fetch all the files form database
        var result = tdaRepository.findAll();
        var mapped = new ArrayList<TdaSingle>();

        for (var x : result) {
            mapped.add(mapper.map(x, TdaSingle.class));
        }
        return mapped;
    }

    @Override
    public void saveAllFilesList(List<TdaEntity> fileList) {
        for (TdaEntity fileModal : fileList)
            this.tdaRepository.save(fileModal);
    }

    @Override
    public List<Tda> saveFile(String file) {
        try {
            var tdaEntity = new TdaEntity();
            tdaEntity.setName("zeljko");
            tdaEntity.setFileName("appi");
            tdaEntity.setFileContent(file);

            var result = this.tdaRepository.save(tdaEntity);

            // process file rows and columns
            var processed = this.processColumns(file);

            for (var p : processed) {
                // this.saveChunkedData(p);

                var tdaFileEntity = new TdaFileEntity();
                tdaFileEntity.setFirstName(p.getColumns().get("ime"));
                tdaFileEntity.setEducation(p.getColumns().get("fakultet"));
                tdaFileEntity.setLastName(p.getColumns().get("prezime"));
                tdaFileEntity.setJob(p.getColumns().get("posao"));
                tdaFileEntity.setJobE(p.getColumns().get("zanimanje"));
                tdaFileEntity.setFileId(result.getId());
                this.tdaFileRepository.save(tdaFileEntity);
            }

            var all = this.getContentById(result.getId());

            // map with model mapper

            return all;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Tda> getContentById(Long id) {
        var result = this.tdaFileRepository.findByFileId(id);

        // map from DB model to CORE model, from result -> mapped
        var mapped = new ArrayList<Tda>();

        for (var x : result) {
            mapped.add(mapper.map(x, Tda.class));
        }
        // return mapped model

        return mapped;
    }
//
//    @Override
//    public List<String> getContentById(String filename) {
//        var result = this.tdaRepository.getContentById(filename);
//        var mappedResult = new ArrayList<String>(); //// map here;
//
//        return mappedResult;
//    }

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
        return excelDataList;
    }

}