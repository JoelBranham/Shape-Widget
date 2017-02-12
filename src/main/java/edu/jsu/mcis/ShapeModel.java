package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import java.util.*;

public class ShapeModel{
		private boolean selected;
		private int sides;
		private Point2D.Double[] points;
		
		public ShapeModel(){
			this(4);
		}

		public ShapeModel(int sides){
			try{
				if (sides <= 0){
					throw new ShapeException();
				}	
				else{
					this.sides = sides;
					selected = false;
					points = new Point2D.Double[sides];
				}
			}
			catch(ShapeException e){}
		}

		public void calculateVertices(double windowWidth, double windowHeight, double widthOffset, double heightOffset){
			Double angleAdd = 360.0 / sides;
			for (int i = 0, sumTheta = 0; sumTheta < 360; i++, sumTheta += angleAdd){
				points[i] = new Point2D.Double( Math.cos(Math.toRadians(sumTheta)), Math.sin(Math.toRadians(sumTheta)) );
			}
			double sideLength = Math.min(windowWidth, windowHeight) / 8;
			for(int i = 0; i < sides; i++) {
				points[i].setLocation(widthOffset + points[i].x * sideLength, heightOffset + points[i].y * sideLength);
			}
		}
		
		public Shape getShape(){
			int[] x = new int[points.length];
			int[] y = new int[points.length]; 
			for(int i = 0; i < points.length; i++) {
				x[i] = (int) points[i].x;
				y[i] = (int) points[i].y;
			}
			return new Polygon(x, y, points.length);
		}

		public void select(){
			selected = !selected;
		}
		
		public boolean isSelected(){
			return selected;
		}
		
}