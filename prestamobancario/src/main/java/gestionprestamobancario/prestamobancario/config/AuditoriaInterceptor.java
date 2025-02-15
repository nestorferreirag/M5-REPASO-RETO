package gestionprestamobancario.prestamobancario.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuditoriaInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        System.out.println("Solicitud entrante: " + request.getMethod());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        if (response.getStatus() == 200) {
            // Registra cualquier error que haya ocurrido durante la solicitud
            String completionInfo = String.format("Solicitud completada: %s %s,  - Estado: %d",
                    request.getMethod(), request.getRequestURI(), response.getStatus());
            System.out.println(completionInfo);

        } else {
            // Registra que la solicitud se completó sin errores
            String completionInfo = String.format("Error durante solicitud: %s %s,  - Estado: %d",
                    request.getMethod(), request.getRequestURI(), response.getStatus());
            System.out.println(completionInfo);

        }
    }
}
