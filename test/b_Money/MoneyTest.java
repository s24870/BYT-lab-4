package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		// Testujemy, czy metoda getAmount() zwraca poprawną kwotę
				assertEquals(Integer.valueOf(10000), SEK100.getAmount());
				assertEquals(Integer.valueOf(1000), EUR10.getAmount());
	}

	@Test
	public void testGetCurrency() {
		// Testujemy, czy metoda getCurrency() zwraca poprawną walutę
				assertEquals(SEK, SEK100.getCurrency());
				assertEquals(EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		// Testujemy, czy metoda toString() zwraca poprawny ciąg reprezentujący kwotę
				assertEquals("100.0 SEK", SEK100.toString());
				assertEquals("10.0 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		// Testujemy, czy metoda universalValue() zwraca poprawną wartość uniwersalną
				assertEquals(Integer.valueOf(1500), SEK100.universalValue());
				assertEquals(Integer.valueOf(1500), EUR10.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		// Testujemy, czy metoda equals() poprawnie porównuje dwa obiekty Money
				assertTrue(SEK100.equals(new Money(10000, SEK)));
				assertTrue(SEK100.equals(SEK100));
	}

	@Test
	public void testAdd() {
		// Testujemy, czy metoda add() poprawnie dodaje dwa obiekty Money
				assertEquals(SEK200.getAmount(), SEK100.add(SEK100).getAmount());
				assertEquals(EUR20.getAmount(), EUR10.add(EUR10).getAmount());
	}

	@Test
	public void testSub() {
		// Testujemy, czy metoda sub() poprawnie odejmuje dwa obiekty Money
				assertEquals(SEK0.getAmount(), SEK100.sub(SEK100).getAmount());
				assertEquals(EUR0.getAmount(), EUR10.sub(EUR10).getAmount());
	}

	@Test
	public void testIsZero() {
		// Testujemy, czy metoda isZero() poprawnie sprawdza, czy kwota wynosi zero
				assertTrue(SEK0.isZero());
				assertFalse(SEK100.isZero());
	}

	@Test
	public void testNegate() {
		// Testujemy, czy metoda negate() poprawnie neguje kwotę
				assertEquals(SEKn100.getAmount(), SEK100.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		// Testujemy, czy metoda compareTo() poprawnie porównuje dwa obiekty Money
		assertEquals(0, SEK100.compareTo(new Money(10000, SEK))); // SEK100 jest równa 10000 SEK
	    assertEquals(-1, EUR10.compareTo(new Money(20000, EUR))); // EUR10 jest mniejsza od 20000 EUR
	    assertEquals(0, SEK200.compareTo(new Money(20000, SEK))); // SEK200 jest równa 20000 SEK
	    assertEquals(1, EUR20.compareTo(new Money(1000, EUR))); // EUR20 jest większa od 1000 EUR
	    assertEquals(0, SEK0.compareTo(new Money(0, SEK))); // SEK0 jest równa 0 SEK
	    assertEquals(1, EUR0.compareTo(new Money(-10000, EUR))); // EUR0 jest większa od -10000 EUR
	    assertEquals(-1, SEKn100.compareTo(new Money(10000, SEK))); // SEKn100 jest mniejsza od 10000 SEK
	    }
}
