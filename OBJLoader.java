import java.io.*;
import java.util.ArrayList;

public class OBJLoader {

    public static Mesh load(String filename) throws IOException {

        ArrayList<Vector3> vertices = new ArrayList<>();
        ArrayList<int[]> faces = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.split("#", 2)[0].trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts[0].equals("v")) {

                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double z = Double.parseDouble(parts[3]);

                    vertices.add(new Vector3(x, y, z));

                } else if (parts[0].equals("f")) {

                    int[] face = new int[parts.length - 1];

                    for (int i = 1; i < parts.length; i++) {
                        String[] values = parts[i].split("/");
                        int index = Integer.parseInt(values[0]);

                        face[i - 1] = index < 0
                            ? vertices.size() + index
                            : index - 1;
                    }

                    faces.add(face);
                }
            }
        }

        return new Mesh(
            vertices.toArray(new Vector3[0]),
            new int[0][],
            faces.toArray(new int[0][])
        );
    }
}