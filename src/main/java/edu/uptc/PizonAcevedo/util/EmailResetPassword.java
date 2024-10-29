package edu.uptc.PizonAcevedo.util;

public class EmailResetPassword {
    public static String bodyEmail(String name, String lastName, String code, String token, String userName) {
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"es\"><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Código de Verificación</title>\n" +
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
                "        <h1>Código de Verificación para Recuperar tu Contraseña</h1>\n" +
                "        <p>Estimado/a <span class=\"highlight\">" + name + " " + lastName + "</span>,</p>\n" +
                "        <p>Nombre de usuario: <span class=\"highlight\">" + userName + "</span></p>\n" +
                "        <p>Hemos recibido una solicitud para restablecer tu contraseña.</p>\n" +
                "        <p>Para completar el proceso de recuperación, utiliza el siguiente código de verificación:</p>\n" +
                "        <p style=\"text-align: center; font-size: 24px; font-weight: bold; padding: 15px; border: 1px solid #ddd; background-color: #f9f9f9; border-radius: 8px;\">" + code + "</p>\n" +
                "        <p>Este código debe ser ingresado exclusivamente en el siguiente enlace:</p>\n" +
                "        <p style=\"text-align: center;\">\n" +
                "            <a href=\"" + "http://localhost:3000/reset-password?token=" + token + "\" " +
                "               style=\"display: inline-block; padding: 10px 20px; font-size: 18px; color: #ffffff; background-color: #0056b3; border-radius: 8px; text-decoration: none; font-family: Arial, sans-serif;\">\n" +
                "               Ingresar Código de Verificación\n" +
                "            </a>\n" +
                "        </p>\n" +
                "        <p>Recuerda que este código es válido solo por una hora y podrás usarlo una única vez en la página de recuperación de contraseña para completar el proceso.</p>\n" +
                "        <p>Si no solicitaste un cambio de contraseña, puedes ignorar este mensaje. Tu cuenta permanecerá segura.</p>\n" +
                "        <p>Para cualquier consulta adicional, no dudes en ponerte en contacto con nosotros.</p>\n" +
                "        <p>Atentamente,<br><strong>" + "P&A Aires Acondicionados" + "</strong><br>" + "contactInfo" + "</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        return body;
    }
    public static String emailSubject(){
        return "Tu Código de Verificación para Recuperar tu Contraseña";
    }
}

//
//String body = "<!DOCTYPE html>\n" +
//        "<html lang=\"es\"><head>\n" +
//        "    <meta charset=\"UTF-8\">\n" +
//        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//        "    <title>Código de Verificación</title>\n" +
//        "    <style>\n" +
//        "        body {\n" +
//        "            font-size: 18px;\n" +
//        "        }\n" +
//        "        .container {\n" +
//        "            font-family: Arial, sans-serif;\n" +
//        "            background-color: rgba(0, 0, 0, 0.05);\n" +
//        "            max-width: 700px;\n" +
//        "            margin: 0 auto;\n" +
//        "            border-radius: 8px;\n" +
//        "            padding: 20px;\n" +
//        "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\n" +
//        "            color: #333;\n" +
//        "        }\n" +
//        "        h1 {\n" +
//        "            font-size: 24px;\n" +
//        "            color: #333;\n" +
//        "            margin-bottom: 20px;\n" +
//        "            text-align: center;\n" +
//        "            background-color: rgba(0, 0, 0, 0.1);\n" +
//        "            padding: 10px;\n" +
//        "            border-radius: 8px;\n" +
//        "        }\n" +
//        "        p {\n" +
//        "            font-size: 18px;\n" +
//        "            margin-bottom: 20px;\n" +
//        "            text-align: justify;\n" +
//        "            padding: 10px;\n" +
//        "            border-radius: 8px;\n" +
//        "        }\n" +
//        "        .highlight {\n" +
//        "            font-weight: bold;\n" +
//        "            color: #0056b3;\n" +
//        "        }\n" +
//        "    </style>\n" +
//        "</head>\n" +
//        "<body>\n" +
//        "    <div class=\"container\">\n" +
//        "        <h1>Código de Verificación para Recuperar tu Contraseña</h1>\n" +
//        "        <p>Estimado/a <span class=\"highlight\">" + name + " " + lastName + "</span>,</p>\n" +
//        "        <p>Hemos recibido una solicitud para restablecer tu contraseña.</p>\n" +
//        "        <p>Para completar el proceso de recuperación, utiliza el siguiente código de verificación:</p>\n" +
//        "        <p style=\"text-align: center; font-size: 24px; font-weight: bold; padding: 15px; border: 1px solid #ddd; background-color: #f9f9f9; border-radius: 8px;\">" + code + "</p>\n" +
//        "        <p>Este código es válido solo por un tiempo limitado y deberá ser introducido en la página de recuperación de contraseña para completar el proceso.</p>\n" +
//        "        <p>Si no solicitaste un cambio de contraseña, puedes ignorar este mensaje. Tu cuenta permanecerá segura.</p>\n" +
//        "        <p>Para cualquier consulta adicional, no dudes en ponerte en contacto con nosotros.</p>\n" +
//        "        <p>Atentamente,<br><strong>" + "companyName" + "</strong><br>" + "contactInfo" + "</p>\n" +
//        "    </div>\n" +
//        "</body>\n" +
//        "</html>";
