package application;

import java.util.List;

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
		
	}

}
