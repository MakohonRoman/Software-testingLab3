package labs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
@author   $Makohon_Roman
@project   $Company service
@class  $444A
@version  1.0.0
@since 02.10.2024 - 14.21
*/


public class CompanyServiceDiverseTest {

    private ICompanyService companyService = new CompanyService();

    // 1. Перевірка верхнього батька для компанії без батьків (компанія верхнього рівня)
    @Test
    public void testNoParentCompany() {
        Company parent = new Company(null, 300);
        assertEquals(parent, companyService.getTopLevelParent(parent));
    }

    // 2. Перевірка, що компанія із дочірньою компанією правильно визначає верхнього батька
    @Test
    public void testGetTopLevelParentWithOneChild() {
        Company parent = new Company(null, 300);
        Company child = new Company(parent, 100);
        assertEquals(parent, companyService.getTopLevelParent(child));
    }

    // 3. Перевірка кількості працівників для компанії з одним рівнем дочірніх компаній
    @Test
    public void testEmployeeCountOneLevel() {
        Company parent = new Company(null, 200);
        Company child = new Company(parent, 100);
        List<Company> companies = List.of(parent, child);
        assertEquals(300, companyService.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    // 4. Перевірка кількості працівників з двома рівнями ієрархії компаній
    @Test
    public void testEmployeeCountTwoLevels() {
        Company parent = new Company(null, 200);
        Company child = new Company(parent, 150);
        Company grandChild = new Company(child, 50);
        List<Company> companies = List.of(parent, child, grandChild);
        assertEquals(400, companyService.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    // 5. Перевірка кількості працівників у дочірній компанії, де всі дочірні компанії в списку
    @Test
    public void testEmployeeCountForChildWithGrandChild() {
        Company parent = new Company(null, 500);
        Company child = new Company(parent, 200);
        Company grandChild = new Company(child, 50);
        List<Company> companies = List.of(parent, child, grandChild);
        assertEquals(250, companyService.getEmployeeCountForCompanyAndChildren(child, companies));
    }

    // 6. Перевірка повернення нульового результату для компанії без працівників
    @Test
    public void testZeroEmployeeCount() {
        Company company = new Company(null, 0);
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        assertEquals(0, companyService.getEmployeeCountForCompanyAndChildren(company, companies));
    }

    // 7. Перевірка кількості працівників для компанії з повторюваними дочірніми компаніями
    @Test
    void testDuplicateCompaniesInList() {
        Company parentCompany = new Company(null, 100);
        Company childCompany = new Company(parentCompany, 50);

        // Створюємо список з дубльованими компаніями
        List<Company> companies = new ArrayList<>();
        companies.add(parentCompany);
        companies.add(childCompany);
        companies.add(childCompany); // Дубль

        // Використовуємо Set для видалення дублікатів
        Set<Company> uniqueCompanies = new HashSet<>(companies);

        long totalEmployees = companyService.getEmployeeCountForCompanyAndChildren(parentCompany, new ArrayList<>(uniqueCompanies));

        // Перевіряємо, що дублікати не враховані
        assertEquals(150, totalEmployees);  // 100 + 50 = 150
    }


    // 8. Перевірка компанії, в якої немає дочірніх компаній у списку
    @Test
    public void testCompanyWithNoChildrenInList() {
        Company company = new Company(null, 100);
        List<Company> companies = List.of(company);
        assertEquals(100, companyService.getEmployeeCountForCompanyAndChildren(company, companies));
    }

    // 9. Перевірка для компанії з кількома дочірніми компаніями без працівників
    @Test
    public void testCompanyWithEmptyEmployeeChildren() {
        Company parent = new Company(null, 200);
        Company child1 = new Company(parent, 0);
        Company child2 = new Company(parent, 0);
        List<Company> companies = List.of(parent, child1, child2);
        assertEquals(200, companyService.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    // 10. Перевірка для null-компанії (неіснуючої компанії)
    @Test
    public void testEmployeeCountForNullCompany() {
        List<Company> companies = new ArrayList<>();
        assertEquals(0, companyService.getEmployeeCountForCompanyAndChildren(null, companies));
    }

    // 11. Перевірка кількості працівників для ієрархії з двох рівнів
    @Test
    public void testCompanyWithMultipleLevels() {
        Company top = new Company(null, 1000);
        Company mid = new Company(top, 300);
        Company bottom = new Company(mid, 100);
        List<Company> companies = List.of(top, mid, bottom);
        assertEquals(1400, companyService.getEmployeeCountForCompanyAndChildren(top, companies));
    }

    // 12. Перевірка працівників для дитини, що не має дочірніх компаній
    @Test
    public void testSingleChildCompanyNoDescendants() {
        Company parent = new Company(null, 300);
        Company child = new Company(parent, 100);
        List<Company> companies = List.of(parent, child);
        assertEquals(100, companyService.getEmployeeCountForCompanyAndChildren(child, companies));
    }

    // 13. Перевірка для компанії з неіснуючим батьком
    @Test
    public void testCompanyWithNonexistentParent() {
        Company company = new Company(null, 100);
        assertEquals(company, companyService.getTopLevelParent(company));
    }

    // 14. Перевірка підрахунку працівників, якщо дочірні компанії відсутні
    @Test
    public void testEmployeeCountNoChildren() {
        Company company = new Company(null, 200);
        List<Company> companies = new ArrayList<>();
        assertEquals(200, companyService.getEmployeeCountForCompanyAndChildren(company, companies));
    }

    // 15. Перевірка коли всі працівники належать дочірнім компаніям
    @Test
    public void testAllEmployeesInChildCompanies() {
        Company parent = new Company(null, 0);
        Company child1 = new Company(parent, 100);
        Company child2 = new Company(parent, 200);
        List<Company> companies = List.of(parent, child1, child2);
        assertEquals(300, companyService.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    // 16. Перевірка для компанії з кількома дочірніми рівнями та нульовим рівнем працівників
    @Test
    public void testEmployeeCountWithZeroEmployeesInChildren() {
        Company parent = new Company(null, 0);
        Company child1 = new Company(parent, 0);
        Company grandChild = new Company(child1, 0);
        List<Company> companies = List.of(parent, child1, grandChild);
        assertEquals(0, companyService.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    // 17. Перевірка на компанії з однаковою кількістю працівників
    @Test
    public void testEmployeeCountSameForAll() {
        Company parent = new Company(null, 50);
        Company child1 = new Company(parent, 50);
        Company child2 = new Company(parent, 50);
        List<Company> companies = List.of(parent, child1, child2);
        assertEquals(150, companyService.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    // 18. Перевірка для компанії з відсутнім списком дочірніх компаній
    @Test
    void testCompanyWithNullChildrenList() {
        Company parentCompany = new Company(null, 100);

        // Передаємо порожній список
        List<Company> companies = new ArrayList<>();

        long totalEmployees = companyService.getEmployeeCountForCompanyAndChildren(parentCompany, companies);

        assertEquals(100, totalEmployees);
    }


    // 19. Перевірка для компанії з ієрархією, де одна компанія не міститься у списку
    @Test
    public void testCompanyHierarchyMissingInList() {
        Company parent = new Company(null, 200);
        Company child = new Company(parent, 100);
        List<Company> companies = List.of(parent);
        assertEquals(200, companyService.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

    // 20. Перевірка кількості працівників для онучки з відсутнім батьком у списку
    @Test
    void testGrandChildWithNoParentInList() {
        Company parentCompany = new Company(null, 100);
        Company childCompany = new Company(parentCompany, 50);
        Company grandChildCompany = new Company(childCompany, 100);

        // Створюємо список без батьківської компанії
        List<Company> companies = new ArrayList<>();
        companies.add(grandChildCompany);

        // Видаляємо дублікати через Set
        Set<Company> uniqueCompanies = new HashSet<>(companies);

        long totalEmployees = companyService.getEmployeeCountForCompanyAndChildren(grandChildCompany, new ArrayList<>(uniqueCompanies));

        // Перевіряємо, що тільки працівники grandChild враховані
        assertEquals(100, totalEmployees);
    }

}
