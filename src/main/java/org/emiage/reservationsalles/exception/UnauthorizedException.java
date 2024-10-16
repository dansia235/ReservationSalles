package org.emiage.reservationsalles.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Exception pour les erreurs d'authentification.
 */
public class UnauthorizedException extends WebApplicationException {
    public UnauthorizedException(String message) {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorMessage(message))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }

    public static class ErrorMessage {
        private String error;

        public ErrorMessage() {
        }

        public ErrorMessage(String error) {
            this.error = error;
        }

        // Getter et Setter
        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}