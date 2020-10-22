<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/style.css"/>" />
  </head>
  <body>
    <header class="header--form-page">

      <jsp:include page="../../META-INF/header-logged.jsp"/>

      <div class="slogan container container--90">
          <h2>
            Dziękujemy za przesłanie formularza. Na maila prześlemy wszelkie
            informacje o odbiorze.
          </h2>
      </div>
    </header>

    <jsp:include page="../../META-INF/footer.jsp"/>

    <script src="<c:url value="../../resources/js/app.js"/>"></script>
  </body>
</html>
