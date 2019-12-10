package parsing;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


import static pages.MainPage.getAbsolutePath;

public class Parser {

    // читаем файл в Json объект
    public static JsonObject jsonReadFile(String jsonPath) {
        StringBuilder builder = new StringBuilder();
        JsonParser parser = new JsonParser();
        try {

            //прочтем файл построчно
            FileInputStream fstream = new FileInputStream(getAbsolutePath(jsonPath));
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String row;
            while ((row = br.readLine()) != null) {
                builder.append(row);
            }

        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
            System.exit(1);

        }

        //Распарсим полученый текст из файла
        JsonObject elementA = parser.parse(builder.toString()).getAsJsonObject();
        return elementA;
    }

    // читаем файл в строку
    public static String readFile(String jsonPath) {
        try {
            StringBuilder builder = new StringBuilder();
            FileReader fr = new FileReader(getAbsolutePath(jsonPath));
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            fr.close();
            scanner.close();

            return builder.toString();
        } catch (Exception e) {

            System.err.println(e);
            return null;
        }
    }


    public static void saveDataToJson(String jsonPath, JsonObject jo) {
        try (
                Writer writer = new FileWriter(getAbsolutePath(jsonPath))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jo, writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    //получаем значение по ключу из файла
    public static String getKeyValueFromJsonFile(String jsonString, String keyName) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(Parser.readFile(jsonString)).getAsJsonObject();
        return jo.get(keyName).getAsString();
    }

    //получаем элемент массива по ключу
    public static String getArrayElementValueFromJsonFile(String jsonString, String keyName, int elementNumber) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(Parser.readFile(jsonString)).getAsJsonObject();
        JsonArray joArray= jo.get(keyName).getAsJsonArray();
        return joArray.get(elementNumber).getAsString();
    }

    //получаем первое значение в списке из файла
    public static String getFirstLineFromFile(String file) throws IOException {
        try {
            FileReader fr = new FileReader(getAbsolutePath(file));
            Scanner scanner = new Scanner(fr);
            String lineData = scanner.nextLine();
            fr.close();
            scanner.close();
            return lineData;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    //удаляем первое значение в списке из файла
    public static void removeFirstLineFromFile(String filePath) {
        try {
            StringBuilder builder = new StringBuilder();
            FileReader fr = new FileReader(getAbsolutePath(filePath));
            Scanner scanner = new Scanner(fr);
            scanner.nextLine();//пропускаем первую строку

            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + "\n");
            }
            fr.close();
            scanner.close();
            overWriteFile(filePath, builder.toString());
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    //получаем значение по ключу внутри data
    public static String getKeyValueFromJsonData(String jsonString, String keyName) {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(jsonString).getAsJsonObject();
        return jo.getAsJsonObject("data").get(keyName).getAsString();
    }

    //заменяем значение по ключу
    public static void editJsonValue(String filePath, String fieldName, String newValue) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(readFile(filePath)).getAsJsonObject();
        jo.addProperty(fieldName, newValue);
        Parser.saveDataToJson(filePath, jo);
    }

    //чистим массив по ключу
    public static void clearJsonArray(String filePath, String fieldName) {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(readFile(filePath)).getAsJsonObject();
//        JsonArray joArray = jo.getAsJsonArray(fieldName);
//        for(int i=0;i<joArray.size();i++) {
//            joArray.remove(i);
//        }
        jo.add(fieldName, new JsonArray());
        Parser.saveDataToJson(filePath, jo);
    }

    //заменяем значение по ключу внутри data
    public static void editJsonValue(String filePath, String fieldName, String newValue, String parent) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(readFile(filePath)).getAsJsonObject();
        jo.getAsJsonObject(parent).addProperty(fieldName, newValue);
        Parser.saveDataToJson(filePath, jo);
    }

    // перезаписываем файл
    public static void overWriteFile(String filePath, String data) {
        try {
            FileWriter fw = new FileWriter(getAbsolutePath(filePath), false);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //получаем все значения из json без ключей
    public static ArrayList<String> jsonToArray(JsonObject jo) {
        ArrayList<String> joContent = new ArrayList<>();

        for (Object o : jo.entrySet()) {
            Map.Entry e = (Map.Entry) o;
            joContent.add(e.getValue().toString());
        }
        return joContent;
    }

    //составляем ФИО из полей в json
    public static String getFioFromProfileJsonFile(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(Parser.readFile(json)).getAsJsonObject();
        String fio = jo.get("lastName").getAsString() + " " + jo.get("firstName").getAsString();
        if (jo.has("middleName")) {
            fio = fio + " " + jo.get("middleName").getAsString();
        }
        return fio;
    }

    //получаем номер протокола из файла json, на основе которого создавался протокол через API
    public static String getProtocolNumberFromFile(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(Parser.readFile(json)).getAsJsonObject();
        JsonArray properties = jo.getAsJsonObject("pageItems").getAsJsonArray("itemsToSubmit");
        return properties.get(2).getAsJsonObject().get("v").getAsString();
    }

    //создаём список команды из игроков и тренеров 2 команд в виде строки
    public static String createUnitedTeamList(String teamData1, String teamData2) {
        JsonParser parser = new JsonParser();
        JsonObject team1 = parser.parse(Parser.readFile(teamData1)).getAsJsonObject();
        JsonObject team2 = parser.parse(Parser.readFile(teamData2)).getAsJsonObject();
        JsonArray playerArray1 = team1.getAsJsonArray("player");
        JsonArray playerArray2 = team2.getAsJsonArray("player");
        JsonArray coachArray1 = team1.getAsJsonArray("coach");
        JsonArray coachArray2 = team2.getAsJsonArray("coach");
        playerArray1.addAll(playerArray2);
        coachArray1.addAll(coachArray2);
        String team = "Список игроков: " + playerArray1.toString().replaceAll("[\"\\[\\]]","") + " " + "Список тренеров: " + coachArray1.toString().replaceAll("[\"\\[\\]]","");
        System.out.println(team);
        return team;
    }

    public static String creatListFromArray(String jsonFile, String arrayName) {
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(Parser.readFile(jsonFile)).getAsJsonObject();
        JsonArray ja = jo.getAsJsonArray(arrayName);
        return ja.toString().replaceAll("[\"\\[\\]]","");
    }

}