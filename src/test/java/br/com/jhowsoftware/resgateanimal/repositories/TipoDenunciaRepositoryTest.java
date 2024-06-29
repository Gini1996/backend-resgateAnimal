package br.com.jhowsoftware.resgateanimal.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TipoDenunciaRepositoryTest 
{
	 @Mock
	 private TipoDenunciaRepository tipoDenunciaRepository;

	 @Test
	 void testExistsByTipoDenunciaSuccess()
	 {
		 String tipoDenuncia = "Teste";
		 when(tipoDenunciaRepository.existsByTipoDenuncia(tipoDenuncia)).thenReturn(true);
	
		 boolean result = tipoDenunciaRepository.existsByTipoDenuncia(tipoDenuncia);
	
		 assertTrue(result);
	
		 verify(tipoDenunciaRepository, times(1)).existsByTipoDenuncia(tipoDenuncia);
	 }

	 @Test
	 void testExistsByTipoDenunciaFailure()
	 {
		 String tipoDenuncia = "Teste";
	     when(tipoDenunciaRepository.existsByTipoDenuncia(tipoDenuncia)).thenReturn(false);

	     boolean result = tipoDenunciaRepository.existsByTipoDenuncia(tipoDenuncia);

	     assertFalse(result);

	     verify(tipoDenunciaRepository, times(1)).existsByTipoDenuncia(tipoDenuncia);
	 }
	 
	 @Test
	 void testExistsByTipoDenunciaDatabaseException()
	 {
	     String tipoDenuncia = "Teste";
	     when(tipoDenunciaRepository.existsByTipoDenuncia(tipoDenuncia)).thenThrow(new RuntimeException("Erro de banco de dados"));

	     RuntimeException exception = assertThrows(RuntimeException.class, () -> {tipoDenunciaRepository.existsByTipoDenuncia(tipoDenuncia);});

	     assertEquals("Erro de banco de dados", exception.getMessage());

	     verify(tipoDenunciaRepository, times(1)).existsByTipoDenuncia(tipoDenuncia);
	 }
	 
	 @Test
	 void testExistsByTipoDenunciaDifferentTypes()
	 {
	     String tipoDenunciaExistente = "Tipo Existente";
	     String tipoDenunciaInexistente = "Tipo Inexistente";
	     when(tipoDenunciaRepository.existsByTipoDenuncia(tipoDenunciaExistente)).thenReturn(true);
	     when(tipoDenunciaRepository.existsByTipoDenuncia(tipoDenunciaInexistente)).thenReturn(false);

	     assertTrue(tipoDenunciaRepository.existsByTipoDenuncia(tipoDenunciaExistente));
	     assertFalse(tipoDenunciaRepository.existsByTipoDenuncia(tipoDenunciaInexistente));

	     verify(tipoDenunciaRepository, times(1)).existsByTipoDenuncia(tipoDenunciaExistente);
	     verify(tipoDenunciaRepository, times(1)).existsByTipoDenuncia(tipoDenunciaInexistente);
	 }
}