public class NBody {
	public static double readRadius(String fileName){
			In in = new In(fileName);
			int num = in.readInt();
			double Radius = in.readDouble();
			return Radius;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		for(int i = 0;i < num ;i++){
			double xp = in.readDouble();
			double yp = in.readDouble();
			double xv = in.readDouble();
			double yv = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xp, yp, xv, yv, m, img);
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] pps = readPlanets(filename);
		
		StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
		StdDraw.enableDoubleBuffering();

		double t = 0;
		int sum = pps.length;
		double[] xFroces = new double[sum];
		double[] yFroces = new double[sum];

		while(t < T){

			for(int i = 0; i < pps.length; i++){
				xFroces[i] = pps[i].calcNetForceExertedByX(pps);
				yFroces[i] = pps[i].calcNetForceExertedByY(pps);
			}


			for(int i = 0; i < pps.length; i++){
				pps[i].update(dt, xFroces[i], yFroces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			StdDraw.show();

			for(Planet planet : pps){
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t = t + dt;
		}

		StdOut.printf("%d\n", pps.length);
		StdOut.printf("%.2e\n", r);
		for(int i = 0; i < pps.length ; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12\n",
							pps[i].xxPos, pps[i].yyPos, pps[i].xxVel,
							pps[i].yyVel, pps[i].mass, pps[i].imgFileName);
		}
	}

}