package ma.docaposte.stage.controller.swagger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class SwaggerController {
    @GetMapping(value = "/openapi3/controller.yaml", produces = "text/plain")
    public String getSwaggerYaml() throws IOException {
        Resource resource = new ClassPathResource("openapi3/controller.yaml");
        return new String(Files.readAllBytes(Paths.get(resource.getURI())));
    }
}
