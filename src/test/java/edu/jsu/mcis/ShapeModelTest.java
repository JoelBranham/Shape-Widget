package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.util.*;

public class ShapeModelTest{
	private ShapeModel square;
	private ShapeModel hexagon;
	private ShapeModel octagon;
	
	@Before
	public void setup(){
		square = new ShapeModel(4);
		hexagon = new ShapeModel(6);
		octagon = new ShapeModel(8);
	}
	
	@Test
	public void testShapesNotSelectedUponCreation(){
		assertFalse(square.isSelected());
		assertFalse(hexagon.isSelected());
		assertFalse(octagon.isSelected());
	}
	
	@Test
	public void testSelectingShape(){
		square.select();
		assertTrue(square.isSelected());
	}
	
	@Test
	public void testSquareWhereIntended(){
		square.calculateVertices(300,300, 200, 150);
		Rectangle bounds = square.getShape().getBounds();
		assertEquals(199, bounds.x + bounds.width /2);
	}
	
	@Test
	public void testHexagonIsCorrectWidth(){
		hexagon.calculateVertices(400,400, 200, 200);
		Rectangle bounds = hexagon.getShape().getBounds();
		assertEquals(100, bounds.width);
	}

	@Test
	public void testHexagonIsCorrectWidth2(){
		hexagon.calculateVertices(800,800, 200, 200);
		Rectangle bounds = hexagon.getShape().getBounds();
		assertEquals(200, bounds.width);
	}
	
	@Test
	public void testOctagonIsCorrectWidth(){
		octagon.calculateVertices(400,400, 200, 200);
		Rectangle bounds = octagon.getShape().getBounds();
		assertEquals(100, bounds.width);
	}

	@Test
	public void testOctagonIsCorrectWidth2(){
		octagon.calculateVertices(100,100, 200, 200);
		Rectangle bounds = octagon.getShape().getBounds();
		assertEquals(25, bounds.width);
	}
	
}