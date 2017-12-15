/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.assignment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import software.assignment.business.Member;
import software.assignment.data.memberDbase;

/**
 *
 * @author Owner
 */

public class MainController {
    
    //GENERAL PAGES 
    
    //CODE FOR THE MAIN WELCOME SCREEN 
    //CAN SET UP COOKIES HERE. CODE IS BELOW 
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
        
        //CODE FOR WHEN USERS ARE ENTERED INTO THE MIX 
        //IF MEMBER, WILL GIVE ACCESS TO DIFFERENT COUPONS
        //IF ADMINSTRATOR, WILL GIVE ACCESS TO EDIT THE CARS 
        //IF NOT JUST ACCESS TO VIEW 
        //if(request.isUserInRole("member")){
          //  return "mRentals";
        //}
        //else if(request.isUserInRole("administrator")){
          //  return "aRentals";
        //}
        //else{
            return "rentals";            
        //}

    }
    
    //LOGIN CODE 
    public static String login(HttpServletRequest request){
        
        //CHECKS FOR USER ROLE 
        //NEED TO PROGRAM THE DATABASE STUFF FOR IT 
        //WILL CHECK IF ADMIN AND IF ADMIN, GO DIRECTLY TO ADMINPORTAL
        //IF MEMBER WILL GO TO MEMBERPORTAL 
        //IF NOTHING COMES UP WILL RETURN AN ERROR
        if(request.isUserInRole("member")){
            return "memPortal";
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
    //CAN ADD CONTRAINTS IN HERE LATER DURING TESTING PHASE 
    public static String memberConfirm(HttpServletRequest request){
        Member member = new Member();
        member.setfName(request.getParameter("fName"));
        member.setlName(request.getParameter("lName"));
        member.setpNumber(request.getParameter("pNumber"));
        member.setcCard(request.getParameter("cCard"));
        member.setuName(request.getParameter("uName"));
        member.setPassword(request.getParameter("password"));
        
        //CAN ADD IN VALIDATION THINGS HERE
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
    //THERE IS CODE INSIDE THERE FOR VARIFICATION AND COOKIE PURPOSES 
    //CODE IS COMMENTED OUT UNTIL SUITABLE CLASSES ARE MADE 
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
    //HAVE TO REDIRECT TO LOGIN IF NOBODY IS CURRENTLY LOGGED IN 
    //UHH.... YEA NEED TO PROGRAM STUFF FOR ACCESS CONTROL
    public static String memberPortal(HttpServletRequest request){
        
        return "memberPortal";
    }
    
    //GIVE MEMBERS THE ABILITY TO CHANGE THEIR INFO 
    //WILL BE SLIGHTLY DIFFERENT THAN EDITMEMBER
    //WILL NOT HAVE THE ABILITY TO CHAGNE ADMIN FLAG
    public static String memberEdit(HttpServletRequest request){
            int uid = 1; //Integer.parseInt(request.getParameter("uid"));
            Member member = memberDbase.selectOne(uid);
            request.setAttribute("member", member);
            return "memberEdit";       
    }
    
    //ADMIN PAGES
    
    //EDIT MEMBER CODE
    //THERE IS PERMISSIONS SET ON THIS FUNCTION 
    //MUST PROGRAM THE PERMISSIONS PROPERLY
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
    //NEED TO CODE PERMISSIONS
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
        return "confirmCar";
    }
    
    //EDIT CAR FUNCTION 
    public static String editCar(HttpServletRequest request){
        /*if(request.isUserInRole("administrator")){
            return "editCar";
        }else{
            return "error";
        }*/
        return "editCar";
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
        memberDbase.adminUpdate(member);    
        
        return"redirect:viewAll.do";
    }

    static String addCar(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
