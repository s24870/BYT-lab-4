package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		// Testujemy, czy metoda getName() zwraca poprawną nazwę waluty
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		// Testujemy, czy metoda getRate() zwraca poprawny kurs waluty
		assertEquals(0.15, SEK.getRate(), 0.001);
		assertEquals(0.20, DKK.getRate(), 0.001);
		assertEquals(1.5, EUR.getRate(), 0.001);
	}
	
	@Test
	public void testSetRate() {
		// Testujemy, czy metoda setRate() poprawnie ustawia nowy kurs waluty
		SEK.setRate(0.16);
		DKK.setRate(0.21);
		EUR.setRate(1.6);
		assertEquals(0.16, SEK.getRate(), 0.001);
		assertEquals(0.21, DKK.getRate(), 0.001);
		assertEquals(1.6, EUR.getRate(), 0.001);
	}
	
	@Test
	public void testUniversalValue() {
		// Testujemy, czy metoda universalValue() poprawnie przelicza wartość na "uniwersalną" walutę
		assertEquals(1500, (int)SEK.universalValue(10000));
		assertEquals(2000, (int)DKK.universalValue(10000));
		assertEquals(15000, (int)EUR.universalValue(10000));
	}
	
	@Test
	public void testValueInThisCurrency() {
		// Testujemy, czy metoda valueInThisCurrency() poprawnie przelicza wartość z jednej waluty na drugą
		assertEquals(13333, (int)SEK.valueInThisCurrency(10000, DKK));
		assertEquals(7500, (int)DKK.valueInThisCurrency(10000, SEK));
		assertEquals(10000, (int)EUR.valueInThisCurrency(10000, EUR));
	}
}
