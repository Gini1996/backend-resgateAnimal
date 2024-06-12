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
    public void setUp() 
    {
        tipoDenuncia = new TipoDenuncia();
        tipoDenuncia.setIdTipoDenuncia(1L);
        tipoDenuncia.setTipoDenuncia("Abandono");
    }

    @Test
    public void testFindAllSuccess() 
    {
        when(tipoDenunciaRepository.findAll()).thenReturn(List.of(tipoDenuncia));

        List<TipoDenunciaDTO> result = tipoDenunciaService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(tipoDenunciaRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllFailure() 
    {
        when(tipoDenunciaRepository.findAll()).thenThrow(new RuntimeException("Erro no banco de dados"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> { tipoDenunciaService.findAll();});

        assertEquals("Erro ao buscar todos os tipos de denúncia", exception.getMessage());
        verify(tipoDenunciaRepository, times(1)).findAll();
    }

    @Test
    public void testFindByIdSuccess() 
    {
        when(tipoDenunciaRepository.findById(1L)).thenReturn(Optional.of(tipoDenuncia));

        TipoDenunciaDTO result = tipoDenunciaService.findById(1L);

        assertNotNull(result);
        assertEquals("Abandono", result.getTipoDenuncia());
        verify(tipoDenunciaRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByIdFailure() 
    {
        when(tipoDenunciaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RegistroInexistente.class, () -> {tipoDenunciaService.findById(1L);});

        verify(tipoDenunciaRepository, times(1)).findById(1L);
    }

    @Test
    public void testAdicionarTipoDenunciaSuccess() 
    {
        when(tipoDenunciaRepository.existsByTipoDenuncia(anyString())).thenReturn(false);
        when(tipoDenunciaRepository.save(any(TipoDenuncia.class))).thenReturn(new TipoDenuncia());

        assertDoesNotThrow(() -> tipoDenunciaService.adicionarTipoDenuncia("Teste"));

        verify(tipoDenunciaRepository, times(1)).save(any(TipoDenuncia.class));
    }

    @Test
    public void testAdicionarTipoDenunciaValorDivergente() 
    {
        ValorDivergenteException exception = assertThrows(ValorDivergenteException.class, () -> { tipoDenunciaService.adicionarTipoDenuncia("123");});

        assertEquals("Valor informado não corresponde com o tipo de campo solicitado pela aplicação", exception.getMessage());

        verify(tipoDenunciaRepository, never()).save(any(TipoDenuncia.class));
    }

    @Test
    public void testAdicionarTipoDenunciaRegistroDuplicado() 
    {
        when(tipoDenunciaRepository.existsByTipoDenuncia("Teste")).thenReturn(true);

        RegistroDuplicadoException exception = assertThrows(RegistroDuplicadoException.class, () -> {tipoDenunciaService.adicionarTipoDenuncia("Teste");});

        assertEquals("O tipo de denuncia: Teste já se encontra cadastrado no banco de dados", exception.getMessage());

        verify(tipoDenunciaRepository, never()).save(any(TipoDenuncia.class));
    }

    @Test
    public void testAtualizarTipoDenunciaSuccess() 
    {
        Long id = 1L;
        String novoTipoDenuncia = "Novo Tipo";
        
        TipoDenuncia tipoDenuncia = new TipoDenuncia();
        tipoDenuncia.setIdTipoDenuncia(id);
        tipoDenuncia.setTipoDenuncia("Antigo Tipo");

        when(tipoDenunciaRepository.findById(id)).thenReturn(Optional.of(tipoDenuncia));
        when(tipoDenunciaRepository.save(any(TipoDenuncia.class))).thenAnswer(i -> i.getArgument(0));

        TipoDenunciaDTO result = tipoDenunciaService.atualizarTipoDenuncia(id, novoTipoDenuncia);

        assertNotNull(result);
        assertEquals(id, result.getIdTipoDenuncia());
        assertEquals(novoTipoDenuncia, result.getTipoDenuncia());
    }

    @Test
    public void testAtualizarTipoDenunciaNotFound() 
    {
        Long id = 1L;
        String novoTipoDenuncia = "Novo Tipo";

        when(tipoDenunciaRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> { tipoDenunciaService.atualizarTipoDenuncia(id, novoTipoDenuncia);});

        assertEquals("Tipo de denúncia não encontrado com o ID: " + id, thrown.getMessage());
    }

    @Test
    public void testDeletarTipoDenunciaSuccess() 
    {
        long id = 1L;
        when(tipoDenunciaRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> tipoDenunciaService.deletarTipoDenuncia(id));

        verify(tipoDenunciaRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletarTipoDenunciaFailure() 
    {
        long id = 1L;
        when(tipoDenunciaRepository.existsById(id)).thenReturn(false);

        RegistroInexistente exception = assertThrows(RegistroInexistente.class, () -> {tipoDenunciaService.deletarTipoDenuncia(id);});

        assertEquals("O ID: " + id + " não foi localizado no banco de dados", exception.getMessage());

        verify(tipoDenunciaRepository, never()).deleteById(id);
    }
}