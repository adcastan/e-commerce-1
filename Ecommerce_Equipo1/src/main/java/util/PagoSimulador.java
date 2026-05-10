package util;

import DTOs.DatosPagoDTO;

public class PagoSimulador {

    public static class ResultadoPago {
        public final boolean aprobado;
        public final String mensaje;

        public ResultadoPago(boolean aprobado, String mensaje) {
            this.aprobado = aprobado;
            this.mensaje = mensaje;
        }
    }

    public static ResultadoPago procesar(String tipoPago, DatosPagoDTO datos) {
        if (tipoPago == null) {
            return new ResultadoPago(false, "tipo de pago no especificado");
        }

        if ("CONTRA_ENTREGA".equalsIgnoreCase(tipoPago) || "TRANSFERENCIA".equalsIgnoreCase(tipoPago)) {
            return new ResultadoPago(true, "pago registrado, pendiente de confirmaci\u00f3n");
        }

        if ("TARJETA".equalsIgnoreCase(tipoPago)) {
            if (datos == null) {
                return new ResultadoPago(false, "datos de tarjeta requeridos");
            }
            String numero = datos.getNumeroTarjeta() == null ? "" : datos.getNumeroTarjeta().replaceAll("\\s", "");
            String cvv = datos.getCvv() == null ? "" : datos.getCvv().trim();
            String venc = datos.getVencimiento() == null ? "" : datos.getVencimiento().trim();

            if (!numero.matches("\\d{13,19}")) {
                return new ResultadoPago(false, "n\u00famero de tarjeta inv\u00e1lido");
            }
            if (!luhn(numero)) {
                return new ResultadoPago(false, "n\u00famero de tarjeta no pasa validaci\u00f3n");
            }
            if (!cvv.matches("\\d{3,4}")) {
                return new ResultadoPago(false, "cvv inv\u00e1lido");
            }
            if (!venc.matches("(0[1-9]|1[0-2])\\/(\\d{2})")) {
                return new ResultadoPago(false, "vencimiento inv\u00e1lido (formato MM/AA)");
            }
            return new ResultadoPago(true, "pago aprobado");
        }

        return new ResultadoPago(false, "m\u00e9todo de pago no soportado");
    }

    private static boolean luhn(String numero) {
        int suma = 0;
        boolean alterna = false;
        for (int i = numero.length() - 1; i >= 0; i--) {
            int d = numero.charAt(i) - '0';
            if (alterna) {
                d *= 2;
                if (d > 9) d -= 9;
            }
            suma += d;
            alterna = !alterna;
        }
        return suma % 10 == 0;
    }
}
