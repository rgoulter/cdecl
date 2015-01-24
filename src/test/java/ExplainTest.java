import static org.junit.Assert.*;

import org.junit.Test;


public class ExplainTest {
	
	public void assertGibberishToEnglish(String gibberish, String expectedEnglish) {
		String actualEnglish = Explain.gibberishToEnglish(gibberish);
		assertEquals(expectedEnglish, actualEnglish);
	}

	@Test
	public void test() {
		assertGibberishToEnglish("int x = 3;",
		                         "x is int");

		assertGibberishToEnglish("int *x;",
		                         "x is pointer to int");
	}

	@Test
	public void testArray() {
		assertGibberishToEnglish("int x[4];",
		                         "x is array 4 of int");

		assertGibberishToEnglish("int x[5][3];",
		                         "x is array 5 of array 3 of int");
	}

	@Test
	public void testArrayAndPointer() {
		assertGibberishToEnglish("int *x[3];",
		                         "x is array 3 of pointer to int");

		assertGibberishToEnglish("int (*x[5])[3];",
		                         "x is array 5 of pointer to array 3 of int");

		assertGibberishToEnglish("int *(*x)[3];",
		                         "x is pointer to array 3 of pointer to int");

		assertGibberishToEnglish("int (**x)[3];",
		                         "x is pointer to pointer to array 3 of int");

		assertGibberishToEnglish("int **x[3];",
		                         "x is array 3 of pointer to pointer to int");
	}

	@Test
	public void testFunctionPointers() {
		assertGibberishToEnglish("int (*x)(void);",
		                         "x is pointer to function (void) returning int");

	
		assertGibberishToEnglish("int (*x)(int, char*);",
		                         "x is pointer to function (int, pointer to char) returning int");
	}

}
