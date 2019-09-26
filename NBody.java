public class NBody
{
	public static double readRadius(String filename)
	{
		In in = new In(filename);
		int number = in.readInt();
		return in.readDouble();
	}

	public static Body[] readBodies(String filename)
	{
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radius = in.readDouble();

		Body[] listOfBodies = new Body[numPlanets];
		int counter = 0;
		
		while(counter < numPlanets)
		{
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();

			listOfBodies[counter] = new Body(xPos, yPos, xVel, yVel, mass, name);
			counter = counter + 1;
		}

		return listOfBodies;

	}

	public static void main(String[] args)
	{
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Body[] listOfPlanets = readBodies(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for(Body b: listOfPlanets)
		{
			 b.draw();
		}

		StdDraw.enableDoubleBuffering();

		double time = 0;
		while(time <= t)
		{
			double[] xForces = new double[listOfPlanets.length];
			double[] yForces = new double[listOfPlanets.length];

			for(int i = 0; i < listOfPlanets.length; i++)
			{
				xForces[i] = listOfPlanets[i].calcNetForceExertedByX(listOfPlanets);
				yForces[i] = listOfPlanets[i].calcNetForceExertedByY(listOfPlanets);
			} 

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for(int j = 0; j < listOfPlanets.length; j++)
			{
				listOfPlanets[j].update(dt, xForces[j], yForces[j]);
				listOfPlanets[j].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;
		}

		StdOut.printf("%d\n", listOfPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < listOfPlanets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	listOfPlanets[i].xxPos, listOfPlanets[i].yyPos, listOfPlanets[i].xxVel,
            	listOfPlanets[i].yyVel, listOfPlanets[i].mass, listOfPlanets[i].imgFileName);}   

	}


}