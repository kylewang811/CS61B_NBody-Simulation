public class Body
{
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body (double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b)
	{
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body body1)
	{
		return Math.sqrt((Math.pow((this.xxPos - body1.xxPos), 2) + (Math.pow((this.yyPos - body1.yyPos), 2))));
	}

	public double calcForceExertedBy(Body body1)
	{
		double distance = calcDistance(body1);
		return (((6.67e-11) * body1.mass * this.mass)/(Math.pow(distance, 2)));
	}

	public double calcForceExertedByX(Body body1)
	{
		double distance = calcDistance(body1);
		double force = calcForceExertedBy(body1);

		return((force * (body1.xxPos - this.xxPos))/distance);
	}

	public double calcForceExertedByY(Body body1)
	{
		double distance = calcDistance(body1);
		double force = calcForceExertedBy(body1);

		return((force * (body1.yyPos - this.yyPos))/distance);
	}

	public double calcNetForceExertedByX(Body[] bodies)
	{
		int i = 0;
		double netforceX = 0.0;
		while(i < bodies.length)
		{
			if(this.equals(bodies[i]))
			{
				netforceX = netforceX;
			}
			else
			{
				netforceX = netforceX + calcForceExertedByX(bodies[i]);
			}
			i = i + 1;
		}
		return netforceX;
	}

	public double calcNetForceExertedByY(Body[] bodies)
	{
		int i = 0;
		double netforceY = 0.0;
		while(i < bodies.length)
		{
			if(this.equals(bodies[i]))
			{
				netforceY = netforceY;
			}
			else
			{
				netforceY = netforceY + calcForceExertedByY(bodies[i]);
			}
			i = i + 1;
		}
		return netforceY;
	}

	public void update(double dt, double fX, double fY)
	{
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		this.xxVel = this.xxVel + (aX * dt);
		this.yyVel = this.yyVel + (aY * dt);
		this.xxPos = this.xxPos + (this.xxVel * dt);
		this.yyPos = this.yyPos + (this.yyVel * dt);
	}

	public void draw()
	{
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}