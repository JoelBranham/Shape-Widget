package keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.swing.context.Context;

import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.ComponentChooser;
import java.awt.Component;
import java.awt.Shape;
import java.awt.Rectangle;
import edu.jsu.mcis.CustomWidget;

@RobotKeywords
public class CustomWidgetKeywords {

    @RobotKeyword("Clicks on the custom widget at the given coordinates.\n")
    @ArgumentNames({"x", "y"})
    public void clickCustomWidget(int x, int y) {	
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        operator.clickMouse(x, y, 1);	
    }
    
    @RobotKeyword("Clicks inside the shape of the custom widget.\n")
    @ArgumentNames({})
    public void clickHexagon() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle bounds = w.getHexagon().getShape().getBounds();  
        operator.clickMouse(bounds.x + bounds.width/3, bounds.y + bounds.height/2, 1);
    }
	
    @RobotKeyword("Clicks inside the shape of the custom widget.\n")
    @ArgumentNames({})
    public void clickOctagon() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle bounds = w.getOctagon().getShape().getBounds();  
        operator.clickMouse(bounds.x + bounds.width - bounds.width/3, bounds.y + bounds.height/2, 1);
    }
    
    @RobotKeyword("Clicks outside the shape of the custom widget.\n")
    @ArgumentNames({})
    public void clickOutside() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle HexagonBounds = w.getHexagon().getShape().getBounds(); 
		Rectangle OctagonBounds = w.getOctagon().getShape().getBounds(); 
        operator.clickMouse((HexagonBounds.x + OctagonBounds.x) / 2, (HexagonBounds.y + OctagonBounds.y) / 2, 1);
    }
        
    class CustomWidgetChooser implements ComponentChooser {
        public CustomWidgetChooser() {}
        public boolean checkComponent(Component comp) {
            return (comp instanceof CustomWidget);
        }
        public String getDescription() {
            return "Any CustomWidget";
        }
    }
}