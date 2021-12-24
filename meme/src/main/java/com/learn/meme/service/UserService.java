package com.learn.meme.service;

import com.learn.meme.event.OnRegistrationCompleteEvent;
import com.learn.meme.model.Customer;
import com.learn.meme.model.Staff;
import com.learn.meme.model.User;
import com.learn.meme.repository.CustomerRepository;
import com.learn.meme.repository.StaffRepository;
import com.learn.meme.security.CustomUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements CustomUserDetailsService {

    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(CustomerRepository customerRepository, StaffRepository staffRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsernameAndDomain(String username, String domain) throws UsernameNotFoundException {
        if(domain.equals("staff")){
            return staffRepository.getByUsername(username);
        }else if(domain.equals("customer")){
            return customerRepository.getByUsername(username);
        }
        return null;
    }

    public User registerOne(User user, String typeUser) {
        if(typeUser.equalsIgnoreCase("staff")){
            Staff newOne=modelMapper.map(user, Staff.class);
            Staff staff = staffRepository.save(newOne);
            return staff;
        }else if(typeUser.equalsIgnoreCase("customer")){
            Customer customerOne=modelMapper.map(user, Customer.class);
            Customer customer = customerRepository.save(customerOne);
            return customer;
        }
        return null;
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.getCustomerByUsername(username);
    }

    public List<Customer> getNewUsersInMonth(int month, int year) {
        Calendar from=Calendar.getInstance();
        from.set(year, month-1,0);

        Calendar to=Calendar.getInstance();
        to.set(year, month-1,from.getActualMaximum(Calendar.DATE));

        return customerRepository.getNewUsersInMonth(from.getTime(), to.getTime());
    }
}

//eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
