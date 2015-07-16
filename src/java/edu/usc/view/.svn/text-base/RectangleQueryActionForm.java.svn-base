/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usc.view;

import java.awt.geom.Point2D;
import javax.servlet.http.HttpServletRequest;

import oracle.spatial.geometry.JGeometry.Point;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author ubriela
 */
public class RectangleQueryActionForm extends org.apache.struts.action.ActionForm {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        final double lonLeftBelow = Double.valueOf(request.getParameter("lonLeftBelow"));
        final double latLeftBelow = Double.valueOf(request.getParameter("latLeftBelow"));
        final double lonRightAbove = Double.valueOf(request.getParameter("lonRightAbove"));
        final double latRightAbove = Double.valueOf(request.getParameter("latRightAbove"));


        if (lonLeftBelow >= lonRightAbove || latLeftBelow >= latRightAbove) {
            errors.add("point", new ActionMessage("The below cord point must be higher than the higher cord point"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
}