package www.qingshixun.com.springjdbc_crud.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import www.qingshixun.com.springjdbc_crud.dao.UserDao;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	
	@BeforeTransaction
	private void beforeTransaction(){
		System.out.println("执行事务前...");
		userDao.saveUser("zhangsan", "666666");	
		System.out.println("保存了信息...");
	}
	
	@Test
	public void testSave(){
		int temp = userDao.saveUser("lisi", "777777");
		System.out.println(temp);
	}
	
	@Rollback(false)
	@Test
	public void testNoRollback(){
		int temp = userDao.saveUser("wangwu", "888888");
		System.out.println(temp);
	}
	
	@AfterTransaction
	public void afterTransaction(){
		System.out.println("事务完成后执行...");
		System.out.println(userDao.getAllUser().toString());
		//删除一个用户
		userDao.deleteUser("lisi");
	}
}
