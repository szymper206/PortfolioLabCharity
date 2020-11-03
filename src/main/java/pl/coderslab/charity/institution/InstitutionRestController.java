package pl.coderslab.charity.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController // @Controller + @ResponseBody na każdej metodzie
@RequestMapping("/api/institutions") // ścieżka reprezentuje jakiś zasób, np. instytucje
public class InstitutionRestController {

    private final InstitutionService institutionService;

    @GetMapping
    public List<Institution> allInstitution() {
        return institutionService.findAllInstitution();
    }

    @PostMapping
    // Domyślnie Spring buduje obiekt parametru z paremetrów żądania, np name=abac
    // 201 Created + Location z adresem nowo utworzonego zasobu
    public ResponseEntity addInstitution(@Valid @RequestBody Institution institution,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        institutionService.saveInstitution(institution);
        return ResponseEntity.created(URI.create("/api/institutions/" + institution.getId())).build();
    }

    @GetMapping("/{id}")
    // 200 OK + treść
    // 404 Not found
    public ResponseEntity getOne(@PathVariable Long id) {
        return institutionService.findInstitutionById(id)
                .map(institution -> ResponseEntity.ok(institution))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public void editInstitution(@Valid @RequestBody Institution institution,
                                BindingResult result,
                                @PathVariable Long id) {
        if (result.hasErrors()) {
            ;
        }
        if (institution.getId() != id) {
            ;
        }
        institutionService.saveInstitution(institution);
    }

    @DeleteMapping("/{id}")
    // 404 Not found - jeżeli nie istnieje id
    // 200 OK, ale bez treść
    // 204 No content
    public ResponseEntity deleteInstitution(@PathVariable Long id) {
        return institutionService.findInstitutionById(id)
                .map(institution -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
