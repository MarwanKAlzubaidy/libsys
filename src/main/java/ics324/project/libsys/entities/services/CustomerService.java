package ics324.project.libsys.entities.services;


import ics324.project.libsys.Mail.EmailCfg;
import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.repo.BookRepository;
import ics324.project.libsys.repo.EmployeeRepository;
import ics324.project.libsys.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailCfg cfg;

    public void saveCustomer(Customer customer) throws Exception {
        if (employeeRepository.findByuserName(customer.getUserName()) == null)
            customerRepository.save(customer);
        else
            throw new Exception();
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public List<Customer> CustomersAvalibleForBorrow() {
        return customerRepository.availableForBorrow();
    }

    public void saveAll(Set<Customer> customer) {
        customerRepository.saveAll(customer);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByuserName(username);
    }
    //public Customer findByuserName(String value) {
    //  }

    @Scheduled(cron = "0 0 * * * *")
    public void checkForReservation() {
        List<Customer> customerHasReservation = customerRepository.customerHasReservation();
        Iterator<Customer> customerIterable = customerHasReservation.iterator();
        while (customerIterable.hasNext()) {
            Customer customer = customerIterable.next();
            List<Book> reserveBooks = bookRepository.findReserveBooks();
            Set<Book> avaList = new HashSet<>(customer.getBooks());
            Set<Book> notAvaList = new HashSet<>(customer.getBooks());

            avaList.removeAll(reserveBooks);


            notAvaList.retainAll(reserveBooks);

            customer.setBooks(notAvaList);

            if (!avaList.isEmpty()) {
                customer.setBooks(notAvaList);
                customerRepository.save(customer);

                JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
                mailSender.setHost(cfg.getHost());
                mailSender.setPort(cfg.getPort());
                mailSender.setUsername(cfg.getUsername());
                mailSender.setPassword(cfg.getPassword());

                SimpleMailMessage message = new SimpleMailMessage();
                message.setSubject("Book Has been Available");
                String s = "";
                Iterator<Book> iterator = avaList.iterator();
                while (iterator.hasNext()) {
                    s = s + " : " + iterator.next().toString();
                }
                message.setText("Hello " + customer.getFullName() + "\n Book/books you reserved become avalible:" + s);
                message.setFrom("libsys@lib.sys");
                message.setTo(customer.getEmail());
                mailSender.send(message);


            }


        }


    }


    public  List<Customer>  getNewCustomer(){

        return customerRepository.findNewCustomer();
    }
    public List<Customer>  getCust3onelate(){
        return  customerRepository.getCust3onelate();


    }
    public List<Customer> custNoFine(){

        return  customerRepository.getNoFine();
    }


}
