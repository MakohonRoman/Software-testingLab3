package labs;
import java.util.List;/*
@author   $Makohon_Roman
@project   $Company service
@class  $444A
@version  1.0.0
@since 02.10.2024 - 14.21
*/



public interface ICompanyService {
    /**
     * @param child - компанія, для якої ми шукаємо верхню батьківську компанію
     *                (батька батька...)
     * @return верхня батьківська компанія
     */
    Company getTopLevelParent(Company child);

    /**
     * @param company - компанія, для якої ми шукаємо кількість працівників
     *                 (кількість працівників цієї компанії + кількість працівників всіх дочірніх компаній та їхніх дочірніх компаній, тощо)
     * @param companies - всі доступні компанії
     * @return кількість працівників
     */
    long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies);
}

