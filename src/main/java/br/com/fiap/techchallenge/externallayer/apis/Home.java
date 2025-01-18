package br.com.fiap.techchallenge.externallayer.apis;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Home {

    @GetMapping
    @Hidden
    public String showHome() {
        return "Tech Challenge Fase 3: aplicação rodando!<br><br>"+
        "Link para a API: <a href=\"/swagger-ui/index.html\">Swagger UI</a>";
    }
}
