public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				double yV, double m, String img){
			xxPos = xP;
			yyPos = yP;
			xxVel = xV;
			yyVel = yV;
			mass = m;
			imgFileName = img;
	}

	public Planet(Planet p){
			xxPos = p.xxPos;
			yyPos = p.yyPos;
			xxVel = p.xxVel;
			yyVel = p.yyVel;
			mass = p.mass;
			imgFileName = p.imgFileName;		
	}

	public double calcDistance(Planet p){
			return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)
					     +(yyPos - p.yyPos)*(yyPos - p.yyPos));
	}
	
	public double calcForceExertedBy(Planet p){
			double r = calcDistance(p);
			// return ((G*mass*p.mass)/(calcDistance(Planet p)*calcDistance(Planet p)));
			return G * mass * p.mass / (r * r);
	}
	
	public double calcForceExertedByX(Planet p){
			double F = calcForceExertedBy(p);
			double r = calcDistance(p);
			double dx = p.xxPos - xxPos;
			return F * dx / r;
	}
	
	public double calcForceExertedByY(Planet p){
			double F = calcForceExertedBy(p);
			double r = calcDistance(p);
			double dy = p.yyPos - yyPos;
			return F * dy / r;
	}
	
	public double calcNetForceExertedByX(Planet[] ps){
			int count = 0;
			double sumF = 0;
			for(count = 0; count < ps.length ; count++){
				if(this.equals(ps[count])){
					continue;
				}
				sumF = sumF + this.calcForceExertedByX(ps[count]);
			}
			return sumF;
	}

	public double calcNetForceExertedByY(Planet[] ps){
			int count = 0;
			double sumF = 0;
			for(count = 0; count < ps.length ; count++){
				if(this.equals(ps[count])){
					continue;
				}
				sumF = sumF + this.calcForceExertedByY(ps[count]);
			}
			return sumF;
	}
	public void update(double dt, double Fx, double Fy){
			double ax = Fx/mass;
			double ay = Fy/mass;
			xxVel = xxVel + dt * ax;
			yyVel = yyVel + dt * ay;
			xxPos = xxPos + dt * xxVel;
			yyPos = yyPos + dt * yyVel;
	}

	public void draw(){
			StdDraw.picture(xxPos, yyPos,"images/"+imgFileName);
	}
}