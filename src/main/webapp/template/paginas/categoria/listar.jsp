<%@page import="br.harlock.model.Categoriaitemacervo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.harlock.model.Titulo"%>
<%@page import="java.util.Iterator"%>

<%
    boolean mostrar = false;
    ArrayList<Categoriaitemacervo> categorias = new ArrayList();
    if (request.getAttribute("categorias") != null) {
        Iterator iterator = (Iterator) request.getAttribute("categorias");
        mostrar = true;

        if (iterator.hasNext()) {

            while (iterator.hasNext()) {
                categorias.add((Categoriaitemacervo) iterator.next());
            }
        }
    }


%>

<div class="containerX">
    <a href="index.jsp?pagina=categoriaui" class="float-r"><button class="botaoX verde">+Nova Categoria</button></a>
</div>
<%if(mostrar){%>
<table>
    <tr>
        <th class="texto-centro">Nome</th>
        <th class="texto-centro">Descri��o</th>
        <th class="texto-centro">A��o</th>
    </tr>
    <%for (Categoriaitemacervo cat: categorias) {%>
    <tr>   
        <td><%=cat.getNomeCategoria()%></td>
        <td><%=cat.getDescricao()%></td>
        <td class="float-r">
            <a href="Categoria.do?acao=update&ID=<%=cat.getIdCat()%>"><button class="botaoX azul">Editar</button></a>
            <a href="Categoria.do?acao=remover&ID=<%=cat.getIdCat()%>"><button class="botaoX verde">Excluir</button></a>
        </td>
    </tr>
    <%}%>

</table>
<%}%>