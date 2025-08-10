import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileOperations {

    // Запись
    public static void writeToFile(String filename, String data) throws FileOperationException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write(data);
        } catch (IOException e) {
            throw new FileOperationException("Ошибка записи в файл " + filename + ": " + e.getMessage());
        }
    }

    // Чтение
    public static String readFromFile(String filename) throws FileOperationException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            return content.toString();
        } catch (FileNotFoundException e) {
            throw new FileOperationException("Файл не найден: " + filename);
        } catch (IOException e) {
            throw new FileOperationException("Ошибка чтения из файла " + filename + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filename = "nNekrasov.txt";
        String data = "Однажды, в студёную зимнюю пору\n" +
                "Я из лесу вышел; был сильный мороз.\n" +
                "Гляжу, поднимается медленно в гору\n" +
                "Лошадка, везущая хворосту воз.";

        try {
            // Запись в файл
            writeToFile(filename, data);

            // Чтение из файла
            String content = readFromFile(filename);
            System.out.println("\nСодержимое файла:");
            System.out.println(content);

            // Попытка чтения несуществующего файла (вызовет исключение)
            // readFromFile("nonexistent_file.txt");

        } catch (FileOperationException foe) {
            System.out.println("\nПроизошла ошибка при работе с файлом: " + foe.getMessage());
        }
    }
}
