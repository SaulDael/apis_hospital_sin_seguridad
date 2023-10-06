package med.voll.api.Controler;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class TestRolesController {

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accesAdmin(){
        return "Hola, has accedido con el rol de ADMIN";
    }

    @GetMapping("/accessUser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')" )
    public String accesUser(){
        return "Hola, has accedido con el rol de USER";
    }

    @GetMapping("/accessInvited")
    @PreAuthorize("hasRole('INVITED') or hasRole('ADMIN')")
    public String accesInvited(){
        return "Hola, has accedido con el rol de INVITED";
    }
}
