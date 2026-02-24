import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/sessionDemo")
public class SessionDemoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get existing session or create new one
        HttpSession session = request.getSession();

        // Get visit count from session
        Integer visitCount = (Integer) session.getAttribute("visitCount");

        if (visitCount == null) {
            visitCount = 1;
            session.setAttribute("visitCount", visitCount);
            out.println("<h2>Welcome! This is your first visit.</h2>");
        } else {
            visitCount++;
            session.setAttribute("visitCount", visitCount);
            out.println("<h2>Welcome Back!</h2>");
        }

        out.println("<p>Number of visits in this session: " + visitCount + "</p>");
        out.println("<br><a href='sessionDemo'>Refresh Page</a>");
    }
}