public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readDouble();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        in.readDouble();
        in.readDouble();
        Body[] planets = new Body[5];
        double[] input = new double[5];
        for(int i = 0 ; i < 5; i++){
            for (int j = 0; j < 5; j++){
                input[j] = in.readDouble();
            }
            Body planet = new Body(input[0], input[1],
            input[2],input[3],input[4],in.readString());
            planets[i] = planet;
        }

        return planets;
    }
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int numOfPlanets = in.readInt();
        in.readDouble();
        Planet[] ps = new Planet[numOfPlanets];
        for (int count = 0; count < numOfPlanets; count++){
            ps[count] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return ps;

    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double current_T = 0;
        double dt = Double.parseDouble(args[1]);
        double[] xforces = new double[5];
        double[] yforces = new double[5];
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Body[] planets = NBody.readBodies(filename);
        String background = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        // main loop
        while (current_T < T) {
            for (int i = 0; i < 5; i++){
                xforces[i] = planets[i].calcNetForceExertedByX(planets);
                yforces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < 5; i++){
                planets[i].update(dt, xforces[i], yforces[i]);
            }
            StdDraw.setScale(-100, 100);
            StdDraw.picture(0, 0, background);
            StdDraw.show();
            for(int i = 0; i < planets.length; i++){
                Body.draw(planets[i], radius);
            }
            StdDraw.pause(10);
            current_T = current_T + dt;
        }
        // final result
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos,planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
