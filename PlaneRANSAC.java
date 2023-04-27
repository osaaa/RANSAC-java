import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

public class PlaneRANSAC {

    private PointCloud pointCloud;
    private Double eps;

    public PlaneRANSAC(PointCloud pc) {
        this.pointCloud = pc;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getEps() {
        return this.eps;
    }

    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane) {
        int k = (int) (Math.log(1 - confidence) / Math.log(1 - Math.pow((percentageOfPointsOnPlane / 100), 3)));
        return k;
    }

    public void run(int numberOfIterations, String filename) {

        // Initially, no dominant plane has been found, and the best support is set to 0
        int bestSupport = 0;
        Plane3D bestPlane = null;
        ArrayList<Point3D> domPoint3ds = new ArrayList<Point3D>();
        Iterator<Point3D> iterator = pointCloud.iterator();

        for (int i = 0; i < numberOfIterations; i++) {

            // Randomly draw 3 points from the point cloud.
            Point3D pointOne = pointCloud.getPoint();
            Point3D pointTwo = pointCloud.getPoint();
            Point3D pointThree = pointCloud.getPoint();

            // Compute the plane equation from these 3 points.
            Plane3D plane3D = new Plane3D(pointOne, pointTwo, pointThree);

            domPoint3ds.clear();
            iterator = pointCloud.iterator();

            while (iterator.hasNext()) {
                Point3D pt = iterator.next();
                if (plane3D.getDistance(pt) < eps) {
                    domPoint3ds.add(pt);
                }
            }

            if (domPoint3ds.size() > bestSupport) {
                bestSupport = domPoint3ds.size();
                bestPlane = plane3D;
            }
        }

        // remove the inlier points from the point cloud
        iterator = domPoint3ds.iterator();
        while (iterator.hasNext()) {
            Point3D pt = iterator.next();
            pointCloud.remove(pt);
        }

        // save the dominant plane to the xyz file
        PointCloud planePointCloud = new PointCloud();
        iterator = domPoint3ds.iterator();
        while (iterator.hasNext()) {
            planePointCloud.addPoint(iterator.next());
        }
        planePointCloud.save(filename);

    }

    public static void main(String[] args) {

        PointCloud pointCloud = new PointCloud("src/input/PointCloud2.xyz");
        PlaneRANSAC planeRANSAC = new PlaneRANSAC(pointCloud);
        planeRANSAC.setEps(10);
        int num = planeRANSAC.getNumberOfIterations(.99, 10);
        planeRANSAC.run(num, "TestPointCloud2Output.xyz");
    }
}

/*
 * 
 * <----------------debugging------------------------>
 * //Step 1 - done
 * int bestSupport = 0;
 * 
 * //Step 2 - done
 * Point3D pointOne = pointCloud.getPoint();
 * Point3D pointTwo = pointCloud.getPoint();
 * Point3D pointThree = pointCloud.getPoint();
 * 
 * //Step 3 - done
 * Plane3D plane3d = new Plane3D(pointOne, pointTwo, pointThree);
 * 
 * //Step 4
 * int support = 0;
 * for (int i = 0; i < pointCloud.size(); i++){
 * if (plane3d.getDistance(pointThree) < eps){ //THIS IS WRONG, REPLACE
 * pointThree
 * support+=1;
 * }
 * }
 * Plane3D dominantPlane;
 * //Step 5
 * if (support > bestSupport){
 * plane3d = dominantPlane;
 * }
 * 
 * //Step 6
 * 
 * //Step 7
 * 
 */