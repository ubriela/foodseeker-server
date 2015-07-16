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
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author ubriela
 */
public class StatisticQueryAction extends org.apache.struts.action.Action {

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
	    
	    StatisticServices instance = new StatisticServices();
	    Statistic statistic = instance.getStatistic(uid);
	    
	    JSONArray jsonArray = new JSONArray();
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("numberOfSessions", statistic.getNumberOfSessions());
	    jsonObject.put("times", statistic.getTimes());
	    jsonObject.put("speed", statistic.getSpeed());
    
	    jsonArray.put(jsonObject);
	    pw = response.getWriter();
	    pw.println(jsonArray.toString());

	    return mapping.findForward(SUCCESS);
	} finally {
	    return mapping.findForward(SUCCESS);
	}
    }
}
