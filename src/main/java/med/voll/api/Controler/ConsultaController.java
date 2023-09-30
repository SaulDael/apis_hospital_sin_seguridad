package med.voll.api.Controler;

import jakarta.validation.Valid;
import med.voll.api.Domain.Consultas.AgendaDeConsultaService;
import med.voll.api.Domain.Consultas.DatosAgendarConsulta;
import med.voll.api.Domain.Consultas.DatosDetallesConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/v1/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @RequestMapping("/agendarConsulta")
    @Transactional
    public ResponseEntity<DatosDetallesConsulta> agendar(@RequestBody @Valid DatosAgendarConsulta datos){

        var response = service.agendar(datos);

        return ResponseEntity.ok(response);
    }
}
