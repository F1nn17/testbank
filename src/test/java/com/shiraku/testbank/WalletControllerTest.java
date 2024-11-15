package com.shiraku.testbank;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shiraku.testbank.DTO.OperationType;
import com.shiraku.testbank.DTO.WalletRequest;
import com.shiraku.testbank.model.Wallet;
import com.shiraku.testbank.service.WalletService;



@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    UUID walletId = UUID.randomUUID();

     @Test
    public void testCreateWallet() throws Exception {
        WalletRequest request = new WalletRequest();
        request.setWalletId(walletId);
        request.setBalance(0.0);

        Wallet wallet = new Wallet();
        wallet.setWalletId(request.getWalletId());
        wallet.setBalance(request.getBalance());

        when(walletService.createWallet(any(WalletRequest.class))).thenReturn(wallet);

        mockMvc.perform(post("/api/v1/wallet/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.walletId").value(wallet.getWalletId().toString()))
                .andExpect(jsonPath("$.balance").value(wallet.getBalance()));
    }

    @Test
    public void testProcessDeposit() throws Exception {
        WalletRequest request = new WalletRequest();
        request.setWalletId(walletId);
        request.setOperationType(OperationType.DEPOSIT);
        request.setBalance(1000);

        mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testProcessWithdraw() throws Exception {
        WalletRequest request = new WalletRequest();
        request.setWalletId(walletId);
        request.setOperationType(OperationType.WITHDRAW);
        request.setBalance(500);

        mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBalance() throws Exception {
        when(walletService.getBalance(walletId)).thenReturn(1000.0);
        mockMvc.perform(get("/api/v1/wallets/" + walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(1000.0));
    }
}

