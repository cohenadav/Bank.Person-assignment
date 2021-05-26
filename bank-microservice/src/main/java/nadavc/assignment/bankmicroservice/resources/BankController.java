package nadavc.assignment.bankmicroservice.resources;

import nadavc.assignment.bankmicroservice.models.Person;
import nadavc.assignment.bankmicroservice.models.PersonsDetails;
import nadavc.assignment.bankmicroservice.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;


@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping("/costumers/{id}")
    public Person getCostumerDetails(@PathVariable String id) throws URISyntaxException {
        return bankService.getCostumerDetails(id);
    }

    @RequestMapping("/costumers")
    public PersonsDetails getCostumerDetails() throws URISyntaxException {
        return bankService.getAllCostumer();
    }

    @RequestMapping("/costumers/israel")
    public PersonsDetails getCostumersFromIsrael() throws URISyntaxException {
        return bankService.getCostumersFromIsrael();
    }

    @RequestMapping("/costumers/ak")
    public PersonsDetails getCostumersStartsWithAOrK() throws URISyntaxException {
        return bankService.getCostumersStartsWithAOrK();
    }
}
