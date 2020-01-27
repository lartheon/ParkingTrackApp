package com.example.springsecuritymysql.service;

import com.example.springsecuritymysql.model.CustomUserDetails;
import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
