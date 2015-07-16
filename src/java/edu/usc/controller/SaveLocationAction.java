/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.controller;

import edu.usc.services.StatisticServices;
import java.awt.geom.Point2D;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author ubriela
 */
public class SaveLocationAction extends org.apache.struts.action.Action {

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
	    StatisticServices services = new StatisticServices();
	    int uid = Integer.valueOf(request.getParameter("uid"));
	    final double lat = Double.valueOf(request.getParameter("lat"));
	    final double lon = Double.valueOf(request.getParameter("lon"));
	    final boolean isNewSession = Boolean.valueOf(request.getParameter("newsession")).booleanValue();
	    
	    Point2D location = new Point2D() {

		@Override
		public double getX() {
		    return lon;
		}

		@Override
		public double getY() {
		    return lat;
		}

		@Override
		public void setLocation(double d, double d1) {
		}
	    };

	    StatisticServices instance = new StatisticServices();
	    instance.saveLocation(uid, location, isNewSession);

	    pw = response.getWriter();
	    pw.println(isNewSession);

	    return mapping.findForward(SUCCESS);
	} finally {
	    return mapping.findForward(SUCCESS);
	}
    }
}
