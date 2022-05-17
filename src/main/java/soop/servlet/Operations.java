/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package soop.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import soop.engine.Action;
import static soop.engine.Action.getRequestValue;
import soop.engine.Database;

/**
 *
 * @author Administrator
 */
public class Operations extends HttpServlet {

    protected void startsurvey(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String piattaforma = getRequestValue(request, "piattaforma");
        String tiposurvey = getRequestValue(request, "tiposurvey");
        String iduser = getRequestValue(request, "iduser");
        String cf = getRequestValue(request, "cf");

        String ip = getClientIpAddress(request);

//        StringBuilder sb = new StringBuilder();
//        Enumeration<String> he = request.getHeaderNames();
//        while (he.hasMoreElements()) {
//            String name = he.nextElement();
//            sb.append(name).append(" : ").append(request.getHeader(name)).append(";\n");
//        }
//        String header = sb.toString();
//        System.out.println(header);
//        System.out.println(piattaforma);
//        System.out.println(tiposurvey);
//        System.out.println(iduser);
//        System.out.println(cf);
//        System.out.println(ip);
        String msg = "0";
        String link = null;
        String idpathquestionario = tiposurvey.equals("I") ? "questionario1_raf" : "questionario2_raf";
        try {
            Database db;
            if (piattaforma.equals("N")) {
                db = new Database(Action.HOSTNEET);
            } else {
                db = new Database(Action.HOSTDD);
            }

            String sql1 = "SELECT a.idallievi,a.nome,a.cognome,a.email,a.surveyin,a.surveyout "
                    + "FROM allievi a WHERE a.id_statopartecipazione='01' AND a.idallievi=" + 317;
            try ( Statement st1 = db.getConnection().createStatement();  ResultSet rs1 = st1.executeQuery(sql1)) {
                if (rs1.next()) {
                    int rispostaingresso = rs1.getString(5) == null ? 0 : rs1.getInt(5);
                    int rispostauscita = rs1.getString(6) == null ? 0 : rs1.getInt(6);
                    if ((tiposurvey.equals("I") && rispostaingresso == 1) || (tiposurvey.equals("U") && rispostauscita == 1)) {
                        msg = "HA GIA' RISPOSTO AL QUESTIONARIO IN OGGETTO.";
                    } else {
                        link = db.getPath(idpathquestionario);
                        String idq = StringUtils.substringBefore(StringUtils.substringAfterLast(link, "/"), "?").trim();
                        String add = tiposurvey.equals("I") ? "&" + idq + "X2X30=" + piattaforma + "&" + idq + "X2X31=" + iduser
                                : "&" + idq + "X3X29=" + piattaforma + "&" + idq + "X3X28=" + iduser;
                        link = link + add;
                    }
                } else {
                    msg = "UTENTE NON TROVATO. CONTROLLARE LA MAIL RICEVUTA";
                }
            }

            db.closeDB();
        } catch (Exception ex) {
            ex.printStackTrace();
            msg += ex.getMessage();
        }

        if (msg.equals("0") && link != null) {
            request.getSession().setAttribute("lsrv", link);
            request.getSession().setAttribute("logerr", null);
            Action.redirect(request, response, "login_mcn.jsp?play=YRC");
        } else {
            request.getSession().setAttribute("logerr", msg);
            request.getSession().setAttribute("lsrv", null);
            Action.redirect(request, response, "logerr.jsp");
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

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

        String type = getRequestValue(request, "type");
        if (type.equals("startsurvey")) {
            startsurvey(request, response);
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
