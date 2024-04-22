package pro.sky.skyprosheetsandsetsdemo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {
    private List<Employee> employeelist = new ArrayList<>();
    private final int MAX_EMPLIYEE = 5;
    private static int total;

    public String addEmployee(String familName, String name){
        try {
            checkAddEmployee(familName, name);
            Employee newEmployee = new Employee(familName, name);
            employeelist.add(newEmployee);
            return "<h1>Сотрудник %s %s добавлен</h1>".formatted(familName, name);
        }catch (EmployeeStorageIsFullException e){
            System.out.println(e);
            return "<h1>Мест для новых сотрудников нет</h1>";

        }catch (EmployeeAlreadyAddedException e){
            System.out.println(e);
            return "<h1>Сотрудник %s %s уже работает в компании</h1>".formatted(familName, name);

        }

    }
    public String dismissalEmployee(String familName, String name){
        boolean flag = false;
        for (int i = 0; i < employeelist.size(); i++) {
            if (employeelist.get(i).equals(new Employee(familName, name))){
                employeelist.remove(employeelist.get(i));
                flag = true;
            }
        }
        try {
            if (flag){
                return "<h1>Сотрудник %s %s уволен</h1>".formatted(familName, name);
            } else {
                throw new EmployeeNotFoundException("Сотрудник отсутствует в базе данных, увольнение невозможно");
            }
        }catch (EmployeeNotFoundException e){
            System.out.println(e);
            return "Сотрудник %s %s отсутствует в базе данных, увольнение невозможно".formatted(familName, name);
        }
    }
    public String searchEmployee(String familName, String name){
        boolean flag = false;
        for (int i = 0; i < employeelist.size(); i++) {
            if (employeelist.get(i).equals(new Employee(familName, name))){
                System.out.println(employeelist.get(i));
                flag = true;
            }
        }
        try {
            if (flag){
                return "<h1>Найден сотрудник %s %s</h1>".formatted(familName, name);

            } else {
                throw new EmployeeNotFoundException("Сотрудник отсутствует в базе данных");
            }
        }catch (EmployeeNotFoundException e){
            System.out.println(e);
            return "<h1>Сотрудник %s %s не найден в базе данных!!!</h1>".formatted(familName, name);
        }
    }
    public boolean checkAddEmployee(String familName, String name) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException{
        if(employeelist.size() >= MAX_EMPLIYEE){
            throw new EmployeeStorageIsFullException("Мест для новых сотрудников нет");
        }
        for (int i = 0; i < employeelist.size(); i++) {
            if (employeelist.get(i).equals(new Employee(familName, name))) {
                throw new EmployeeAlreadyAddedException("Сотрудник уже работает в компании");
            }
        }
        return true;
    }
    public String printAllEmployee(){ //Может я не так печатую? Если через цикл проводить, то он естественно печатает только одного.
        // Есть способ распечатать сотрудников без []?
        return employeelist.toString();

    }



}
