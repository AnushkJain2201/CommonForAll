<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>This is show page</h1>

    <c:forEach var="student" items="${students}">
      Student name: <c:out value="${student.name}" /> <br>
      Student email: <c:out value="${student.email}" /><br>
      <c:out value = "${student.course.name}" /> <br>
      <c:out value = "${student.course.description}" /> <br>
      <c:out value = "${student.course.capacity}" /> <br>
      <c:out value = "${student.course.schedule}" /> <br>
    </c:forEach>
    
</body>
</html>