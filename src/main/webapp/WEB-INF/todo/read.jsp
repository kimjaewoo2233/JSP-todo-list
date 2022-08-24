<%--
  Created by IntelliJ IDEA.
  User: jw666
  Date: 2022-08-22
  Time: 오후 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <div>
      <input type="text" name="tno" value="${dto.tno}" readonly>
  </div>
  <div>
      <input type="text" name="title" value="${dto.title}" readonly>
  </div>
  <div>
      <input type="date" name="dueDate" value="${dto.dueDate}">
  </div>
  <div>
      <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} readonly>
  </div>

    <div>
        <a href="/todo/modify?tno=${dto.tno}">Modify/Remove</a>
        <a href="/todo/list">List</a>
    </div>
</body>
</html>
