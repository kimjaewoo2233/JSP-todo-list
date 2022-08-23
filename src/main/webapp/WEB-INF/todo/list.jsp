<%--
  Created by IntelliJ IDEA.
  User: jw666
  Date: 2022-08-22
  Time: 오후 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List 페이지</h1>
    <ul>
        <c:forEach var="dto" items="${list}">
           <li>
                   ${dto}
           </li>
        </c:forEach>
    </ul>

</body>
</html>
