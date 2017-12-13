/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software.assignment.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

public class MemberController {
    
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
        if(request.isUserInRole("member")){
            return "mRentals";
        }
        else if(request.isUserInRole("administrator")){
            return "aRentals";
        }
        else{
            return "rentals";            
        }

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
            return "errorLogin";            
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
    
    
    

    

}
