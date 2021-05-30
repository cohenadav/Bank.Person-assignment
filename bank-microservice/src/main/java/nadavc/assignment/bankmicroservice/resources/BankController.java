package nadavc.assignment.bankmicroservice.resources;

import nadavc.assignment.bankmicroservice.models.Person;
import nadavc.assignment.bankmicroservice.models.PersonsDetails;
import nadavc.assignment.bankmicroservice.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;


@RestController
@RequestMapping("/api/costumers/")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("{id}")
    public Person getCostumerDetails(@PathVariable String id) throws URISyntaxException {
        return bankService.getCostumerDetails(id);
    }

    @GetMapping()
    public PersonsDetails getAllCostumers() throws URISyntaxException {
        return bankService.getAllCostumer();
    }

    @GetMapping("israel")
    public PersonsDetails getCostumersFromIsrael() throws URISyntaxException {
        return bankService.getCostumersFromIsrael();
    }

    @GetMapping("ak")
    public PersonsDetails getCostumersStartsWithAOrK() throws URISyntaxException {
        return bankService.getCostumersStartsWithAOrK();
    }
}
