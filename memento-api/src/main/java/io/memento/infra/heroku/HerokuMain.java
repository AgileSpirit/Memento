package io.memento.infra.heroku;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    23/08/2014
 */
public class HerokuMain extends HttpServlet {
    public static void main(String[] args) throws Exception{

        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HerokuMain()),"*//*");
        server.start();
        server.join();
    }
}
