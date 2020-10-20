/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.hibernate.Buku;
import com.dao.BukuDao;
import com.servlet.util.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author java
 */
public class ListBukuServlet extends HttpServlet {

    // <editor-fold defaultstate="expanded" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Buku> buku = null;
        HttpSession sesion = request.getSession();
        String msg = (String)sesion.getAttribute("msj");
        Result res = (Result)sesion.getAttribute("res");
        String titleSearch = request.getParameter("title");
        try (BukuDao libroDao = new BukuDao()) {
            if (titleSearch == null || titleSearch.trim().equals("")) {
                buku = libroDao.getAll();
            } else {
                buku = libroDao.getByTitle(titleSearch);
            }
        } catch (Throwable ex) {
            System.err.println("ListBookServlet.doGet. Error: " + ex.getMessage());
            if (msg == null) {
                msg = "Could not get the list of books.";
            }
            res = Result.Error;
        }
        if (buku == null) {
            buku = new ArrayList<>();
        }
        if (msg != null) {
            request.setAttribute("msj", msg);
            sesion.removeAttribute("msj");
        }
        if (res != null) {
            request.setAttribute("res", res);
            sesion.removeAttribute("res");
        }
        request.setAttribute("buku", buku);
        this.getServletContext().getRequestDispatcher("/ListBuku.jsp").include(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
