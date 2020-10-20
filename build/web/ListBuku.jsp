<%@page import="com.hibernate.Buku"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buku</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function eliminar(id, number) {
                if (confirm('Yakin ingin menghapus buku "' + number + '"?')) {
                    window.location = 'buku/delete?id=' + id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="message.jsp" />
            <div class="row">
                <div class="col-xs-12">
                    <h1>Perpustakaan</h1>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-inline" method="GET" action="buku">
                                <div class="form-group">
                                    <label for="title">Title</label>
                                    <% String title = request.getParameter("title"); %>
                                    <input type="text" class="form-control" id="title" name="title" placeholder="Title" value="<%=((title == null) ? "" : title)%>">
                                </div>
                                <button type="submit" class="btn btn-warning">Cari</button>
                            </form>
                        </div>
                    </div>
                    <table class="table table-bordered table-striped table-responsive">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Terjemahan</th>
                                <th>Edisi</th>
                                <th>ISBN</th>
                                <th>Eksemplar</th>
                                <th>Halaman</th>
                                <th>
                                    <a class="btn btn-success" href="buku/edit">Tambah</a>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<Buku> bukus = (List<Buku>)request.getAttribute("buku"); %>
                            <% if (bukus.size() == 0) { %>
                                <tr>
                                    <td colspan="7">Tidak ada buku</td>
                                </tr>
                            <% } else { %>
                                <% for (Buku buku : bukus) { %>
                                    <tr>
                                        <td><%=buku.getTitle()%></td>
                                        <td><%=buku.getTerjemahan()%></td>
                                        <td><%=buku.getEdisi()%></td>
                                        <td><%=buku.getIsbn()%></td>
                                        <td><%=buku.getEksemplar()%></td>
                                        <td><%=buku.getHalaman()%></td>
                                        <td>
                                            <a class="btn btn-primary" href="buku/edit?id=<%=buku.getIdBuku()%>">Edit</a>
                                            <button class="btn btn-danger" onclick="eliminar(<%=buku.getIdBuku()%>,'<%=buku.getTitle()%>')">Delete</button>
                                        </td>
                                    </tr>
                                <% } %>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
