package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "addServlet",urlPatterns = {"/addServlet/*"})
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String where = request.getRequestURI();
        String[] List = where.split("/");
        //getURI函数是把服务器localhost:8080后面所有的内容扒拉下来，
        //即/zuoye3_war_exploded/addServlet/a=1&b=2，而且注意split，它会使where[0]是空字符串
        if(List.length != 4){
            out.println("输入的级数不足");
        }
        else{
            //正则匹配所有整型数字
            String str= "^a=(-?[1-9]\\d*|0)&b=(-?[1-9]\\d*|0)$";
            Pattern pattern = Pattern.compile(str);
            Matcher m = pattern.matcher(List[3]);
            boolean flag = false;
            while(m.find()){
                flag = true;
                Integer a = Integer.parseInt(m.group(1));
                Integer b = Integer.parseInt(m.group(2));
                request.getSession().setAttribute("a",a);
                request.getSession().setAttribute("b",b);
            }
            if(!flag){
                out.println("输入的参数不够或错误");
            }
            else{
                request.getRequestDispatcher("/doneServlet").forward(request,response);
            }
        }
    }
}
