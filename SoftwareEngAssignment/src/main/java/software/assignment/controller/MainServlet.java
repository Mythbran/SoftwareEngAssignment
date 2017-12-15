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
import software.assignment.business.Member;
import software.assignment.data.memberDbase;

/**
 *
 * @author Oracle
 */

public class MainServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        String jspPath = null;
        String view = null;
    
        switch (action) {
            
            //GENERAL WEBPAGES 
            
            //MAIN WELCOME SCREEN 
            //WILL ACT AS A PORTAL TO EVERY OTHER WEBPAGE
            case "/hello.do":{
                    jspPath = "/WEB-INF/jsp/general/";
                    view = MainController.hello(request);        
                    break;
            }
            /*
            //IDK TBH HERE FOR TESTING PURPOSES WILL REPURPOSE LATER 
            case "/index.do": {
                view = "index";
                jspPath = "/WEB-INF/jsp/general/";
                break;
            }
                   */    
            //MAIN PAGE FOR RENTALS 
            //THIS IS THE MAIN WEBPAGE TO SHOW ALL VEHICLES AVAILABLE FOR RENTAL
            case "/rentals.do":{
                jspPath = "/WEB-INF/jsp/general/";
                view = MainController.rentals(request);
                break;
            }
            
            case "/rent.do":{
                jspPath = "/WEB-INF/jsp/general/";
                view = MainController.rent(request);
                break;
            }
            
            case "/login.do":{
                view = MainController.login(request);
                jspPath = "WEB-INF/jsp/general/";
                break;
            }
            
            case "/loginCheck.do":{
                view = MainController.loginCheck(request);
                break;
            }

            //MEMBERSHIP PAGES 
            
            //THIS WILL SEND TO A PAGE THAT HANDLES MEMBER REGISTRATIONS
            //TAKES ALL REGISTRATIONS AND SENDS IT TO MEMBERCONFIRM CLASS
            case "/membership.do":{
                view = MainController.membership(request);
                jspPath = "/WEB-INF/jsp/member/";      
                break;
            }
            
            //THIS WILL HANDLE CONFIRMING IF THE ENTERED DATA IS CORRECT 
            //TAKES INPUT FROM MEMBERSHIP CLASS
            //SENDS THE CONFIRMED DATA TO THE SUBMIT METHOD 
            //THAT'S WHERE IT GETS SENT INTO THE DATABASE 
            //FOR WHEN ADDING NOT EDITING 
            case "/memberConfirm.do":{
                jspPath = "/WEB-INF/jsp/member/";
                view = MainController.memberConfirm(request);
                break;
            }
            
            //OH SUBMIT 
            //THIS IS WHERE THE MAGIC HAPPENS 
            //AND DATABASES FILL UP
            //ADDS ALL INPUT INTO INTO THE MEMBER DATABASE 
            case "/memberSubmit.do":{
                
                view = MainController.memberSubmit(request);
                break;
            }
            
            //MEMBER PORTAL
            //THIS WILL HOLD THE MEMBER EDIT PAGE AND SHIT
            //IDK WHAT ELSE WOULD BE IN HERE 
            //MEH WHO CARES 
            case "/memberPortal.do":{
                jspPath = "/WEB-INF/jsp/member/";
                view = MainController.memberPortal(request);
                break;
            }
            
            //BASICALLY SAME AS ADMIN EDIT MEMBER 
            //WILL NOT INCLUDE ADMIN FLAG
            //HEH AINTN GIVING THOSE PRIVILEGES
            case "/memberEdit.do":{
                jspPath = "/WEB-INF/jsp/member/";
                view = MainController.memberEdit(request);
                break;
            }
            
            //WHEN MEMBERS EDIT MEMBERS 
            case "/memberConfirmEdit.do":{
                view = MainController.memberConfirmEdit(request);
                break;
            }            


            //ADMIN PAGES  
                                          
            //ADMIN PORTAL 
            //NEEDS MUCH MORE WORK 
            //WILL SEND TO THE MAIN PAGE HANDLING ALL ADMIN RELATED THINGS
            case "/adminPortal.do":{
                view = MainController.adminPortal(request);
                jspPath = "/WEB-INF/jsp/admin/";
                break;
            }
            
            //ADD CAR 
            //NEEDS DATABASE WORK 
            //THIS WILL ALLOW ADMINS TO ADD CARS TO THE DATABASE 
            case "/addCar.do":{
                view = MainController.addCar(request);
                jspPath = "/WEB-INF/jsp/admin/";
                break;
            }
            
            //CONFIRM CAR   
            //ADDCAR.DO WILL PASS TO THIS CLASS             
            case "/confirmCar.do":{
                view = MainController.confirmCar(request);
                jspPath = "/WEB-INF/jsp/admin/";
                break;
            }
            
            case"/carConfirm.do":{
                view = MainController.carConfirm(request);
                break;
            }

            //EDIT CAR
            //NEEDS DATABASE WORK 
            //THIS WILL ALLOW ADMINS TO EDIT CARS INCLUDING DELETING FROM DATABASE
            case "/editCar.do":{
                view = MainController.editCar(request);
                jspPath = "WEB-INF/jsp/admin/";
                break; 
            }
            
            case "/editOrder.do":{
                view = "editOrder";
                jspPath= "/WEB-INF/jsp/member/";
                //Order order = new Order();
                //orderDbase.selectOne(Integer.parseInt(request.getParameter("oid")));
                
                break;
            }    
                
            //VIEW ALL CARS 
            case"/viewAllCars.do":{
                view = MainController.viewAllCars(request); 
                jspPath = "WEB-INF/jsp/admin/";
                break;
            }
            //VIEW ALL MEMBERS
            //THIS WILL ALLOW ADMINS TO VIEW MEMBERS REGISTERED 
            //ADMINS WILL BE ALLOWED TO SET MEMBERS AS ADMINS
            case "/viewAll.do":{
                view = MainController.viewAll(request);
                jspPath = "/WEB-INF/jsp/admin/";
                break;
            }
            
            //DELETE MEMBER
            //BASED ON UID 
            case "/delete.do":{
                view = MainController.delete(request);
                jspPath = "WEB-INF/jsp/admin/";
                break;

            }
            
            case "/deleteCar.do":{
                view = MainController.deleteCar(request);
                break;
            }
            
            //WHEN ADMINS EDIT MEMBERS 
            case "/confirmEdit.do":{
                view = MainController.confirmEdit(request);
                break;
            }
            
            //WHEN ADMINS EDIT CARS
            case "/confirmCarEdit.do":{
                view = MainController.confirmCarEdit(request);
                break;
            }
            
            //EDIT MEMBER
            //THIS WILL ALLOW SIGNED IN MEMBERS TO EDIT THEIR INFORMATION 
            //WILL ONLY BE AVAILABLE IF SIGNED IN 
            //WILL ONLY ALLOW THE SIGNED IN MEMBER TO EDIT THEIR OWN INFO
            
            //EDIT : WILL ONLY BE AVAILABLE TO ADMIN
            //TIME CONSTRAINTS :( 
            case "/editMember.do":{
                view = MainController.editMember(request);
                jspPath= "/WEB-INF/jsp/admin/";
                break;
            }
            
            case "/placeOrder.do":{
                view = MainController.placeOrder(request);
                break;
            }
            
            
            

            default: {
                response.sendError(404);
                return;
            }
        }
         
        if(view.startsWith("redirect:")){
            response.sendRedirect(response.encodeRedirectURL(view.substring(9)));
        }else {
            request.getRequestDispatcher(jspPath + view + ".jsp").forward(request, response);
        }
        
    
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
