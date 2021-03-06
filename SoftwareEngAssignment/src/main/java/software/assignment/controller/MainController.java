package software.assignment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import software.assignment.business.*;
import software.assignment.data.carDbase;
import software.assignment.data.memberDbase;
import software.assignment.data.orderDbase;

/**
 *
 * @author Owner
 */

public class MainController {
    
    //GENERAL PAGES 
    
    //CODE FOR THE MAIN WELCOME SCREEN 
    public static String hello(HttpServletRequest request){
        //Code for cookies below 
        /*String userName = CookieUtil.getCookieValue(request.getCookies(), "userName");
        if(userName.isEmpty()){
            return input(request);
        }*/
        return "hello";
    }
    
    //MAIN RENTAL PAGE
    //WILL ACCESS THE DATABASE FROM HERE
    public static String rentals(HttpServletRequest request){ 
        //if(request.isUserInRole("member")){
          //  return "mRentals";
        //}
        //else if(request.isUserInRole("administrator")){
          //  return "aRentals";
        //}
        //else{
                        
        //}
        
            List<Car> cars = carDbase.selectAll();
            request.setAttribute("cars", cars);
            return "rentals";     
    }

    public static String rent(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carDbase.selectOne(id);
        if(car != null){
            request.setAttribute("car", car);
            return "rent";
        }else{
            return  "redirect:rentals.do";
        }
    }

    
    //LOGIN CODE 
    public static String login(HttpServletRequest request){
        
        //CHECKS FOR USER ROLE 
        //WILL CHECK IF ADMIN AND IF ADMIN, GO DIRECTLY TO ADMINPORTAL
        //IF MEMBER WILL GO TO MEMBERPORTAL 
        //IF NOTHING COMES UP WILL RETURN AN ERROR
        if(request.isUserInRole("member")){
            return "memberPortal";
        }
        else if(request.isUserInRole("administrator")){
            return "adminPortal";
        }
        else{
            return "login";            
        }
        
        
    }
    
    //CHECK FOR LOGIN 
    public static String loginCheck(HttpServletRequest request){
        
        String uName = request.getParameter("uName");
        String password = request.getParameter("password");
        boolean check = memberDbase.passRetrieve(uName, password);
        if(check == true){
            return "redirect:hello.do";
        }else{
            return "redirect:login.do";
        }
    }
    
    //MEMBERSHIP PAGES 
    
    //MEMBERSHIP CODE
    //THIS WILL ALLOW YOU TO REGISTER AN ACCOUNT 
    public static String membership(HttpServletRequest request){
        
        return "membership";
    }
    
    //MEMBERSHIP CONFIRM CLASS 
    //WILL TAKE THE INPUT FROM THE MEMBERSHIP CLASS AND REPOST IT TO CONFIRM ALL DATA IS CORRECT 
    public static String memberConfirm(HttpServletRequest request){
        Member member = new Member();
        member.setfName(request.getParameter("fName"));
        member.setlName(request.getParameter("lName"));
        member.setpNumber(request.getParameter("pNumber"));
        member.setcCard(request.getParameter("cCard"));
        member.setuName(request.getParameter("uName"));
        member.setPassword(request.getParameter("password"));
        
        /*
        Set<ConstraintViolation<Member>> errors = ValidationUtil.getValidator().validate(member);
        
        if(errors.isEmpty(){
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
        
            return "memberConfirm";
        }else{
            request.setAttribute("errors, errors); 
            return input(request);
        }
        
        */
        
        HttpSession session = request.getSession();
        session.setAttribute("member", member);
        return "memberConfirm";
        
    }
    
    //ADDS THE MEMBER TO THE DATABASE 
    public static String memberSubmit(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        //if(session == null || session.getAttribute("member") == null){
            //return "error";
        //}else{
            Member member = (Member)session.getAttribute("member");
            memberDbase.insert(member);
            /*
            String userName = String.format("%s, member.getuName().trim();
            Cookie cookie = new Cookie("userName", userName);
            cookie.setMaxAge(30 * 24 * 60 * 60) //one month 
            response.addCookie(cookie);
            
            */
            return "redirect:hello.do";
        //}
        
    }
    
    //GO TO MEMBER PORTAL
    public static String memberPortal(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        
        if(cookies != null)
            return "memberPortal";
        
        else
            return "redirect:login";
    }
    
    //GIVE MEMBERS THE ABILITY TO CHANGE THEIR INFO 
    //WILL BE SLIGHTLY DIFFERENT THAN EDITMEMBER
    //WILL NOT HAVE THE ABILITY TO CHANGE ADMIN FLAG
    public static String memberEdit(HttpServletRequest request){
            int uid = 1; //Integer.parseInt(request.getParameter("uid"));
            Member member = memberDbase.selectOne(uid);
            request.setAttribute("member", member);
            return "memberEdit";       
    }
    
    //ADMIN PAGES
    
    //EDIT MEMBER CODE
    //THERE IS ADMIN PERMISSIONS SET ON THIS FUNCTION 
    public static String editMember(HttpServletRequest request){
       /* if(request.isUserInRole("member")){
            //Will have to pull the uid from the signed in user for this
            return "editMember";
        }
        else if(request.isUserInRole("administrator")){
            //will have to pull the account the administrator clicks for this
            return"editMember";
        }
        else{
            return "error";
        }*/
     
           int uid = Integer.parseInt(request.getParameter("uid"));
           Member member = memberDbase.selectOne(uid);
           if(member != null){
               request.setAttribute("member", member);
               return "editMember";
               
           }
           else{
                   return "redirect:viewAll.do";
           }
      
       //memberDbase.selectOne("uid");
       //return "editMember";
        
    }
    
    //MEMBER CONFIRM EDIT
    //BASICALLY THE EXACTY SAME AS THE ADMIN VERSION 
    //MINUS THE ABILITY TO CHANGE ADMIN FLAG 
    public static String memberConfirmEdit(HttpServletRequest request){
        Member member = new Member();
        
        try{
            member.setUid(Integer.parseInt(request.getParameter("uid")));
            member.setfName(request.getParameter("fName"));
            member.setlName(request.getParameter("lName"));
            member.setpNumber(request.getParameter("pNumber"));
            member.setcCard(request.getParameter("cCard"));        
            memberDbase.adminUpdate(member);
            
        }catch(NumberFormatException e){
            
        }
        memberDbase.memberUpdate(member);    
        
        return"redirect:memberPortal.do";   
        
    }
    
    //ADMIN PORTAL CODE 
    public static String adminPortal(HttpServletRequest request){
        /*if(request.isUserInRole("administrator")){
            return "adminPortal";
        }else{
            return "error";
        }*/
        return "adminPortal";
    }
    
    
    //CONFIRM CAR FUNCTION 
    //INPUT IS PASSED FROM ADD CAR ROLE 
    //USE THIS TO VERIFY THAT ALL CAR DATA IN INPUTTED CORRECTLY 
    public static String confirmCar(HttpServletRequest request){
        /*if(request.isUserInRole("administrator")){
            return"confirmCar";
        }else{
            return "error";
        }*/
        Car car = new Car();
        car.setModel(request.getParameter("model"));
        car.setMake(request.getParameter("make"));
        car.setPrice(Float.parseFloat(request.getParameter("price")));
        car.setAvailability(request.getParameter("availability"));
        car.setLocation(request.getParameter("location"));

        /*
        Set<ConstraintViolation<Member>> errors = ValidationUtil.getValidator().validate(member);
        
        if(errors.isEmpty(){
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
        
            return "memberConfirm";
        }else{
            request.setAttribute("errors, errors); 
            return input(request);
        }
        
        */
        
        HttpSession session = request.getSession();
        session.setAttribute("car", car);       
        return "confirmCar";
    }
    
    //CAR CONFIRM
    //SIMILAR TO MEMBER CONFIRM
    //TAKES INPUT FROM CONFIRMCAR AND ADDS IT INTO DBASE 
    public static String carConfirm (HttpServletRequest request){
        HttpSession session = request.getSession(false);
        //if(session == null || session.getAttribute("member") == null){
            //return "error";
        //}else{
            Car car = (Car)session.getAttribute("car");
            carDbase.insert(car);
            /*
            String userName = String.format("%s, member.getuName().trim();
            Cookie cookie = new Cookie("userName", userName);
            cookie.setMaxAge(30 * 24 * 60 * 60) //one month 
            response.addCookie(cookie);
            
            */
            return "redirect:adminPortal.do";
        
    }
    
    //VIEW ALL CARS 
    //WILL BE ABLE TO DELETE AND EDIT CARS FROM THERE 
    public static String viewAllCars(HttpServletRequest request){
            List<Car> cars = carDbase.selectAll();
            request.setAttribute("cars", cars);
            return "viewAllCars";      
        
    }
    
    //EDIT CAR FUNCTION 
    public static String editCar(HttpServletRequest request){
        /*if(request.isUserInRole("administrator")){
            return "editCar";
        }else{
            return "error";
        }*/
        int uid = Integer.parseInt(request.getParameter("id"));
        Car car = carDbase.selectOne(uid);
        if(car != null){
            request.setAttribute("car", car);
            return "editCar";
               
        }
        else{
            return "redirect:viewAllCar.do";
        }
             
        
    }


    public static String viewAll(HttpServletRequest request){
        //if(request.isUserInRole("administrator")){
            List<Member> members = memberDbase.selectAll();
            request.setAttribute("members", members);
            return "viewAll";
        //}else{
          //  return "error";
        //}
    }
    
    public static String delete(HttpServletRequest request){
        //if(request.isUserInRole("administrator")){
            memberDbase.deleteOne(Integer.parseInt(request.getParameter("uid")));
            return "redirect:viewAll.do";
        //}else{
          //  return "error";
        //}
    }
    
    public static String deleteCar(HttpServletRequest request){
        carDbase.deleteOne(Integer.parseInt(request.getParameter("id")));
        return "redirect:viewAllCars.do";
    }

    public static String confirmEdit(HttpServletRequest request) {
        Member member = new Member();
        
        try{
            member.setUid(Integer.parseInt(request.getParameter("uid")));
            member.setfName(request.getParameter("fName"));
            member.setlName(request.getParameter("lName"));
            member.setpNumber(request.getParameter("pNumber"));
            member.setcCard(request.getParameter("cCard"));
            String adminT = request.getParameter("admin");
            member.setAdmin((adminT == null) ? "no" : "yes");           
            memberDbase.adminUpdate(member);
            return "redirect:viewAll.do";
            
        }catch(NumberFormatException e){
            
        } 
        
        return"redirect:viewAll.do";
    }
    
    
    
    public static String confirmCarEdit (HttpServletRequest request){
        Car car = new Car();
        try{
            
            car.setId(Integer.parseInt(request.getParameter("id")));
            car.setMake(request.getParameter("make"));
            car.setModel(request.getParameter("model"));
            car.setPrice(Float.parseFloat(request.getParameter("price")));
            car.setAvailability(request.getParameter("availability"));
            car.setLocation(request.getParameter("location"));
            carDbase.update(car);
        }catch(NumberFormatException e){
        
        }
        return "redirect:viewAllCars.do";
    }
    
    static String addCar(HttpServletRequest request) {
        return "addCar";
    }
    


     
     public static String placeOrder(HttpServletRequest request){       
        Order order = new Order();
        order.setId(Integer.parseInt(request.getParameter("id")));
        order.setUid(Integer.parseInt(request.getParameter("uid")));         
        orderDbase.insert(order);
         
         
        return "redirect:hello.do";
     }
     
     public static String editOrder(HttpServletRequest request){
         
         return "editOrder";
     }
    
    public static String viewAllOrders(HttpServletRequest request){
        List<Order> orders = orderDbase.selectAll();
        request.setAttribute("orders", orders);    
        
        return "viewAllOrders";
    }
    
    public static String deleteOrder(HttpServletRequest request){
        orderDbase.deleteOne(Integer.parseInt(request.getParameter("oid")));
        System.out.println("oid");
        return "redirect:viewAllOrders.do";
    }

}
