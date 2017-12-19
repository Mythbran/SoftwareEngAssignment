package software.assignment.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
            
            //PLACING ORDERS
            case "/placeOrder.do":{
                view = MainController.placeOrder(request);
                break;
            }
            //MAIN WELCOME SCREEN 
            //WILL ACT AS A PORTAL TO EVERY OTHER WEBPAGE
            case "/hello.do":{
                    jspPath = "/WEB-INF/jsp/general/";
                    view = MainController.hello(request);        
                    break;
            }

            //MAIN PAGE FOR RENTALS 
            //THIS IS THE MAIN WEBPAGE TO SHOW ALL VEHICLES AVAILABLE FOR RENTAL
            case "/rentals.do":{
                jspPath = "/WEB-INF/jsp/general/";
                view = MainController.rentals(request);
                break;
            }
            
            //BRINGS YOU TO THE CONFRIM RENTAL PAGE
            case "/rent.do":{
                jspPath = "/WEB-INF/jsp/general/";
                view = MainController.rent(request);
                break;
            }
            
            
            //SIMPLE LOGIN CHECKER 
            case "/login.do":{
                view = MainController.login(request);
                jspPath = "WEB-INF/jsp/general/";
                break;
            }
            
            //LOGIN CHECK 
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

            //ADDS ALL INPUT INTO INTO THE MEMBER DATABASE 
            case "/memberSubmit.do":{

                view = MainController.memberSubmit(request);
                break;
            }
            
            //MEMBER PORTAL
            //THIS WILL HOLD THE MEMBER EDIT PAGE
            case "/memberPortal.do":{
                jspPath = "/WEB-INF/jsp/member/";
                view = MainController.memberPortal(request);
                break;
            }
            
            //SAME AS ADMIN EDIT MEMBER 
            //WILL NOT INCLUDE ADMIN FLAG
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
            
            //CAN EDIT AN ORDER
            case "/editOrder.do":{
                view = MainController.editOrder(request);
                jspPath= "/WEB-INF/jsp/member/";                
                break;
            }  


            //ADMIN PAGES  
                                          
            //ADMIN PORTAL 
            //WILL SEND TO THE MAIN PAGE HANDLING ALL ADMIN RELATED THINGS
            case "/adminPortal.do":{
                view = MainController.adminPortal(request);
                jspPath = "/WEB-INF/jsp/admin/";
                break;
            }
            
            //ADD CAR 
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
            
            //CONFIRMS IF THE INFO IS CORRECT FOR THE CAR YOU WANT TO ADD 
            case"/carConfirm.do":{
                view = MainController.carConfirm(request);
                break;
            }

            //EDIT CAR
            //THIS WILL ALLOW ADMINS TO EDIT CARS INCLUDING DELETING FROM DATABASE
            case "/editCar.do":{
                view = MainController.editCar(request);
                jspPath = "WEB-INF/jsp/admin/";
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
            
            //DELETE CAR
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
            //THIS WILL ALLOW ADMINS TO EDIT MEMBERS INFORMATION 
            //WILL ONLY BE AVAILABLE TO ADMIN
            //WILL ONLY ALLOW THE ADMIN TO EDIT THEIR OWN INFO
            case "/editMember.do":{
                view = MainController.editMember(request);
                jspPath= "/WEB-INF/jsp/admin/";
                break;
            }
            
            //ALLOWS ADMINS TO SEE ALL ORDERS
            case "/viewAllOrders.do":{
                 view= MainController.viewAllOrders(request);
                 jspPath = "/WEB-INF/jsp/admin/";
                 break;
            }

            //DELETES ORDER
            case "/deleteOrder.do":{
                view = MainController.deleteOrder(request);
                System.out.println(request.getParameter("uid"));
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
         String username = request.getParameter("uName");
        String password = request.getParameter("password");
        Cookie uc = new Cookie("userCookie", username);
        Cookie pc = new Cookie("passCookie", password);
        
        //adds cookies to the response
        response.addCookie(uc);
        response.addCookie(pc);
        
        //send cookies back to browser
        PrintWriter pw = response.getWriter();
        pw.println("Login cookies have been added");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for a Car Rental Service";
    }// </editor-fold>

}
