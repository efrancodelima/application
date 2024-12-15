package br.com.fiap.tech_challenge.external_layer.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeApi {

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public RedirectView redirectToSwagger() {
        String currentUrl = request.getRequestURL().toString();
        String link = currentUrl.concat("swagger-ui/index.html");
        return new RedirectView(link);
    }
}
