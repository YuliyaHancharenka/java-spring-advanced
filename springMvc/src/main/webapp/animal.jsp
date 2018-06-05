<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>

<tr>
   <th scope="row">${i}</th>
   <td><img style ="width:50px; height:50px;" src="${item.img}" /></td>
   <td>${item.name}</td>
   <td><ex:hello value="${item}" /></td>
</tr>