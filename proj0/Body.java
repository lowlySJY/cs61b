public class Body {
	public double xxPos;
	// current x position

	public double yyPos;
	// current y position

	public double xxVel;
	// current velocity in the x direction

	public double yyVel;
	// current velocity in the y direction

	public double mass;
	// mass

	public String imgFileName;
	// the name of the file that corresponds to the image that 
	// depicts the body

	public Body(double xP, double yP, double xV,
				double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		/**copy the Body b */
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		/**return a double equal to the distance between two body */
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		double dis = Math.sqrt(dx*dx + dy*dy);
		return dis;
	}

	public double calcForceExertedBy(Body b) {
		/**return a double describing the force exerted on this body
		 * by the given body
		 */
		double dis = this.calcDistance(b);
		double G = 6.67e-11;
		double force = G * this.mass * b.mass / (dis * dis);
		return force;
	}

	public double calcForceExertedByX(Body b){
		/**return the force exerted in the X direction */
		double force = this.calcForceExertedBy(b);
		double dx = -(this.xxPos - b.xxPos);
		double dis = this.calcDistance(b);
		double xforce = force * dx / dis;
		return xforce;
	}

	public double calcForceExertedByY(Body b){
		/**return the force exerted in the Y direction */
		double force = this.calcForceExertedBy(b);
		double dy = -(this.yyPos - b.yyPos);
		double dis = this.calcDistance(b);
		double yforce = force * dy / dis;
		return yforce;
	}

	public double calcNetForceExertedByX(Body[] b){
		// double[] netforcex = new double[b.length];
		double sum = 0;
		for (int i = 0; i < b.length; i++) {
			if (this.equals(b[i])){
				continue;
			} else{
				double xforce = this.calcForceExertedByX(b[i]);
				sum = sum + xforce;
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Body[] b){
		// double[] netforcex = new double[b.length];
		double sum = 0;
		for (int i = 0; i < b.length; i++) {
			if (this.equals(b[i])){
				continue;
			} else{
				double yforce = this.calcForceExertedByY(b[i]);
				sum = sum + yforce;
			}
		}
		return sum;
	}

	public void update(double dt, double xforce, double yforce){
		double a_x = xforce / this.mass;
		double a_y = yforce / this.mass;
		double new_x_v = this.xxVel + dt * a_x;
		double new_y_v = this.yyVel + dt * a_y;
		this.xxVel = new_x_v;
		this.yyVel = new_y_v;
		double new_x_p = this.xxPos + dt * new_x_v;
		double new_y_p = this.yyPos + dt * new_y_v;
		this.xxPos = new_x_p;
		this.yyPos = new_y_p;
	}

	public static void draw(Body b, double radius){
		double x = b.xxPos / radius * 100;
		double y = b.yyPos / radius * 100;
		String filename = "images/" + b.imgFileName;
		StdDraw.setScale(-100, 100);
        StdDraw.picture(x, y, filename);
        StdDraw.show();
	}
}