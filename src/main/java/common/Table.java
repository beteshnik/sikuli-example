package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table {
    private static WebElement table = Browser.getDriver().findElement(By.tagName("table"));

    public static WebElement getTable() {
        return table;
    }

    public static List<String> getColumnNames() {
        return table.findElements(By.tagName("th"))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Map<String, Integer> getColumnMap() {
        return IntStream.range(0, getColumnNames().size())
                .boxed()
                .collect(Collectors.toMap(getColumnNames()::get,
                        Function.identity()));
    }

    public static List<WebElement> getElementsOfSpecificColumn(String column) {
        return table.findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .map(tds -> tds.get(getColumnMap().get(column)))
                .collect(Collectors.toList());
    }

    public static List<String> getElementsOfSpecificColumnAsString(String column) {
        return table.findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .map(tds -> tds.get(getColumnMap().get(column)))
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static List<List<WebElement>> getRows() {
        return table.findElements(By.tagName("tr"))
                .stream()
                .map(tr -> tr.findElements(By.tagName("td")))
                .filter(tds -> tds.size() > 0)
                .collect(Collectors.toList());
    }

    public static List<List<String>> getRowsAsString() {
        return getRows().stream()
                .map(row -> row.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

//    public static List<Map<String, WebElement>> getRowsMappedToHeadings() {
//        List<String> headingsAsString = getColumnNames();
//        return getRows().stream()
//                .map(row -> row.stream()
//                        .collect(Collectors.toMap(e -> headingsAsString.get(row.indexOf(e)), Function.identity())))
//                .collect(Collectors.toList());
//    }
//
//    public static List<Map<String, WebElement>> getRowsMappedToHeadings(List<String> headings) {
//        return getRowsMappedToHeadings().stream()
//                .map(e -> e.entrySet().stream().filter(m -> headings.contains(m.getKey()))
//                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
//                .collect(Collectors.toList());
//    }
//
//    public static List<Map<String, String>> getRowsAsStringMappedToHeadings() {
//        return getRowsMappedToHeadings(getColumnNames()).stream()
//                .map(m -> m.entrySet().stream()
//                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getText())))
//                .collect(Collectors.toList());
//    }
}
