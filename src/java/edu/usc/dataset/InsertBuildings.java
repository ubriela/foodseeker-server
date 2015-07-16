/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.dataset;

import edu.usc.utils.OracleConnect;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.csv4j.CSVFieldMapProcessor;
import net.sf.csv4j.CSVFileProcessor;
import net.sf.csv4j.CSVLineProcessor;
import net.sf.csv4j.ParseException;
import net.sf.csv4j.ProcessingException;

/**
 *
 * @author ubriela
 */
public class InsertBuildings {

    private OracleConnect con;
    final CSVFileProcessor fp = new CSVFileProcessor();

    public void insertBuildings(String userInfoStr, String message) {
        try {
            con = new OracleConnect();


            fp.processFile("/Users/ubriela/Dropbox/USC/CSCI587SERVER/src/java/edu/usc/dataset/uscbuildings.csv", new CSVLineProcessor() {

                public void processHeaderLine(final int linenumber, final List<String> fieldNames) {
                    // do something with the list of header columns
                    for (final String fieldName : fieldNames) {
                        System.out.println(fieldName);
                    }
                }

                public void processDataLine(final int linenumber, final List<String> fieldValues) {
                    int userid = 0;
                    String name = "";
                    String shortname = "";
                    String address = "";
                    double lat = 0.0;
                    double lon = 0.0;

                    // use the data
                    userid = Integer.valueOf(fieldValues.get(0));
                    name = fieldValues.get(1);
                    shortname = fieldValues.get(2);
                    address = fieldValues.get(3);
                    lat = Double.valueOf(fieldValues.get(5));
                    lon = Double.valueOf(fieldValues.get(6));


                    String insertQuery = "INSERT INTO Buildings (id, name, shortname, address, location) "
                            + "VALUES( " + userid + ", "
                            + "'" + name + "', "
                            + "'" + shortname + "', "
                            + "'" + address + "', "
                            + "SDO_GEOMETRY(2001, 8307, SDO_POINT_TYPE(" + lat + ", " + lon + ", NULL), NULL, NULL))";
                    System.out.println(insertQuery);
                    try {
                        
                        con.executeQuery(insertQuery);
                    } catch (SQLException ex) {
                        Logger.getLogger(InsertBuildings.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public boolean continueProcessing() {
                    return true;
                }
            });


//            fp.processFile("/Users/ubriela/Dropbox/USC/CSCI587SERVER/src/java/edu/usc/dataset/uscbuildings.csv", new CSVFieldMapProcessor() {
//
//                public void processDataLine(final int linenumber, final Map<String, String> fields) {
//                    // print out the field names and values
//                    for (final Entry field : fields.entrySet()) {
//                        System.out.println(field.getKey() + " " + field.getValue());
//                    }
//                }
//
//                public boolean continueProcessing() {
//                    return true;
//                }
//            });


        } catch (IOException ex) {
            Logger.getLogger(InsertBuildings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProcessingException ex) {
            Logger.getLogger(InsertBuildings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(InsertBuildings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
