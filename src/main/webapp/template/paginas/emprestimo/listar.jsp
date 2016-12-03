<%@page import="java.util.Iterator"%>
<%@page import="br.harlock.model.Emprestimo"%>
<%@page import="java.util.ArrayList"%>
<%

    boolean mostrar = false;
     Iterator iterator = null;
    if (request.getAttribute("emprestimos") != null) {
        mostrar = true;
        iterator = (Iterator) request.getAttribute("emprestimos");
    }

%>
<div class="containerX">
    <div class="float-r">
        <a href="index.jsp?pagina=novoemprestimo"><button class="botaoX verde texto-branco">+ novo emprestimo</button></a>
    </div>
</div>
<% if (mostrar) { %>
<h2>N�o h� emprestimos cadastrados.</h2>
<%} else {%>
<table>
    <tr>
        <th class="texto-centro">Destinado </th>
        <th class="texto-centro">Situa��o</th>
        <th class="texto-centro">Data de devolu��o</th>
        <th class="texto-centro">A��o</th>
    </tr>
    <% while (iterator.hasNext()) {
        Emprestimo emp = (Emprestimo) iterator.next();
    %>
    <tr>
        <td><%=emp.getUsuarioDoSistema().getNome()%></td>
        <td><%=emp.getSituacao()%></td>
        <td><%=emp.getDataPrevDevolucao()%></td>
        <td class="float-r">
            <a href="Emprestimo.do?acao=editar&ID=<%=emp.getIdEmp()%>"><button class="botaoX azul">Editar</button></a>
            <a href="Emprestimo.do?acao=detalhes&ID=<%=emp.getIdEmp()%>"><button class="botaoX verde">Detalhes</button></a>
        </td>
    </tr>
    <%}%>
</table>
<%}%>