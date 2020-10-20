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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author java
 */
public class DeleteServlet extends HttpServlet {

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
        Buku buku = null;
        String msg = null;
        Result res = Result.Error;
        HttpSession sesion = request.getSession();
        try (BukuDao bukuDao = new BukuDao()) {
            String idStr = request.getParameter("id");
            int id;
            if (idStr != null) {
                id = Integer.parseInt(idStr);
                buku = bukuDao.getById(id);
                if (buku != null) {
                    bukuDao.delete(buku);
                    msg = "Book '" + buku.getTitle()+ "' removed.";
                    res = Result.Exit;
                } else {
                    msg = "The book to delete was not found.";
                }
            } else {
                msg = "The book to delete was not specified.";
            }
        } catch (Throwable ex) {
            System.err.println("LibrosEliminarServlet.doGet. Error: " + ex.getMessage());
            msg = "The workbook could not be deleted due to an internal error.";
        }
        sesion.setAttribute("res", res);
        sesion.setAttribute("msj", msg);
        response.sendRedirect("../buku");
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
