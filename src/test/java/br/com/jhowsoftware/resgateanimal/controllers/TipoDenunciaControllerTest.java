package br.com.jhowsoftware.resgateanimal.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.jhowsoftware.resgateanimal.dtos.TipoDenunciaDTO;
import br.com.jhowsoftware.resgateanimal.exceptions.RegistroDuplicadoException;
import br.com.jhowsoftware.resgateanimal.exceptions.RegistroInexistente;
import br.com.jhowsoftware.resgateanimal.services.TipoDenunciaService;

@WebMvcTest(TipoDenunciaController.class)
public class TipoDenunciaControllerTest 
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoDenunciaService tipoDenunciaService;

    @InjectMocks
    private TipoDenunciaController tipoDenunciaController;

    @BeforeEach
    public void setUp() 
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() throws Exception 
    {
        List<TipoDenunciaDTO> denuncias = new ArrayList<>();
        when(tipoDenunciaService.findAll()).thenReturn(denuncias);

        mockMvc.perform(get("/tipodenuncia")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value("200"))
               .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    public void testFindAll_NotFound() throws Exception 
    {
        when(tipoDenunciaService.findAll()).thenReturn(null);

        mockMvc.perform(get("/tipodenuncia"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testFindById() throws Exception 
    {
        TipoDenunciaDTO denuncia = new TipoDenunciaDTO();
        when(tipoDenunciaService.findById(1L)).thenReturn(denuncia);

        mockMvc.perform(get("/tipodenuncia/1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testFindById_NotFound() throws Exception 
    {
    	doThrow(new RegistroInexistente("Tipo de denúncia não encontrado")).when(tipoDenunciaService).findById(any(Long.class));

        mockMvc.perform(get("/tipodenuncia/1"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testAddTpDenuncia() throws Exception 
    {
        String json = "{\"tipoDenuncia\":\"Denúncia Teste\"}";
        TipoDenunciaDTO denuncia = new TipoDenunciaDTO();
        denuncia.setTipoDenuncia("Denúncia Teste");

        when(tipoDenunciaService.adicionarTipoDenuncia("Denúncia Teste")).thenReturn(denuncia);

        mockMvc.perform(post("/tipodenuncia/addTpDenuncia")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.status").value("201"))
               .andExpect(jsonPath("$.data.tipoDenuncia").value("Denúncia Teste"));
    }

    @Test
    public void testAddTpDenuncia_InvalidRequest() throws Exception 
    {
    	doThrow(new RegistroDuplicadoException("Tipo de denúncia duplicado")).when(tipoDenunciaService).adicionarTipoDenuncia("");

        mockMvc.perform(post("/tipodenuncia/addTpDenuncia")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"id\":null,\"tipoDenuncia\":\"\"}"))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateTpDenuncia() throws Exception 
    {
        String json = "{\"tipoDenuncia\":\"Denúncia Atualizada\"}";
        TipoDenunciaDTO denuncia = new TipoDenunciaDTO();
        denuncia.setTipoDenuncia("Denúncia Atualizada");

        when(tipoDenunciaService.atualizarTipoDenuncia(1L, "Denúncia Atualizada")).thenReturn(denuncia);

        mockMvc.perform(put("/tipodenuncia/attTpDenuncia/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value("200"))
               .andExpect(jsonPath("$.data.tipoDenuncia").value("Denúncia Atualizada"));
    }

    @Test
    public void testUpdateTpDenuncia_NotFound() throws Exception 
    {
        doThrow(new RegistroInexistente("Tipo de denúncia não encontrado"))
                .when(tipoDenunciaService).atualizarTipoDenuncia(1L, "Nova Denúncia");

        mockMvc.perform(put("/tipodenuncia/attTpDenuncia/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"id\":1,\"tipoDenuncia\":\"Nova Denúncia\"}"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteTpDenuncia() throws Exception 
    {
        doNothing().when(tipoDenunciaService).deletarTipoDenuncia(1L);

        mockMvc.perform(delete("/tipodenuncia/excluir/1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value("200"))
               .andExpect(jsonPath("$.message").value("Denuncia ID: 1 excluída com sucesso"));
    }

    @Test
    public void testDeleteTpDenuncia_NotFound() throws Exception 
    {
        doThrow(new RegistroInexistente("Tipo de denúncia não encontrado"))
                .when(tipoDenunciaService).deletarTipoDenuncia(1L);

        mockMvc.perform(delete("/tipodenuncia/excluir/1"))
               .andExpect(status().isNotFound());
    }
}