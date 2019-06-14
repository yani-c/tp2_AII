package duidoku;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDuidokuProblem {

	@Test
	void testGetSuccessors() {
		Casilla[][] board= {{new Casilla(true,0),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,1),new Casilla(false,9),new Casilla(false,2)},
				{new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,7),new Casilla(false,6),new Casilla(false,8)},
				{new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,4),new Casilla(false,3),new Casilla(false,5)},
				{new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,3),new Casilla(false,2),new Casilla(false,4)},
				{new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,6),new Casilla(false,5),new Casilla(false,7)},
				{new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,9),new Casilla(false,8),new Casilla(false,1)},
				{new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,5),new Casilla(false,4),new Casilla(false,6)},
				{new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,8),new Casilla(false,7),new Casilla(false,9)},
				{new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,2),new Casilla(false,1),new Casilla(false,3)}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		DuidokuProblem p = new DuidokuProblem(s);
		DuidokuState s1= new DuidokuState(s);
		s1.setMax(false);
		s.getBoard()[0][0].setFst(false);
		s.getBoard()[0][0].setSnd(8);
		List<DuidokuState> list= new LinkedList<DuidokuState>();
		list.add(s);
		boolean resultado;
		resultado= p.getSuccessors(s1).get(0).equals(list.get(0));//sabemos que tiene un solo elemento
		assertTrue(resultado);//hacemos asi porque assertEquals compara entre objetos
	}

	@Test
	void testEnd() {
		Casilla[][] board= {{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)},
							{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0),new Casilla(false,0)}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		DuidokuProblem p = new DuidokuProblem(s);
		assertTrue(p.end(s));
	}

	@Test
	void testValue1() {
		//estado final
		Casilla[][] board= {{new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,1),new Casilla(false,9),new Casilla(false,2)},
				{new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,7),new Casilla(false,6),new Casilla(false,8)},
				{new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,4),new Casilla(false,3),new Casilla(false,5)},
				{new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,3),new Casilla(false,2),new Casilla(false,4)},
				{new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,6),new Casilla(false,5),new Casilla(false,7)},
				{new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,9),new Casilla(false,8),new Casilla(false,1)},
				{new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,5),new Casilla(false,4),new Casilla(false,6)},
				{new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,8),new Casilla(false,7),new Casilla(false,9)},
				{new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,2),new Casilla(false,1),new Casilla(false,3)}};boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		DuidokuProblem p = new DuidokuProblem(s);
		assertEquals(p.value(s),Integer.MIN_VALUE);
	}
	
	@Test
	void testValue2() {
		//cuando no es estado final
		Casilla[][] board= {{new Casilla(true,0),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,1),new Casilla(false,9),new Casilla(false,2)},
				{new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,7),new Casilla(false,6),new Casilla(false,8)},
				{new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,4),new Casilla(false,3),new Casilla(false,5)},
				{new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,3),new Casilla(false,2),new Casilla(false,4)},
				{new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,6),new Casilla(false,5),new Casilla(false,7)},
				{new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,9),new Casilla(false,8),new Casilla(false,1)},
				{new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,5),new Casilla(false,4),new Casilla(false,6)},
				{new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,8),new Casilla(false,7),new Casilla(false,9)},
				{new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,2),new Casilla(false,1),new Casilla(false,3)}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		DuidokuProblem p = new DuidokuProblem(s);
		assertEquals(p.value(s),((s.cantCasillasLibres()+1) % 2)+ s.cantConflictos());
	}

}
