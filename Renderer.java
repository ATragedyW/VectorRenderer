import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.*;
import java.io.IOException;


public class Renderer extends JPanel {
   
    private Mesh cube = Mesh.createCube();
    private double angle = 0;
    
    private double cameraDistance = 5.0;
    private double focalLength = 500.0;
    
    private double cameraX = 0;
    private boolean rotating = true;

    private Vector3 lightDirection =
    new Vector3(-1, 1, -1).normalize();

private Color baseColor = new Color(70, 180, 255);
    public Renderer() {
         try {
        cube = OBJLoader.load("pyramid.obj");
        } catch (IOException e) {
        e.printStackTrace();
        }

    setBackground(Color.BLACK);

    setFocusable(true);

    addKeyListener(new KeyAdapter() {
        @Override 
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                    cameraDistance = Math.max(3.0,
                    cameraDistance - 0.2);
                    break;
                    
                case KeyEvent.VK_S:
                    cameraDistance += 0.2;
                     break;
                case KeyEvent.VK_A:
                    cameraX -= 0.2;
                    break;
                
                case KeyEvent.VK_D:
                    cameraX += 0.2;
                    break;
                case KeyEvent.VK_SPACE:
                    rotating = !rotating;
                    break;
                case KeyEvent.VK_O:
                    openModel();
                    break;
                case KeyEvent.VK_C:
                    cube = Mesh.createCube();
                    angle = 0;
                    repaint();
                    break; 
            }
            repaint();
        }
    });

    Timer timer = new Timer(16, e -> {
        if(rotating){
        angle += 0.02;
        }
        
        repaint();

        });

    timer.start();
    }

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Rotate all vertices
    Vector3[] rotated = new Vector3[cube.vertices.length];

    for (int i = 0; i < rotated.length; i++) {
        rotated[i] = rotateY(cube.vertices[i]);
    }

    // Store face indices
    Integer[] order = new Integer[cube.faces.length];

    for (int i = 0; i < order.length; i++) {
        order[i] = i;
    }

    // Sort faces from farthest to nearest
    Arrays.sort(order, (a, b) -> Double.compare(
        faceDepth(cube.faces[b], rotated),
        faceDepth(cube.faces[a], rotated)
    ));

    // Draw each face
    for (int index : order) {

        int[] face = cube.faces[index];

        int[] xPoints = new int[face.length];
        int[] yPoints = new int[face.length];

        for (int i = 0; i < face.length; i++) {
            Vector3 v = rotated[face[i]];

            xPoints[i] = projectX(v);
            yPoints[i] = projectY(v);
        }

        Vector3 normal = calculateNormal(face, rotated);

        g.setColor(calculateLighting(normal));
        g.fillPolygon(xPoints, yPoints, face.length);

        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, face.length);
    }
    
}
private double faceDepth(int[] face, Vector3[] vertices) {
    double sum = 0;

    for (int index : face) {
        sum += vertices[index].z;
    }

    return sum / face.length;
}
private Vector3 calculateNormal(int[] face, Vector3[] vertices) {

    Vector3 a = vertices[face[0]];
    Vector3 b = vertices[face[1]];
    Vector3 c = vertices[face[2]];

    Vector3 edge1 = b.subtract(a);
    Vector3 edge2 = c.subtract(a);

    return edge1.cross(edge2).normalize();
}
private Color calculateLighting(Vector3 normal) {

    double brightness = Math.max(
        0, normal.dot(lightDirection)
    );

    brightness = 0.2 + brightness * 0.8;

    return new Color(
        (int)(baseColor.getRed() * brightness),
        (int)(baseColor.getGreen() * brightness),
        (int)(baseColor.getBlue() * brightness)
    );
}

        private int projectX(Vector3 v) {
            double depth = v.z + cameraDistance;

            return (int)(getWidth() / 2.0
            + ((v.x - cameraX) * focalLength) / depth);
        }

        private int projectY(Vector3 v) {
            double depth = v.z + cameraDistance;

            return (int)(getHeight() / 2.0
        - (v.y * focalLength) / depth);
        }
    
        private Vector3 rotateY(Vector3 v) {
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);

            return new Vector3(
            v.x * cos + v.z * sin,
            v.y,
            -v.x * sin + v.z * cos
    );
    }
    private void openModel() {
    JFileChooser chooser = new JFileChooser();

    chooser.setFileFilter(
        new javax.swing.filechooser.FileNameExtensionFilter(
            "Wavefront OBJ Models", "obj"
        )
    );

    int result = chooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        try {
            cube = OBJLoader.load(
                chooser.getSelectedFile().getAbsolutePath()
            );

            angle = 0;
            repaint();

        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                this,
                "Failed to load model."
            );
        }
    }

    requestFocusInWindow();
}
}   