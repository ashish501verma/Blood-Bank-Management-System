package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class BloodSearch extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        String url = "jdbc:mysql://localhost/";
        String dbName = "bbms";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        Statement st=null;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("connected!.....");
            String id = request.getParameter("date");
            ArrayList al = null;
            ArrayList pid_list = new ArrayList();
            String query = "select * from donor";
            if(id!=null && !id.equals("")){
                query = "select * from donor where regdate='" + id + "' ";
            }
            System.out.println("query " + query);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                al = new ArrayList();

                al.add(rs.getString(1));
                al.add(rs.getString(2));
                al.add(rs.getString(3));
                al.add(rs.getString(4));
                al.add(rs.getString(5));
                al.add(rs.getString(6));
                al.add(rs.getString(7));
                al.add(rs.getString(8));
                al.add(rs.getString(9));
                System.out.println("al :: " + al);
                pid_list.add(al);
            }

            request.setAttribute("piList", pid_list);
            RequestDispatcher view = request.getRequestDispatcher("bloodsearch.jsp");
            view.forward(request, response);
            conn.close();
            System.out.println("Disconnected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "getting records from database through servlet controller";
    }// </editor-fold>
}
