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
 * @author GuillermoGutierrez
 */
public class EditServlet extends HttpServlet {

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
        String idStr = request.getParameter("id");
        int id;
        if (idStr == null) {
            buku = new Buku();
        } else {
            try (BukuDao bukuDao = new BukuDao()) {
                id = Integer.parseInt(idStr);
                buku = bukuDao.getById(id);
                if (buku == null) {
                    msg = "The requested book was not found.";
                    request.setAttribute("msj", msg);
                    request.setAttribute("res", Result.Failure);
                    getServletContext().getRequestDispatcher("/ListBuku.jsp").include(request, response);
                    return;
                }
            } catch (Throwable ex) {
                System.err.println("LibrosEditarServlet.doGet. Error: " + ex.getMessage());
                msg = "An error occurred while trying to get the required book information.";
                request.setAttribute("msj", msg);
                request.setAttribute("res", Result.Error);
                getServletContext().getRequestDispatcher("/ListBuku.jsp").include(request, response);
                return;
            }
        }
        request.setAttribute("buku", buku);
        getServletContext().getRequestDispatcher("/EditBuku.jsp").include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Buku buku = null;
        String idStr = request.getParameter("id");
        try (BukuDao bukuDao = new BukuDao()) {
            int id;
            if (idStr == null) {
                buku = new Buku();
            } else {
                id = Integer.parseInt(idStr);
                buku = bukuDao.getById(id);
            }
            buku.setEdisi(Integer.parseInt(request.getParameter("edisi")));
            buku.setEksemplar(Integer.parseInt(request.getParameter("eksemplar")));
            buku.setIsbn(Long.parseLong(request.getParameter("isbn")));
            buku.setHalaman(Integer.parseInt(request.getParameter("halaman")));
            buku.setTerjemahan(request.getParameter("terjemahan"));
            buku.setTitle(request.getParameter("title"));
            
            // Guardando
            bukuDao.update(buku);
            HttpSession sesion = request.getSession();
            sesion.setAttribute("res", Result.Exit);
            String msg;
            if (idStr == null) {
                msg = "Book '" + buku.getTitle()+  "' successfully created.";
            } else {
                msg = "Book '" + buku.getTitle()+  "' updated successfully.";
            }
            sesion.setAttribute("msj", msg);
            response.sendRedirect("../buku");
        } catch(NumberFormatException nfe) {
            System.err.println("LibrosEditarServlet.doPost. Error: " + nfe.getMessage());
            request.setAttribute("buku", (buku == null) ? new Buku() : buku);
            request.setAttribute("res", Result.Failure);
            request.setAttribute("msj", "One or more invalid data.");
            getServletContext().getRequestDispatcher("/EditBuku.jsp").include(request, response);
        }catch (Throwable ex) {
            System.err.println("LibrosEditarServlet.doPost. Error: " + ex.getMessage());
            request.setAttribute("buku", (buku == null) ? new Buku() : buku);
            request.setAttribute("res", Result.Error);
            request.setAttribute("msj", "An error occurred while trying to save the book information.");
            getServletContext().getRequestDispatcher("/EditBuku.jsp").include(request, response);
        }
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
