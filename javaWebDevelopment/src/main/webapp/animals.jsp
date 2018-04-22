<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="animals" class="container">
   <table class="table">
      <thead>
         <tr>
            <th scope="col">#</th>
            <th scope="col">animal</th>
            <th scope="col">name</th>
         </tr>
      </thead>
      <tbody>
         <c:forEach items="${animals}" var="item" varStatus="i">
            <c:set var="i" value="${i.index}" scope="request"></c:set>
            <c:set var="item" value="${item}" scope="request"></c:set>
            <jsp:include page="animal.jsp" />
         </c:forEach>
      </tbody>
   </table>
</div>