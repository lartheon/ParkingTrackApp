package com.example.springsecuritymysql.service;

import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.repository.EmployeeRepository;
import com.example.springsecuritymysql.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        Employee employee = this.repository.findEmployeeByEmail(emailAddress).orElseThrow(
                () ->  new UsernameNotFoundException("Unable to find a user with these credentials: " + emailAddress)).get(0);
        if(employee == null) { throw  new UsernameNotFoundException("Unable to find a user with these credentials: " + emailAddress);}
        else { return CustomUserDetails.create(employee); }
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        Employee employee = repository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(("Unable to find a user with this id: " + id))
        );
        return CustomUserDetails.create(employee);
    }
/*    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Employee employee = repository.findEmployeeByEmail(emailAddress)
                .orElseThrow(() ->  new UsernameNotFoundException("Unable to find a user with these credentials: " + emailAddress)).get(0);
              if(employee == null) { throw  new UsernameNotFoundException("Unable to find a user with these credentials: " + emailAddress);}
              else {
                  return new User(employee.getEmail(), employee.getPassword(), emptyList());
//                  return CustomUserDetails.create(employee);
              }
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        Employee employee = repository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(("Unable to find a user with this id: " + id))
        );
        return new User(employee.getEmail(), employee.getPassword(), emptyList());
//        return CustomUserDetails.create(employee);
    }*/
}
/*
@Service
public class CustomUserDetailsService implements UserDetailsService {
//    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<Employee> optionalEmployee = employeeRepository.findByEmail(email);

        if(optionalEmployee != null){
            if(!optionalEmployee.isEmpty()){
                return new CustomUserDetails(optionalEmployee.get(0));
            }
        else throw new UsernameNotFoundException("User not found. Email doesn't exist");
        }
       else throw new UsernameNotFoundException("User not found. Email doesn't exist");
//        log.info("loadUserByUsername() : {}", optionalEmployee.get(0).getEmail());

//        optionalEmployee.orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist"));
//        return optionalEmployee.map(CustomUserDetails::new).get(); //
//         return optionalEmployee.map(employee -> {
//             for(Employee emp : employee){
//             return new CustomUserDetails(emp);
//             }
//             return null;
//         }).get();
    }
}
*/
