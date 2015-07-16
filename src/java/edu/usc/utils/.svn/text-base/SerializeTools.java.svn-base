/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.utils;

import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ubriela
 */
public class SerializeTools {

    static byte[] point2DToBuf(Point2D gJavaPoints) {

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
	return buf;
    }

    static Point2D bufToPoint2D(byte[] buf) {
	//  Deserialize object
	Point2D location = null;
	{
	    ObjectInputStream in = null;
	    try {
		in = new ObjectInputStream(new ByteArrayInputStream(buf));
		location = (Point2D) in.readObject();
		in.close();
	    } catch (IOException ex) {
		Logger.getLogger(SerializeTools.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (ClassNotFoundException e) {
	    } finally {
		try {
		    in.close();
		} catch (IOException ex) {
		    Logger.getLogger(SerializeTools.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	}
	return location;
    }
}
