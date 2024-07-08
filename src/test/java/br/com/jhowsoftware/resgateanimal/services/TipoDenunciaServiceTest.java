package br.com.jhowsoftware.resgateanimal.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import br.com.jhowsoftware.resgateanimal.services.impl.TipoDenunciaServiceImpl;
import br.com.jhowsoftware.resgateanimal.utils.ServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.entities.TipoDenuncia;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.RegistroInexistente;
import br.com.jhowsoftware.resgateanimal.utils.exceptions.ValorDivergenteException;
import br.com.jhowsoftware.resgateanimal.repositories.TipoDenunciaRepository;

@ExtendWith(MockitoExtension.class)
public class TipoDenunciaServiceTest
{

	@Mock
    private TipoDenunciaRepository tipoDenunciaRepository;

    @Mock
    private ServiceUtils serviceUtils;
    
    @InjectMocks
    private TipoDenunciaServiceImpl tipoDenunciaService;

    private TipoDenuncia tipoDenuncia;

    @BeforeEach
    public void setUp() 
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
        when(tipoDenunciaRepository.findAll()).thenThrow(new RegistroInexistente("Não existem denuncias cadastradas"));

        RuntimeException exception = assertThrows(RegistroInexistente.class, () -> tipoDenunciaService.findAll());

        assertEquals("Não existem denuncias cadastradas", exception.getMessage());
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

        assertThrows(RegistroInexistente.class, () -> tipoDenunciaService.findById(1L));

        verify(tipoDenunciaRepository, times(1)).findById(1L);
    }

    @Test
    void testAdicionarTipoDenunciaValorDivergente()
    {
        doThrow(new ValorDivergenteException("Valor informado não corresponde com o tipo de campo solicitado pela aplicação")).when(serviceUtils).validaString(any());

        ValorDivergenteException exception = assertThrows(ValorDivergenteException.class, () -> tipoDenunciaService.adicionarTipoDenuncia("123"));

        assertEquals("Valor informado não corresponde com o tipo de campo solicitado pela aplicação", exception.getMessage());

        verify(tipoDenunciaRepository, never()).save(any(TipoDenuncia.class));
    }

    @Test
    void testAdicionarTipoDenunciaRegistroDuplicado()
    {
        when(tipoDenunciaRepository.existsByTipoDenuncia("Teste")).thenReturn(true);

        RegistroDuplicadoException exception = assertThrows(RegistroDuplicadoException.class, () -> tipoDenunciaService.adicionarTipoDenuncia("Teste"));

        assertEquals("O tipo de denuncia: Teste já se encontra cadastrado no banco de dados", exception.getMessage());

        verify(tipoDenunciaRepository, never()).save(any(TipoDenuncia.class));
    }

    @Test
    void testAtualizarTipoDenunciaSuccess()
    {
        Long id = 1L;
        String novoTipoDenuncia = "Novo Tipo";

        TipoDenuncia tipoDenuncia = new TipoDenuncia();
        tipoDenuncia.setIdTipoDenuncia(id);
        tipoDenuncia.setTipoDenuncia("Antigo Tipo");

        doNothing().when(serviceUtils).validaString(any());
        when(tipoDenunciaRepository.findById(anyLong())).thenReturn(Optional.of(tipoDenuncia));
        when(tipoDenunciaRepository.save(any(TipoDenuncia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TipoDenunciaDTO result = tipoDenunciaService.atualizarTipoDenuncia(id, novoTipoDenuncia);

        assertNotNull(result);
        assertEquals(id, result.getIdTipoDenuncia());
        assertEquals(novoTipoDenuncia, result.getTipoDenuncia());
    }

    @Test
    void testAtualizarTipoDenunciaNotFound()
    {
        Long id = 1L;
        String novoTipoDenuncia = "Novo Tipo";

        when(tipoDenunciaRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> tipoDenunciaService.atualizarTipoDenuncia(id, novoTipoDenuncia));

        assertEquals("Tipo de denúncia não encontrado com o ID: " + id, thrown.getMessage());
    }

    @Test
    void testDeletarTipoDenunciaFailure()
    {
        long id = 1L;
        when(tipoDenunciaRepository.existsById(id)).thenReturn(false);

        RegistroInexistente exception = assertThrows(RegistroInexistente.class, () -> tipoDenunciaService.deletarTipoDenuncia(id));

        assertEquals("O ID: " + id + " não foi localizado no banco de dados", exception.getMessage());

        verify(tipoDenunciaRepository, never()).deleteById(id);
    }
}