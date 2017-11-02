/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.assignment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;
import javax.validation.ConstraintViolation;

/**
 *
 * @author Oracle
 */

public class MainServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        String jspPath = "/WEB-INF/jsp/";
        String view;
    
        switch (action) {
            
            //GENERAL WEBPAGES 
            
            //MAIN WELCOME SCREEN 
            //WILL ACT AS A PORTAL TO EVERY OTHER WEBPAGE
            case "/hello.do": {
                    view = "hello";
                    break;
                }
   
            //IDK TBH HERE FOR TESTING PURPOSES WILL REPURPOSE LATER 
            case "/index.do": {
                view = "index";
                break;
            }
                       
            //MAIN PAGE FOR RENTALS 
            //THIS IS THE MAIN WEBPAGE TO SHOW ALL VEHICLES AVAILABLE FOR RENTAL
            case "/rentals.do":{
                view = "rentals";
                break;
            }

            //MEMBERSHIP PAGES 

            //MEMBERSHIP LOGIN SCREEN 
            case "/membershipLogin.do":{
                view = "membershipLogin";
                break;
            }
            
            //THIS WILL SEND TO A PAGE THAT HANDLES MEMBER REGISTRATIONS 
            case "/membership.do":{
                view = "membership";
                break;
            }
            
            //ADMIN PAGES  
            
            //ADMIN LOGIN SCREEN 
            //WILL BE USED STRICTLY FOR ADMINS TO LOGIN 
            //ACTUAL ADMIN PORTAL WILL BE SOMETHING DIFFERENT 
            case "/adminLogin.do":{
                view = "adminLogin";
                break;
            }
            
            //ADMIN PORTAL 
            //NEEDS MUCH MORE WORK 
            //WILL SEND TO THE MAIN PAGE HANDLING ALL ADMIN RELATED THINGS
            case "/adminPortal.do":{
                view = "adminPortal";
                break;
            }

            default: {
                response.sendError(404);
                return;
            }
        }
    
        
    
        request.getRequestDispatcher(jspPath + view + ".jsp").forward(request, response);

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
