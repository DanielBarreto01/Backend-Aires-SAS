package edu.uptc.PizonAcevedo.util;

public class EmailResetPassword {
    private static final String phoneNumber = "+57 314 2305348";
    private static final String contactEmail = "Director@pinzonyacevedosas.com.co";

    public static String bodyEmail(String name, String lastName, String code, String token, String userName) {
        String body = "<!DOCTYPE html><html lang=\"es\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Código de Verificación</title></head>" +
                "<body style=\"font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;\">" +
                "<div style=\"max-width: 600px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\">" +
                "<h1 style=\"font-size: 24px; color: #333; text-align: center; background-color: #e8e8e8; padding: 10px; border-radius: 8px;\">Código de Verificación para Recuperar tu Contraseña</h1>" +
                "<p style=\"font-size: 18px; color: #333;\">Estimado/a <span style=\"font-weight: bold; color: #0056b3;\">" + name + " " + lastName + "</span>,</p>" +
                "<p style=\"font-size: 18px; color: #333;\">Nombre de usuario: <span style=\"font-weight: bold; color: #0056b3;\">" + userName + "</span></p>" +
                "<p style=\"font-size: 18px; color: #333;\">Hemos recibido una solicitud para restablecer tu contraseña.</p>" +
                "<p style=\"font-size: 18px; color: #333;\">Para completar el proceso de recuperación, utiliza el siguiente código de verificación:</p>" +
                "<p style=\"text-align: center; font-size: 24px; font-weight: bold; padding: 15px; border: 1px solid #ddd; background-color: #f9f9f9; border-radius: 8px; color: #333;\">" + code + "</p>" +
                "<p style=\"font-size: 18px; color: #333;\">Este código debe ser ingresado exclusivamente en el siguiente enlace:</p>" +
                "<div style=\"text-align: center; margin-top: 20px;\">" +
                "<a href=\"" + "https://frontend-aires-sas.vercel.app/reset-password?token=" + token + "\" style=\"display: inline-block; padding: 10px 20px; font-size: 18px; color: #ffffff; background-color: #0056b3; border-radius: 8px; text-decoration: none;\">Ingresar Código de Verificación</a>" +
                "</div>" +
                "<p style=\"font-size: 18px; color: #333; margin-top: 20px;\">Recuerda que este código es válido solo por una hora y podrás usarlo una única vez en la página de recuperación de contraseña para completar el proceso.</p>" +
                "<p style=\"font-size: 18px; color: #333;\">Si no solicitaste un cambio de contraseña, puedes ignorar este mensaje. Tu cuenta permanecerá segura.</p>" +
                "<p style=\"font-size: 18px; color: #333;\">Para cualquier consulta adicional, no dudes en ponerte en contacto con nosotros.</p>" +
                "<p style=\"font-size: 18px; color: #333;\"> Atentamente,<br><strong>" + "P&A Aires Acondicionados" + "</strong><br>" +
                "           Teléfono: " + phoneNumber + "<br>" +
                "           Correo electrónico: <a href=\"mailto:" + contactEmail + "\">" + contactEmail + "</a>" +
                "        </p>\n" +
                "</div></body></html>";
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


//////////////////////////////////////////////////////////////////////////////////////
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
//        "        <p>Nombre de usuario: <span class=\"highlight\">" + userName + "</span></p>\n" +
//        "        <p>Hemos recibido una solicitud para restablecer tu contraseña.</p>\n" +
//        "        <p>Para completar el proceso de recuperación, utiliza el siguiente código de verificación:</p>\n" +
//        "        <p style=\"text-align: center; font-size: 24px; font-weight: bold; padding: 15px; border: 1px solid #ddd; background-color: #f9f9f9; border-radius: 8px;\">" + code + "</p>\n" +
//        "        <p>Este código debe ser ingresado exclusivamente en el siguiente enlace:</p>\n" +
//        "        <p style=\"text-align: center;\">\n" +
//        "            <a href=\"" + "https://frontend-aires-sas.vercel.app/reset-password?token=" + token + "\" " +
//        "               style=\"display: inline-block; padding: 10px 20px; font-size: 18px; color: #ffffff; background-color: #0056b3; border-radius: 8px; text-decoration: none; font-family: Arial, sans-serif;\">\n" +
//        "               Ingresar Código de Verificación\n" +
//        "            </a>\n" +
//        "        </p>\n" +
//        "        <p>Recuerda que este código es válido solo por una hora y podrás usarlo una única vez en la página de recuperación de contraseña para completar el proceso.</p>\n" +
//        "        <p>Si no solicitaste un cambio de contraseña, puedes ignorar este mensaje. Tu cuenta permanecerá segura.</p>\n" +
//        "        <p>Para cualquier consulta adicional, no dudes en ponerte en contacto con nosotros.</p>\n" +
//        "        <p>Atentamente,<br><strong>" + "P&A Aires Acondicionados" + "</strong><br>" + "contactInfo" + "</p>\n" +
//        "    </div>\n" +
//        "</body>\n" +
//        "</html>";
//        return body;
