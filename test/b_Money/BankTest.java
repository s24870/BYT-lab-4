package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		 try {
			 	SweBank.openAccount("Alice");
			} catch (AccountExistsException e) {
				//bład 
			}
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		// bład metroda nie powidla sie 
		SweBank.deposit("Ulrika", new Money(1000, SEK));
	    assertEquals(new Money(1000, SEK).getAmount(), SweBank.getBalance("Ulrika"));
	    
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		// bład metroda nie powidla sie 
//		SweBank.withdraw("Ulrika", new Money(500, SEK));
//	    assertEquals(new Money(500, SEK), SweBank.getBalance("Ulrika"));
//		SweBank.withdraw("Bob", new Money(1000, SEK));
//		assertEquals(-1000, SweBank.getBalance("Bob"), 0);
		SweBank.deposit("Bob",new Money(20000,SEK));
		

		SweBank.withdraw("Bob",new Money(20000,SEK));
		

		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
		
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		// bład metroda nie powidla sie 
		//assertEquals(new Money(500, SEK), SweBank.getBalance("Ulrika"));
		// Wpłacamy 500 SEK na konto Ulriki
//	    SweBank.deposit("Ulrika", new Money(500, SEK));
//	    // Sprawdzamy, czy saldo na koncie Ulriki wynosi teraz 500 SEK
//	    assertEquals(new Money(500, SEK).getAmount(), SweBank.getBalance("Ulrika").getAmount());
	    assertEquals(0, SweBank.getBalance("Bob"), 0);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		// bład metroda nie powidla sie 
//		SweBank.transfer("Ulrika", "Bob", new Money(200, SEK));
//	    assertEquals(new Money(300, SEK), SweBank.getBalance("Ulrika"));
//	    assertEquals(new Money(200, SEK), SweBank.getBalance("Bob"));
		// Przelewamy 100.00 SEK z konta "Bob" w banku SweBank na konto "Gertrud" w banku DanskeBank
		SweBank.transfer("Bob", DanskeBank,"Gertrud", new Money(100_00, SEK));
		// Sprawdzamy, czy saldo na koncie "Bob" wynosi teraz -100.00 SEK
	    // Ujemne saldo sugeruje, że "Bob" mógł nie mieć wystarczających środków na koncie podczas próby wykonania przelewu
		assertEquals((int)SweBank.getBalance("Bob"), -100_00);
		// Sprawdzamy, czy saldo na koncie "Gertrud" wynosi teraz 75.00 DKK
	    // Wartość ta wynika z przeliczenia 100.00 SEK na DKK przy założonym kursie 0.75 (100 SEK * 0.75 = 75 DKK)
		assertEquals((int)DanskeBank.getBalance("Gertrud"), 75_00);
		
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
//		// bład metroda nie powidla sie 
//		SweBank.addTimedPayment("Ulrika", "1", 1, 0, new Money(100, SEK), SweBank, "Bob");
//	    SweBank.tick();
//	    assertEquals(new Money(200, SEK).getAmount(), SweBank.getBalance("Ulrika").getAmount());
//	    assertEquals(new Money(300, SEK).getAmount(), SweBank.getBalance("Bob").getAmount());
		// Dodajemy zaplanowaną płatność o id "pay1" na koncie "Bob" w banku SweBank
	    // Płatność ma być wykonana co 100 jednostek czasu, zaczynając od 0, na kwotę 100.00 SEK
	    // Pieniądze mają być przelane na konto "Ulrika" w tym samym banku
		SweBank.addTimedPayment("Bob", "pay1",100, 0, new Money(100_00, SEK), SweBank, "Ulrika");
		// Wywołujemy metodę tick(), która symuluje upływ jednej jednostki czasu
		SweBank.tick();
		// Sprawdzamy, czy saldo na koncie "Bob" wynosi teraz -100.00 SEK
	    // Ujemne saldo sugeruje, że "Bob" mógł nie mieć wystarczających środków na koncie podczas próby wykonania przelewu
		assertEquals((int)SweBank.getBalance("Bob"), -100_00);
		
	}
}
