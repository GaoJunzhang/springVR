package com.zgj.min3d;

import com.zgj.min3d.model.Menu;
import com.zgj.min3d.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Min3dApplicationTests {

	@Autowired
	private MenuService menuService;
	@Test
	public void contextLoads() {
		List<Menu> list = menuService.findAll();
		System.out.println(list.size()+"================");
	}

//	public static void main(String[] args){
//		ArrayList list = new ArrayList();
//		list.add("000");
//		list.add("000");
//		LinkedList linkedList = new LinkedList();
//		System.out.println(linkedList);
//		System.out.println(list);
//	}

}
