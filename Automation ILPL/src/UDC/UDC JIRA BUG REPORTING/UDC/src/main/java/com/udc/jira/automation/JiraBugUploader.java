package com.udc.jira.automation;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.Base64;

public class JiraBugUploader {
    private static final String JIRA_BASE_URL  = "https://zapgift.atlassian.net";
    private static final String JIRA_EMAIL     = "himanshu.choudhary@infinitelocus.com";
    private static final String JIRA_API_TOKEN = "ATATT3xFfGF0IsoBmX3e7CEuRErMH7AW4U1AuWiNxtFAdnOtsAK0SqO9cdCGEGuJMqg-Gp-l_rAYldn3PtsSYlkfQoecbRvx2jMbkWcLAglnsi73Md67VMUJI4JgjlzkSqYTp6vLqMvMpLY3MUE_aeJz3QbM9488QKBcTQHHauvqDjwyg9qYp58=A2D1422F";
    private static final String PROJECT_KEY    = "UL";

    public static void main(String[] args) throws Exception {
        File excelFile = new File("src/test/resources/UDC_Bugs.xlsx");
        System.out.println("Excel exists: " + excelFile.exists());

        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFile));
        Sheet sheet       = workbook.getSheetAt(0);
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) break;

            // Stop if the row is effectively empty by checking the 'module' column
            Cell moduleCell = row.getCell(0);
            if (moduleCell == null || moduleCell.getCellType() == CellType.BLANK || getCellStringValue(moduleCell).trim().isEmpty()) {
                System.out.println("Empty row detected at row " + (i + 1) + ". Stopping import.");
                break;
            }

            // Read your columns
            String module      = getCellStringValue(row.getCell(0));
            String type        = getCellStringValue(row.getCell(1));
            String description = getCellStringValue(row.getCell(3));
            String status      = getCellStringValue(row.getCell(4));

            // Build summary & payload
            String summary = module + " - " + type;
            Map<String, Object> fields = new HashMap<>();
            fields.put("summary", summary);
            fields.put("description", description);
            fields.put("issuetype", Map.of("name", "Bug"));
            fields.put("project",   Map.of("key", PROJECT_KEY));
            fields.put("priority",  Map.of("name", "Medium"));

            String jsonPayload = mapper.writeValueAsString(Map.of("fields", fields));

            // Create issue and get its key
            String issueKey = createJiraIssue(jsonPayload);
            System.out.println("Created: " + issueKey);

            // Write that key into column 6 (index 5)
            Cell keyCell = row.getCell(5);
            if (keyCell == null) keyCell = row.createCell(5);
            keyCell.setCellValue(issueKey);

            Thread.sleep(500); // optional delay between requests
        }

        // Save changes back to the same Excel file
        try (FileOutputStream out = new FileOutputStream(excelFile)) {
            workbook.write(out);
        }
        workbook.close();
        System.out.println("All done – Excel updated with Jira keys.");
    }

    private static String createJiraIssue(String jsonPayload) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(JIRA_BASE_URL + "/rest/api/2/issue");
            post.setHeader("Authorization", getBasicAuth());
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(jsonPayload, ContentType.APPLICATION_JSON));

            try (CloseableHttpResponse response = client.execute(post)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String body    = new String(response.getEntity().getContent().readAllBytes());

                if (statusCode == 201) {
                    JsonNode node = new ObjectMapper().readTree(body);
                    return node.get("key").asText();
                } else {
                    throw new RuntimeException("Failed to create issue (" + statusCode + "): " + body);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating Jira issue", e);
        }
    }

    private static String getCellStringValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue();
            case NUMERIC -> {
                double d = cell.getNumericCellValue();
                yield (d == (long) d) ? String.valueOf((long) d) : String.valueOf(d);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default      -> "";
        };
    }
    private static String getBasicAuth() {
        String creds = JIRA_EMAIL + ":" + JIRA_API_TOKEN;
        return "Basic " + Base64.getEncoder().encodeToString(creds.getBytes());
    }
}