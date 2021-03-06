/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.services;

import edu.usc.model.Buildings;
import edu.usc.model.BuildingsDistance;
import edu.usc.utils.OracleConnect;
import java.awt.geom.Point2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import oracle.jdbc.OracleResultSet;
import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

/**
 *
 * @author ubriela
 */
public class QueryServices {

    public Vector<Buildings> queryWithinDistance(Point2D center, String tableName, float distance, String unit) throws SQLException {
        Vector<Buildings> buildingList = new Vector<Buildings>();

        double lon = center.getX();
        double lat = center.getY();

        String sqlQuery = "SELECT * FROM " + tableName + " b "
                + " WHERE SDO_WITHIN_DISTANCE(b.location,"
                + " SDO_GEOMETRY ( 2001, 8307, SDO_POINT_TYPE ("
                + lat + ", " + lon + ", " + "null),null,null),"
                + "'DISTANCE=" + distance + " UNIT=" + unit
                + "')='TRUE'";

        System.out.println(sqlQuery);
        OracleConnect connect = new OracleConnect();
        ResultSet ors = connect.executeQuery(sqlQuery);

        while (ors.next()) {
            JGeometry geom;

            BigDecimal id;
            String name;
            String shortname;
            String address;

            //  Extract JDBC object from record into structure
            STRUCT dbObject = (STRUCT) ors.getObject("location");

            //  Import structure into Geometry object
            geom = JGeometry.load(dbObject);

            //  non-spatial data
            id = ors.getBigDecimal("id");
            name = ors.getString("name");
            shortname = ors.getString("shortname");
            address = ors.getString("address");

            Point2D gJavaPoints = geom.getJavaPoint();

            //  Serialize Point object
            byte[] buf = null;
            try {
                // Serialize to a file
//                ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
//                out.writeObject(gJavaPoints);
//                out.close();

                // Serialize to a byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(gJavaPoints);
                out.close();

                // Get the bytes of the serialized object
                buf = bos.toByteArray();
            } catch (IOException e) {
            }

            Buildings aBuilding = new Buildings(id, name, shortname, address, buf);

            buildingList.add(aBuilding);
        }

        return buildingList;
    }

    public Vector queryKNN(Point2D center, String tableName, int k, String unit) throws SQLException {
        Vector<BuildingsDistance> buildingList = new Vector<BuildingsDistance>();

        double lon = center.getX();
        double lat = center.getY();

        String sqlQuery = "SELECT b.id, b.name, b.shortname, b.address, b.location, SDO_NN_DISTANCE(1) dist FROM "
                + tableName + " b "
                + " WHERE SDO_NN(b.location,"
                + " SDO_GEOMETRY(2001, 8307, SDO_POINT_TYPE ("
                + lat + ", " + lon + ", " + "null),null,null),"
                + "'SDO_BATCH_SIZE=100 UNIT=" + unit + "',1"
                + ")='TRUE'"
                + "AND ROWNUM<=" + k;

        System.out.println(sqlQuery);
        OracleConnect connect = new OracleConnect();
        OracleResultSet ors = connect.getOracleResultSet(sqlQuery);

        while (ors.next()) {
            JGeometry geom;

            BigDecimal id;
            String name;
            String shortname;
            String address;
            double dist;

            //  Extract JDBC object from record into structure
            STRUCT dbObject = (STRUCT) ors.getObject("location");

            //  Import structure into Geometry object
            geom = JGeometry.load(dbObject);

            //  non-spatial data
            id = ors.getBigDecimal("id");
            name = ors.getString("name");
            shortname = ors.getString("shortname");
            address = ors.getString("address");
            dist = ors.getDouble("dist");

            Point2D gJavaPoints = geom.getJavaPoint();

            //  Serialize Point object
            byte[] buf = null;
            try {
                // Serialize to a byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(gJavaPoints);
                out.close();

                // Get the bytes of the serialized object
                buf = bos.toByteArray();
            } catch (IOException e) {
            }

            Buildings aBuilding = new Buildings(id, name, shortname, address, buf);
            BuildingsDistance aBuildingDistance = new BuildingsDistance(aBuilding, dist);
            buildingList.add(aBuildingDistance);
        }

        return buildingList;
    }

    public Vector queryRectangle(Point2D leftBelowPoint, Point2D rightAbovePoint, String tableName) throws SQLException {
        Vector<Buildings> buildingList = new Vector<Buildings>();

        double lon1 = leftBelowPoint.getX();
        double lat1 = leftBelowPoint.getY();
        double lon2 = rightAbovePoint.getX();
        double lat2 = rightAbovePoint.getY();

        String sqlQuery = "SELECT * FROM " + tableName + " b "
                + " WHERE SDO_FILTER(b.location,"
                + " SDO_GEOMETRY ( 2003, 8307, NULL,"                   
                + " SDO_ELEM_INFO_ARRAY (1, 1003, 3),"
                + " SDO_ORDINATE_ARRAY(" + lat1 + "," + lon1 + "," + lat2 + "," + lon2 + ")"
                + "))='TRUE'";

        System.out.println(sqlQuery);
        OracleConnect connect = new OracleConnect();
        OracleResultSet ors = connect.getOracleResultSet(sqlQuery);

        while (ors.next()) {
            JGeometry geom;

            BigDecimal id;
            String name;
            String shortname;
            String address;

            //  Extract JDBC object from record into structure
            STRUCT dbObject = (STRUCT) ors.getObject("location");

            //  Import structure into Geometry object
            geom = JGeometry.load(dbObject);

            //  non-spatial data
            id = ors.getBigDecimal("id");
            name = ors.getString("name");
            shortname = ors.getString("shortname");
            address = ors.getString("address");

            Point2D gJavaPoints = geom.getJavaPoint();

            //  Serialize Point object
            byte[] buf = null;
            try {

                // Serialize to a byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(gJavaPoints);
                out.close();

                // Get the bytes of the serialized object
                buf = bos.toByteArray();
            } catch (IOException e) {
            }

            Buildings aBuilding = new Buildings(id, name, shortname, address, buf);

            buildingList.add(aBuilding);
        }

        return buildingList;
    }

    public Vector queryPolygon(String[] points, String tableName) throws SQLException {
        Vector<Buildings> buildingList = new Vector<Buildings>();
        
        String strPoints = "";
        for (int i = 0; i < points.length; i++) {
            strPoints += points[i];
            if (i != points.length - 1) {
                strPoints += ",";
            }

        }
        
        String sqlQuery = "SELECT * FROM " + tableName + " b "
                + " WHERE SDO_FILTER(b.location,"
                + " SDO_GEOMETRY ( 2003, 8307, NULL,"
                + " SDO_ELEM_INFO_ARRAY (1, 1003, 1),"
                + " SDO_ORDINATE_ARRAY(" + strPoints + ")"
                + "))='TRUE'";

        System.out.println(sqlQuery);
        OracleConnect connect = new OracleConnect();
        OracleResultSet ors = connect.getOracleResultSet(sqlQuery);

        while (ors.next()) {
            JGeometry geom;

            BigDecimal id;
            String name;
            String shortname;
            String address;

            //  Extract JDBC object from record into structure
            STRUCT dbObject = (STRUCT) ors.getObject("location");

            //  Import structure into Geometry object
            geom = JGeometry.load(dbObject);

            //  non-spatial data
            id = ors.getBigDecimal("id");
            name = ors.getString("name");
            shortname = ors.getString("shortname");
            address = ors.getString("address");

            Point2D gJavaPoints = geom.getJavaPoint();

            //  Serialize Point object
            byte[] buf = null;
            try {

                // Serialize to a byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(gJavaPoints);
                out.close();

                // Get the bytes of the serialized object
                buf = bos.toByteArray();
            } catch (IOException e) {
            }

            Buildings aBuilding = new Buildings(id, name, shortname, address, buf);

            buildingList.add(aBuilding);
        }

        return buildingList;
    }
}
