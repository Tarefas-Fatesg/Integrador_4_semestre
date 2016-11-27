<%-- 
    Document   : index
    Created on : 21/11/2016, 20:08:09
    Author     : Harlock
--%>
<%@ page import="br.harlock.model.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>


<%
    Usuario u = (Usuario) request.getAttribute("login");
    String pagina = "home";
        if(request.getParameter("pagina")!=null) {
            pagina = request.getParameter("pagina");
        }
    
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <link rel="stylesheet" href="template/css/estilo.css" media="screen" title="no title">
</head>
<body>
  <div class="container bg">
    <div  class="containerLogo">
      <div class="containerMD">
        <img  class="logo" src="template/imgs/logo.png" alt="" />
      </div>
      <div class="containerMD direita horizontal">
          <a href="index.jsp?pagina=entrar" ><button class="botaoX azul">Entrar</button></a>
          <a href="index.jsp?pagina=cadastro"  ><button class="botaoX vermelho">Cadastrar</button></a>
      </div>
    </div>
  </div>
  <!--- aqui vai corpo -->
  
    <div class="containerX">
        <%if(pagina.equals("cadastro")){%>
        <jsp:include page = "template/paginas/cadastro.jsp" />
        
        <%}else if(pagina.equals("entrar")){%>
            <jsp:include page = "template/paginas/login.html" />
        <%}else{%>
            <jsp:include page = "template/paginas/home.html" />
        <%}%>
    </div>
  </div>
  
  <!-- aqui vai css -->
  <script src="js/sessao.js" charset="utf-8"></script>
  <script src="js/formularios.js" charset="utf-8"></script>
</body>
</html>
