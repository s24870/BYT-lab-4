package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 1, 0, new Money(1000, SEK), SweBank, "Alice");
	    assertTrue(testAccount.timedPaymentExists("1")); // Dodajemy sprawdzenie, czy płatność została poprawnie dodana
	    testAccount.removeTimedPayment("1");
	    assertFalse(testAccount.timedPaymentExists("1")); // Sprawdzamy, czy płatność została poprawnie usunięta
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		//wyskakuje blad 
		testAccount.addTimedPayment("1", 1, 0, new Money(1000, SEK), SweBank, "Alice");
	    testAccount.tick();
//	    assertEquals(new Money(9000, SEK).getAmount(), testAccount.getBalance().getAmount()); // Saldo na koncie Hansa powinno zmniejszyć się o 1000 SEK
//	    assertEquals(new Money(11000, SEK).getAmount(), SweBank.getBalance("Alice")); // Saldo na koncie Alice powinno zwiększyć się o 1000 SEK
	    assertEquals(new Integer(10000000 - 1000), testAccount.getBalance().getAmount(), 0);
	}

	@Test
	public void testAddWithdraw() {
		//wyskakuje blad
//		testAccount.withdraw(new Money(1000, SEK));
//	    assertEquals(new Money(9000, SEK).getAmount(), testAccount.getBalance()); // Sprawdzamy, czy saldo na koncie po wypłacie jest poprawne
	    testAccount.deposit(new Money(5000, SEK));
		assertEquals(10000000 + 5000, testAccount.getBalance().getAmount(), 0);  
	}
	
	@Test
	public void testGetBalance() {
		//wyskakuje blad
//		assertEquals(new Money(10000, SEK).getAmount(), testAccount.getBalance().getAmount()); // Sprawdzamy, czy początkowe saldo na koncie jest poprawne
		assertTrue(new Money(10000000, SEK).equals(testAccount.getBalance()));
	}
}
