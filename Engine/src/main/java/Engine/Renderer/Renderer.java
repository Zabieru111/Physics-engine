package Engine.Renderer;
import javax.swing.*;
import java.awt.*;

public class Renderer {
    public void render(){
        JFrame frame = new JFrame("My Window");

        // Set the size of the window
        frame.setSize(400, 300);

        // Specify what happens when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        // Make the window visible
        frame.setVisible(true);
    }
    public void render(Scene scene){

    }
}
