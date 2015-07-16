/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.controller;

import edu.usc.model.Buildings;
import edu.usc.services.QueryServices;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ubriela
 */
public class PolygonQueryAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PrintWriter pw = null;
        try {
            String cords = request.getParameter("cords");
            QueryServices services = new QueryServices();
            
            String [] points = cords.split(",");
            
            String tableName = "Buildings";
            QueryServices instance = new QueryServices();
            Vector buildings = instance.queryPolygon(points, tableName);
            JSONArray jsonArray = new JSONArray();
            Iterator it = buildings.iterator();
            while (it.hasNext()) {
                Buildings aBuilding = (Buildings) it.next();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", aBuilding.getId());
                jsonObject.put("name", aBuilding.getName());
                jsonObject.put("shortname", aBuilding.getShortname());
                jsonObject.put("address", aBuilding.getAddress());

                //  Deserialize object
                Point2D location = null;
                {
                    ObjectInputStream in = null;
                    try {
                        byte[] bytes = (byte[]) aBuilding.getLocation();
                        in = new ObjectInputStream(new ByteArrayInputStream(bytes));
                        location = (Point2D) in.readObject();
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(RectangleQueryAction.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException e) {
                    } finally {
                        try {
                            in.close();
                        } catch (IOException ex) {
                            Logger.getLogger(RectangleQueryAction.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                jsonObject.put("lon", location.getX());
                jsonObject.put("lat", location.getY());

                jsonArray.put(jsonObject);
            }
            pw = response.getWriter();
            pw.println(jsonArray.toString());
            return mapping.findForward(SUCCESS);
        } catch (IOException ex) {
            Logger.getLogger(RectangleQueryAction.class.getName()).log(Level.SEVERE, null, ex);
            return mapping.findForward(FAILURE);
        } catch (JSONException ex) {
            Logger.getLogger(RectangleQueryAction.class.getName()).log(Level.SEVERE, null, ex);
            return mapping.findForward(FAILURE);
        } catch (SQLException ex) {
            Logger.getLogger(RectangleQueryAction.class.getName()).log(Level.SEVERE, null, ex);
            return mapping.findForward(FAILURE);
        } finally {
            pw.close();
            return mapping.findForward(SUCCESS);
        }
    }
}
