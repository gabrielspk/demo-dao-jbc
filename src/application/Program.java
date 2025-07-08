package application;

import java.util.List;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {	
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("==== TEST 1: seller findById =====");
		Seller sellerById = sellerDao.findById(3);
		System.out.println(sellerById);
		
		
		System.out.println("==== TEST 2: department findById =====");
		Department departmentById = departmentDao.findById(2);
		System.out.println(departmentById);
		
		
		System.out.println("==== TEST 3: seller findByDepartment =====");
		Department department = new Department(1, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller sel : list) {
			System.out.println(sel);
		}
		
		
		System.out.println("=== TEST 4: seller findAll =====");
		List<Seller> listFindAllSel = sellerDao.findAll();
		for (Seller sel : listFindAllSel) {
			System.out.println(sel);
		}
		
		System.out.println("=== TEST 4: dep findAll =====");
		List<Department> listFindAllDep = departmentDao.findAll();
		for (Department dep : listFindAllDep) {
			System.out.println(dep);
		}
		
		
		System.out.println("==== TEST 5: seller insert =====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 3000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("inserted! New id = " + newSeller.getId());
		
		System.out.println("==== TEST 5: Department insert =====");
		Department newDepartment = new Department(null, "Games");
		departmentDao.insert(newDepartment);
		System.out.println("inserted! New id = " + newSeller.getId());
		
		
		System.out.println("==== TEST 6: seller update =====");
		Seller sellerupdate = sellerDao.findById(1);
		sellerupdate.setName("Martha Waine");
		sellerDao.update(sellerupdate);
		System.out.println("Update completed!");
		
		System.out.println("==== TEST 7: Department update =====");
		Department departmentUpdate = departmentDao.findById(1);
		departmentUpdate.setName("IT");
		departmentDao.update(departmentUpdate);
		System.out.println("Update completed!");
		
		System.out.println("==== TEST 8: Seller delete =====");
		sellerDao.deleteById(20);
		System.out.println("Delete completed!");
		
	}

}
