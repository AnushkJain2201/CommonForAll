<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Student Course Registration</title>
  </head>
  <body>
    <h1>Student Course Registration</h1>
    <form action="courseRegistration.do" method="post">
      <label for="student_name">Enter your name</label>
      <input type="text" name="name" id="student_name" />
      <label for="student_email">Enter your email</label>
      <input type="email" name="email" id="student_email" />
      <label for="course">Course Name:</label>
      <select name="course" id="course">

        <c:forEach var="course" items="${courses}">
              <option value="${course.courseId}">${course.name}</option>
        </c:forEach>
        <br /><br />
      <!-- <label for="seats">Seats Requested:</label>
        <input type="number" id="seats" name="seats" min="1" max="10"><br><br> -->
      <input type="submit" value="Register" />
    </form>
    
    <form action="show.do" method="post">
      <label for="student_email">Enter your email to view courses</label>
      <input type="email" name="email" id="student_email" />
      <!-- <label for="course">Course Name:?</label> -->
      <input type="submit" value="View Courses" />
    </form>
  </body>
</html>
