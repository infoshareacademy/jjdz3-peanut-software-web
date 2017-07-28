package peanut.medicine.web;

import org.apache.logging.log4j.Logger;
import peanut.medicine.web.admin.AdminStatistics;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;

import static org.apache.logging.log4j.LogManager.getLogger;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private static final Logger LOGGER = getLogger(SessionListener.class);

    @Inject
    private AdminStatistics statistics;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        String userEmail = (String) session.getAttribute("email");
        String sessionId = session.getId();
        statistics.setLogoutActivity(userEmail, sessionId);
        LOGGER.debug("Session destroyed!" );
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
