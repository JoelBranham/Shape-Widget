package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.util.*;

public class CustomWidget extends JPanel implements MouseListener {
    private java.util.List<ShapeObserver> observers;
    private final Color HEX_SELECTED_COLOR = Color.green;
    private final Color OCT_SELECTED_COLOR = Color.red;
    private final Color DEFAULT_COLOR = Color.white;
	private ShapeModel hexagon, octagon;
	
    public CustomWidget() {
        observers = new ArrayList<>();		
		hexagon = new ShapeModel(6);
		octagon = new ShapeModel(8);
		hexagon.select();
		calculateHexagonVertices();
		calculateOctagonVertices();
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
    }
	
	private void calculateHexagonVertices(){
        Dimension dim = getPreferredSize();
		hexagon.calculateVertices(getWidth(), getHeight(), getWidth()/3, getHeight()/2);
	}
	
	private void calculateOctagonVertices(){
		Dimension dim = getPreferredSize();
		octagon.calculateVertices(getWidth(), getHeight(), getWidth() - getWidth()/3, getHeight()/2);
	}
    
    public void addShapeObserver(ShapeObserver observer) {
        if(!observers.contains(observer)) observers.add(observer);
    }
    public void removeShapeObserver(ShapeObserver observer) {
        observers.remove(observer);
    }
    private void notifyObservers() {
        ShapeEvent event = new ShapeEvent(hexagon.isSelected(), octagon.isSelected());
        for(ShapeObserver obs : observers) {
            obs.shapeChanged(event);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {   
        return new Dimension(200, 200);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
		calculateHexagonVertices();
		calculateOctagonVertices();
        Shape hexagonShape = hexagon.getShape(), octagonShape = octagon.getShape();
		g2d.setColor(Color.black);
		g2d.draw(octagonShape);
        g2d.draw(hexagonShape);
		if(hexagon.isSelected()) {
            g2d.setColor(HEX_SELECTED_COLOR);
            g2d.fill(hexagonShape); 
        }
		else{
			g2d.setColor(DEFAULT_COLOR);
			g2d.fill(hexagonShape);
		}
        if (octagon.isSelected()){
			g2d.setColor(OCT_SELECTED_COLOR);
			g2d.fill(octagonShape);    
        }
		else{
			g2d.setColor(DEFAULT_COLOR);
			g2d.fill(octagonShape);
		}
    }

    public void mouseClicked(MouseEvent event) { 
		Shape hexagonShape = hexagon.getShape(), octagonShape = octagon.getShape();
        if(hexagonShape.contains(event.getX(), event.getY())) {
            hexagon.select();
			if (octagon.isSelected()){
				octagon.select();
			}
            notifyObservers();
        }
        if(octagonShape.contains(event.getX(), event.getY())) {
            octagon.select();
			if (hexagon.isSelected()){
				hexagon.select();
			}
            notifyObservers();
        }
		repaint(hexagonShape.getBounds());
		repaint(octagonShape.getBounds());
    }
    public void mousePressed(MouseEvent event) {}
    public void mouseReleased(MouseEvent event) {}
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}

	
	public ShapeModel getHexagon(){
		return hexagon;
	}
	
	public ShapeModel getOctagon(){
		return octagon;
	}

	public static void main(String[] args) {
		JFrame window = new JFrame("Custom Widget");
        window.add(new CustomWidget());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 300);
        window.setVisible(true);
	}
	
}
