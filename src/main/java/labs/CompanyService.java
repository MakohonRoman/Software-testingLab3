package labs;
import java.util.List;/*
@author   $Makohon_Roman
@project   $Company service
@class  $444A
@version  1.0.0
@since 02.10.2024 - 14.21
*/



public class CompanyService implements ICompanyService {

    @Override
    public Company getTopLevelParent(Company child) {
        Company current = child;
        // Перебір вверх по ієрархії для знаходження верхнього батька
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return current; // Повертаємо верхню батьківську компанію
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        if (company == null) {
            return 0; // Повертаємо 0, якщо компанія дорівнює null
        }

        long totalEmployees = company.getEmployeeCount(); // Підрахунок працівників для даної компанії

        // Додаємо працівників з дочірніх компаній
        for (Company child : companies) {
            if (child.getParent() != null && child.getParent().equals(company)) {
                totalEmployees += getEmployeeCountForCompanyAndChildren(child, companies);
            }
        }

        return totalEmployees;
    }
}
