package com.example.springsecuritymysql;

import com.example.springsecuritymysql.model.Employee;
import com.example.springsecuritymysql.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class EmployeeRepositoryIntegrationTest {

//    @Autowired
    private Employee employee;

//    @Autowired
    private TestEntityManager entityManager;

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryIntegrationTest(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
        this.employeeRepository.findAll();
    }

    // write test cases here
    @Test
    public void findEmployeeByEmail() {
        // given
        employee = new Employee("Larsson","Kabukoba",null,"larsson.kabukoba@raytheon.o.uk","test123","Engineering","007",null,null);
        // when
        List<Employee> found = employeeRepository.findByEmail(employee.getEmail());

        // then
        if(!found.isEmpty()){
//            assertEquals(employee.getEmail(),found.get(0).getEmail());
            assertEquals(found.get(0).getEmail(),employee.getEmail());
        }else{
            assertEquals(employee.getEmail(),"");
        }
    }

}
