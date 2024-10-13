package labs;
import java.util.ArrayList;
import java.util.List;
/*
@author   $Makohon_Roman
@project   $Company service
@class  $444A
@version  1.0.0
@since 02.10.2024 - 14.21
*/



public class Main {
    public static void main(String[] args) {
        // Створення деяких компаній
        Company parentCompany = new Company(null, 100); // Компанія верхнього рівня
        Company childCompany1 = new Company(parentCompany, 50);
        Company childCompany2 = new Company(parentCompany, 30);
        Company grandChildCompany = new Company(childCompany1, 20);

        // Список усіх компаній
        List<Company> companies = new ArrayList<>();
        companies.add(parentCompany);
        companies.add(childCompany1);
        companies.add(childCompany2);
        companies.add(grandChildCompany);

        // Створення екземпляру служби
        ICompanyService companyService = new CompanyService();

        // Отримання верхнього батька для дочірньої компанії
        Company topParent = companyService.getTopLevelParent(grandChildCompany);
        System.out.println("Кількість працівників верхньої батьківської компанії: " + topParent.getEmployeeCount());

        // Отримання кількості працівників для дочірньої компанії та її дочірніх компаній
        long totalEmployees = companyService.getEmployeeCountForCompanyAndChildren(childCompany1, companies);
        System.out.println("Загальна кількість працівників у дочірній компанії 1 та її дочірніх компаніях: " + totalEmployees);
    }
}
