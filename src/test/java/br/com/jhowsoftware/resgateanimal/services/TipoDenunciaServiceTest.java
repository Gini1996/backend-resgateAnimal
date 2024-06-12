package br.com.jhowsoftware.resgateanimal.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import br.com.jhowsoftware.resgateanimal.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.exceptions.RegistroInexistente;
import br.com.jhowsoftware.resgateanimal.exceptions.ValorDivergenteException;
import br.com.jhowsoftware.resgateanimal.repositories.TipoDenunciaRepository;

@ExtendWith(MockitoExtension.class)
public class TipoDenunciaServiceTest 
{

    @Mock
    private TipoDenunciaRepository tipoDenunciaRepository;

    @InjectMocks
    private TipoDenunciaService tipoDenunciaService;

    private TipoDenuncia tipoDenuncia;

    @BeforeEach
    void setUp() 
    {
        tipoDenuncia = new TipoDenuncia();
        tipoDenuncia.setIdTipoDenuncia(1L);
        tipoDenuncia.setTipoDenuncia("Abandono");
    }

    @Test
    void testFindAllSuccess() 
    {
        when(tipoDenunciaRepository.findAll()).thenReturn(List.of(tipoDenuncia));

        List<TipoDenunciaDTO> result = tipoDenunciaService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tipoDenunciaRepository, times(1)).findAll();
    }

    @Test
    void testFindAllFailure() 
    {
        when(tipoDenunciaRepository.findAll()).thenThrow(new RuntimeException("Erro no banco de dados"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> { tipoDenunciaService.findAll();});

        assertEquals("Erro ao buscar todos os tipos de denúncia", exception.getMessage());
        verify(tipoDenunciaRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() 
    {
        when(tipoDenunciaRepository.findById(1L)).thenReturn(Optional.of(tipoDenuncia));

        TipoDenunciaDTO result = tipoDenunciaService.findById(1L);

        assertNotNull(result);
        assertEquals("Abandono", result.getTipoDenuncia());
        verify(tipoDenunciaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdFailure() 
    {
        when(tipoDenunciaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RegistroInexistente.class, () -> {tipoDenunciaService.findById(1L);});

        verify(tipoDenunciaRepository, times(1)).findById(1L);
    }

    @Test
    void testAdicionarTipoDenunciaSuccess() 
    {
        when(tipoDenunciaRepository.existsByTipoDenuncia(anyString())).thenReturn(false);
        when(tipoDenunciaRepository.save(any(TipoDenuncia.class))).thenReturn(new TipoDenuncia());

        assertDoesNotThrow(() -> tipoDenunciaService.adicionarTipoDenuncia("Teste"));

        verify(tipoDenunciaRepository, times(1)).save(any(TipoDenuncia.class));
    }

    @Test
    void testAdicionarTipoDenunciaValorDivergente() 
    {
        ValorDivergenteException exception = assertThrows(ValorDivergenteException.class, () -> { tipoDenunciaService.adicionarTipoDenuncia("123");});

        assertEquals("Valor informado não corresponde com o tipo de campo solicitado pela aplicação", exception.getMessage());

        verify(tipoDenunciaRepository, never()).save(any(TipoDenuncia.class));
    }

    @Test
    void testAdicionarTipoDenunciaRegistroDuplicado() 
    {
        when(tipoDenunciaRepository.existsByTipoDenuncia("Teste")).thenReturn(true);

        RegistroDuplicadoException exception = assertThrows(RegistroDuplicadoException.class, () -> {tipoDenunciaService.adicionarTipoDenuncia("Teste");});

        assertEquals("O tipo de denuncia: Teste já se encontra cadastrado no banco de dados", exception.getMessage());

        verify(tipoDenunciaRepository, never()).save(any(TipoDenuncia.class));
    }

    @Test
    void testAtualizarTipoDenunciaSuccess() 
    {
        when(tipoDenunciaRepository.existsById(1L)).thenReturn(true);
        when(tipoDenunciaRepository.findById(1L)).thenReturn(Optional.of(tipoDenuncia));

        tipoDenunciaService.atualizarTipoDenuncia(1L, "Abandono de Animais");

        verify(tipoDenunciaRepository, times(1)).save(any(TipoDenuncia.class));
    }

    @Test
    void testAtualizarTipoDenunciaFailure() 
    {
        when(tipoDenunciaRepository.existsById(1L)).thenReturn(false);

        assertThrows(RegistroInexistente.class, () -> { tipoDenunciaService.atualizarTipoDenuncia(1L, "Abandono de Animais");});

        verify(tipoDenunciaRepository, never()).save(any(TipoDenuncia.class));
    }

    @Test
    void testDeletarTipoDenunciaSuccess() 
    {
        long id = 1L;
        when(tipoDenunciaRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> tipoDenunciaService.deletarTipoDenuncia(id));

        verify(tipoDenunciaRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeletarTipoDenunciaFailure() 
    {
        long id = 1L;
        when(tipoDenunciaRepository.existsById(id)).thenReturn(false);

        RegistroInexistente exception = assertThrows(RegistroInexistente.class, () -> {tipoDenunciaService.deletarTipoDenuncia(id);});

        assertEquals("O ID: " + id + " não foi localizado no banco de dados", exception.getMessage());

        verify(tipoDenunciaRepository, never()).deleteById(id);
    }
}