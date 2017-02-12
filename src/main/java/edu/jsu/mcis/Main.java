package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Point2D;

public class Main extends JPanel implements ShapeObserver {
    private CustomWidget widget;
    private JLabel label;

    public Main() {   
		label = new JLabel("Hexagon", JLabel.CENTER);
		label.setName("label");
        widget = new CustomWidget();
		widget.addShapeObserver(this);
        setLayout(new BorderLayout());
        add(widget, BorderLayout.CENTER);
        add(label, BorderLayout.NORTH);
    }
    
    public void shapeChanged(ShapeEvent event) {
		if (event.isHexSelected()){
			label.setText("Hexagon");
		}
		else if (event.isOctSelected()){
			label.setText("Octagon");
		}
		else{
			label.setText("NOT SELECTED");
		}
    }

	public static void main(String[] args) {
		JFrame window = new JFrame();
        window.setTitle("Main");
        window.add(new Main());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 500);
        window.setVisible(true);
	}
}