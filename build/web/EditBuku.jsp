<%@page import="com.hibernate.Buku"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String idBuku = request.getParameter("id");
    Buku buku = (Buku)request.getAttribute("buku");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=(idBuku == null) ? "New" : "Edit"%> Buku</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-1.11.2.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="message.jsp" />
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-horizontal" method="POST" action="">
                        <% if(idBuku != null) { %>
                        <input type="hidden" name="id" value="<%=idBuku%>">
                        <% } %>
                        <h1><%=(idBuku == null) ? "New" : "Edit"%> Buku</h1>
                        <div class="form-group">
                            <label for="title" class="col-sm-2 col-md-1 control-label">Title</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="text" class="form-control" id="title" name="title" placeholder="Title" value="<%=buku.getTitle()%>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="terjemahan" class="col-sm-2 col-md-1 control-label">Terjemahan</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="text" class="form-control" id="terjemahan" name="terjemahan" placeholder="Terjemahan" value="<%=buku.getTerjemahan()%>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edisi" class="col-sm-2 col-md-1 control-label">Edisi</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="edisi" name="edisi" placeholder="Edisi" value="<%=buku.getEdisi()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="isbn" class="col-sm-2 col-md-1 control-label">ISBN</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="isbn" name="isbn" placeholder="ISBN" value="<%=buku.getIsbn()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="eksemplar" class="col-sm-2 col-md-1 control-label">Eksemplar</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="eksemplar" name="eksemplar" placeholder="Eksemplar" value="<%=buku.getEksemplar()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="halaman" class="col-sm-2 col-md-1 control-label">Halaman</label>
                            <div class="col-sm-10 col-md-11">
                                <input type="number" class="form-control" id="halaman" name="halaman" placeholder="Halaman" value="<%=buku.getHalaman()%>" required min="0">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-md-offset-1 col-sm-10 col-md-11">
                                <button type="submit" class="btn btn-success"><%=(idBuku == null) ? "Buat" : "Simpan"%></button>
                                <a class="btn btn-danger" href="../buku">Cancel</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
