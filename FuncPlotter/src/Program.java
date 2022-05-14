import java.awt.Color;

import graphics.Canvas;

public class Program {
	// Canvas GUI instance providing the drawing area on which to plot the functions
	private static Canvas canvas;
	private static final int THICKNESS = 2;

	public static void main(String[] args) {

		// Create the canvas instance, set its range to x:[-360, 360] and y:[-240, 240]
		// then
		// open it on the screen. Use canvas.plot(pX, pY) to plot a point at the (pX,
		// pY) coords
		canvas = new Canvas();
		canvas.setRange(-360, -240, 360, 240);
		canvas.open();

		Function sine = new Function() {
			public double calc(double x) {
				return (200 * Math.sin(Math.PI * ((double) x / 360)));
			}

			public Color colorer() {
				return Color.blue;
			}
		};
		Function quad = new Function() {
			public double calc(double x) {
				return ((x - 200) * (x + 200)) / 250;
			}

			public Color colorer() {
				return Color.green;
			}
		};
		Function log = new Function() {
			public double calc(double x) {
				if (x <= 0) return Integer.MAX_VALUE;
				else if (x == 1) return -240;
				return (20 * Math.log(x));
			}

			public Color colorer() {
				return Color.magenta;
			}
		};
		Function cubic = new Function() {
			public double calc(double x) {
				return (Math.pow(x, 3) / 200000);
			}

			public Color colorer() {
				return Color.black;
			}
		};
		Function step = new Function() {
			public double calc(double x) {
				return x <= -100 ? -100 : x >= 100 ? 100 : x;
			}

			public Color colorer() {
				return Color.orange;
			}
		};
		NonFunction circle = new NonFunction() {
			public double[] calc1(double x) {
				double[] outputs = new double[2];
				double output1 = 10000-x*x;
				if (output1 < 0) {
					outputs[0] = Integer.MAX_VALUE;
				} else {
					outputs[0] = (Math.sqrt(output1));
				}
				outputs[1] = -outputs[0];
				return outputs;
			}
			
			public double[] calc2(double y) {
				double[] outputs = new double[2];
				double output1 = -(y*y+10000);
				if (output1 < 0) {
					outputs[0] = Integer.MAX_VALUE;
				} else {
					outputs[0] = (Math.sqrt(output1));
				}
				outputs[1] = -outputs[0];
				return outputs;
			}
			
			public Color colorer() {
				return Color.red;
			}
		};
		/*NonFunction arc = new NonFunction() {
			
		}*/
				
		//System.out.println(Math.log(-10));

		plotFunctions(sine, quad, log, cubic, step);
		plotNonFunctions(circle);
		//drawLine(-2, -200, 2, 200);

		// Pause and close the canvas then terminate the program.
		canvas.pause();
		canvas.close();
	}

	// plots various functions
	public static void plotFunctions(Function... funcs) {

		/* // Draw a short red diagonal on the canvas
		canvas.pause();
		canvas.setColor(Color.red);
		for (int i = 0; i < 100; i++) {
			betterPlot(i, i);
		}*/

		// Draws all passed functions
		for (Function f : funcs) {
			canvas.pause();
			canvas.setColor(f.colorer());
			int lastY = Integer.MAX_VALUE;
			for (int x = -360; x <= 360; x++) {
				//canvas.plot(x, (int) f.calc(x));
				int cur = (int) f.calc(x);
				if (lastY != Integer.MAX_VALUE) drawLine(x-1, lastY, x, cur);
				lastY = cur;
			}
		}
	}
	
	// plots nonfunctions
	public static void plotNonFunctions(NonFunction... nFuncs) {
		
		for (NonFunction f : nFuncs) {
			canvas.pause();
			canvas.setColor(f.colorer());
			for (int x = -360; x <= 360; x++) {
				double[] outputs = f.calc1(x);
				for (double y : outputs) {
					betterPlot(x, (int) y);
				}
			}
			for (int y = -240; y <= 240; y++) {
				double[] outputs = f.calc1(y);
				for (double x : outputs) {
					betterPlot((int) x, y);
				}
			}
		}
		
	}
	
	// allows for thicker lines
	public static void betterPlot(int x, int y) {
		if (y == Integer.MAX_VALUE) {
			return;
		}
		for (int i = x - THICKNESS + 1; i < x + THICKNESS; i++) {
			for (int i2 = y - THICKNESS + 1; i2 < y + THICKNESS; i2++) {
				canvas.plot(i, i2);
			}
		}
	}
	
	// draws a line of best fit between two points
	public static void drawLine(int x1, int y1, int x2, int y2) {
		double slope = (double) (y2-y1) / (x2-x1);
		double b = y1 - slope * x1;
		for (int i = x1; i < x2; i++) {
			betterPlot(i, (int) (slope * i + b));
		}
		for (int i = y1; i < y2; i++) {
			betterPlot((int) ((i-b)/slope), i);
		}
	}
}
