package com.example.demogradle.constans;


/**
 * @author John
 *
 */
public final class HttpStatus {

    /** Petición aceptada y procesada de manera asíncrona. */
    public static final int ACCEPTED = 202;

    /** Recurso no encontrado. */
    public static final int NOT_FOUND = 404;

    /** OK. */
    public static final int OK = 200;

    /** Solicitud inválida o mal formada. */
    public static final int BAD_REQUEST = 400;

    /** Error en el servidor. */
    public static final int SERVER_ERROR = 500;
    
    /** The server successfully processed the request, and is not returning any content.. */
    public static final int NO_CONTENT = 204;

    /**
     * Constructor privado para evitar instancias de clase de constantes.
     */
    private HttpStatus() {
    }

}
