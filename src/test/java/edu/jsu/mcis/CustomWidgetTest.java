package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.*;
import java.awt.event.*;

public class CustomWidgetTest {
    private CustomWidget widget;
    
    @Before
    public void setUp() {
        widget = new CustomWidget();
    }
    
	@Test
	public void testOctagonIsInitiallyDeselected() {
		assertFalse(widget.getOctagon().isSelected());
	}

}