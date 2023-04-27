import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class PointCloud {

    // private String filename;
    private ArrayList<Point3D> points;
    private Random random;

    public PointCloud(String filename) {
        this();
        try {
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = br.readLine();
            while ((strLine = br.readLine()) != null) {
                // source:
                // https://stackoverflow.com/questions/20841980/read-a-file-and-split-lines-in-java
                // https://stackoverflow.com/questions/18297732/unhandled-exception-filenotfoundexception
                // https://sebhastian.com/slash-s-plus-java/
                String[] coor = strLine.split("\\s+");
                // System.out.println(coor[0] + " " + coor[1] + " " + coor[2]); // For debugging

                double X_coor = Double.parseDouble(coor[0]);
                double Y_coor = Double.parseDouble(coor[1]);
                double Z_coor = Double.parseDouble(coor[2]);
                Point3D newPoints = new Point3D(X_coor, Y_coor, Z_coor);
                points.add(newPoints);

                // ---------More Debugging----------
                // pointCloud.addPoint(strLine);
                // System.out.println(strLine);
                // System.out.println(" ");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public PointCloud() {
        this.points = new ArrayList<Point3D>();
        this.random = new Random();
    }

    public void addPoint(Point3D pt) {

        points.add(pt);

    }

    public void remove(Point3D pt) {
        points.remove(pt);
    }

    public Point3D getPoint() {

        int arrayIndex = random.nextInt(points.size());
        return points.get(arrayIndex);

    }

    public int size() {
        return points.size();
    }

    public void save(String filename) {

        // Source:
        // https://mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/

        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Point3D point : points) {
                bufferedWriter.write(point.getX() + " " + point.getY() + " " + point.getZ());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Iterator<Point3D> iterator() {

        return points.iterator();
    }

    // <------------------Debugging--------------------->
    /*
     * public static void main(String[] args) {
     * 
     * PointCloud pc = new PointCloud("input/PointCloud1.xyz");
     * Point3D p3 = new Point3D(2.332, 4.432, 0.4233);
     * 
     * System.out.println(pc.size());
     * pc.addPoint(p3);
     * System.out.println(pc.size());
     * pc.save("input/PointCloudTest1.xyz");
     * }
     */

    /*
     * try {
     * //PointCloud pointCloud = new PointCloud("input/PointCloud1.xyz");
     * FileInputStream fstream = new FileInputStream("./input/PointCloud1.xyz");
     * BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
     * String strLine = br.readLine();
     * // String line = null;
     * while ((strLine = br.readLine()) != null) {
     * Point3D point3d = new Point3D(0, 0, 0)
     * //pointCloud.addPoint(strLine);
     * System.out.println(strLine);
     * System.out.println("  ");
     * }
     * br.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     */

}
