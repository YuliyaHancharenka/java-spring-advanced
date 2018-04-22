<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
   <head>
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
      <!-- Optional JavaScript -->
      <title>Hello, world!</title>
      <!-- jQuery first, then Popper.js, then Bootstrap JS -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
   </head>
   <body>

      <div class="container-fluid row">
         <main class="col-12 col-md-9 col-xl-8 py-md-3 pl-md-5 bd-content">
            <h1>Lets play!</h1>

            <button type="button" id="get" class="btn btn-primary">Get animals</button>
            <button type="button" id="post" class="btn btn-success">Add animal</button>
            <button type="button" id="put" class="btn btn-warning">Put animal</button>
            <button type="button" id="delete" class="btn btn-danger">Delete animal</button>
            <div id="result">
               no animals yet
            </div>
         </main>
      </div>
   </body>
   <script>
      $("#get").click(function() {
          $.ajax({
              url: "/webapp/animal",
              method: "GET",
              success: function(result) {
                  $("#result").html(result);
              }
          });
      });

      $("#post").click(function() {
          $.ajax({
              url: "/webapp/animal",
              method: "POST",
              success: function(result) {
                  $("#animals").html(result);
              }
          });
      });

      $("#put").click(function() {
          $.ajax({
              url: "/webapp/animal",
              method: "put",
              success: function(result) {
                  $("#animals").html(result);
              }
          });
      });

      $("#delete").click(function() {
          $.ajax({
              url: "/webapp/animal",
              method: "delete",
              success: function(result) {
                  $("#animals").html(result);
              }
          });
      });
   </script>
</html>