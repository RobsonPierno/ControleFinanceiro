package junit;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import br.com.utils.PasswordUtils;

public class PasswordTest {

	@Test
	public void encryptTest() {
		PasswordUtils passUtils = new PasswordUtils();
		String senhaEncriptada = passUtils.encrypt("senha");
		
		assertFalse("Erro ao criptografar a senha.", senhaEncriptada == null);
	}
	
	@Test
	public void validatePasswordTest() {
		PasswordUtils passUtils = new PasswordUtils();
		String senhaEncriptada1 = passUtils.encrypt("senha");
		String senhaEncriptada2 = passUtils.encrypt("senha");
		String senhaEncriptada3 = passUtils.encrypt("senha3");
		
		assertFalse("Senhas divergentes.", !senhaEncriptada1.equals(senhaEncriptada2));
		assertFalse("Senhas iguais quando não deveriam ser.", senhaEncriptada1.equals(senhaEncriptada3));
	}
}
