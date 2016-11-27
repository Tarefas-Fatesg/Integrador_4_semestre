<%@page import="br.harlock.model.Titulo"%>
<%@page import="br.harlock.model.Autor"%>
<%@page import="br.harlock.model.ProdutoraConteudo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
TESTE
<%
    ArrayList<Autor> autores = new ArrayList();
    ArrayList<ProdutoraConteudo> produtoras = new ArrayList();
    if (request.getAttribute("autores") != null && request.getAttribute("prdoutoras") != null) {
        Iterator iteratorAutores = (Iterator) request.getAttribute("autores");
        Iterator iteratorProdutoraDeConteudo = (Iterator) request.getAttribute("prdoutoras");
        if (iteratorAutores.hasNext()) {
            autores.add((Autor) iteratorAutores.next());
        }
        if (iteratorProdutoraDeConteudo.hasNext()) {
            produtoras.add((ProdutoraConteudo) iteratorProdutoraDeConteudo.next());
        }
    }
    Titulo titulo = new Titulo("");
    if (request.getAttribute("titulo") != null) {
        titulo = (Titulo) request.getAttribute("titulo");
    }
    String[] tipoDeTitulo = {"Livro", "V�deo", "Artigo", "Revista"};
%>
<div class="containerX">
    <div class="col-12">
        <label>Nome da obra</label>
        <input type="text" name="obra" >
        <input type="hidden" id="idTitulo" value="<%=titulo.getIdTitu()%>"
    </div>
    <div class="col-10 float-l">
        <label>tipo de obra</label>
        <select onclick="tipoDeTitulo()" id="tipoobra" name="tipoObra">
            <option>Selecione</option>
            <%
                for (String tipo : tipoDeTitulo) {
                    if (tipo == titulo.getTipoDeObra()) {
            %>
            <option value="<%=tipo%>" selected=""><%=tipo%></option>
            <% } else {%>
            <option value=""><%=tipo%></option>
            <%
                    }
                }
            %>
        </select>
    </div>
    <div class="col-5 float-l">
        <label>Descri��o</label>
        <textarea id="descricao" name="descricao" value="<%=titulo.getDescricao()%>"></textarea>
    </div>
    <div class="col-5 float-l">
        <label>Imagem</label>
        <input type="file" name="pic" accept="image/*">
        <img src="" width="140" height="170">
    </div>
    <div class="col-10">
        <label >ISBN</label>
        <input type="text" id="ISBN" name="ISBN"  value="<%=titulo.getIsbn()%>">
    </div>
    <br>

    <div class="col-10">

        <label>ISSN</label>
        <input type="text" name="ISSN" id="ISSN"  value="<%=titulo.getIssn()%>">
    </div>
    <div class="col-5 float-l">
        <label>Edi��o</label>
        <select>
            <option>
                Selecione um editor
            </option>
            <%for (Autor a : autores) {%>

            <option value="<%=a.getIdAutor()%>"><%=a.getNome()%></option>
            <%}%>
        </select>
    </div>
    <div class="col-5 float-l">
        <label>Edi��o</label>
        <input type="text" id="edicao" name="edicao" value="<%=titulo.getEdicao()%>"
    </div>
</div>

<div class="col-5 float-l">
    <label>Dura��o</label>
    <input type="text" name="duracao" id="duracao"  value="<%=titulo.getDuracao()%>">
</div>
<div class="col-5 float-l">
    <label>N�mero de p�ginas </label>
    <input type="text" name="numeropaginas" id="numeropaginas"  value="<%=titulo.getQuantidadePaginas()%>">
</div>
<div class="col-5 float-l">
    <label>Data de publica��o</label>
    <input type="date" name="datapublicacao"  value="<%=titulo.getDataDePublicacao()%>">
</div>
<div class="col-5 float-l">
    <label>Estado de Publicacao</label>
    <select id="estado" name="estado"  value="<%=titulo.getEstadoPublicacao()%>"></select>
</div>
<div class="col-5 float-l">
    <label>Cidade de publica��o</label>
    <select id="cidade" name="cidade"  value="<%=titulo.getCidadePublicacao()%>"></select>
</div>


<div class="col-5 float-l">
    <label>Idioma Original</label>
    <select id="idiomaObra" name="idiomaObra"  value="<%=titulo.getIdioma()%>"></select>
</div>

<div class="col-5 float-l">
    <label>Idioma da tradu��o</label>
    <select id="traducao" name="traducao"  value="<%=titulo.getTraducao()%>"></select>
</div>
<div class="col-5 float-l">
    <label>Produtora</label>
    <select id="produtora" name="produtora">
        <% for (ProdutoraConteudo p : produtoras) {%>
        <% if (p.getIdPdc() == titulo.getFkItemPdc()) {%>
        <option value="<%=p.getIdPdc()%>" selected=""><%=p.getNomeProdutora()%></option>
        <%}%>
        <option value="<%=p.getIdPdc()%>"><%=p.getNomeProdutora()%></option>
        <% }%>
    </select>
</div>
<div class="col-5 float-l">
    <label>Autores e co-autores</label>
    <table id="AutoresTb" name="AutoresTb">
        <tbody>
            <tr>
                <th class="texto-centro">Tipo de autor</th>
                <th class="texto-centro">Nome autor </th>
                <th><a onclick="addRowAutores()"><button class="botaoS verde">+</button></a></th>
            </tr>
        </tbody>

    </table>
</div>
<div class="col-5 float-l"></div>
<div class="col-5 float-l"> 
    <a href=""><button class="botaoX verde float-r">Salvar</button></a>
    <div class="float-r"></div>
</div>
</div>




<script>

    function addRowAutores(parameters) {
        var html = "<tr><td><select id='tipoDeAutor' ><option value='Autor'>Autor</option><option value='Co-autor'>Co-autor</option></select></td><td><select id='autorObra'><%for (Autor a : autores) {%><option value='<%=a.getIdAutor()%>'><%=a.getNome()%></option><%}%></select></td><td><a onclick='deletarRowAutores(this);'><button class='botaos vermelho'>-</button></a> </td></tr>";

        $('#AutoresTb > tbody:last-child').append(html);
    }
 
    function deletarRowAutores(remove){
        $(remove).parent().parent().remove();
    }
    function tipoDeTitulo() {
        switch ($("#tipoobra option:selected").text()) {
            case "Livro":
                todoscamposTituloUInormal();
                document.getElementById("ISSN").value = "";
                document.getElementById("ISSN").disabled = true;
                document.getElementById("duracao").value = "";
                document.getElementById("duracao").disabled = true;
                break;
            case "Revista":
                todoscamposTituloUInormal();
                document.getElementById("ISBN").value = "";
                document.getElementById("ISBN").disabled = true;

                document.getElementById("duracao").value = "";
                document.getElementById("duracao").disabled = true;
                break;
            case "Artigo":
                todoscamposTituloUInormal();
                todoscamposTituloUInormal();
                document.getElementById("ISBN").value = "";
                document.getElementById("ISBN").disabled = true;
                break;
            case "V�deo":
                todoscamposTituloUInormal();
                document.getElementById("numeropaginas").value = "";
                document.getElementById("numeropaginas").disabled = true;
                document.getElementById("ISSN").value = "";
                document.getElementById("ISSN").disabled = true;
                document.getElementById("ISBN").value = "";
                document.getElementById("ISBN").disabled = true;

        }
        function todoscamposTituloUInormal() {
            document.getElementById("ISBN").disabled = false;
            document.getElementById("ISSN").disabled = false;
            document.getElementById("duracao").disabled = false;
            document.getElementById("numeropaginas").disabled = false;
//            document.getElementById("ISBN").disabled = false;

        }
        ;


    }


</script>





