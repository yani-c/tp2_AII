package duidoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

class TestDuidokuState {

	@Test
	void testCantCasillasLibres() {
		Casilla blanca=new Casilla(true,0);
		Casilla[][] board= {{blanca,new Casilla(false,1),blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{new Casilla(false,2),new Casilla(false,3),blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		assertEquals(78,s.cantCasillasLibres());
	}

	@Test
	void testCantConflictos() {
		Casilla[][] board= {{new Casilla(false,1),new Casilla(false,5),new Casilla(false,6),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(false,2),new Casilla(false,9),new Casilla(true,0),new Casilla(false,8),new Casilla(false,3),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(true,0),new Casilla(false,4),new Casilla(false,7),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(false,5),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(false,9),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(false,7),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(false,6),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(false,3),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)},
				{new Casilla(false,8),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0),new Casilla(true,0)}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		assertEquals(2,s.cantConflictos());
	}

	@Test
	void testGetOptions() {
		Casilla blanca=new Casilla(true,0);
		Casilla[][] board= {{blanca,new Casilla(false,1),blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{new Casilla(false,2),new Casilla(false,3),blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		List<Integer> list=new ArrayList<Integer>();
		for(int i=4;i<10;i++) {
			list.add(i);
		}
		assertEquals(list,s.getOptions(0, 0));
	}

	@Test
	void testGetOptionsRow() {
		Casilla blanca=new Casilla(true,0);
		Casilla[][] board= {{blanca,new Casilla(false,1),blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		Set<Integer> list=new HashSet<Integer>();
		for(int i=2;i<10;i++) {
			list.add(i);
		}
		assertEquals(list,s.getOptionsRow(0, 0));
	}

	@Test
	void testGetOptionsColumn() {
		Casilla blanca=new Casilla(true,0);
		Casilla[][] board= {{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{new Casilla(false,3),blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		Set<Integer> list=new HashSet<Integer>();
		list.add(1);
		list.add(2);
		for(int i=4;i<10;i++) {
			list.add(i);
		}
		assertEquals(list,s.getOptionsColumn(0, 0));
	}

	@Test
	void testGetOptionsSection() {
		Casilla blanca=new Casilla(true,0);
		Casilla[][] board= {{blanca,new Casilla(false,2),blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca},
				{blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca,blanca}};
		boolean m=true;
		DuidokuState s= new DuidokuState(board,m);
		Set<Integer> list=new HashSet<Integer>();
		list.add(1);
		for(int i=3;i<10;i++) {
			list.add(i);
		}
		assertEquals(list,s.getOptionsSection(0, 0));
	}
	
/*	@Test
	void testEquals(State other) {		Casilla[][] board= {{new Casilla(true,0),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,1),new Casilla(false,9),new Casilla(false,2)},
			{new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,7),new Casilla(false,6),new Casilla(false,8)},
			{new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,4),new Casilla(false,3),new Casilla(false,5)},
			{new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,3),new Casilla(false,2),new Casilla(false,4)},
			{new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,6),new Casilla(false,5),new Casilla(false,7)},
			{new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,9),new Casilla(false,8),new Casilla(false,1)},
			{new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,5),new Casilla(false,4),new Casilla(false,6)},
			{new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,3),new Casilla(false,2),new Casilla(false,1),new Casilla(false,8),new Casilla(false,7),new Casilla(false,9)},
			{new Casilla(false,9),new Casilla(false,8),new Casilla(false,7),new Casilla(false,6),new Casilla(false,5),new Casilla(false,4),new Casilla(false,2),new Casilla(false,1),new Casilla(false,3)}};
		boolean m=true;
		DuidokuState s1= new DuidokuState(board,m);
		State s2= new State(board,m);
		assertTrue(s1.equals(s2));
	}*/

}
