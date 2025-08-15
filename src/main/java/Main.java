import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String json = readString("new_data.json");

        List<Employee> employees = jsonToList(json);

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static String readString(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Main.class.getClassLoader().getResourceAsStream(fileName)))) {

            if (reader == null) {
                throw new IOException("Файл не найден в resources: " + fileName);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return content.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> employees = new ArrayList<>();

        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();

        Gson gson = new GsonBuilder().create();
        
        for (JsonElement element : jsonArray) {
            Employee employee = gson.fromJson(element, Employee.class);
            employees.add(employee);
        }

        return employees;
    }
}