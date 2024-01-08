package com.example.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;

	@Override
	public String create(Map<String, Object> map) {
		int affectRowCount = this.bookDao.insert(map);
		if (affectRowCount == 1) {
			return map.get("book_id").toString();
		}
		return null;
	}
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map) {
		return this.bookDao.selectDetail(map);
	}
	
	@Override
	public boolean edit(Map<String, Object> map) {
		int affectRowCount = this.bookDao.update(map);
		return affectRowCount == 1;
	}
	
	@Override
	public boolean remove(Map<String, Object> map) {
		int affectRowCount = this.bookDao.delete(map);
		return affectRowCount == 1;
	}
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		return this.bookDao.selectList(map);
	}

	@Transactional
	@Override
	public void transactionTest() {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("title", "t1");
		map1.put("category", "c1");
		map1.put("price", 1);
		
		this.bookDao.insert(map1);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "t2");
		map2.put("category", "c2");
		map2.put("price", 2);
		
		this.bookDao.insert(map2);
		
		throw new RuntimeException("runtimeException");
	}
}
