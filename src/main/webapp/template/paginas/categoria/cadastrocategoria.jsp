<%@page import="br.harlock.model.Sistema"%>
<%
    Sistema s = new Sistema();
%>

<div class="containerX">
            <div class="containerMD">
                <form class="form-login" name="salvarForm" action="<%=s.url%>Categoria.do" id="salvarForm" method="post">
                    <input type="hidden" id="acao" name="acao" value="cadastrar" />
                    <div class="float-l">
                        <label>ID</label>
                        <input type="text" name="ID" class="campo-form" value="" size="30"> </div>
                    <div class="float-l">
                        <label>Nome</label>
                        <input type="text" name="nome" class="campo-form" value="" size="30"> </div>
                    <div class="float-l">
                        <label>Descri��o</label>
                        <input type="text" name="desc" class="campo-form" value="" size="30"> </div>
                    <div class="float-l">
                        <a onclick="salvar('cadastrar')">
                            <input type="button" class="botaoX verde" name="cadastrar" value="cadastrar"> </a>
                    </div>
                    <div class="float-l">
                        <a onclick="salvar('atualizar')">
                            <input type="button" class="botaoX azul" name="atualizar" value="atualizar"> </a>
                    </div>
                    <div class="float-l">
                        <a onclick="salvar('remover')">
                            <input type="button" class="botaoX vermelho" name="remover" value="remover"> </a>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function salvar(acao) {
                document.getElementById("acao").value = acao;
                document.getElementById("salvarForm").submit();
            }
        </script>