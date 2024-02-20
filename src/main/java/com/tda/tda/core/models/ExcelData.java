package com.tda.tda.core.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExcelData {
    private String fileName;
    private Map<String, String> columns = new HashMap<>();
    private List<String> columnOrder;
}
