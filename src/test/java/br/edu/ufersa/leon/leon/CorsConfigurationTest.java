package br.edu.ufersa.leon.leon;

@WebMvcTest
public class CorsConfigurationTest {

    public static final String ORIGIN = "Origin";
    public static final String HTTP_FAIL_URL = "https://fail.com";
    public static final String HTTP_SUCCESS_URL = "https://www.mydomain.com";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAcceptByCors() throws Exception {
        mockMvc.perform(
                options("/api/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
                        .header(ORIGIN, HTTP_SUCCESS_URL)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }


    @Test
    public void shouldDenyByCors() throws Exception {
        mockMvc.perform(
                options("/api/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
                        .header(ORIGIN, HTTP_FAIL_URL)
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}