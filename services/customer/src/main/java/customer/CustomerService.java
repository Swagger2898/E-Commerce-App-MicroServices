package customer;

import exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        // Save the customer and return the generated ID as a String
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();  // This will return a String
    }


    public void updateCustomer(@Valid CustomerRequest request) {

        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("cannot update customer:: NO CUSTOMER FOUND WITH THE PROVIDED ID:: %s", request.id())

                ));
        mergerCustomer(customer, request);
        customerRepository.save(customer);

    }

    private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {

        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname((request.firstname()));
        }
        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname((request.lastname()));
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail((request.email()));
        }
        if (request.address() != null) {
            customer.setAddress((request.address()));
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(format("No customer found with the provided ID :: %s",customerId)));

    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
   