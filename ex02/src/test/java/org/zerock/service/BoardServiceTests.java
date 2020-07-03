package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = { @Autowired } )
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info("service: " + service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 2020 07 03");
		board.setContent("새로 작성하는 내용 2020 07 03");
		board.setWriter("뉴비");
		
		log.info("생성된 게시물의 번호1: " + board.getBno());
		
		service.register(board);
		
		log.info("생성된 게시물의 번호2: " + board.getBno());
	}
	
	@Test
	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(1, 10)).forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() {
		log.info(service.get(21L));
	}
	
	@Test
	public void testDelete() {
		log.info("REMOVE RESULT: " + service.remove(1L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(21L);
		
		if(board == null) {
			log.info("해당하는 데이터가 없습니다.");
			return;
		}
		
		board.setTitle("제목을 수정합니다.");
		log.info("MODIFY RESULT: " + service.modify(board));
	}
}
