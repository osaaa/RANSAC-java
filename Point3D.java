public class Point3D {
    private Double x, y, z;

    public Point3D(double X_coor, double Y_coor, double Z_coor) {
        this.x = X_coor;
        this.y = Y_coor;
        this.z = Z_coor;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    // public static void main(String[] args) {
    // Point3D point3D = new Point3D(2.0, 4.0, 9.8);
    // System.out.println(point3D.getX());

    // }
}