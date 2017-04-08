/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.EquiposCtrl;
import com.sv.udb.modelo.Equipos;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Jose Lira
 */
@MultipartConfig
@WebServlet(name = "EquiposServ", urlPatterns = {"/EquiposServ"})
public class EquiposServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       boolean esValido = request.getMethod().equals("POST");
        String mens = "";
        if(!esValido)
        {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else
        {
            String CRUD = request.getParameter("btonEqui");
            if(CRUD.equals("Guardar"))
            {
            Equipos obje = new Equipos();
            obje.setNombEqui(request.getParameter("nomb"));
            obje.setDescEqui(request.getParameter("desc"));
            Part filePart = request.getPart("ima");
            int fotoSize = (int)filePart.getSize();
            byte[] foto = null;
            foto = new byte[fotoSize];
            try(DataInputStream dataImg = new DataInputStream(filePart.getInputStream()))
            {
                dataImg.readFully(foto);
            }
            obje.setImag(foto);
            if(new EquiposCtrl().guar(obje))
            {
                
                mens = "Datos Guardados";
            }
            else
            {
                mens = "Error al guardar";
            }
            }
            else if(CRUD.equals("Consultar"))
            {
                int codi= Integer.parseInt(request.getParameter("codiEquiRadi").isEmpty() ? "-1" : request.getParameter("codiEquiRadi"));
                Equipos obje  = new EquiposCtrl().cons(codi);
                if(obje != null)
                {
                request.setAttribute("codi", obje.getCodiEqui());                
                request.setAttribute("nomb", obje.getNombEqui());                
                request.setAttribute("desc", obje.getDescEqui()); 
                request.setAttribute("ima", obje.getImag());
                mens = "Información consultada";
                }
                else 
                {
                    mens= "Error al consultar";
                }
            }
            else if (CRUD.equals("Eliminar"))
            {
             Equipos obje = new Equipos();
             obje.setCodiEqui(Integer.parseInt(request.getParameter("codi")));
            if(new EquiposCtrl().elim(obje))
            {
                mens = "Dato Eliminado";
            }
            else
            {
                mens = "Error al Eliminar";
            }
            }
            else if (CRUD.equals("Modificar"))
            {
                Equipos obje = new Equipos();
                //int codi= Integer.parseInt(request.getParameter("codi").isEmpty() ? "-1" : request.getParameter("codi"));
                //obje.setCodiEqui(codi);
                obje.setCodiEqui(Integer.parseInt(request.getParameter("codi")));
                obje.setNombEqui(request.getParameter("nomb"));
                obje.setDescEqui(request.getParameter("desc"));
                Part filePart = request.getPart("ima");
                int fotoSize = (int)filePart.getSize();
            byte[] foto = null;
            foto = new byte[fotoSize];
            try(DataInputStream dataImg = new DataInputStream(filePart.getInputStream()))
            {
                dataImg.readFully(foto);
            }
            obje.setImag(foto);
                if(new EquiposCtrl().modi(obje))
                {
                    mens = "Datos Modificados";
                }
                else
                {
                    mens = "Error al Modificar";
                }
            }
            else if(CRUD.equals("Nuevo"))
            {
                request.setAttribute("codi", "");
                request.setAttribute("nomb", "");
                request.setAttribute("desc", "");
            }
            
            request.setAttribute("mensAler",mens);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
