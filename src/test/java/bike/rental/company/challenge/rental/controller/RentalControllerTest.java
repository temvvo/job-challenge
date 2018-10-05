package bike.rental.company.challenge.rental.controller;

import bike.rental.company.challenge.client.dao.ClientRepository;
import bike.rental.company.challenge.client.model.Client;
import bike.rental.company.challenge.rental.dao.RentalRepository;
import bike.rental.company.challenge.rental.model.Rental;
import bike.rental.company.challenge.rental.model.RentalItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RentalController.class)
public class RentalControllerTest {

  private final static long  MOCK_NOT_EXISTING_USER_ID = -11;


  @MockBean RentalRepository rentalRepository;
  @MockBean ClientRepository clientRepository;

  @Autowired private MockMvc mockMvc;
  @Before
  public void setUp() {}



  @Test
  public void rentNotExistingClient() throws Exception{

    RequestBuilder request =
            post("/rental/purchase/" + MOCK_NOT_EXISTING_USER_ID)
                    .contentType(MediaType.APPLICATION_JSON);


    Mockito.when(clientRepository.findById(MOCK_NOT_EXISTING_USER_ID)).thenReturn(Optional.empty());

    mockMvc.perform(request)
            .andExpect(status().isBadRequest());
  }



}