import java.lang.Math;

public class Plane3D {

    private Double a, b, c, d;

    public Plane3D(Point3D p1, Point3D p2, Point3D p3) {
        // Source:
        // https://math.stackexchange.com/questions/2686606/equation-of-a-plane-passing-through-3-points
        // The values for a,b,c,d can be found using the cross product of the vectors
        // that lie on the plane

        a = (p2.getY() - p1.getY()) * (p3.getZ() - p1.getZ()) - (p3.getY() - p1.getY()) * (p2.getZ() - p1.getZ());
        b = (p2.getZ() - p1.getZ()) * (p3.getX() - p1.getX()) - (p3.getZ() - p1.getZ()) * (p2.getX() - p1.getX());
        c = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p3.getX() - p1.getX()) * (p2.getY() - p1.getY());
        d = -(a * p1.getX() + b * p1.getY() + c * p1.getZ());

    }

    public Plane3D(double a, double b, double c, double d) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getDistance(Point3D pt) {
        // Using the formula |Axo + Byo + Czo + D| / √(A2 + B2 + C2)
        // From Source:
        // https://www.cuemath.com/geometry/distance-between-point-and-plane/

        double numerator = (a * pt.getX() + b * pt.getY() + c * pt.getZ() + d);
        double denominator = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));

        return numerator / denominator;

    }

}
