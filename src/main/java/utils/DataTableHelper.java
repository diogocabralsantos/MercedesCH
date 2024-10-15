package utils;

import io.cucumber.datatable.DataTable;

import java.util.Map;

public class DataTableHelper {

    public static Map<String, String> convertDataTableToMap(DataTable dataTable) {
        return dataTable.asMap(String.class, String.class);
    }
}
