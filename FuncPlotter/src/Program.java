import java.awt.Color;

import graphics.Canvas;

public class Program {
    // Canvas GUI instance providing the drawing area on which to plot the functions
    private static Canvas canvas;

    public static void main(String[] args) {
        
        // Create the canvas instance, set its range to x:[-360, 360] and y:[-240, 240] then 
        // open it on the screen. Use canvas.plot(pX, pY) to plot a point at the (pX, pY) coords
        canvas = new Canvas();
        canvas.setRange(-360, -240, 360, 240);
        canvas.open();

        Function sine = (x) -> {return (200 * Math.sin(Math.PI * ((double) x / 360)));};
    	Function quad = (x) -> {return ((x-200) * (x+200)) / 250;};
    	Function log = (x) -> {return (20 * Math.log(x));};
    	Function cubic = (x) -> {return (Math.pow(x, 3) / 200000);};
    	Function step = (x) -> {return x <= -100 ? -100 : x >= 100 ? 100 : x;};
        
        //Function[] funcs = {sine, quad, log, cubic};
        
        plotFunctions(sine, quad, log, cubic, step);

        // Pause and close the canvas then terminate the program.
        canvas.pause();
        canvas.close();
    }
    
    // plots various functions
    public static void plotFunctions(Function... funcs) {
    	
    	/*Function sine = (x) -> {return (200 * Math.sin(Math.PI * ((double) x / 360)));};
    	Function quad = (x) -> {return ((x-200) * (x+200)) / 250;};
    	Function log = (x) -> {return (20 * Math.log(x));};
    	Function cubic = (x) -> {return (Math.pow(x, 3) / 200000);};*/
    	
    	 // Draw a short red diagonal on the canvas
        canvas.pause();
        canvas.setColor(Color.red);
        for (int i = 0; i < 100; i++) {
            canvas.plot(i, i);
        }
        
        // Draws a sine function
        canvas.pause();
        canvas.setColor(Color.blue);
        for (int i = -360; i <= 360; i++) {
        	canvas.plot(i, (int) funcs[0].calc(i));
        }
        
        // Draws a quadratic function
        canvas.pause();
        canvas.setColor(Color.green);
        for (int i = -360; i <= 360; i++) {
        	canvas.plot(i, (int) funcs[1].calc(i));
        }
        
        // Draws a log function
        canvas.pause();
        canvas.setColor(Color.magenta);
        for (int i = -360; i <= 360; i++) {
        	canvas.plot(i, (int) funcs[2].calc(i));
        }
        
        // Draws a cubic function
        canvas.pause();
        canvas.setColor(Color.black);
        for (int i = -360; i <= 360; i++) {
        	canvas.plot(i, (int) funcs[3].calc(i));
        }
        
        // Draws a step function
        canvas.pause();
        canvas.setColor(Color.orange);
        for (int i = -360; i <= 360; i++) {
        	canvas.plot(i, (int) funcs[4].calc(i));
        }
    	
    }
}
