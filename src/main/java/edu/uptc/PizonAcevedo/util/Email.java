package edu.uptc.PizonAcevedo.util;

import java.time.ZonedDateTime;

public class Email {
    public static String bodyEmail(String name, String lastName, String userName, String password) {
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"es\"><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Credenciales de Acceso</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-size: 18px;\n" +
                "        }\n" +
                "        .container {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: rgba(0, 0, 0, 0.05);\n" +
                "            max-width: 700px;\n" +
                "            margin: 0 auto;\n" +
                "            border-radius: 8px;\n" +
                "            padding: 20px;\n" +
                "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            font-size: 24px;\n" +
                "            color: #333;\n" +
                "            margin-bottom: 20px;\n" +
                "            text-align: center;\n" +
                "            background-color: rgba(0, 0, 0, 0.1);\n" +
                "            padding: 10px;\n" +
                "            border-radius: 8px;\n" +
                "        }\n" +
                "        p {\n" +
                "            font-size: 18px;\n" +
                "            margin-bottom: 20px;\n" +
                "            text-align: justify;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 8px;\n" +
                "        }\n" +
                "        .highlight {\n" +
                "            font-weight: bold;\n" +
                "            color: #0056b3;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Credenciales de Acceso a tu Cuenta</h1>\n" +
                "        <p>Estimado/a <span class=\"highlight\">" + name + " " + lastName + "</span>,</p>\n" +
                "        <p>¡Bienvenido/a!</p>\n" +
                "        <p>Nos complace informarte que ya tienes acceso a nuestra plataforma. A continuación, te compartimos tus credenciales de acceso, las cuales deberás usar para ingresar a tu cuenta:</p>\n" +
                "        <p>Nombre de usuario: <span class=\"highlight\">" + userName + "</span></p>\n" +
                "        <p>Contraseña temporal: <span class=\"highlight\">" + password + "</span></p>\n" +
                "        <p>Por favor, sigue los siguientes pasos al recibir este correo:</p>\n" +
                "        <ol>\n" +
                "            <li>Ingresa a la plataforma a través de este enlace: <a href=\"" + "https://frontend-aires-sas.vercel.app" + "\">" + "Haz click aca" + "</a>.</li>\n" +
                "            <li>Usa las credenciales proporcionadas para iniciar sesión.</li>\n" +
                "            <li>Te recomendamos cambiar la contraseña temporal por una de tu preferencia para mayor seguridad en tu primera sesión.</li>\n" +
                "        </ol>\n" +
                "        <p>Si tienes alguna duda o necesitas asistencia, no dudes en contactarnos.</p>\n" +
                "        <p>¡Estamos encantados de tenerte con nosotros!</p>\n" +
                "        <p>Atentamente,<br><strong>" + "P&A Aires Acondicionados" + "</strong><br>" + "contactInfo" + "</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return body;
    }

    public static String emailSubject(){
        return "Tus Credenciales de Acceso a P&A Aires acondicionados";
    }
}
