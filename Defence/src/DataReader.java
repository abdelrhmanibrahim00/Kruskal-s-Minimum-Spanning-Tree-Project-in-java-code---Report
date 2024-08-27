import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    public static void readEdges(String filePath, ArrayList<Edge> outputData) {
        FileInputStream fileStream = null;
        Scanner scanner = null;

        try {
            fileStream = new FileInputStream(filePath);
            scanner = new Scanner(fileStream, "UTF-8");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || !line.matches("\\d+,\\d+,\\d+")) {
                    continue;
                }

                String[] parts = line.split(",");

                int v1 = Integer.parseInt(parts[0].trim());
                int v2 = Integer.parseInt(parts[1].trim());
                int weight = Integer.parseInt(parts[2].trim());

                outputData.add(new Edge(v1, v2, weight));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (fileStream != null) {
                try {
                    fileStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
