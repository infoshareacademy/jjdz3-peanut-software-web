<%--
  Created by IntelliJ IDEA.
  User: mati
  Date: 09.06.17
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>


<!DOCTYPE html>
<html>
<head>
    <jsp:include page="partials/meta.jsp" />
</head>
<body>

<div class="container">
    <h1>Statystyki:</h1>

<form method="post">

    <table border="4" align="center" style="width:100%"  >
        <tr>
            <td align="center" >SPECJALIZACJA</td> <td align="center" >Liczba pacjentów</td>
        </tr>

        <%
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/peanutDB";
                String username="peanut_user";
                String password="admin";
                String query="select preferedSpecialization, count(name) from isaDB GROUP BY  preferedSpecialization";
                Connection conn=DriverManager.getConnection(url, username, password);
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                while(rs.next())
                {

        %>

        <tr>
            <td><%=rs.getString("SPECJALIZACJA") %></td>
        </tr>
        <tr>
            <td><%=rs.getInt("Liczba pacjentów") %></td>
        </tr>

        <%

            }
        %>

    <%
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    %>
        </table>
</form>

<div class="form-group">
    <a href="/peanut/index3.jsp" id="CreateCourse" ><button type="submit">BACK</button></a>

</div>
    <jsp:include page="partials/footer.jsp" />
</div>
</body>

</html>
