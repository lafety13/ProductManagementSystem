package ua.goit.java.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ua.goit.java.models.UserBuilder;
import ua.goit.java.models.hibernate.User;
import ua.goit.java.service.interfaces.UserService;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
    private MockMvc mockMvc;
    @Mock
    private UserService userService;

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

        return viewResolver;
    }

    @Before
    public void setUp() throws Exception {
        UserController userController = new UserController();
        userController.setUserService(userService);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver())
                .build();
    }

    @Test
    public void showRegistrationForm_ShouldRenderRegistrationView() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(forwardedUrl("/WEB-INF/views/registration.jsp"))
                .andExpect(model().attribute("userForm", is(new User())));
    }

    @Test
    public void registration_ShouldRegisterObjectAndRedirectToWelcomePage() {
        User user = new UserBuilder()
                .id(1L)
                .username("name")
                .password("1")
                .confirmPassword("1")
                .buidl();

        //mockMvc.perform(post("/registration"))
    }
    @Test
    public void login_Should() {

    }
    @Test
    public void admin_ShouldRenderWelcomeView() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"))
                .andExpect(forwardedUrl("/WEB-INF/views/welcome.jsp"));
    }

    @Test
    public void admin_ShouldRenderAdminView() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(forwardedUrl("/WEB-INF/views/admin.jsp"));
    }
}