/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soop.engine;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Administrator
 */
public class Action {

    public static String getRequestValue(HttpServletRequest request, String fieldname) {
        String out = request.getParameter(fieldname);
        if (out == null) {
            out = "";
        } else {
            out = out.trim();
        }
        return out;
    }
    
    public static String[] getCF(String id) {
        String[] out = {"N", "I", "90", "CSCRFL86E19C352O"};
        if (id.length() > 19) {
            out[0] = StringUtils.substring(id, 0, 1);
            out[1] = StringUtils.substring(id, 6, 7);
            String idremovelast = StringUtils.substringBeforeLast(id, "-");
            out[2] = StringUtils.substring(idremovelast, 12);

        }
        return out;
    }

    public static String generaidsurvey(String piattaforma, String tiposurvey, String iduser) {
        return piattaforma + RandomStringUtils.randomNumeric(5) + tiposurvey 
                + RandomStringUtils.randomAlphabetic(5) + iduser + "-" + RandomStringUtils.randomAlphabetic(5);
    }

    public static void main(String[] args) {
        String idgenerato = generaidsurvey("D", "I", "2693");

        String[] out = getCF(idgenerato);
        //N - D
        //5 numeri
        //I - U
        //5 lettere
        // numeri id
        // char - 
        //5 lettere
        System.out.println(idgenerato + " soop.engine.Action.main() "
                + Arrays.asList(out).toString());
    }
}
