package com.pages.bugeting.importBudgetPages;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.data.dynamic.FakerDataFactory;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.pages.bugeting.AllBudgetsPage;

public class ImportBudgetPage extends AllBudgetsPage {
    String importBudgetText = "//div[text()='Import Budget']";
    String importBtn = "//span[text()='Import']";

    public ImportBudgetPage validateImportBudget() {
        waitForElementToLoad(importBudgetText);
        if (isVisible(importBudgetText, "Import Budget Text")) {
            reportPass("User reaches to the right Import Budget page");
        } else {
            reportFailAndThrowException("Import Budget Page is not loaded.");
        }
        return this;
    }

    public ImportBudgetPage importBudget(String fileName) {

        uploadFile(fileName);
        reportPass("User successfully Imported the Budget file");
        return new ImportBudgetPage();
    }

    public static String readAndModifyCsv() {
        String csvFile = "./src/test/resources/upload/Budget_Import_Chrome.csv";
        String updatedBudgetName = "";
        List<String[]> csvData = new ArrayList<>();
        clientCompanyName.set(FakerDataFactory.getFirstName());

        // Step 1: Read the CSV file and update the "BUDGET NAME" value
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length >= 2 && line[0].equalsIgnoreCase("BUDGET NAME")) {
                    String originalBudgetName = line[1];
                    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    updatedBudgetName = clientCompanyName.get() + timeStamp;
                    line[1] = updatedBudgetName;
                }
                // Trim each line to remove trailing spaces
                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].trim();
                }
                // Add the trimmed line to the csvData list
                csvData.add(line);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        // Step 2: Write the updated data back to the CSV file without adding an extra line at the end
        try (FileWriter fileWriter = new FileWriter(csvFile, false)) {
            for (int i = 0; i < csvData.size(); i++) {
                String[] row = csvData.get(i);
                StringBuilder rowString = new StringBuilder();
                for (int j = 0; j < row.length; j++) {
                    if (j > 0) {
                        rowString.append(CSVWriter.DEFAULT_SEPARATOR);
                    }
                    rowString.append(row[j]);
                }
                fileWriter.write(rowString.toString());
                if (i < csvData.size() - 1) {
                    fileWriter.write(CSVWriter.DEFAULT_LINE_END);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updatedBudgetName;
    }


    public static String updatedCsvValue = "";

    public static String readUpdatedValueInCsv() {
        readAndModifyCsv();
        String csvFile = "./src/test/resources/upload/Budget_Import_Chrome.csv";
        Map<String, String> csvData = new HashMap<>();

        // Step 3: Read the CSV file again to verify the updated value
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length >= 2) {
                    csvData.put(line[0], line[1]);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        updatedCsvValue = csvData.get("BUDGET NAME");
        return csvData.get("BUDGET NAME");
    }


    public AllBudgetsPage clickImportBtn() {
        click(importBtn, "Import Button");
        pause("medium");

        return new AllBudgetsPage();
    }
}
