/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.test;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleResultSet;
import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

/**
 *
 * @author ubriela
 */
public class Geometry {

    static void printGeometries(Connection dbConnection, String tableName, String geoColumn, String predicate, int printStyle) throws SQLException {
        long totalPoint = 0;
        long totalSize = 0;
        JGeometry geom;

        //  Construct sql query
        String sqlQuery = "SELECT " + geoColumn + " FROM " + tableName + " "
                + predicate;
        System.out.println(sqlQuery);

        //  Execute query
        Statement stmt = null;
        OracleResultSet ors = null;

        stmt = dbConnection.createStatement();
        ors = (OracleResultSet) stmt.executeQuery(sqlQuery);

        //  Process results
        int rowNumber = 0;
        while (ors.next()) {
            ++rowNumber;

            //  Extract JDBC objecdt from record into structure
            STRUCT dbObject = (STRUCT) ors.getObject(1);
            
            //  Import structure into Geometry object
            geom = JGeometry.load(dbObject);
            
            //  Extract details from JGeometry object
            int gType = geom.getType();
            int gSRID = geom.getSRID();
            int gDimensions = geom.getDimensions();
            int gNumPoints = geom.getNumPoints();
            long gSize = geom.getSize();
            boolean isPoint = geom.isPoint();
            boolean isCircle = geom.isCircle();
            boolean hasCircularArcs = geom.hasCircularArcs();
            boolean isGeodeticMBR = geom.isGeodeticMBR();
            boolean isLRSGeometry = geom.isLRSGeometry();
            boolean isMultiPoint = geom.isMultiPoint();
            boolean isRectangle = geom.isRectangle();
            
            //  Point
            double gPoint[] = geom.getPoint();
            
            //  element info array
            int gEleInfo[] = geom.getElemInfo();
            int gNumElements = (gEleInfo == null ? 0 : gEleInfo.length/3);
            
            //  ordinates array
            double gOrdinates[] = geom.getOrdinatesArray();
            
            //  other information
            double[] gFirstPoint = geom.getFirstPoint();
            double[] gLastPoint = geom.getLastPoint();
            Point2D gLabelPoint = geom.getLabelPoint();
            Point2D gJavaPoint = geom.getJavaPoint();
            Point2D[] gJavaPoints = (isMultiPoint ? geom.getJavaPoints() : null);
            double [] gMBR = geom.getMBR();
            Shape gShape = geom.createShape();
            
            totalSize += gSize;
            totalPoint += gNumPoints;
            
            if (printStyle > 0)
                System.out.println("Geometry #" + rowNumber + ":");
            
            //  Print out geometry in SDO_GEOMETRY format
            if ((printStyle & 1) == 1)
                System.out.println(printGeom(geom));
            
            //  Print out formatted geometry
            if ((printStyle & 2) == 2) {
                System.out.println(" Type:  " + gType);
                System.out.println(" SRID:  " + gSRID);
                System.out.println(" Dimensions:    " + gDimensions);
                System.out.println(" NumPoints: " + gNumPoints);
                System.out.println(" Size:  " + gSize);
                System.out.println(" isPoint:   " + isPoint);
                System.out.println(" isCircle:  " + isCircle);
                System.out.println(" hasCircularArcs:   " + hasCircularArcs);
                System.out.println(" isGeodeticMBR: " + isGeodeticMBR);
                System.out.println(" isLRSGeometry: " + isLRSGeometry);
                System.out.println(" isMultiPoint:  " + isMultiPoint);
                System.out.println(" isRectangle:   " + isRectangle);
                System.out.println(" MBR:   (" + gMBR[0] + " " + gMBR[1] + ") (" + gMBR[2] + " " + gMBR[3] + ") ");
                System.out.println(" First Point:   " + printPoint(gFirstPoint));
                System.out.println(" Last Point:    " + printPoint(gLastPoint));
                System.out.println(" Label Point:   " + printPoint(gLabelPoint));
                System.out.println(" Point: " + printPoint(gPoint));
                System.out.println(" Java Point:    " + printPoint(gJavaPoint));
                System.out.println(" Java Point List:   " + (gJavaPoints == null ? "NULL" : "[" + gJavaPoints.length + "]"));
                if (gJavaPoints != null)
                    for (int i = 0; i < gJavaPoints.length; i++)
                        System.out.println("    [" + (i+1) + "] (" + gJavaPoints[i].getY() + ", " + gJavaPoints[i].getY() + ")");
                
                System.out.println(" Elements:  [" + gNumElements + " elements" );
                if (gEleInfo != null)
                    for (int i = 0; i < gNumElements; i++)
                        System.out.println("    [" + (i+1) + "] (" + gEleInfo[i*3] + ", " + gEleInfo[i*3+1] + ", " + gEleInfo[i*3+2] + ")");
                
                System.out.println(" Points List:   [" + gNumPoints + " points]");
                if (gOrdinates != null) 
                    for (int i = 0; i < gNumPoints; i++) {
                        System.out.print("  [" + (i+1) + "] (");
                        for (int j = 0; j < gDimensions; j++) {
                            System.out.print(gOrdinates[i*gDimensions + j]);
                            if (j < gDimensions-1)
                                System.out.print(", ");
                        }
                        System.out.println(")");
                    }
            }
            
        }
        
        stmt.close();
        System.out.println("");
        System.out.println("Done - " + rowNumber + " geometries extracted");
        System.out.println(" " + totalPoint + " points");
        System.out.println(" " + totalSize + " bytes");
    }
    
    static String printPoint(double[] point) {
        String formattedPoint;
        if (point == null)
            formattedPoint = "NULL";
        else {
            formattedPoint = "[" + point.length + "] (";
            for (int i = 0; i < point.length; i++) {
                formattedPoint += point[i];
                if (i < point.length - 1)
                    formattedPoint += ", ";
            }
            formattedPoint +=")";
        }
        return formattedPoint;
    }
    
    static String printPoint(Point2D point) {
        String formattedPoint;
        if (point == null)
            formattedPoint = "NULL";
        else
            formattedPoint = "[2] (" + point.getX() + ", " + point.getY() + ")";
        return formattedPoint;
    }
    
    static String printGeom(JGeometry geom) {
        String fg;
        
        //  Extract details form JGeometry object
        int gType = geom.getType();
        int gSRID = geom.getSRID();
        int gDimensions = geom.getDimensions();
        boolean isPoint = geom.isPoint();
        
        //  Point
        double gPoint[] = geom.getPoint();
        
        //  Element info array
        int gElemInfo[] = geom.getElemInfo();
        
        //  Ordinates array
        double gOrdinates[] = geom.getOrdinatesArray();
        
        //  Format JGeometry in SDO_GEOMETRY format
        int sdo_gtype = gDimensions * 1000 + gType;
        int sdo_srid = gSRID;
        
        fg = "(SDO_GEOMETRY(" + sdo_gtype + ", ";
        if (sdo_srid == 0)
            fg = fg + "NULL, ";
        else
            fg = fg + sdo_srid + ", ";
        
        if (gPoint == null)
            fg = fg + "NULL), ";
        else {
            fg = fg + "SDO_POINT_TYPE(" + gPoint[0] + ", " + gPoint[1] + ", ";
            if (gPoint.length < 3)
                fg = fg + "NULL), ";
            else if (java.lang.Double.isNaN(gPoint[2]))
                fg = fg + "NULL), ";
            else
                fg = fg + gPoint[2] + "), ";
        }
        
        if (!isPoint & gElemInfo != null) {
            fg = fg + "SDO_ELEM_INFO_ARRAY( ";
            for (int i = 0; i < gElemInfo.length; i++)
                fg = fg + gElemInfo[i] + ", ";
            fg = fg + gElemInfo[gElemInfo.length - 1] + "), ";
        } else
            fg = fg + "NULL, ";
        
        if (!isPoint & gOrdinates != null) {
            fg = fg + "SDO_ORDINATE_ARRAY( ";
            for (int i = 0; i < gOrdinates.length; i++)
                fg = fg + gOrdinates[i] + ", ";
            fg = fg + gOrdinates[gOrdinates.length - 1] + ")";
        } else
            fg = fg + "NULL";
        
        fg = fg + ")";
        
        return (fg);
            
    }
}
