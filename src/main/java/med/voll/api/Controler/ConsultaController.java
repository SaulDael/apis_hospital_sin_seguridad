package med.voll.api.Controler;

import jakarta.validation.Valid;
import med.voll.api.Domain.Consultas.AgendaDeConsultaService;
import med.voll.api.Domain.Consultas.DatosAgendarConsulta;
import med.voll.api.Domain.Consultas.DatosCancelarConsulta;
import med.voll.api.Domain.Consultas.DatosDetallesConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/v1/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @RequestMapping("/agendarConsulta")
    @Transactional
    public ResponseEntity<DatosDetallesConsulta> agendar(@RequestBody @Valid DatosAgendarConsulta datos) {

        var response = service.agendar(datos);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequestMapping("/listadoConsultas")
    public ResponseEntity<Page<DatosDetallesConsulta>> listarConsultas(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacion){
        var response = service.consultar(paginacion);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cancelarConsulta")
    @Transactional
    public ResponseEntity<DatosCancelarConsulta> cancelar(@RequestBody @Valid DatosCancelarConsulta datos){
        service.cancelar(datos);
        return ResponseEntity.noContent().build();
    }


}
