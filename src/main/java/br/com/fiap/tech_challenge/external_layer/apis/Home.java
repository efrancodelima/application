package br.com.fiap.tech_challenge.external_layer.apis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.Hidden;

@Controller
@RequestMapping("/")
public class Home {

    @GetMapping
    @ResponseBody
    @Hidden
    public String showHome() {
        return "Tech Challenge Fase 3: aplicação rodando!<br><br>"+
        "Link para a API: <a href=\"/swagger-ui/index.html\">Swagger UI</a>";
    }
}
